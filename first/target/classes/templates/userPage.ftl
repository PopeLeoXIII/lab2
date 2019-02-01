<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>


<@c.page>
<body>



<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link " href="./author">Author</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/user-page/${userId}/assign">Assign</a>
    </li>
    <li>
        <a class="nav-link" href="/${userId}/add-ticket">New ticket</a>
    </li>
</ul>
    <#list tickets as ticket>
    <div class="card my-3">

        <div class="card-header">${ticket.name}</div>
        <div class="card-body">
            <p>${ticket.description}</p>
            <i>Author: ${ticket.authorName?ifExists}</i>
            <i>Assign: ${ticket.assignsName?ifExists}</i>

            <span>${ticket.status}  ${ticket.priority}</span>
            <p>Creating time: ${ticket.dateBegining?ifExists}  ${ticket.dateEnding?ifExists}</p>
            <a href="/ticket/${ticket.id}" class="btn btn-primary">Подробности</a>
            <#if userId?number = ticket.assignsId>

            <a href="/${userId}/add-ticket?parentId=${ticket.id}&parentName=${ticket.name}" class="btn btn-primary">Создать дочерний тикет</a>
        </#if>

        </div>
    </div>
    <#else>
    No ticket
    </#list>
<div>
</body>
</@c.page>