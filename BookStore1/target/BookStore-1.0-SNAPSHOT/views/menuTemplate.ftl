<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <link href="css/logReg.css" rel="stylesheet" type="text/css"/>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div id="top_bar_black">
    <div id="logo_container">
        <a href="/">
            <div id="logo_image">

            </div></a>
        <div id="nav_block">
            <a href="/" class="selected">
                <div class="nav_button">Products</div>
            </a>
        <#if Session.session_uname??>

            <a href="/bucket">
                <div class="nav_button">Cart</div>
            </a>
            <a href="/profile">
                <div class="nav_button">Profile</div>
            </a>
            <a href="/logout">
                <div class="nav_button">Logout</div>
            </a>
            <#if Session.session_uname == "admin">
                <a href="/AddItem">
                    <div class="nav_button">Add Product</div>
                </a>
            </#if>
        <#else>

            <a href="/reg">
                <div class="nav_button">Sign up</div>
            </a>
            <a href="/login">
                <div class="nav_button">Sign in</div>
            </a>

        </#if>

        </div>
    </div>
</div>
<@content></@content>
</body>
</html>