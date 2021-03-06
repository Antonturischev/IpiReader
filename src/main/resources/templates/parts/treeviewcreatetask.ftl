<#macro treeViewCreateTask listItems>
    <#list listItems as item>
        <#if item.children?has_content>
            <li><span class="caret"><a class="c-page-link" href="#">${item.getTitle()}</a></span>
                <ul class="nested">
                    <@treeViewCreateTask item.children/>
                </ul>
            </li>
        <#else>
            <li><a class="c-page-link" href="/createtask/${item.getId()}">${item.getTitle()}</a></li>
        </#if>
    </#list>
</#macro>