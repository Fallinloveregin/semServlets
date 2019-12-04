<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>BookStore</title>
</head>
<body>
<div id="top_bar_black">
    <div id="logo_container">
        <a href="/">
            <div id="logo_image">

            </div>
        </a>
        <div id="nav_block">
            <a href="/" class="selected">
                <div class="nav_button">Products</div>
            </a>
            <a href="/bucket">
                <div class="nav_button">Cart</div>
            </a>
            <c:if test="${sessionScope.session_uname == null}">
                <a href="/reg">
                    <div class="nav_button">Sign up</div>
                </a>
                <a href="/login">
                    <div class="nav_button">Sign in</div>
                </a>
            </c:if>
            <c:if test="${sessionScope.session_uname != null}">
                <a href="/profile">
                    <div class="nav_button">Profile</div>
                </a>
                <a href="/logout">
                    <div class="nav_button">Logout</div>
                </a>
                <c:if test="${sessionScope.session_uname == 'admin'}">
                    <a href="/AddItem">
                        <div class="nav_button">Add Product</div>
                    </a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${not empty cart}">
        <div class="container">
            <div class="row">
                <c:forEach items="${cart}" var="item">
                    <div class="col-md-4">
                        <form action="/item" method="get">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <a><input type="submit" class="itemName" value="${item.name}"></a>
                        </form>
                        <br>
                        <h4>${item.price} <br></h4>
                        <img src=/images/${item.picture} width="100" height="100" alt="${item.picture}">
                        <form action="/deleteCart" method="post">
                            <button type="submit" name="id" class="btn btn-danger" value="${item.id}">Delete</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <center>
            <button type="submit" class="btn btn-success" onclick="alert('Cart subtotal: ${sessionScope.cost} ')">Checkout
            </button>
        </center>
    </c:when>
    <c:otherwise>
        <h3>No products yet</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
