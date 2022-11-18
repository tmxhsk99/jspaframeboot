<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-11-18
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>room-list</title>
</head>
<body>
    <h1>RoomList</h1>
    <c:forEach var="room" items="${rooms}">
        <li><a href="/chat/rooms/${room.id}"></a></li>
    </c:forEach>

</body>
</html>
