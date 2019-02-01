<#import "parts/common.ftl" as c>

<@c.page>

<form method="post">
    <div class="form-group">
        <label for="exampleFormControlInput1">Ticket name</label>
        <input type="text" class="form-control" id="exampleFormControlInput1"  name = "tname" placeholder="You ticket name" <#if editTicket??>value="${editTicket.name}"</#if> >
    </div>
    <div class="form-group">
        <label for="exampleFormControlSelect1">Priority</label>
        <select class="form-control" id="exampleFormControlSelect1" name="priority">
            <#if editTicket??>
                <option <#if editTicket.priority = "LOW"> selected</#if> >LOW</option>
                <option <#if editTicket.priority = "NORMAL"> selected</#if> >NORMAL</option>
                <option <#if editTicket.priority = "MAJOR"> selected</#if> >MAJOR</option>
                <option <#if editTicket.priority = "CRITICAL"> selected</#if> >CRITICAL</option>
                <option <#if editTicket.priority = "BLOCKER"> selected</#if> >BLOCKER</option>
            <#else>
                <option >LOW</option>
                <option >NORMAL</option>
                <option >MAJOR</option>
                <option >CRITICAL</option>
                <option >BLOCKER</option>
            </#if>
        </select>
    </div>
    <div class="form-group">
        <label for="exampleFormControlSelect1">Assign</label>
        <select class="form-control" id="exampleFormControlSelect2" name="assign">
            <option value = "54"></option>

            <#list users as user>
                <#if editTicket??>


            <option value = ${user.id} <#if user.id = editTicket.assignsId> selected </#if> >${user.username}</option>
                <#else>
                    <option value = ${user.id}>${user.username}</option>
                </#if>
            <#else>
                somefing wrong
            </#list>
        </select>
    </div>
    <div class="form-group">
        <label for="exampleFormControlSelect1">Parent ticket</label>
        <select class="form-control" id="exampleFormControlSelect3" name="parent">
            <#if editTicket??>
                <#if editTicket.parentId = -1>
                <#else>
                    <option value = ${editTicket.parentId}>${editTicket.parentName}</option>
                </#if>
            </#if>

            <option value="${parentId}">${parentName}</option>
            <option value = "65">#no parent#</option>
            <#list tickets as ticket>
            <option value = ${ticket.id}>${ticket.name}</option>
            <#else>
            somefing wrong
        </#list>

        </select>
    </div>
    <div class="form-group">
        <label for="exampleFormControlTextarea1">Description</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name = "description"><#if editTicket??>${editTicket.description}<#else>You description</#if></textarea>
    </div>
    <div class="form-group">
        <label for="exampleFormControlTextarea1">Finish date</label>
        <input type="date" name="dateEnding" value="2019-02-01"
                max="2022-01-01" min="2019-01-01">
    </div>
    <#if editTicket??>
        <div class="form-group">
            <input type="text" class="form-control" name="text" placeholder="Введите сообщение" value="Ticket has been edited"/>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="tag" placeholder="Тэг" value="edit">
        </div>
    </#if>


     <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button class="btn btn-primary" type="submit"><#if editTicket??>Изменить<#else>Добавить</#if>Добавить</button>
</form>
</@c.page>
