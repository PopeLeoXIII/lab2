<#import "parts/common.ftl" as c>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    userId = user.getId()>

<#if ticket.assignsId??>
<#assign
fuckingAssignId = ticket.assignsId >
<#else>
<#assign
fuckingAssignId = -1 >
</#if>

<@c.page>

<#if userId = ticket.authorId>
    <a href="/${userId}/add-ticket?editTicket=${ticket.id}" class="btn btn-primary">Edit</a>
</#if>

<#if userId = fuckingAssignId || userId = ticket.authorId>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false" aria-controls="collapseExample">
        Change status
    </a>
    <div class="collapse" id="collapseExample1">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" name="form1">
                <div class="form-group">
                    <label>New status</label>
                    <select class="form-control" id="exampleFormControlSelect1" name="status">
                        <option>OPEN</option>
                        <option>INPROGRESS</option>
                        <option>RFT</option>
                        <option>REOPEN</option>
                        <option>CLOSE</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Comment(auto generation)</label>
                    <input type="text" class="form-control" name="text" value="Change ${ticket.status} "/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <input name = "changeStatus" type="submit" class="btn btn-primary" value="Выбрать"/>
                </div>
            </form>
        </div>
    </div>
</#if>

<#if fuckingAssignId = -1>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample3" role="button" aria-expanded="false" aria-controls="collapseExample">
    Take ticket
</a>
<div class="collapse" id="collapseExample3">
    <form method="post" enctype="multipart/form-data" name="form3">
        <div class="form-group mt-3">
            <label>Вы уверены, что хотите взять этот тикет?</label>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <input name = "take" type="submit" class="btn btn-primary" value="Взять тикет"/>
            </div>
        </div>
    </form>
</div>
</#if>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample2" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new comment
</a>
<div class="collapse" id="collapseExample2">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" name="form2">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="tag" placeholder="Тэг">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <input name = "addComment" type="submit" class="btn btn-primary" value="Добавить">
            </div>
        </form>
    </div>
</div>


<div class="card text-center mt-3">
    <div class="card-header">
        Ticket № ${ticket.id}
    </div>
    <div class="card-body">
        <h5 class="card-title">${ticket.name}</h5>
        <p class="card-text">${ticket.description}</p>
        <span>status: ${ticket.status};  priopity: ${ticket.priority}</span>
        <#if ticket.parentId = -1>
        <p class="card-text" >No parent ticket</p>
        <#else>
        <p class="card-text" >Parent ticket: <a href="/ticket/${ticket.parentId}">${ticket.parentName}</a></p>
        </#if>

    <p class="card-text">Creating date: ${ticket.dateBegining?ifExists}</p>

</div>
    <div class="card-footer text-muted">
        ${ticket.dateEnding?ifExists}
    </div>
</div>
<div class="row mt-3">
    <div class="col-sm-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Author</h5>
                <p class="card-text">${ticket.authorName}</p>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Assign</h5>
                <#if fuckingAssignId = -1>
                <p class="card-text">no one has taken up the execution of this ticket</p>
                <#else>
                <p class="card-text">${ticket.assignsName}</p>
            </#if>
            </div>
        </div>
    </div>
</div>


<div class="card text-center mt-3" ><h4>Список сообщений</h4></div>
<#list messages as message>
<div class="card mt-3">
    <div class="card-body">
        <h5 class="card-title">>${message.text}</h5>
        <h6 class="card-subtitle mb-2 text-muted">#${message.tag}</h6>
        <p class="card-text">
            Author: ${message.authorName}
            Create: ${message.creatingDate}</p>
    </div>
</div>
<#else>
No message
</#list>


</@c.page>
