<#macro pager url page dlm>
    <#if page.getTotalPages() gt 7>
        <#assign
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber() + 1

        head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
        tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
        bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
        bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

        body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>
<div class="c-pagination">
	<div class="c-pagination-selector-container">    
	    <div class="input-group c-pagination-selector">
		  <div class="input-group-prepend">
		    <label class="input-group-text c-label" >Элементов на странице</label>
		  </div>
	        <select class="custom-select" onchange="location = this.value;">
	            <#list [5, 10, 25, 50] as c>
	                <#if c == page.getSize()>
	                     <option selected value="#">${c}</option>
	                <#else>
	                    <option class="" value="${url}${dlm}page=${page.getNumber()}&size=${c}">${c}</option>
	                </#if>
	            </#list>
	        </select>
		</div>
    </div> 
    
    <div class="c-pagination-pages">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Страницы</a>
            </li>
            <#list body as p>
                <#if (p - 1) == page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1">${p}</a>
                    </li>
                <#elseif p == -1>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">...</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="${url}${dlm}page=${p - 1}&size=${page.getSize()}" tabindex="-1">${p}</a>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>
 </div>   
</#macro>