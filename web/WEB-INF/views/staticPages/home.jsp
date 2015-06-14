<%-- 
    Document   : home
    Created on : Jun 9, 2015, 8:33:06 PM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:main >
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <c:set var="base" value="${pageContext.request.contextPath}/Products"/>
        <!-- Jumbotron Header -->
        <header class="jumbotron hero-spacer">
            <h1>A Warm Welcome!</h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsa, ipsam, eligendi, in quo sunt possimus non incidunt odit vero aliquid similique quaerat nam nobis illo aspernatur vitae fugiat numquam repellat.</p>
            <p><a class="btn btn-primary btn-large">Call to action!</a>
            </p>
        </header>

        <hr>

        <!-- Title -->
        <div class="row">
            <div class="col-lg-12">
                <h3>Latest Products</h3>
            </div>
        </div>
        <!-- /.row -->

        <!-- Page Features -->
        <div class="row text-center">
            <c:forEach var="product" items="${products}">
                <div class="col-md-3 col-sm-6 hero-feature">
                    <div class="thumbnail">
                        <img src="http://placehold.it/800x500" alt="">
                        <div class="caption">
                            <h3>${product.getName()}</h3>
                            <p>${product.getDescription()}</p>
                            <p>
                                <a href="${base}/show/${product.getId()}" class="btn btn-primary">Buy Now!</a> <a href="#" class="btn btn-default">More Info</a>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        <!-- /.row -->
    </jsp:body>    
</template:main>
