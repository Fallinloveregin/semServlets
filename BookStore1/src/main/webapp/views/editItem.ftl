<#include "menuTemplate.ftl">
<#macro content>

<title>Edit product</title>

    <#if Message??>
    <h3>${Message}</h3>
    </#if>

    <#if Request.id??>
    <h4 id="center">Edit product:</h4>
    <br/>
    <div class="col-md-8">
        <div class="col-md-5">
            <form action="editItem" method="post" enctype="multipart/form-data">
                <br>
                <input type="hidden" name="id" value="${Request.id}">
                <input type="text" class="input" name="name" placeholder="Name" required><br><br>
                <input type="text" class="input" name="description" placeholder="Description" required><br><br>
                <input type="number" class="input" name="price" placeholder="Price" required><br><br>
                <input type="text" class="input" name="genre" placeholder="Genre" required><br><br>
                <input type="file" class="btn" name="image" size="50" required><br><br>
                <input type="submit" class="btn btn-primary" id="add" value="Edit"/>
            </form>
        </div>
    </div>
    </#if>
</#macro>