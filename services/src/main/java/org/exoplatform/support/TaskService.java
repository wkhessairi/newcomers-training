package org.exoplatform.support;

import javax.jcr.Node;
import java.util.List;

public interface TaskService {

    public abstract void createTask(String title,String description,String due);
    public abstract void editTask(String taskId,String title,String description,String due);
    public abstract void deleteTask(String eventID);
    public abstract List<Node> getAllTasks();

}
