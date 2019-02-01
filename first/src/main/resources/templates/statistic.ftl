<#import "parts/common.ftl" as c>
<#assign
user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
curUser = user.getId()
curName = user.getUsername()>
<@c.page>
<div class="row">
    <div class="col-sm-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Статистика по тикетам пользователя ${name}</h5>
                <p class="card-text">Тикетов в статусе OPEN:  ${count0}</p>
                <p class="card-text">Тикетов в статусе INPROGRESS:  ${count1}</p>
                <p class="card-text">Тикетов в статусе RFT:  ${count2}</p>
                <p class="card-text">Тикетов в статусе CLOSE:  ${count3}</p>
                <p class="card-text">Тикетов в статусе REOPEN:  ${count4}</p>
                <p class="card-text">Тикетов созданно:  ${countAuthor}</p>
                <#if name = curName>
                <#else>
                    <p class="card-text">Вы можете просмотреть собственную статистику </p>
                    <a href="/statistic/${curUser}" class="btn btn-primary">Проверить свою</a>
                </#if>
            </div>
        </div>
    </div>

</div>

</@c.page>