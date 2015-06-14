<%-- 
    Document   : show
    Created on : Jun 13, 2015, 4:51:13 PM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:main>
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <c:set var="base" value="${pageContext.request.contextPath}/Products"/>
        <h2>${pageTitle}</h2>
        <div class="row">
            <div class="col-md-4">
                <h4>Meniu</h4>
                <ul>
                    <li><a href="${base}/index">Back to All Products</a></li>
                </ul>
            </div>
            <div class="col-md-8">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr>
                                <td>${product.getName()}</td>
                                <td>${product.getDescription()}</td>
                                <td>${product.getPrice()}</td>                                
                                <td>
                                    <a href="${base}/buy/${product.getId()}/user/${pageContext.session.getAttribute('userId')}">BUY</a>
                                    <a href="${base}/addToCart/${product.getId()}/user/${pageContext.session.getAttribute('userId')}">ADD TO CART</a>
                                </td>
                            </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </jsp:body>
</template:main>
