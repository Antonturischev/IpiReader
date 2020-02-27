<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5">
    <#if page?exists>
       <@p.pager url page/>
       		<h2>Заявки</h2>
       <@t.tasklist page/>
       <#else>
       		<h2>Заявки отсутствуют</h2>
    </#if>
 	</div>
</@c.page>