<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/WEB-INF/header.xhtml" %>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>  
    <br>
    <div class="container-fluid">
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
                <h4><b><font color="black" style="font-family: fantasy;">My First Login Demo</font> </b></h4>
            </div>
            <div class="panel-body"align="center">
                 
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
   
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
                       
                        <div class="panel-heading form-group">
                            <b><font color="white">
                                Login Form</font> </b>
                        </div>
                   
                        <div class="panel-body" >

                        <form action="LogoutServlet" method="post" >
                            <div class="form-group">
                                <label>Wollen Sie sich wirklich ausloggen?</label>                              
                            </div>
                            <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Ausloggen</b></button>
                                                  
                        </form>

                        </div>
                    </div>
                   
                </div>
               
            </div>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2014  <a href="http://mysite.com/">mysite.com</a>, All Rights Reserved. </font></div>
        </div>
    </div>
   
</body>
</html>
