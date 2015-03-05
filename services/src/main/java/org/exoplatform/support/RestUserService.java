package org.exoplatform.support;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;



@Path("/demo")

@Produces("application/json")

public class RestUserService implements ResourceContainer {

    @GET

    @Path("/listtasks")

    public Response getListTask(@QueryParam("user") String userId) {

        JSONArray list = new JSONArray();
        JSONObject jsonGlobal = new JSONObject();

        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);

        ExoContainer container = ExoContainerContext.getCurrentContainer();
        TaskService task = (TaskService) container.getComponentInstanceOfType(TaskService.class);

        try {
            for (String ts : task.getTasksByUser(userId)) {
                JSONObject json = new JSONObject();
                json.put("task", ts);
                list.put(json);
            }
            jsonGlobal.put("tasks", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok(jsonGlobal.toString(), MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();
    }
}


