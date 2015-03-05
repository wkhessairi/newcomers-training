package org.exoplatform.support;


import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.config.RepositoryConfigurationException;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.picocontainer.Startable;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.List;


public class TaskServiceImpl implements TaskService,Startable {

    protected static Log log = ExoLogger.getLogger(TaskServiceImpl.class);
    private static final String TASKS_ROOT_NODE = "tasks";
    private Node tasksNode;
    RepositoryService repositoryService;
    ConversationState conv;
    Identity user;


    public TaskServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;

    }

    private String getRelPath(String userId) {
        if (userId.length() <= 3) {
            return ("Users/"+userId.charAt(0)+"___/"+userId.charAt(0)+userId.charAt(1)+"___/"
                    +userId);
        }
        else {
            return ("Users/"+userId.charAt(0)+"___/"+userId.charAt(0)+userId.charAt(1)+"___/"
                    +userId.charAt(0)+userId.charAt(1)+userId.charAt(2)+"___/"+userId);
        }
    }

    private void initTaskNode() {
        conv = ConversationState.getCurrent();
        user = conv.getIdentity();
        ManageableRepository repository = null;
        try {
            repository = repositoryService.getDefaultRepository();
            Session session = repository.getSystemSession("collaboration");
            Node userNode = session.getRootNode().getNode(getRelPath(user.getUserId()));

            if (userNode.hasNode(TASKS_ROOT_NODE)) {
                tasksNode = userNode.getNode(TASKS_ROOT_NODE);
            } else {
                tasksNode = userNode.addNode(TASKS_ROOT_NODE);
            }
            userNode.save();
        }
        catch (RepositoryException e) {
            log.error("Failed starting Task Service", e);
        } catch (RepositoryConfigurationException e) {
            log.error("Failed starting Task Service", e);
        }
    }

    public void createTask(String title, String description, String due) {
        try {
            initTaskNode();
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
    }

    public List<Node> getAllTasks() {
        List<Node> nodes = new ArrayList<Node>();
        NodeIterator it = null;

        initTaskNode();
        if (tasksNode != null) {
            try {
                it = tasksNode.getNodes();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            while(it.hasNext()){
                nodes.add(it.nextNode());
            }
            log.info("there are "+nodes.size()+" nodes");
        }
        return nodes;
    }

    public List<String> getTasksByUser(String userId) {
        ManageableRepository repository = null;
        NodeIterator it = null;
        List<String> res = new ArrayList<String>();

        try {
            repository = repositoryService.getDefaultRepository();

            Session session = repository.getSystemSession("collaboration");

            Node userNode = session.getRootNode().getNode(getRelPath(userId));

            if (userNode.hasNode(TASKS_ROOT_NODE)) {
                tasksNode = userNode.getNode(TASKS_ROOT_NODE);
                if (tasksNode != null) {
                    it = tasksNode.getNodes();

                    while (it.hasNext()) {
                        res.add(it.nextNode().getProperty("title").getString());
                    }
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (RepositoryConfigurationException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}