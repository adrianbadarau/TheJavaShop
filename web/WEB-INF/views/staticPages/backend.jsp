<%-- 
    Document   : backend
    Created on : Jun 13, 2015, 10:28:09 AM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:main>
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <c:set var="base" value="${pageContext.request.contextPath}/backend"/>
        <div class="row">
            <div class="col-md-4">
                <div class="panel">
                    <div class="panel-title"><h3>Menu</h3></div>
                    <div class="panel-body">
                        <ul>
                            <li><a href="${base}/Users/index">Users</a></li>
                            <li><a href="${base}/Products/index">Products</a></li>
                            <li><a href="${base}/ManualSale">Manual Sale Generator</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <h4>Latest Sales</h4>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Product Price</th>
                            <th>Client Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sale" items="${sold}">
                            <tr>
                                <td>${sale.getProduct().getName()}</td>
                                <td>${sale.getProduct().getPrice()}</td>
                                <td>${sale.getClient().getName()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</template:main>