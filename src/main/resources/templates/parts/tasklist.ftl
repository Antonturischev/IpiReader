<#macro tasklist page>
    <#if page??>
        <#list page.content as task>
            <div id="accordion${task.getId()}">
                <div class="card">
                    <div class="card-header" id="headingOne${task.getId()}">
                        	<div class="c-card-header-subject">
	                            <a class="btn btn-link collapsed c-task-item-a" data-toggle="collapse" data-target="#collapse${task.getId()}" aria-expanded="true" aria-controls="collapse${task.getId()}">
	                                <b>${task.getId()} ${task.getAuthor()}</b> 
	                                <p class="c-item-subscription">${task.getSubject()}</p>

	                            </a>
                            </div>
                            <div class="c-card-header-status">
                            	${task.getDateAdded()}
                            	 <#if task.getStatus()="completed"><i style="color:green" class="oi oi-circle-check"></i>
	                             <#else>
	                              	<#if task.getStatus()="canceled"><i style="color:red" class="oi oi-circle-x"></i>
	                              	<#else><i style="color:blue" class="oi oi-arrow-circle-right"></i>
	                               	</#if>
	                             </#if></div>
                    </div>
                    <div id="collapse${task.getId()}" class="collapse" aria-labelledby="headingOne${task.getId()}" data-parent="#accordion${task.getId()}">
                        <div class="card-body c-card-body">
                            <p>${task.getDescription()}</p> <a href="/task/${task.getId()}">подробнее...</a>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        <p>У вас нет активных заявок</p>
    </#if>
</#macro>