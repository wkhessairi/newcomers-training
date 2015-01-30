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
</head>

<body>

<div id="navigation">
    <portlet:renderURL var="createURL">
        <portlet:param name="redirectToCreate" value="true" />
    </portlet:renderURL>
    

    <a href="<%= createURL %>"><span>Go to Create</span></a>
</div>

<div id="tasks-wrapper" class="container">

    <h6>Hello Support Newcomers, here are all available tasks:</h6>

    <ol style="list-style: circle">
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
         <li><%= node.getProperty("title").getString() %>
            <ul>
                <li>Description: <%= node.getProperty("description").getString() %></li>
                <li>Date: <%= node.getProperty("due").getString() %></li>
                <li> <a href="<%= deleteURL %>"><span>Delete  </span></a> <a href="<%= editURL %>"><span> Edit</span></a></li>
            </ul>
         </li>
      <% } %>
    </ol>
</div>

</body>
</html>