<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="css/simplePagination.css"/>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
    <title>BookStore</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="../js/addToCart.js"></script>
    <script src="../js/countCharacters.js"></script>
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
<div class="container" id="itemContainer">
    <div class="row">
        <div class="col-md-5">
            <img src="/images/${item.picture}" width="400" height="300" alt="${item.picture}">
            <h1>${item.price} </h1><br></div>
        <div class="col-md-6">
            <h2>${item.name}</h2><br>
            <h5>${item.description}</h5><br>
        </div>


        <form id="addToCartForm" action="/bucket" method="post">
            <div class="col-md-1">
                <input type="hidden" name="id" value="${item.id}"/>
                <input type="submit" name="submit" class="btn btn-primary" id="add" value="Add to cart"
                       onclick="this.value='Added'"/></div>
        </form>
    </div>
</div>
<div class="col-md-6">
    <c:choose>
        <c:when test="${sessionScope.session_uname != null}">
            <form action="/addComment" method="post">
                <input type="textarea" class="form-control" rows="3" id="comment1" name="comment" oninput="ready()"
                       placeholder="Your review" required>
                <input type="hidden" value="${sessionScope.session_uname}" name="username">
                <input type="hidden" value="${item.id}" name="item_id">
                <div class="counter">Free symbols: <span id="counter"></span></div>
                <input type="submit" class="btn btn-info" id="Apply" value="Add">
            </form>
        </c:when>
        <c:otherwise>
            Sign in to add a comment
        </c:otherwise>
    </c:choose>
</div>
<div class="col-md-12">
    <h3>Customer reviews:</h3>
    <c:choose>
        <c:when test="${not empty comments}">
            <c:forEach items="${comments}" var="comment">
                <div class="comment">
                    <h3>
                            ${comment.login}
                            ${comment.date}
                            ${comment.text}
                    </h3>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h3>No comments yet</h3>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>