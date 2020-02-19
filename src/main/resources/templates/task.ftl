<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5">
    <#if task??>
        <h4>Номер задачи: ${task.getId()}</h4>
        <p>Тема: ${task.getSubject()}</p>
        <p>Описание: ${task.getDescription().getText()}</p>
    <#else>
    	<h4>Задача не найдена</h4>    
    </#if>    
    </div>
</@c.page>