<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "parts/tasklist.ftl" as t>
<@c.page>
    <#include "parts/security.ftl">
    <#include "parts/navbar.ftl">

    <div class="container mt-5">
        <h2>Расширенный поиск</h2>
        <select class="custom-select">

        </select>

        <a style="color:green;" href="#">[+]</a>
        
        <div class="parentId"></div>
        <script type="text/javascript">
			$('.parentId').click(function() {
				  $('parentId').append($('<div>', {
				    'text': 'Текст... ['+ now() +'])'
				  }));
		</script>
    </div>
</@c.page>
