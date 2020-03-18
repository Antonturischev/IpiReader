<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5 c-container">
    <#if topicid?exists>
    <div class="col-sm-8">
		<form method = "POST" enctype="multipart/form-data">
            <input name = "subject" placeholder="Тема заявки" required></input></br>
            <textarea name = "description"  placeholder="Описание заявки"></textarea></br>
            <a onclick="addFileInput()" href="#">Добавить вложение</a>
            <div id="parentFileContainerId"></div>
			<input type = "submit" value = "Создать заявку"/>
		</form>
    </div>
	</#if>
    <div class="col-sm-4">
    	<div>
    	</div>
    </div>
    </div>
</@c.page>