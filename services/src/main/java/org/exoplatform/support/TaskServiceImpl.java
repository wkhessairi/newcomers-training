package org.exoplatform.support;


import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.config.RepositoryConfigurationException;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.picocontainer.Startable;

import javax.jcr.*;
import java.util.ArrayList;
import java.util.List;


public class TaskServiceImpl implements TaskService,Startable {

    protected static Log log = ExoLogger.getLogger(TaskServiceImpl.class);
    private static final String TASKS_ROOT_NODE = "tasks";
    private Node tasksNode;
    RepositoryService repositoryService;


    public TaskServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;

    }

    public void createTask(String title, String description, String due) {
        try {
            Node task = tasksNode.addNode(String.valueOf(System.currentTimeMillis()));
            task.setProperty("id", String.valueOf(System.currentTimeMillis()));
            task.setProperty("title", title);
            task.setProperty("description", description);
            task.setProperty("due", due);
            tasksNode.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }

    public void editTask(String taskId, String title, String description, String due) {

        try {
            tasksNode.getNode(taskId).setProperty("title", title);
            tasksNode.getNode(taskId).setProperty("description", description);
            tasksNode.getNode(taskId).setProperty("due", due);
            tasksNode.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }


    }

    public void deleteTask(String taskId) {

        try {
            tasksNode.getNode(taskId).remove();
            tasksNode.save();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        log.info("The node identified by "+taskId+" will be deleted");
    }

    public List<Node> getAllTasks() {
        List<Node> nodes = new ArrayList<Node>();
        NodeIterator it = null;
        try {
            it = tasksNode.getNodes();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        while(it.hasNext()){
            nodes.add(it.nextNode());
        }
        log.info("there are "+nodes.size()+" nodes");
        return nodes;
    }

    @Override
    public void start() {
        ManageableRepository repository = null;
        try {
            repository = repositoryService.getDefaultRepository();

            Session session = repository.getSystemSession("collaboration");
            Node rootNode = session.getRootNode();
            if (rootNode.hasNode(TASKS_ROOT_NODE)) {
                tasksNode = rootNode.getNode(TASKS_ROOT_NODE);
            } else {
                tasksNode = rootNode.addNode(TASKS_ROOT_NODE);
            }
            rootNode.save();
        } catch (RepositoryException e) {
            log.error("Failed starting Task Service", e);
        } catch (RepositoryConfigurationException e) {
            log.error("Failed starting Task Service", e);
        }
        log.info("Task Service started successfully");

    }

    @Override
    public void stop() {

    }
}