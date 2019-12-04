<#include "menuTemplate.ftl">
<#macro content>
<title>Sign in</title>
    <#if error??>
    <h3>Wrong login or password</h3>
    </#if>
<div id="login-form">

    <h1>Sign up</h1>
    <div id="somediv"></div>
    <fieldset>
        <form action="login" method="post" class="auth-forms">
            <div id="auth-info"></div>
            <input class="input" type="text" name="username" id="username " placeholder="login">
            <input class="input" type="password" name="password" id="password" placeholder="password">
            <label class="remember"><input type="checkbox" name="remember" value="true"> Keep me signed in</label>
            <input class="btn btn-success" type="submit" value="Sign in">
        </form>
        <h6><a href="reg">Create a new account</a></h6>
    </fieldset>
</div>
</#macro>