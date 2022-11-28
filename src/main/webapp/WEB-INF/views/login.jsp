<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-11-24
  Time: 오후 5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>KTX FWTS Client Web</title>
    <link rel="stylesheet" type="text/css" href="system/style.css">
    <link rel="stylesheet" type="text/css" href="system/docs/docs.css">
    <script src="js/login.js"></script>
</head>
<body>
    <div class="container">
        <div class="layout">
            <p class="col col-main"></p>
            <div class="standard-dialog">
                <p class="heading center">KTX FWTS Client WEB</p>
                <p class="desc center">
                    <span role="img" class="apple" aria-label="Apple"></span>login
                </p>
                <section class="component">
                    <p class="col col-main"></p>
                    <form action="/login" method="post">
                        <label for="id">ID</label>
                        <br>
                        <input id="id" type="text" placeholder="input your ID">
                        <br>
                        <label for="pwd">Password</label>
                        <br>
                        <input id="pwd" type="password" placeholder="password">
                        <br>
                        <button id="login-btn" class="btn">Submit</button>
                        <button type="reset" class="btn">Cancel</button>
                    </form>
                    <p class="col col-main"></p>
                </section>
            </div>
            <p class="col col-main"></p>
        </div>
    </div>
</body>
</html>
