<#import "parts/common.ftl" as c>
<#import "parts/searchpager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">

    <div class="container mt-5">
        <h2>Расширенный поиск</h2>
        <#if selectedParams?exists>
        <select class="custom-select" id="selectBox" onchange="addField();">
            <option id="" value="">Критерий поиска</option>
            <#list selectedParams as param>
                <option id="${param.parameter}" value="${param.parameter}" ${param.isSelected}>${param.value}</option>
            </#list>
        </select>
        </#if>
        <form method="get" action="/search">
            <div class="parentId" id="parentId">
                <#list selectedParams as param>
                    <#if param.isSelected!="">
                        <div><input class="input" name="${param.parameter}" value="${param.selectedValue}" />
                        <a style="color:red;"  href="#" selectcount="${param.index}" class="addInput" onclick="deleteField(this)">x</a></div>
                    </#if>
                </#list>
            </div>
            <button type="submit" class="btn btn-primary">Найти</button>
        </form>
    </div>
    <div>
        <#if page?exists>
            <@p.pager url page/>
        </#if>
        <div class="container mt-5">
            <h2>Список заявок</h2>
            <@t.tasklist page/>
        </div>
    </div>
</@c.page>
