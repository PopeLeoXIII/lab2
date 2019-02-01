
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<body>
    <div>
        <@l.logout/>
        <span><a href="/user">user list</a></span>
        <span><a href="/add-ticket">add ticket</a></span>
    </div>

    <div>
        <form method="post">
            <input type="text" name = "text" placeholder="введите сообщение">
            <input type="text" name = "tag" placeholder="введите тег">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
            <strong>${message.creatingDate}</strong>
        </div>
    <#else>
        No message
    </#list>

</@c.page>
