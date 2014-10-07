



<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
 
<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
	<head>
		<title>Upload My Picture</title>
	</head>
	<body>

		<h1>Tweet My Picture</h1>
	<hr/>
		<h2>Upload a picture</h2>
		<form action="<%= blobstoreService.createUploadUrl("/simpleupload") %>" method="post" enctype="multipart/form-data">
			File :
			<input type="text" name="filename"/>
			<input type="file" name="uploadfile"/>
			<input type="submit" value="Upload Picture"/>
		</form>
</html>
