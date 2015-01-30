<%@ page pageEncoding="UTF-8" %>
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
<meta charset="utf-8" />
<title>Create task</title>
</head>
<body>
<div id="tasks-wrapper" class="container">
    <p>Select the Task you are going to delete</p>
    <portlet:actionURL var="deleteTaskURL" portletMode="view"/>
    <form action="<%= deleteTaskURL %>"  method="post">
        <FORM>
        <SELECT name="taskToDelete" size="1">
        <% for (Node node : myTasks)
        { %>
        <OPTION><%= node.getProperty("id").getString()+" - "+node.getProperty("title").getString() %>
        <% } %>
        </SELECT>
        </FORM>
        <input type="submit" value="Delete">
    </form>
</div>
</body>
</html>