<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5 c-container">
    <#if task?exists>
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
        <p><b>Описание:</b> <#if task.getDescription()?exists>${task.getDescription()}</#if></p>
        <#if task.getAttachments()?has_content>
            <#list task.getAttachments() as attach>
        		<a href="${task.getAttachmentsPath()}${attach.getFilename()}" download>${attach.getFilename()}&nbsp;</a>
        	</#list>
        </#if>
        <span class="delimiter"></span>
        <#if task.getComments()?has_content>
			<#list task.getComments() as comment>
				<blockquote class="blockquote">
					<p class="mb-0">${comment.getText()}</p>
					<footer class="blockquote-footer">${comment.getAuthor()} <cite title="Source Title">${comment.getDateAdded()}</cite></footer>
				</blockquote>
	    	</#list>    	
    	</#if>
    	<div>
            <form id="TaskForm" enctype="multipart/form-data" method="POST">
    	        <textarea class="form-control c-text-area" name = "comment" placeholder="Добавьте свой комментарий"> </textarea> <br/>
				<div class="task-buttons-container">
					<div style="width:104px">
						<input class="btn btn-primary" type = "submit" value = "Сохранить"/>
					</div>
					<div style="width:120px">
							<a  style="float:right" class="btn btn-info md-5" onclick="addFileInput()" href="#">Добавить вложение</a>
					</div>
				</div>
				<div style = "width:100%">
					<div id="parentFileContainerId"></div>
				</div>
    	    </form>
    	</div>
    </div>
    <div class="col-sm-4">
    	<p><b>Автор:</b> ${task.getAuthor()}</p>
<!--Статус-------------------------------------------------------------------------------------------------------------------------------------------->
    	<p><b>Статус:</b>
        <select name = "statusid" form = "TaskForm" class="badge badge-pill">
            <option value="7" <#if task.getStatus()="Completed">selected</#if>>Выполнена</option>
            <option value="5" <#if task.getStatus()="Canceled">selected</#if>>Отклонена</option>
            <option value="1" <#if task.getStatus()="New">selected</#if>>Новая</option>
            <option value="9" <#if task.getStatus()="Blocked">selected</#if>>Блокирована</option>
            <option value="3" <#if task.getStatus()="In progress">selected</#if>>На исполнении</option>
            <option value="4" <#if task.getStatus()="Postponed">selected</#if>>Отложена</option>
            <option value="6" <#if task.getStatus()="Resumed">selected</#if>>Возобновлена</option>
            <option value="8" <#if task.getStatus()="Reconciliation">selected</#if>>На согласовании</option>
            <option value="2" <#if task.getStatus()="Verified">selected</#if>>На проверке</option>
        </select>
        <#if task.getDateAdded()??>
        	<p><b>Дата создания:</b> ${task.getDateAdded()}</p>
        </#if>
        <#if task.getDateChanged()??>	
        	<p><b>Дата изменения:</b> ${task.getDateChanged()}</p>
        </#if>
        <#if task.getDateClosed()??>
        	<p><b>Дата закрытия:</b> ${task.getDateClosed()}</p>
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
	    	<#if task.getGroupFromTopic()?has_content>
	    	<span><b>Группы из раздела:</b></span>
	    		<#list task.getGroupFromTopic() as group>
	    			<span class="nowrap">${group}</span>
	    		</#list>
	    	</#if>    	
	    	<#if task.getSpectratorsGroup()?has_content>
	    	<span><b>Группы наблюдателей:</b></span>
	    		<#list task.getSpectratorsGroup() as group>
	    			<span class="nowrap">${group}</span>
	    		</#list>
	    	</#if>
	    	<#if task.getUsersFromTopic()?has_content>
	    	<br/><b>Наблюдатели из раздела:</b>
	    		<#list task.getUsersFromTopic() as user>
	    			<span class="nowrap">${user}</span>
	    		</#list>
	    	</#if>
	    	<#if task.getSpectrators()?has_content>
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