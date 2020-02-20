<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <@p.pager url page/>
    <div class="container mt-5">
        <h2>Список заявок</h2>
        <#if page??>
            <#list page.content as task>
            <div id="accordion${task.getId()}">
                <div class="card">
                    <div class="card-header" id="headingOne${task.getId()}">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse${task.getId()}" aria-expanded="true" aria-controls="collapse${task.getId()}">
                                ${task.getId()} <span>${task.getSubject()}</span>
                            </button>
                        </h5>
                    </div>
                    <div id="collapse${task.getId()}" class="collapse" aria-labelledby="headingOne${task.getId()}" data-parent="#accordion${task.getId()}">
                        <div class="card-body">
                            <p>${task.getDescription()}</p> <a href="/task/${task.getId()}">подробнее...</a>
                        </div>
                    </div>
             </div>
            </div>
            </#list>
            <#else>
            <p>У вас нет активных заявок</p>
        </#if>
    </div>
</@c.page>