<#include "menuTemplate.ftl">
<#macro content>

<head><title>Sign up</title></head>
<body>
<#if errorPswFormat??>
<h3>Invalid password format (The password must contain at least 6 characters)</h3>
</#if>

<#if errorEmailFormat??>
<h3>Invalid email format</h3>
</#if>
<#if errorEmailExist??>
<h3>This email already exists</h3>
</#if>

<#if errorLoginExist??>
<h3>This login already exists</h3>
</#if>
<div id="login-form">
    <h1>Sign up</h1>
    <fieldset>
        <form action="reg" method="post">

            <div class="form-group">
                <input class="input" type="text" name="login" placeholder="login">
                <input class="input" type="password" name="password" placeholder="password">
                <input class="input" type="text" name="email" placeholder="email">
                <input class="btn btn-success" type="submit" value="Sign up">
        </form>
    </fieldset>
</div>
</body>
</#macro>
