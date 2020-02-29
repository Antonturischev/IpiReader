<#import "parts/common.ftl" as c>
<#import "parts/treeview.ftl" as tt>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">
    <div class="container mt-5">
    <h4 class="mb-4">Разделы: </h4>
        <ul id="myUL">
            <@tt.treeView topics/>
        </ul>
    </div>
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
</@c.page>