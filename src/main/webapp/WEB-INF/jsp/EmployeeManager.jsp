<%@ page import="com.nagarro.hrmanagement.model.Employee" %>
<%@ page import="com.nagarro.hrmanagement.controller.LoginController" %><%--
  Created by IntelliJ IDEA.
  User: yuvrajsingh
  Date: 15-04-2021
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<% Employee[] employees = (Employee[]) request.getSession().getAttribute("employees");
String name = (String) request.getSession().getAttribute("user");
%>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <p>Welcome: <%=name%></p>
        </div>
        <div class="col-md-3">
            <button class="btn btn-info btn-lg" data-toggle="modal" data-target="#uploadfile">Upload Employee Details</button>
        </div>
        <div class="col-md-3">
            <form method="get" action="<%=request.getContextPath()%>/DownloadFile">
            <button class="btn btn-info btn-lg">Download Employee Details</button>
            </form>
        </div>
        <div class="col-md-3">
            <form method="get" action="<%=request.getContextPath()%>/Logout">
            <button class="btn btn-info btn-lg">Logout</button>
            </form>
        </div>
    </div>
</div>
<table>
    <tr>
        <th>Employee Code</th>
        <th>Employee Name</th>
        <th>Location</th>
        <th>Email</th>
        <th>Date Of Birth</th>
        <th>Action</th>
    </tr>
    <%
        Long employeeCode=null;
        String employeeName=null;
        String location=null;
        String email=null;
        java.sql.Date dob = null;


        for(int i=0; i<employees.length; i++){
            employeeCode=employees[i].getEmployeeCode();
            employeeName=employees[i].getEmployeeName();
            location=employees[i].getLocation();
            email=employees[i].getEmail();
            dob = employees[i].getDob();

    %>
    <tr>
        <td><%=employeeCode%></td>
        <td><%=employeeName%></td>
        <td><%=location%></td>
        <td><%=email%></td>
        <td><%=dob%></td>
        <td><button type="button" class="btn btn-info btn-lg"
                    data-toggle="modal" data-target="#myModal" onclick="fill(<%=employeeCode%>,'<%=employeeName%>','<%=location%>',
                '<%=email%>','<%=dob%>')">Edit</button></td>
    </tr>
    <%
        }
    %>
</table>
<div class="modal fade" id="uploadfile" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <p class="modal-title">Upload CSV File</p>
            </div>
            <div class="modal-body">
                <form method="post" action="<%=request.getContextPath()%>/UploadFile" enctype="multipart/form-data">
                    <input type="file" class="col-md-6 col-sm-6" name="file"
                           required="required" />
                    <button class="btn btn-primary col-md-3 col-sm-3 col-md-offset-1">Upload</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <p class="modal-title">Edit Employee Details</p>
            </div>
            <div class="modal-body">
                <form modelAttribute="emp" method="post">
                    <div class="form-group row">
                        <label class="col-md-4 col-sm-4 col-form-label">Employee
                            Code: </label> <input name="employeeCode" class=" col-md-7 col-sm-7" id="employeeCode"
                                                  type="text" required readonly>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-sm-4 col-form-label">Employee
                            Name: </label> <input name="employeeName" class=" col-md-7 col-sm-7" id="employeeName"
                                                  type="text" required>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-sm-4 col-form-label">Location:
                        </label> <input name="location" class=" col-md-7 col-sm-7 " id="employeeLocation"
                                        type="text" required>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-sm-4 col-form-label">Email: </label>
                        <input name="email" class=" col-md-7 col-sm-7 " id="employeeEmail" type="text"
                            required>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-4 col-sm-4 col-form-label">Date of
                            Birth: </label> <input name="dob" class=" col-md-7 col-sm-7 "
                                                   id="employeeBirthDate" type="text" required>
                    </div>
                    <div class="modal-footer justify-content-center">
                        <button type="submit" class="btn btn-success">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function fill(code,name,location,email,dateOfBirth,pos){
        document.getElementById("employeeCode").value=code;
        document.getElementById("employeeName").value=name;
        document.getElementById("employeeLocation").value=location;
        document.getElementById("employeeEmail").value=email;
        document.getElementById("employeeBirthDate").value=dateOfBirth;
    }
</script>
</body>
</html>
