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
<title>Create task</title>
</head>
<body>
    <p>please fill the following information:</p>
    </br>
     <portlet:actionURL var="editTaskURL" portletMode="view">
        <portlet:param name="redirectToEdit" value="true" />
     </portlet:actionURL>
        <form action="<%= editTaskURL %>"  method="post">
            <label for="newTaskTitle">Name: </label><input id="newTaskTitle" type="text" value="<%=oldTitle%>" name="newTaskTitle" />
            <label for="newTaskDescription">Description: </label><input id="newTaskDescription" type="text" value="<%=oldDescription%>" name="newTaskDescription" />
            <label for="newTaskDate">Date: </label><input id="newTaskDate" type="text" value="<%=oldDate%>" name="newTaskDate" />
            <input type="submit" value="Edit">
        </form>
</body>
</html>