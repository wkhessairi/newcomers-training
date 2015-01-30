<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Create task</title>
</head>
<body>
    <p>please fill the following information:</p>
    </br>
     <portlet:actionURL var="createTaskURL" portletMode="view">
     <portlet:param name="redirectToCreate" value="true" />
     </portlet:actionURL>
        <form action="<%= createTaskURL %>"  method="post">
            <label for="taskTitle">Name: </label><input id="taskTitle" type="text" name="taskTitle" />
            <label for="taskDescription">Description: </label><input id="taskDescription" type="text" name="taskDescription" />
            <label for="taskDate">Date: </label><input id="taskDate" type="text" name="taskDate" />
            <input type="submit" value="Create">
        </form>
</body>
</html>