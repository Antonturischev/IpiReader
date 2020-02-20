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
        <#if task.getDateAdded()??>
        	<p>Дата создания: ${task.getDateAdded()}</p>
        </#if>
        <#if task.getDateChanged()??>	
        	<p>Дата изменения: ${task.getDateChanged()}</p>
        </#if>
        <#if task.getDateClosed()??>
        	<p>Дата изменения: ${task.getDateClosed()}</p>
        </#if>
        <p>Приоритет: ${task.getPriority()}</p>
        <#if task.getProject()??>
        	<p>Проект: ${task.getProject().getTitle()}</p>
        </#if>
        
        <#if task.getParent()??>
        	<p>Родительская задача: <a href="/task/${task.getParent().getId()}">${task.getParent().getSubject()}</a></p>
    	</#if>    
    	<#if task.getChildren()??>
    		<p>Подзадачи: 
    		<#list task.getChildren() as child>
    			<a href="/task/${child.getId()}">${child.getSubject()}</a><br/>
    		</#list>
    		</p>
    	</#if> 
    	<p>Раздел: 
    		<#list task.getTopics() as topic>
    			/<a href="#">${topic.getTitle()}</a>
    		</#list>
    	</p>   
    	<p> Ответственный: 
	    	<#if task.getResponsible()??>
	 			${task.getResponsible()}
	    	</#if>
	    	<#if task.getResponsibleGroup()??>
	 			${task.getResponsibleGroup()}
	    	</#if>
    	</p>
    	<div>
	    	<#if task.getSpectratorsGroup()??>
	    	<span>Группы наблюдателей:</span>
	    		<#list task.getSpectratorsGroup() as group>
	    			<span>${group}</span>
	    		</#list>
	    	</#if>
	    	<#if task.getSpectrators()??>
	    	<br/>Наблюдатели:
	    		<#list task.getSpectrators() as spectrator>
	    			<span>${spectrator}</span>
	    		</#list>
	    	</#if>
    	</div>
    	<#if task.getCommenst()??>
			<#list task.getCommenst() as comment>
	    		<span>${comment.getDateAdded()}</span><span>${comment.getAuthor()}</span><span>${comment.getText()}</span> <br/>
	    	</#list>    	
    	</#if>
    <#else>
	    <div class="alert alert-danger" role="alert">
	  		<span>Задача не найдена</span>
		</div>	    
    </#if>    
    </div>
</@c.page>