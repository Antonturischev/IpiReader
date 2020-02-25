<#macro treeView listItems>
    <#list listItems as item>
        <#if item.children?has_content>
            <li><span class="caret"><a href="/topic/${item.getId()}">${item.getTitle()}</a></span>
                <ul class="nested">
                    <@treeView item.children/>
                </ul>
            </li>
        <#else>
            <li><a href="/topic/${item.getId()}">${item.getTitle()}</a></li>
        </#if>
    </#list>
</#macro>