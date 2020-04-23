<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">
<#import "elements/notice.ftl" as macrosN>

<@macros.environment>
    <#if !user??>
        <@macrosN.notice str="Please, sign in"/>
    </#if>
    <h5>Hello ${login!} !</h5>
    <div>This is a simple task manager</div>
    <div>Today is ${today!}</div>
</@macros.environment>
