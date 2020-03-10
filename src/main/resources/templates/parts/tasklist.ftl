<#macro tasklist page>
    <#if page??>
        <#list page.content as task>
            <div id="accordion${task.getId()?c}">
                <div class="card">
                    <div class="card-header" id="headingOne${task.getId()?c}">
                        	<div class="c-card-header-subject">
	                            <a class="btn btn-link collapsed c-task-item-a" data-toggle="collapse" data-target="#collapse${task.getId()?c}" aria-expanded="true" aria-controls="collapse${task.getId()?c}">
	                                <b>${task.getId()?c} ${task.getAuthor()}</b> 
	                                <p class="c-item-subscription">${task.getSubject()}</p>
	                            </a>
                            </div>
                            <div class="c-card-header-status">
                            	${task.getDateAdded()}
                            	 <#if task.getStatus()="Completed"><i style="color:green" class="oi oi-circle-check"></i>
	                             <#else>
	                              	<#if task.getStatus()="Canceled"><i style="color:red" class="oi oi-circle-x"></i>
	                              	<#else><i style="color:blue" class="oi oi-arrow-circle-right"></i>
	                               	</#if>
	                             </#if>
	                        </div>
                    </div>
                    <div id="collapse${task.getId()?c}" class="collapse" aria-labelledby="headingOne${task.getId()?c}" data-parent="#accordion${task.getId()?c}">
                        <div class="card-body c-card-body">
                        	<#if task.getDescription()?exists>
                            	<p>${task.getDescription()}</p> <a href="/task/${task.getId()?c}">подробнее...</a>
                            <#else>
                            	<a href="/task/${task.getId()?c}">подробнее...</a>
                            </#if>	
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        <p>Заявки отсутствуют</p>
    </#if>
</#macro>