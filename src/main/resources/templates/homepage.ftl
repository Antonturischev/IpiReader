<#import "parts/common.ftl" as c>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5">
        <h2>Список заявок</h2>
        <#if tasks??>
            <#list tasks as task>
            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${task.getId()}" aria-expanded="true" aria-controls="collapse${task.getId()}">
                                ${task.getId()} <span>${task.getSubject()}</span>
                            </button>
                        </h5>
                    </div>

                    <div id="collapse${task.getId()}" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <p>${task.getDescription().text}</p>
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