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
<body>
<div class="container">
    <form class="w-50 mx-auto mt-3 p-5" action="login" method="post">
        <h5 class="text-left">  <img src="https://cdn-icons-png.freepik.com/256/5352/5352118.png?semt=ais_hybrid" alt="logo" width="30" height="30"> University System</h5>
        <h4 class="text-center">Sign in</h4>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
        </div>

        <div class="alert alert-danger" role="alert">
            Invalid email or password
        </div>
        <div class="text-center mt-3">
            <button class="btn btn-success">Sign In</button>
        </div>
        <div class="text-center mt-2">
            <a href="registration.jsp" class="link">Do not have an account? Sign up</a>
        </div>
    </form>
</div>
</body>
</html>
