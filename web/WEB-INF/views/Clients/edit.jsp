<%-- 
    Document   : edit
    Created on : Jun 13, 2015, 11:25:47 AM
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
                <form:form action="/TheJavaShop/backend/Users/manage" method="POST" commandName="userForm" cssClass="form-horizontal">
                    <div class="form-group">
                        <form:label path="Name">Name: </form:label>
                        <form:input path="Name" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:label path="Pass">Pass:  </form:label>
                        <form:input path="Pass" cssClass="form-control"/>
                    </div>
                    <c:if test="${userForm.getId() != 0}">
                        <form:hidden path="Id"/>
                    </c:if>
                    <input type="submit" value="Submit"/>
                </form:form>
            </div>
        </div>

    </jsp:body>
    
</template:main>