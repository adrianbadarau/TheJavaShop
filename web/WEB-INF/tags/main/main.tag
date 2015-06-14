<%-- 
    Document   : main
    Created on : Jun 9, 2015, 9:25:21 PM
    Author     : adrian
--%>

<%@tag description="the main layout of the page" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle" fragment="true" required="false"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Simple Java Ecommerce Site">
        <meta name="author" content="Badarau Adrian | adi.badarau@yahoo.com">
        <title>The Java Shop | <jsp:invoke fragment="pageTitle"/></title>
        <link href="<c:url value="/resources/bootstrap-3.3.4-dist/css/bootstrap.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>
        <script src="<c:url value="/resources/JQuery/jquery-2.1.4.js"/>"></script>
        <script src="<c:url value="/resources/bootstrap-3.3.4-dist/js/bootstrap.js"/>"></script>        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
    </head>
    <body>
        <c:set var="base" value="${pageContext.request.contextPath}"/>
        <div class="header">
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/TheJavaShop/">The Java Store</a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <c:if test="${pageContext.session.getAttribute('isAdmin').equals('true')}">
                                <li>
                                    <a href="/TheJavaShop/backend/">BackEnd</a>
                                </li>
                            </c:if>
                                <li><a href="${base}/logout">LogOut <c:out value="${pageContext.session.getAttribute('userName')}"/></a></li>                           

                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container -->
            </nav>

        </div>
<!-- Page Content -->
<div class="container">
        <div class="content">
            <jsp:doBody/>
        </div>
        <div class="footer">
            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
            </footer>
        </div>
</div><!-- /.container -->
    </body>
</html>