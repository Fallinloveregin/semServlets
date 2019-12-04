<#include "menuTemplate.ftl">
<#macro content>
<title>BookStore</title>


        <h4 id="center">Add product:</h4>
<div class="col-md-8">

    <div class="col-md-5">
        <br/>
        <form action="AddItem" method="post" enctype="multipart/form-data">
            <br>
            <input type="text" class="input" name="name" placeholder="Name" required><br><br>
            <input type="text" class="input" name="description" placeholder="Description" required><br><br>
            <input type="number" class="input" name="price" placeholder="Price" required><br><br>
            <input type="text" class="input" name="genre" placeholder="Genre" required><br><br>
            <input type="file" class="btn" name="image" size="50" required><br><br>
            <input type="submit" class="btn btn-primary" id="add" value="Add"/>

        </form>
    </div>
    <div class="col-md-2">
        <h2><#if Message??>
    ${Message}
    </#if>
        </h2>
    </div>
</div>
</#macro>