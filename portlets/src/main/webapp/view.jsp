<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ page import="javax.jcr.*" %>
<%@ page import="java.util.List" %>

<portlet:defineObjects/>

<%
    List<Node> myTasks = (List<Node>) renderRequest.getAttribute("nodes");
%>

<html>
<head>
    <title>Tasks</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   	<style>
   .task-row {
       margin-bottom: 14px;
   }

   .task-row:last-child {
       margin-bottom: 0;
   }

   .drop-task {
       margin: 13px 0;
       padding: 5px;
       height: 100%;
   }

   .drop-task:hover {
       cursor: pointer;
   }

   .table-task-information > tbody > tr {
       border-top: 1px solid rgb(221, 221, 221);
   }

   .table-task-information > tbody > tr:first-child {
       border-top: 0;
   }


   .table-task-information > tbody > tr > td {
       border-top: 0;
   }

   	</style>
</head>

<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<div id="navigation">
    <portlet:renderURL var="createURL">
        <portlet:param name="redirectToCreate" value="true" />
    </portlet:renderURL>
</div>

<br><br>
<div class="container">
 <div class="well col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1">
 	
	<% for (Node node : myTasks)
	{%>
		 <portlet:actionURL var="deleteURL">
			<portlet:param name="taskId" value="<%=node.getName()%>" />
			<portlet:param name="redirectToDelete" value="true" />
		 </portlet:actionURL>
		 <portlet:renderURL var="editURL">
			<portlet:param name="newTaskId" value="<%=node.getName()%>" />
			<portlet:param name="oldTitle" value="<%=node.getProperty(\"title\").getString()%>" />
			<portlet:param name="oldDescription" value="<%=node.getProperty(\"description\").getString()%>" />
			<portlet:param name="oldDate" value="<%=node.getProperty(\"due\").getString()%>" />
			<portlet:param name="redirectToEdit" value="true" />
		 </portlet:renderURL>
	
		<div class="row task-row">
			<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
            	<strong><%= node.getProperty("title").getString()%></strong><br>
    		</div>

        	<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1 drop-task" data-for=".<%= node.getProperty("title").getString()%>">
	            <i class="glyphicon glyphicon-chevron-down text-muted"></i>
		    </div>
		</div>

		<div class="row task-infos <%= node.getProperty("title").getString()%>">
		    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1">
		        <div class="panel panel-primary">
		            <div class="panel-heading">
		                <h3 class="panel-title">Task information</h3>
		            </div>
		            <div class="panel-body">
		                <div class="row">

		                    <div class="col-xs-10 col-sm-10 hidden-md hidden-lg">
		                        <strong><%= node.getProperty("title").getString()%></strong><br>
		                        <dl>
		                            <dt>Description:</dt>
		                            <dd><%= node.getProperty("description").getString() %></dd>
		                            <dt>Date:</dt>
		                            <dd><%= node.getProperty("due").getString() %></dd>
		                        </dl>
		                    </div>

		                    <div class=" col-md-9 col-lg-9 hidden-xs hidden-sm">
		                        <strong><%= node.getProperty("title").getString()%></strong><br>
		                        <table class="table table-task-information">
		                            <tbody>
		                            <tr>
		                                <td>Description:</td>
		                                <td><%= node.getProperty("description").getString() %></td>
		                            </tr>
		                            <tr>
		                                <td>Date:</td>
		                                <td><%= node.getProperty("due").getString() %></td>
		                            </tr>
		                           </tbody>
		                        </table>
		                    </div>
		                </div>
		            </div>

		            <div class="panel-footer">
						<a href="<%= editURL %>">                            
						<button class="btn btn-sm btn-warning" type="button"
	                            data-toggle="tooltip"
	                            data-original-title="Edit this task"><i class="glyphicon glyphicon-edit"></i></button>
						</a>
						<a href="<%= deleteURL %>">
	                    <button class="btn btn-sm btn-danger" type="button"
	                            data-toggle="tooltip"
	                            data-original-title="Remove this task"><i class="glyphicon glyphicon-remove"></i></button>
						</a>
		                </span>
		            </div>
		        </div>
		    </div>
		</div>
	
	<% } %>
	<a href="<%= createURL %>"><span><button type="button" class="btn btn-default btn-sm">New task</button></a>
	</div>
</div>

<script>
$(document).ready(function() {
		var panels = $('.task-infos');
		var panelsButton = $('.drop-task');
		panels.hide();

		//Click dropdown
		panelsButton.click(function() {
			//get data-for attribute
			var dataFor = $(this).attr('data-for');
			var idFor = $(dataFor);

			//current button
			var currentButton = $(this);
			idFor.slideToggle(400, function() {
				//Completed slidetoggle
				if(idFor.is(':visible'))
				{
					currentButton.html('<i class="glyphicon glyphicon-chevron-up text-muted"></i>');
				}
				else
				{
					currentButton.html('<i class="glyphicon glyphicon-chevron-down text-muted"></i>');
				}
			})
		});


		$('[data-toggle="tooltip"]').tooltip();

	});
</script>

</body>
</html>