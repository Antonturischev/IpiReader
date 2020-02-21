<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">

    <div class="container mt-5">
        <h2>Расширенный поиск</h2>
        <select class="custom-select" id="selectBox" onchange="addField();">
            <option class="" value="">Критерий поиска</option>
            <option class="" value="number">Номер</option>
            <option class="" value="author">Автор</option>
            <option class="" value="theme">Тема</option>
            <option class="" value="description">Описание</option>
            <option class="" value="comment">Комментарий</option>
        </select>
        <form method="get" action="/search">
            <div class="parentId" id="parentId">
                <#if selectedParams?exists>
                    <#list selectedParams as key, param>
                        <div><input class="input" name="${key}" value="${param}" /> <a style="color:red;" href="#" class="addInput" onclick="deleteField(this)">x</a></div>
                    </#list>
                 </#if>
            </div>
            <button type="submit" class="btn btn-primary">Найти</button>
        </form>
    </div>
</@c.page>
