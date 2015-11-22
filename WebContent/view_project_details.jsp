<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="phrojects.model.interface_dao.ProjectDAO, phrojects.model.mysql_dao.MySQLProjectDAO,
     phrojects.model.objects.Project, phrojects.model.objects.Government,  phrojects.model.objects.Proposal,  phrojects.model.objects.Account, phrojects.model.interface_dao.AccountDAO, phrojects.model.mysql_dao.MySQLAccountDAO "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PHrojects - Project Details</title>
	<script type="text/javascript" src="resources/vendors/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="resources/vendors/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/vendors/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/style/style.css">
</head>
<body>

<%
    ProjectDAO projectDAO = new MySQLProjectDAO();
	Project p = projectDAO.getProject(request.getParameter("project_id"));
	String status = null; 
	String author = null;
	
	if (p.getProjectType().equals("GOVERNMENT")) {
		Government g = (Government) p;
		status = g.getProjStatus();
		author = "Government";
	} else {
		Proposal prop = (Proposal) p;
		status = prop.getStatus();
		AccountDAO accountDAO = new MySQLAccountDAO();
		Account account =  accountDAO.getProjectAccount(p.getProjectID());
		author = "aklsdj";
		author = account.getLastName() + ", " +account.getFirstName();
	}
	
%>

<nav class="navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="index.jsp">PHrojects.</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="about.html">About</a></li>
						<li><a href="help.html">Help</a></li>
						<li><a href="contactus.html">Contact Us</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
</nav>
<div class="row">
	<br>
</div>
<div class="container-fluid">

	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-8">
					<div class="row"  align="center">
						<div class="col-md-12">
						    
							<h2 id="project-details-name"><%= p.getTitle() %></h5>
							<p id="project-details-author">by <%= author  %></p>
						</div>
					</div>
					<div class="row" >
						<div class="col-md-6">
				
							<p id="project-details-description"><%= p.getDescription() %></p>

						</div>
						<div class="col-md-6"  style="border-left: 1px solid black">
							<label style="color:black">Department:</label>
							<p id="project-details-department"><%= p.getDepartment() %></p>
							<label style="color:black">Project Type:</label>
							<p id="project-details-regions"><%= p.getProjectType() %></p>
							<label style="color:black">Status:</label>
							<p id="project-details-status"><%= status %></p>
							<!-- Progress Bar -->
							<% 
							     int numLikes = projectDAO.getUpvotes(p.getProjectID());
							     int numDislikes = projectDAO.getDownvotes(p.getProjectID());
							     double total = numLikes + numDislikes;
							     
							     double likePercentage = (total == 0) ? 0 : (numLikes / total) * 100;
							     double dislikePercentage = (total == 0) ? 0 : (numDislikes / total) * 100;
							%>
							
							<div class="progress">
							  <div class="progress-bar progress-bar-success" role="progressbar" style="width:<%=likePercentage%>%">
									<%= (total == 0) ? "" : numLikes %>
							  </div>
							  <div class="progress-bar progress-bar-danger" role="progressbar" style="width:<%=dislikePercentage%>%">
									<%=  (total == 0) ? "" : numDislikes %>
							  </div>
							</div>
							
							<!--  Like and Dislike -->
							<button type="button" class="btn btn-default btn-lg">
								  <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> 
							</button>
							<button type="button" class="btn btn-default btn-lg">
								  <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
							</button>
						</div>
					</div>
					<div class="row" >
						<div class="col-md-12">
							<p  style="color: blue">
								  <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> <%= p.getRegions() %>
							</p>
						</div>
					</div>
					
		</div>
		<div class="col-md-2">
		</div>
	</div>
</div>
</body>
</html>