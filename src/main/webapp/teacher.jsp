<!doctype html>
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
</head>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<body>
<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <img src="https://cdn-icons-png.freepik.com/256/5352/5352118.png?semt=ais_hybrid" alt="logo" width="50" height="50">
        <div class="container-fluid">
            <a class="navbar-brand">University System</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse align" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2 active" aria-current="page" href="student.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2" aria-current="page" href="teacher-students.jsp">Your students</a>
                    </li>
                </ul>
            </div>
            <form action="profile" method="get">
                <button type="submit" class="btn btn-primary">Profile</button>
            </form>
        </div>
    </nav>
</header>
<section class="hero-section"></section>
<div class="container">
    <h1 class="text-center">Welcome to the university system.</h1>
    <p class="text-center fst-italic">The University System represents the interaction between student and teachers.</p>

</div>
</body>
</html>