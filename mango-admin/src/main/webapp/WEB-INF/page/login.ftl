<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href="/resources/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="/resources/js/jquery.min.js"></script>
    <!----webfonts--->
    <link href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
    <!---//webfonts--->
    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/js/bootstrap.min.js"></script>
    <script>
        function loginSubmit() {
            return true;
        }
    </script>
</head>
<body id="login">
<div class="login-logo">

    <a href="index.html"> <img src="/resources/images/logo.png" alt=""/>
    </a>
</div>
<h2 class="form-heading">login</h2>

<div class="app-cam">
    <form id="loginForm" name="loginForm" action="/mango/login/post" method="post" onsubmit="loginSubmit()">
        <input name="userName" type="text" class="text" value="E-mail address" onfocus="this.value = '';"
               onblur="if (this.value == '') {this.value = 'E-mail address';}">
        <input name="password" type="password" value="Password" onfocus="this.value = '';"
               onblur="if (this.value == '') {this.value = 'Password';}">

        <div class="submit"><input type="submit" onclick="loginSubmit()" value="Login"></div>
    </form>
</div>
<div class="copy_layout login">
    <p>Copyright &copy; 2015.Kinglong All rights reserved.</p>
</div>
</body>
</html>
