<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5" xmlns="http://www.w3.org/1999/html">
        <h4 class="mb-4">Разделы: </h4>
        <#if projects?exists>
            <#list projects as project>
                <a class="c-page-link" href="/projects/${project.getId()}?page=0&size=5">${project.getTitle()}</a><br/>
            </#list>
        <#else>
        </#if>
    </div>
</@c.page>