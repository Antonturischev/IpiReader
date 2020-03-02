<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">

    <div class="container mt-5">
        <#if selectedParams?exists>
        <select class="custom-select search-select" id="selectBox" onchange="addField();">
            <option id="" value=""></option>
            <#list selectedParams as param>
                <option id="${param.parameter}" value="${param.parameter}" ${param.isSelected}>${param.value}</option>
            </#list>
        </select>
        </#if>
        <form method="get" action="/search">
            <div class="parentId" id="parentId">
                <#list selectedParams as param>
                    <#if param.isSelected!="">
                        <div><input class="input selected-params-input" name="${param.parameter}" value="${param.selectedValue}" />
                        <a style="color:red;"  href="#" selectcount="${param.index}" class="addInput" onclick="deleteField(this)">x</a></div>
                    </#if>
                </#list>
            </div>
            <button type="submit" class="btn btn-primary mt-1">Найти</button>
        </form>
    </div>
    <div>
	    <div class="container mt-5">
            <h4 class="mb-4">Заявки: </h4>
	        <#if page?exists>
	            <@p.pager url page dlm/>
	            <@t.tasklist page/>
	        <#else>
                <div class="alert alert-danger" role="alert">
                    Заявок не найдено
                </div>
	        </#if>
	    </div>
    </div>
</@c.page>
