<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5 c-container">
    <#if task??>
    <div class="col-sm-8">
        <h4>Номер задачи: ${task.getId()}</h4>
    	<p>Раздел: 
    		<#list task.getTopics() as topic>
    			/<a href="/topic/${topic.getId()}">${topic.getTitle()}</a>
    		</#list>
    	</p>
    	<#if task.getParent()?has_content>
        	<p>Родительская задача: <a href="/task/${task.getParent().getId()}">${task.getParent().getSubject()}</a></p>
    	</#if>    
    	<#if task.getChildren()?has_content>
    		<p>Подзадачи: 
    		<#list task.getChildren() as child>
    			<a href="/task/${child.getId()}">${child.getSubject()}</a><br/>
    		</#list>
    		</p>
    	</#if>         
        <p>Тема: ${task.getSubject()}</p>
        <p>Описание: ${task.getDescription()}</p>
        <#if task.getCommenst()?has_content>
			<#list task.getCommenst() as comment>
				<blockquote class="blockquote">
					<p class="mb-0">${comment.getText()}</p>
					<footer class="blockquote-footer">${comment.getAuthor()} <cite title="Source Title">${comment.getDateAdded()}</cite></footer>
				</blockquote>
	    	</#list>    	
    	</#if>
    </div>
    <div class="col-sm-4">
    	<p>Автор: ${task.getAuthor()}</p>  	
    		<#if task.getStatus()="completed"><p>Статус: <span  class="badge badge-pill badge-success">${task.getStatus()}</span></p>
	        <#else>
	            <#if task.getStatus()="canceled"><p>Статус: <span class="badge badge-pill badge-danger">${task.getStatus()}</span></p>
	            <#else><p>Статус: <span class="badge badge-pill badge-primary">${task.getStatus()}</span></p>
	            </#if>
	        </#if>
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
    </div>



   


    <#else>
	    <div class="alert alert-danger" role="alert">
	  		<span>Задача не найдена</span>
		</div>	    
    </#if>    
    </div>
</@c.page>