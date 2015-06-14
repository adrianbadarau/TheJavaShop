<%-- 
    Document   : login
    Created on : Jun 13, 2015, 2:03:35 PM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:main>
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <c:set var="base" value="${pageContext.request.contextPath}"/>
        <div class="row">
            <div class="col-md-8">
                <h4>${pageTitle}</h4>
                <form action="${base}/authenticate" method="POST" class="form-horizontal">
                    <div class="form-group">
                        <input type="text" name="userName" placeholder="Username"/>
                        <input type="text" name="userPass" placeholder="Password" />
                    </div>
                    <button class="btn btn-default" type="submit">LogIn</button>
                </form>
                <p>Or you can register <a href="${base}/backend/Users/new">here</a></p>
            </div>
        </div>
    </jsp:body>
</template:main>