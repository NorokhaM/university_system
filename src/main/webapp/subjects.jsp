<%@ page import="java.util.List" %>
<%@ page import="com.university.model.SubjectModel" %>
<%@ page import="com.university.entity.Subject" %>
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
    SubjectModel model=SubjectModel.getInstance();
    List<Subject> subjects = model.getAllSubjects();
    request.setAttribute("subjects", subjects);
    request.setAttribute("message", session.getAttribute("message"));
%>

<body>
<section class="hero-section"></section>
<div class="container">
    <form class="w-50 mx-auto mt-3 p-3">
        <table class="table table-sm">
            <thead>
            <tr>
                <th scope="col" class="fs-6">#</th>
                <th scope="col" class="fs-6">Name</th>
                <th scope="col" class="fs-6">Description</th>
                <th scope="col" class="fs-6">Teacher</th>
                <th scope="col" class="fs-6">Sign up</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subjects}" var="subject">
                <c:set var="teacherName" value="${subject.firstNameTeacher} ${subject.lastNameTeacher}" />
                <tr>
                    <td>
                        <c:out value="${i=i+1}"/>
                    </td>
                    <td class="fs-6">${subject.name}</td>
                    <td class="fs-6">${subject.description}</td>
                    <td class="fs-6">${teacherName}</td>
                    <td>
                        <a href="subject-sign-up?subjectName=${subject.name}"><i class="bi bi-check-square"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty message}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${message}"/>
                <%
                    session.removeAttribute("message");
                    request.removeAttribute("message");
                %>
            </div>
        </c:if>
        <div class="text-center">
            <a class="btn btn-primary" href="student.jsp">Back</a>
        </div>
    </form>
</div>
</body>
</html>
