<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <link href="css/simplePagination.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>BookStore</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="../js/addToCart.js"></script>
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
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <form action="/" method="get">
                <select name="genre" class="form-control" id="margin">
                    <option value="%" selected>Select a genre</option>
                    <option value="fantasy">Fantasy</option>
                    <option value="comedy">Comedy</option>
                    <option value="detective">Detective</option>
                    <option value="drama">Drama</option>
                </select>
                <input type="submit" class="btn btn-info" id="Apply" value="Apply">
            </form>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${not empty products}">
        <div class="container">
            <div class="row">
                <c:forEach items="${products}" var="item">
                    <div class="col-md-3" id="item">
                        <form action="/item" method="get">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <a><input type="submit" class="itemName" value="${item.name}"></a>
                        </form>
                        <div class="cost"><h4>${item.price}</h4></div>
                        <br>
                        <img src="/images/${item.picture}" width="200" height="200" alt="${item.picture}" class="image">
                        <c:if test="${sessionScope.session_uname == 'admin'}">
                            <form action="/delete" method="post">
                                <button type="submit" class="btn btn-danger" name="id" value="${item.id}"
                                        onclick="return confirm('Are you sure you want to delete?')">Delete
                                </button>
                            </form>
                            <a href="/editItem?id=${item.id}">
                                <div>Edit Product</div>
                            </a>
                        </c:if>

                        <form id="addToCartForm" action="/bucket" method="post">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <input type="submit" name="submit" class="btn btn-primary" id="add" value="Add to cart"
                                   onclick="this.value='Added'"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>



        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <table class="dark-theme">
                        <tr>
                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="/?page=${i}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h3>No products yet</h3>
    </c:otherwise>
</c:choose>

</body>
</html>