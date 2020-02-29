<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5">
        <#if page?exists>
            <h4 class="mb-4">Заявки по разделу: </h4>
            <@p.pager url page dlm/>
            <@t.tasklist page/>
        <#else>
            <div class="alert alert-danger" role="alert">
                Зяавки отсутствуют
            </div>
        </#if>
    </div>
</@c.page>