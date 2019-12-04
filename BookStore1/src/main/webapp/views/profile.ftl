<#include "menuTemplate.ftl">
<#macro content>
<title>Profile</title>

    <#if Session.session_uname??>
    <h2>Hello, ${Session.session_uname}!</h2>
    </#if>

    <#if update??>
    <h3>Password, login and email were changed</h3>
    </#if>
    <#if reg??>
    ${reg}
    </#if>
<div class="col-md-4">
    <form class="form-signin" action="profile" method="post">
        Change login<br>
        <input type="text" class="input" name="newUsername" placeholder="New login" required class="form-control"><br><br>
        Change password<br>
        <input type="password" class="input" name="newPassword" placeholder="New password" required class="form-control"><br><br>
        Change email<br>
        <input type="text" class="input" name="newEmail" placeholder="New email" required class="form-control"><br><br>
        <button type="submit" class="btn btn-primary" id="add">Change</button>
    </form>
</div>
</#macro>