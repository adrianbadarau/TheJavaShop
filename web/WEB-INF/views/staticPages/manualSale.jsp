<%-- 
    Document   : manualSale
    Created on : Jun 14, 2015, 11:44:29 AM
    Author     : adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/main/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:main >
    <jsp:attribute name="pageTitle">${pageTitle}</jsp:attribute>
    <jsp:body>
        <c:set var="base" value="${pageContext.request.contextPath}/Products"/>
        <form:form action="/buy" method="POST" commandName="saleForm" id="saleForm">
            <form:select path="product" id="prodId" cssClass="form-control">
                <form:option value="NONE" label="--- Select ---"/>
                <form:options items="${products}" />
            </form:select>
            <form:select path="client" id="clientId" cssClass="form-control">
                <form:option value="NONE" label="--- Select ---"/>
                <form:options items="${clients}" />
            </form:select>
            <input type="submit" value="Create Sale"/>
        </form:form>
        
        <script>
            $(function(){
                $('#saleForm').on('submit', function (event){
                    event.preventDefault();
                    $.ajax({
                        url: '${base}/buy/'+$('#prodId').val()+'/user/'+$('#clientId').val(),
                        method: 'GET'
                    })
                    .done(function (data){
                        alert("Sale created");
                        $('#saleForm').trigger('reset');
                    })
                    .fail(function (data){
                        console.log(data);
                    })
                })
            });
        </script>
    </jsp:body>    
</template:main>
