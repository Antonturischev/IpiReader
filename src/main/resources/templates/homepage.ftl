<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<#import "parts/treeview.ftl" as tt>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <ul id="myUL">
        <@tt.treeView topics/>
    </ul>
    <script>
        var toggler = document.getElementsByClassName("caret");
        var i;

        for (i = 0; i < toggler.length; i++) {
            toggler[i].addEventListener("click", function() {
                this.parentElement.querySelector(".nested").classList.toggle("active");
                this.classList.toggle("caret-down");
            });
        }
    </script>
    <#if page??>
        <@p.pager url page/>
    </#if>
    <div class="container mt-5">
        <h2>Заявки</h2>
        <@t.tasklist page/>
    </div>
</@c.page>