<ul class="pagination">
    <li class="page-item disabled">
        <a class="page-link" href="#" tabindex="-1">Элементов на странице</a>
    </li>
    <#list [5, 10, 25, 50] as c>
        <#if c == page.getSize()>
            <li class="page-item active">
                <a class="page-link" href="#" tabindex="-1">${c}</a>
            </li>
        <#else>
            <li class="page-item">
                <a class="page-link" href="${url}?page=${page.getNumber()}&size=${c}" tabindex="-1">${c}</a>
            </li>
        </#if>
    </#list>
</ul>


.header-top-bg {
    background-color: #0050b2;
    background-image: radial-gradient(circle at 50% 15px,rgba(0,0,0,0) 0,rgba(0,0,0,0) 50%,#00276c 100%),url(img/patterned-bg.png);
    box-shadow: 0 3px 9px #c1c1c1;
    border-bottom: 3px solid #003270;
    position: relative;
    z-index: 2;
}