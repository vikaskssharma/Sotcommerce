<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<%@ include file="taglib.jsp" %>


<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Budget Homes</title>
        
        <script type="text/javascript" src='<spring:url value="resources/jquery/jquery-1.10.2.js"/>'></script>
        <link rel="stylesheet" href='<spring:url value="resources/bootstrap/css/bootstrap.min.css"/>' />
        <link rel="stylesheet" href='<spring:url value="resources/bootstrap/css/bootstrap-theme.min.css"/>' />
        <script type="text/javascript" src='<spring:url value="resources/bootstrap/js/bootstrap.min.js"/>'></script>
        <link rel="stylesheet" href='<spring:url value="resources/css/styles.css"/>' />
        <script type="text/javascript" src='<spring:url value="resources/js/app.js"/>'></script>
        <link rel="stylesheet" href="resources/css/style.css" />
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
		<script type="text/javascript" src="resources/jquery/particle.js"></script>
	 
        <decorator:head/>        
</head>
<body>

 <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="welcome">Budget Home</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="welcome">Home</a></li>
            <li><a href="admin">Administration</a></li>
            <li><a href="contact">Contact</a></li>
            <li><a href="logout">Logout</a></li>
          </ul>
        </div>
      </div>
    </div>
   <div id="particle-frame" style="width: 2000px; height: 200px;"></div>
        <div class="container">    
                 
          <div class="row">
                  <div class="span12">
   
                    <decorator:body/>
                  </div>
          </div>
    
    </div>
   
    <div th:fragment="foot">
    <footer>
        <p> Copyright 2014, Sapient Corporation. All rights reserved Powered by Sapient IT</p>
    </footer>
</div>
</body>
</html>