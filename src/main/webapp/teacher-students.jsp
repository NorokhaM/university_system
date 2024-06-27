<%@ page import="java.util.List" %>
<%@ page import="com.university.controller.StudentController" %>
<%@ page import="com.university.entity.Student" %>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
</head>
<%
    StudentController studentController = new StudentController();
    List<Student> students = studentController.getStudentsByTeacherEmail(session.getAttribute("email").toString());
    request.setAttribute("students", students);
    request.setAttribute("studentEmail", students.get(0).getEmail());
%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<body>
<section class="hero-section"></section>
<div class="container">
    <form class="w-50 mx-auto mt-3 p-3">
        <table class="table table-sm">
            <thead>
            <tr>
                <th scope="col" class="fs-6">#</th>
                <th scope="col" class="fs-6">Student</th>
                <th scope="col" class="fs-6">Phone</th>
                <th scope="col" class="fs-6">Address</th>
                <th scope="col" class="fs-6">Set mark</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${students}" var="students">
                <c:set var="studentName" value="${students.firstName} ${students.lastName}" />
                <tr>
                    <td>
                        <c:out value="${i=i+1}"/>
                    </td>
                    <td class="fs-6">${studentName}</td>
                    <td class="fs-6">${students.phone}</td>
                    <td class="fs-6">${students.address}</td>
                    <td class="fs-6">
                        <a data-bs-toggle="modal" data-bs-target="#exampleModal">
                            <i class="bi bi-arrow-right-square"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${sessionScope.error}"/>
                <%
                    session.removeAttribute("error");
                %>
            </div>
        </c:if>
        <div class="text-center">
            <a class="btn btn-primary" href="teacher.jsp">Back</a>
        </div>
    </form>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Set mark</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="set-mark" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="mark" class="form-label">Enter your mark (1-10)</label>
                        <input type="number" class="form-control" id="mark" name="mark">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <input type="hidden" name="email" value="${studentEmail}">
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
