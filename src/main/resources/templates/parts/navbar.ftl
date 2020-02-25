
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">IpiTaskReader</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Заявки
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                	<a class="dropdown-item" href="/topics">По разделам</a>
                    <a class="dropdown-item" href="#">Заявки мне и подчиненным</a>
                    <a class="dropdown-item" href="#">Созданные мной</a>
                    <a class="dropdown-item" href="#">Выполненные мной</a>
                    <a class="dropdown-item" href="#">Наблюдаемые мной</a>
                    <a class="dropdown-item" href="#">В работе</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Расширенный поиск</a>
            </li>
        </ul>
        <form class="form-inline my-3 my-lg-0" method="POST" action="/task">
            <input name="id" class="form-control mr-sm-2" type="search" placeholder="Найти по номеру" aria-label="Search">
            <button class="btn btn-outline-success my-3 my-sm-0" type="submit">Найти</button>
        </form>
        <div class="nav-item">
            <a class="nav-link nav-link-custom" href="/logout">Выход</a>
        </div>

    </div>
</nav>