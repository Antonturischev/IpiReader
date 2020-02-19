<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5">
    <#if task??>
        <h4>Номер задачи: ${task.getId()}</h4>
        <p>Автор: ${task.getAuthor()}</p>
        <p>Тема: ${task.getSubject()}</p>
        <p>Описание: ${task.getDescription()}</p>
        <p>Статус: ${task.getStatus()}</p>
        <#if task.getProject()??>
        	<p>Проект: ${task.getProject().getTitle()}</p>
        </#if>
        <p>Раздел: </p>
        <#if task.getParent()??>
        	<p>Родительская задача: <a href="/task/${task.getParent().getId()}">${task.getParent().getSubject()}</a></p>
    	</#if>    
    	
    <#else>
    	<h4>Задача не найдена</h4>    
    </#if>    
    </div>
</@c.page>