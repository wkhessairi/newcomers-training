<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>Create task</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <style>
  body {padding-top:20px;}
  </style>
</head>

<body>
    <portlet:actionURL var="createTaskURL" portletMode="view">
        <portlet:param name="redirectToCreate" value="true" />
    </portlet:actionURL>
    <div class="container">
    	<div class="row">
          <div class="col-md-6 col-md-offset-3">
            <div class="well well-sm">
              <form class="form-horizontal" action="<%= createTaskURL %>" method="post">
              <fieldset>
                <legend class="text-center">Create task</legend>

                <!-- Title input-->
                <div class="form-group">
                  <label class="col-md-3 control-label" for="taskTitle">Title</label>
                  <div class="col-md-6">
                    <input id="taskTitle" name="taskTitle" type="text" placeholder="add a task title" class="form-control" required>
                  </div>
                </div>

                <!-- Date input-->
                <div class="form-group">
                  <label class="col-md-3 control-label" for="taskDate">Date</label>
                  <div class="col-md-6">
                    <input id="taskDate" name="taskDate" type="text" placeholder="add a date" class="form-control">
                  </div>
                </div>

                <!-- Description input -->
                <div class="form-group">
                  <label class="col-md-3 control-label" for="taskDescription">Description</label>
                  <div class="col-md-6">
                    <textarea class="form-control" id="taskDescription" name="taskDescription" placeholder="Please enter a description..." rows="5"></textarea>
                  </div>
                </div>

                <!-- Form actions -->
                <div class="form-group">
                  <div class="col-md-9 text-right">
                    <button type="submit" class="btn btn-primary btn-sm">Create</button>
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