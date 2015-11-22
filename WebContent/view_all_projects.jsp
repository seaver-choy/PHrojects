<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="phrojects.model.interface_dao.ProjectDAO, phrojects.model.mysql_dao.MySQLProjectDAO,
     phrojects.model.objects.Project, phrojects.model.objects.Government,  phrojects.model.objects.Proposal "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PHrojects - View All Projects</title>
	<script type="text/javascript" src="resources/vendors/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="resources/vendors/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/vendors/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/style/style.css">
</head>
<body>

<%
 	ProjectDAO projectDAO = new MySQLProjectDAO();
	Project[] projects = projectDAO.getAllProjects();
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
<div class="container-fluid">
	<div class="row">
		<% for (int i = 0; i < projects.length; i++) { 
		   Project p = projects[i];
		   String prodId = p.getProjectID();
		%>
		
		<div class="col-md-4 project-thumbnail">
			<div class="panel panel-default">
			    <a href="view_project_details.jsp?project_id=<%= prodId %>">
				<div class="panel-body project-title">
					<h5><%= p.getTitle() %></h5>
					<h6><%= p.getDepartment() %></h6>
					<% 
					   String status = null;
					   if (p.getProjectType().equals("GOVERNMENT")) {
						   	Government gov = (Government) p;
						   	status = gov.getProjStatus().toString();
					   } else {
					        Proposal proposal = (Proposal) p;
					        status = proposal.getStatus();
					   }
					%>
					<p><%= status %></p>
					<p><%= p.getDescription() %></p>
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
				</div>
				</a>
			</div>
		</div>
		<% } %>
		
	</div>
</div>
</body>
</html>