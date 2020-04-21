<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">
<#import "elements/notice.ftl" as macrosN>

<@macros.environment>
    <#if !token?has_content>
        <@macrosN.notice str="Please, sign in"/>
    </#if>
    <h5>Hello!</h5>
    <div>Today is ${today!}</div>
    <div>This is a simple task manager</div>
    <div>${token!}</div>
</@macros.environment>
