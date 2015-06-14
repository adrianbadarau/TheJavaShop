<%-- 
    Document   : index
    Created on : Jun 13, 2015, 11:23:12 AM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:main>
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <c:set var="base" value="${pageContext.request.contextPath}/backend/Users"/>
        <h2>${pageTitle}</h2>
        <div class="row">
            <div class="col-md-4">
                <h4>Meniu</h4>
                <ul>
                    <li><a href="${base}/new/">Create new Client</a></li>
                </ul>
            </div>
            <div class="col-md-8">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.getName()}</td>
                                <td>${user.getPass()}</td>
                                <td>
                                    <a href="${base}/edit/${user.getId()}">Edit</a>
                                    <a href="${base}/delete/${user.getId()}">Delete</a>
                                </td>
                            </tr>
                            
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </jsp:body>
</template:main>
