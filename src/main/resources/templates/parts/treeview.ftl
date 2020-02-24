<#macro treeView listItems>
    <#list listItems as item>
        <#if item.children.empty!true>
            <li><span class="caret">${item.getId()}</span>
                <ul class="nested">
                    <@treeView item/>
                </ul>
            </li>
        <#else>
            <li>${item.getId()}</li>
        </#if>
    </#list>
</#macro>