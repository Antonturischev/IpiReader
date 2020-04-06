<#import "parts/common.ftl" as c>
<@c.page>
    <div class="container mt-5">
        <div class="form-row justify-content-md-center loginform">
            <div class="form-group col-md-4 ">
                <form method="POST" action="/login" class="form-inline">
                    <input type="text" name="username" placeholder="Логин" class="form-control" value="ivanov"/>
                    <input type="text" name="password" value="Q1w2e3r4"/>
                    <button type="submit" class="btn-primary btn ml-2">Войти</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>