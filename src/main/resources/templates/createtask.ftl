<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5 c-container">
    <#if topicid?exists>
    <div class="col-sm-8">
		<form method = "POST">
            <input name = "subject" placeholder="Тема заявки"></input></br>
            <textarea name = "description"  placeholder="Описание заявки"></textarea></br>
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