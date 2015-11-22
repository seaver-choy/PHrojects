<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="phrojects.model.interface_dao.ProjectDAO, phrojects.model.mysql_dao.MySQLProjectDAO,
     phrojects.model.objects.Project, phrojects.model.objects.Government,  phrojects.model.objects.Proposal "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PHrojects</title>

    <!-- Bootstrap Core CSS -->
    <link href="resources/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="resources/vendors/bootstrap/css/scrolling-nav.css" rel="stylesheet">
    <style>
      .background1{
        background-image: url('resources/vendors/images/Projects.jpg');
        background-repeat: no-repeat;
      	background-size: 100% 100%;
      }
	
	  .addopacity{
	  	background:black;
	  	opacity:.6;
	  	height:100%;
	  	width:100%;
	  	top:0;
	  	left:0;
	  	position:absolute;
	  }
	  
      h1{
        font-size: 6em;
        padding-top: 70px;
      }

      .popular-project-subfont{
        font-size: 2em;
        padding-top: 20px;
        padding-bottom: 20px;
      }
      
      .contactus-font{
  color: black;
  font-size: 6em;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.numbers-font{
  color: black;
  font-size: 1.8em;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.nav.navbar-nav.navbar-right li a {
  color: #2ecc71;
}

.putapadding{
  padding-top: 110px;
}
  .aboutus-font{
        color: black;
      }

      .aboutpadding{
        padding-top: 45px;
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      }

      .aboutstyle{
        text-align: justify;
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      }
      

    </style>
    	<link rel="stylesheet" type="text/css" href="resources/style/style.css">
</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	<%
		boolean isLoggedIn = (session.getAttribute("username") !=  null);
	%>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>	
                <a class="navbar-brand page-scroll" href="#page-top">PHroject</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                
<ul class="nav navbar-nav">
    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
    <li class="hidden">
        <a class="page-scroll" href="#page-top"></a>
    </li>
    <li>
        <a class="page-scroll" href="#services">Discover</a>
    </li>
    <li>
        <a href=<%= (isLoggedIn) ? "create_phroject.jsp" : "PHrojectslogin.html" %>>Start a Project </a>
    </li>
    <li>
        <a class="page-scroll" href="#about">About us</a>
    </li>
</ul>
<ul class="nav navbar-nav navbar-right">
      <li>
          <a href=<%= (isLoggedIn) ? "LogoutServlet" : "PHrojectslogin.html" %>><%= (isLoggedIn) ? "Log Out" : "Sign In" %> </a>
      </li>
      <li>
          <a>Sign Up </a>
      </li>
</ul>
                
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Intro Section -->
    <section id="intro" class="background1 intro-section">
        <div class="container">
            <div class="row">
            <div class = "addopacity">
            </div>
                <div class="col-lg-12">
                    <h1 style="color:white">Welcome to PHrojects</h1>
                    <p class="popular-project-subfont" >Help the Government, Help You</P>
                    <a type="button" class="btn btn-lg btn-success" href="view_all_projects.jsp"> View All Projects </a>
                </div>
            </div>
        </div>
    </section>

    <%
 	ProjectDAO projectDAO = new MySQLProjectDAO();
	Project[] projects = projectDAO.getBestProjects();
    //Project[] projects = projectDAO.getAllProjects();
	%>
    <!-- Services Section -->
    <section id="services" class="services-section">
        <div class="container-fluid">
	<h1> What's Popular </h1>
	<div class="row">
		<% for (int j = 0; j < 3 && j < projects.length; j++) { 
		   Project p = projects[j];
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
						   	status = gov.getProjStatus().toString().substring(0, 50);
					   } else {
					        Proposal proposal = (Proposal) p;
					        status = proposal.getStatus();
					   }
					%>
					<p><%= status %></p>
					<p><%= p.getDescription().substring(0, 50) %></p>
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
    </section>
	
	<!-- About Section -->
    <section id="about" class="about-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 aboutus-font">
                    <h1>About Us</h1>
                    <div class="row">
                      <div class="col-lg-6 aboutpadding aboutstyle">
                        <p>Let the government hear you out. Start something new that not only benefits you but also the people around you. Who knows? the more you think about other the more they'll think of you and your passion to make the PH a better place.</p>
                      </div>
                      <div class="col-lg-6 aboutstyle">
                        <p>We dream of an open and transparent communication between the government and its people, that's why we want make the information available to you, the people. With the innovation of technology and through the direction its heading towards, data and information has never been this valuable. We want to ensure that information is presented and available to the people and the government be transparent and accountable for their actions. We believe the communication is the key for a brighter PH and through coninuous and just projects, together, we can make better PHrojects.</p>
                      </div>
                    </div>

                </div>
            </div>
        </div>
    </section>
	
    <!-- Contact Section -->
    <section id="contact" class="contact-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
    <p class="contactus-font putapadding">Contact Us</p>
</div>
</div>
<div class="row">
<div class="col-lg-6">
<p class="numbers-font"> GLOBE : 0917-886-5800</p>
</div>
<div class="col-lg-6">
<p class="numbers-font"> SMART : 0917-886-5800</p>
</div>
</div>

            </div>
        </div>
    </section>

    <!-- jQuery -->
    <script src="resources/vendors/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/vendors/bootstrap/js/bootstrap.min.js"></script>

    <!-- Scrolling Nav JavaScript -->
    <script src="resources/vendors/bootstrap/js/jquery.easing.min.js"></script>
    <script src="resources/vendors/bootstrap/js/scrolling-nav.js"></script>

</body>

</html>
