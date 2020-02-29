<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <#if page?exists>
    <div class="container mt-5">
        <h4 class="mb-4">Заявки из раздела ${topic!''}</h4>
        <@p.pager url page dlm/>
        <@t.tasklist page/>
    </div>
    </#if>
</@c.page>