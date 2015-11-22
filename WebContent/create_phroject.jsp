<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="phrojects.model.interface_dao.ProjectDAO, phrojects.model.mysql_dao.MySQLProjectDAO,
     phrojects.model.objects.Project, phrojects.model.objects.Government,  phrojects.model.objects.Proposal "%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PHrojects - Create Project</title>
	<script type="text/javascript" src="resources/vendors/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="resources/vendors/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/vendors/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/style/style.css">
	
	<script src='resources/vendors/bootstrap-tags/js/bootstrap-tags.min.js'></script>
	<link rel="stylesheet" type="text/css" href="resources/vendors/bootstrap-tags/css/bootstrap-tags.css" />
</head>
<body>
<div class="container-fluid">
		<div class="row" align="center">
			<div class="col-md-12">
				<h3 style="color:black; align:center;">Start your Project!</h3>
			</div>
		</div>
		<div class="row" align="center">
			<div class="col-md-12" >
				<p style="color:black; style:normal;">Add descriptions, the managing department, and location</p>
			</div>
		</div>
		<div class="row" >
			<div class="col-md-12">
				<form class="form-horizontal" action="CreateProjectServlet" method="post">
					<div class="form-group">
						<label class="control-label col-sm-2" for="name" style="color:black">Project title:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="title">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="description" style="color:black">Description:</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="description" name="description"></textarea>
						</div>
						
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="department" style="color:black">Department:</label>
						<div class="col-sm-10">
							<select class="form-control sort-type" id="department" name="department">
								<option>Department of Agragarian Reform</option>
								<option>Department of Agriculture</option>
								<option>Department of Budget and Management</option>
								<option>Department of Education</option>
								<option>Department of Environment and Natural Resources</option>
								<option>Department of Finance</option>
								<option>Department of Foreign Affairs</option>
								<option>Department of Health</option>
								<option>Department of Justice</option>
								<option>Department of Labor and Employment</option>
								<option>Department of National Defense</option>
								<option>Department of Public Works and Highways</option>
								<option>Department of Science and Technology</option>
								<option>Department of Social Welfare and Development</option>
								<option>Department of the Interior and Local Government</option>
								<option>Department of Tourism</option>
								<option>Department of Trade and Industry</option>
								<option>Department of Transportation and Communication</option>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="priority" style="color:black">Priority:</label>
						<div class="col-sm-10">
							<select class="form-control sort-type" id="department" name="priority">
								<option>Low</option>
								<option>Medium</option>
								<option>High</option>
							</select>
						</div>

					</div>
					
				<div class="form-group">
						<label class="control-label col-sm-2" for="name" style="color:black">Regions:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="regions">
						</div>
					</div>
					<div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-success">Create</button>
					  <a class="btn btn-danger" href="index.jsp">Cancel</a>
				    </div>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>