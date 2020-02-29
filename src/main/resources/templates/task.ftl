<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5 c-container">
    <#if task??>
    <div class="col-sm-8">
        <h6><b>Номер задачи:</b> ${task.getId()}</h6>
    	<p><b>Раздел:</b>
    		<#list task.getTopics() as topic>
    			\ <a class="nowrap badge badge-secondary" href="/topic/${topic.getId()}">${topic.getTitle()}</a>
    		</#list>
    	</p>
    	<#if task.getParent()?has_content>
        	<p><b>Родительская задача:</b> <a class="c-page-link" href="/task/${task.getParent().getId()}">${task.getParent().getSubject()}</a></p>
    	</#if>    
    	<#if task.getChildren()?has_content>
    		<p><b>Подзадачи:</b>
    		<#list task.getChildren() as child>
    			<a class="c-page-link" href="/task/${child.getId()}">${child.getSubject()}</a><br/>
    		</#list>
    		</p>
    	</#if>         
        <p><b>Тема:</b> ${task.getSubject()}</p>
        <p><b>Описание:</b> ${task.getDescription()}</p>

        <span class="delimiter"></span>
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
    	<p><b>Автор:</b> ${task.getAuthor()}</p>
    		<#if task.getStatus()="completed"><p><b>Статус:</b> <span  class="badge badge-pill badge-success">${task.getStatus()}</span></p>
	        <#else>
	            <#if task.getStatus()="canceled"><p><b>Статус:</b> <span class="badge badge-pill badge-danger">${task.getStatus()}</span></p>
	            <#else><p><b>Статус:</b> <span class="badge badge-pill badge-primary">${task.getStatus()}</span></p>
	            </#if>
	        </#if>
        <#if task.getDateAdded()??>
        	<p><b>Дата создания:</b> ${task.getDateAdded()}</p>
        </#if>
        <#if task.getDateChanged()??>	
        	<p><b>Дата изменения:</b> ${task.getDateChanged()}</p>
        </#if>
        <#if task.getDateClosed()??>
        	<p><b>Дата изменения:</b> ${task.getDateClosed()}</p>
        </#if>
        <p><b>Приоритет:</b> ${task.getPriority()}</p>
        <#if task.getProject()??>
        	<p><b>Проект:</b> ${task.getProject().getTitle()}</p>
        </#if> 
    	<p> <b>Ответственный:</b>
	    	<#if task.getResponsible()??>
	 			${task.getResponsible()}
	    	</#if>
	    	<#if task.getResponsibleGroup()??>
	 			${task.getResponsibleGroup()}
	    	</#if>
    	</p>
    	<div>
	    	<#if task.getSpectratorsGroup()??>
	    	<span><b>Группы наблюдателей:</b></span>
	    		<#list task.getSpectratorsGroup() as group>
	    			<span class="nowrap">${group}</span>
	    		</#list>
	    	</#if>
	    	<#if task.getSpectrators()??>
	    	<br/><b>Наблюдатели:</b>
	    		<#list task.getSpectrators() as spectrator>
	    			<span class="nowrap">${spectrator}</span>
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