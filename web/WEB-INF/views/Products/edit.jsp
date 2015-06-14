<%-- 
    Document   : edit
    Created on : Jun 11, 2015, 8:30:23 PM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<template:main>
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <h1>${pageTitle}</h1>
        <div class="row">
            <div class="col-md-4">
                <div class="panel">
                    <h4>Menu</h4>
                </div>
            </div>
            <div class="col-md-8 pull-right">
                <form:form action="/TheJavaShop/backend/Products/manage" method="POST" commandName="productForm" cssClass="form-horizontal">
                    <div class="form-group">
                        <form:label path="name">Name: </form:label>
                        <form:input path="name" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:label path="price">Price:  </form:label>
                        <form:input path="price" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:label path="description">Details: </form:label>
                        <form:textarea path="description" cssClass="form-control"/>
                    </div>
                    
<!--                    <div class="form-group">
                        <%--<form:label path="picturePath">Picture: </form:label>--%>
                        <input type="file" name="picturePath"/>
                    </div>-->
                        <c:if test="${productForm.getId() != 0}">
                            <form:hidden path="id"/>
                        </c:if>
                    <input type="submit" value="Submit"/>
                </form:form>
            </div>
        </div>

    </jsp:body>
    
</template:main>