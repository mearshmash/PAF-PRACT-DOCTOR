<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css" >
<script src="Components/jquery-3.5.1.js"></script>
<script src="Components/docs.js"></script>
</head>
<body>
	<div class="container">
	   <div class="row">
	       <div class="col-6">    
	       		
	       		<h1>Doctor Management</h1>
	          	
	          	<form id="formDoc" name="formDoc" method="post" action="docs.jsp">
	          	    Doctor code:
	          	    <input id="docCode" name="docCode" type="text" class="form-control form-control-sm">
	          	    <br> Doctor name:  
	          	    <input id="docName" name="docName" type="text" class="form-control form-control-sm">  
	          	    <br> Doctor fee:  
	          	    <input id="docFee" name="docFee" type="text" class="form-control form-control-sm">  
	          	    <br> Doctor description:  
	          	    <input id="docDesc" name="docDesc" type="text" class="form-control form-control-sm">  
	          	    <br>  
	          	    <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
	          	    <input type="hidden" id="hidDocIDSave" name="hidDocIDSave" value=""> 
	          	 </form> 
	          	 
	          	 <div id="alertSuccess" class="alert alert-success"></div>
	          	 <div id="alertError" class="alert alert.danger"></div>
	          	 
	          	 <br>
	          	 <div id="divDocsGrid">
	          	 <%
	          	 	Doc docObj = new Doc();
	          	 	out.print(docObj.readDocs());
	          	 %>
	          	 </div>
	       </div>   
	   </div>  
	</div> 
</body>
</html>