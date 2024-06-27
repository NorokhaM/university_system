
<%@ page import="com.university.controller.StudentController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.university.entity.Student" %>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>University System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<%
    StudentController controller=StudentController.getInstance();
    List<Student> students = controller.getStudentByEmail(request);
    request.setAttribute("students", students);
%>

<body>
<section class="hero-section"></section>
<div class="container">
    <form class="w-50 mx-auto mt-3 p-3" action="login" method="post">
        <h5 class="text-center p-3">${sessionScope.role} profile</h5>
        <div class="row">
            <div class="col-4">
                <img src="getImage" alt="logo" width="170" height="150">
                <p class="text-center">${sessionScope.role}</p>
            </div>
            <div class="col-8">
                <c:forEach var="student" items="${students}">
                    <p>firstName: ${student.firstName}</p>
                    <p>lastName: ${student.lastName}</p>
                    <p>Age: ${student.age}</p>
                    <p>Email: ${student.email}</p>
                    <p>Address: ${student.address}</p>
                    <p>Phone: ${student.phone}</p>
                </c:forEach>
            </div>
        </div>
        <div class="text-center">
            <a class="btn btn-primary" href="student.jsp">Back</a>
        </div>
        <div class="text-center">
            <a class="btn btn-info" href="update-student.jsp">Update profile</a>
        </div>
        <div class="text-center">
            <a class="btn btn-danger" href="logout">Logout</a>
        </div>
    </form>
</div>
</body>
</html>
