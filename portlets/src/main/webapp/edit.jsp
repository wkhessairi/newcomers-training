<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>


<portlet:defineObjects/>
<%
    String oldTitle = (String) renderRequest.getAttribute("oldTitle");
    String oldDescription = (String) renderRequest.getAttribute("oldDescription");
    String oldDate = (String) renderRequest.getAttribute("oldDate");
%>

<html>
<head>
<meta charset="utf-8" />
<title>Edit task</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
body {padding-top:20px;}
</style>
</head>
<body>

    <portlet:actionURL var="editTaskURL" portletMode="view">
       <portlet:param name="redirectToEdit" value="true" />
    </portlet:actionURL>

    <div class="container">
        <div class="row">
          <div class="col-md-6 col-md-offset-3">
            <div class="well well-sm">
              <form class="form-horizontal" action="<%= editTaskURL %>" method="post">
              <fieldset>
                <legend class="text-center">Edit task</legend>

                <!-- Title input-->
                <div class="form-group">
                  <label class="col-md-3 control-label" for="newTaskTitle">Title</label>
                  <div class="col-md-6">
                    <input id="newTaskTitle" name="newTaskTitle" type="text" value="<%=oldTitle%>" class="form-control" required>
                  </div>
                </div>

                <!-- Date input-->
                <div class="form-group">
                  <label class="col-md-3 control-label" for="newTaskDate">Date</label>
                  <div class="col-md-6">
                    <input id="newTaskDate" name="newTaskDate" type="text" value="<%=oldDate%>" class="form-control">
                  </div>
                </div>

                <!-- Description input -->
                <div class="form-group">
                  <label class="col-md-3 control-label" for="newTaskDescription">Description</label>
                  <div class="col-md-6">
                    <textarea class="form-control" id="newTaskDescription" name="newTaskDescription" placeholder="<%=oldDescription%>" rows="5"></textarea>
                  </div>
                </div>

                <!-- Form actions -->
                <div class="form-group">
                  <div class="col-md-9 text-right">
                    <button type="submit" class="btn btn-primary btn-sm">Edit</button>
                  </div>
                </div>
              </fieldset>
              </form>
            </div>
          </div>
        </div>
    </div>

</body>
</html>