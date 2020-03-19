<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5 c-container">
    <#if topicid?exists>
    <div class="col-sm-8">
		<form method = "POST" enctype="multipart/form-data">
            <input class="form-control" name = "subject" placeholder="Тема заявки" required></input></br>
            <textarea class="form-control c-text-area" name = "description"  placeholder="Описание заявки"></textarea></br>

			<input class="btn btn-primary" type = "submit" value = "Создать заявку"/>
		</form>
    </div>
	</#if>
    <div class="col-sm-4">
        <a class="btn btn-info md-5" onclick="addFileInput()" href="#">Добавить вложение</a>
        <div id="parentFileContainerId"></div>
    </div>
    </div>
</@c.page>