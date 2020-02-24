<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<#import "parts/topictree.ftl" as tt>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <@tt.topictree/>
    <#if page?exists>
        <@p.pager url page/>
    <div class="container mt-5">
        <h2>Заявки из раздела ${topic!''}</h2>
        <@t.tasklist page/>
    </div>
    </#if>
</@c.page>