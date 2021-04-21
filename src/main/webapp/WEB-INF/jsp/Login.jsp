<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: yuvrajsingh
  Date: 15-04-2021
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1 class="display-4">Login</h1>
        <hr class="my-4">
        <form:form modelAttribute="user">
            <div class="form-group">
                <label>Username:</label>
                <form:input cssClass="form-control" path="userName"/>
            </div>
            <div class="form-group">
                <label>Password:</label>
                <form:password cssClass="form-control" path="password"/>
            </div>
            <input type="submit" class="btn btn-primary" value="Login >>">
        </form:form>
    </div>
</div>
</body>
</html>
