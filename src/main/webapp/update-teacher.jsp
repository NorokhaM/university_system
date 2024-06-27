<%@ page import="java.util.List" %>
<%@ page import="com.university.controller.TeacherController" %>
<%@ page import="com.university.entity.Teacher" %>
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%
    TeacherController controller= TeacherController.getInstance();
    List<Teacher> teacher = controller.getTeacherByEmail(request);
    request.setAttribute("teacher", teacher);
%>

<body>
<section class="hero-section"></section>
<div class="container">
    <form class="w-50 mx-auto mt-3 p-3" action="updateProfile" method="post">
        <h5 class="text-center p-3">${sessionScope.role} profile</h5>
        <div class="row">
            <div class="col-4">
                <img src="getImage" alt="logo" width="170" height="150">
                <p class="text-center">${sessionScope.role}</p>
            </div>
            <div class="col-8">
                <div class="mb-3 row">
                    <label for="firstName" class="form-label col-sm-2">firstName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="firstName" name="firstName" style="width: 50%; height: 75%;">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="lastName" class="form-label col-sm-2">lastName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="lastName" name="lastName" style="width: 50%; height: 75%;">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="age" class="form-label col-sm-2">Age</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="age" name="age" style="width: 50%; height: 75%;">
                    </div>
            </div>
                <div class="mb-3 row">
                    <label for="address" class="form-label col-sm-2">Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="address" name="address" style="width: 50%; height: 75%;">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="phone" class="form-label col-sm-2">Phone</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phone" name="phone" style="width: 50%; height: 75%;">
                    </div>
                </div>
                <div class="dropdown mb-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        Choose subject
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item">Mathematics</a></li>
                        <li><a class="dropdown-item">History</a></li>
                        <li><a class="dropdown-item">Computer Science</a></li>
                        <li><a class="dropdown-item">Physics</a></li>
                        <li><a class="dropdown-item">Chemistry</a></li>
                        <li><a class="dropdown-item">Electrical Engineering</a></li>
                        <li><a class="dropdown-item">Mechanical Engineering</a></li>
                        <li><a class="dropdown-item">Data Structure</a></li>
                        <li><a class="dropdown-item">Machine Learning</a></li>
                        <li><a class="dropdown-item">Software Engineering</a></li>
                        <li><a class="dropdown-item">Networking</a></li>
                        <li><a class="dropdown-item">Database Systems</a></li>
                    </ul>
                </div>
                <input type="hidden" id="selectedSubject" name="subject">
                <script>
                    $(document).ready(function(){
                        $('.dropdown-menu a').click(function(){
                            $('#dropdownMenuButton1').text($(this).text());
                            $('#selectedSubject').val($(this).text());
                        });
                    });
                </script>
            </div>
        </div>
        <div class="text-center mt-3">
            <button class="btn btn-success">Save settings</button>
        </div>
    </form>
</div>
</body>
</html>
