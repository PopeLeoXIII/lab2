
<#import "parts/common.ftl" as c>

<@c.page>
<body>


    <div>Список сообщений</div>
    <form method="get" action="/all-ticket ">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>

    <#list tickets as ticket>
    <div class="card my-3">

        <div class="card-header">${ticket.name}</div>
        <div class="card-body">
            <p>${ticket.description}</p>
            <i>Author: ${ticket.authorName?ifExists}</i>

            <div class="mt-1"><span>ticket status: ${ticket.status}        ticket prior: ${ticket.priority}</span>
            </div>
            <div class="mt-1"><i>Assign: ${ticket.assignsName?ifExists}</i></div>
            <div class="mt-1"><p>Creating time: ${ticket.dateBegining?ifExists}  Finish time: ${ticket.dateEnding?ifExists}</p></div>
            <a href="/ticket/${ticket.id}" class="btn btn-primary">подробности</a>

        </div>
    </div>
    <#else>
    No ticket
</#list>
<div>

</@c.page>
