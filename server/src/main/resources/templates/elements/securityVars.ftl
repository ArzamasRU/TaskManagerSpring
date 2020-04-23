<#assign
    context = Session.SPRING_SECURITY_CONTEXT??
    today = .now?date>

<#if context>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        login = user.getLogin()>
</#if>
