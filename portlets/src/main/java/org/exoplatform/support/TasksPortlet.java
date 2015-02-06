package org.exoplatform.support;

/**
 * Created by eXo Platform MEA on 25/11/14.
 *
 * @author <a href="mailto:marwen.trabelsi.insat@gmail.com">Marwen Trabelsi</a>
 */


import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;

import javax.jcr.Node;
import javax.portlet.*;
import java.io.IOException;
import java.util.List;

public class TasksPortlet extends GenericPortlet
{
    private static final String INITIAL_VIEW = "/view.jsp";
    private static final String EDIT_VIEW = "/edit.jsp";
    private static final String CREATE_VIEW = "/create.jsp";
    private TaskService ts;
    private String newId;

    private PortletRequestDispatcher initialView;
    private PortletRequestDispatcher editView;
    private PortletRequestDispatcher createView;

    public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        if ("true".equals(request.getParameter("redirectToCreate"))) {
            createView.include(request, response);
        }
        else if ("true".equals(request.getParameter("redirectToEdit"))) {
            newId = request.getParameter("newTaskId");

            request.setAttribute("oldTitle", request.getParameter("oldTitle"));
            request.setAttribute("oldDescription", request.getParameter("oldDescription"));
            request.setAttribute("oldDate", request.getParameter("oldDate"));

            editView.include(request, response);
        }
        else {
            doView(request, response);
        }
    }

    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException
    {
        if ("true".equals(request.getParameter("redirectToCreate")))
        {
            String title = request.getParameter("taskTitle");
            String description = request.getParameter("taskDescription");
            String date = request.getParameter("taskDate");

            ts.createTask(title, description, date);
        }
        else if ("true".equals(request.getParameter("redirectToDelete"))) {
//            deleteView.include(request, response);
            String id = request.getParameter("taskId");

            ts.deleteTask(id);

        }
        else if ("true".equals(request.getParameter("redirectToEdit"))) {
            String newTitle = request.getParameter("newTaskTitle");
            String newDescription = request.getParameter("newTaskDescription");
            String newDate = request.getParameter("newTaskDate");


            ts.editTask(newId,newTitle,newDescription,newDate);
        }
    }

    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
        if (ts.getAllTasks() != null) {
            List<Node> nodes = ts.getAllTasks();
            request.setAttribute("nodes", nodes);
            initialView.include(request, response);
        }
    }

    public void init(PortletConfig config) throws PortletException
    {
        super.init(config);
        initialView = config.getPortletContext().getRequestDispatcher(INITIAL_VIEW);
        createView = config.getPortletContext().getRequestDispatcher(CREATE_VIEW);
        editView = config.getPortletContext().getRequestDispatcher(EDIT_VIEW);

        ExoContainer container = ExoContainerContext.getCurrentContainer();
        ts = (TaskService) container.getComponentInstanceOfType(TaskService.class);
    }

    public void destroy()
    {
        initialView = null;
        createView = null;
        editView = null;
        super.destroy();
    }

}
