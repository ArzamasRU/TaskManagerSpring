<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">

<@macros.environment>
    <form
            method="post">
        <input
                type="hidden"
                name="token"
                value="${token}"/>
        <button
                value="/deleteUser"
                class="btn btn-primary"
                type="submit">
			Delete user
        </button>
    </form>
</@macros.environment>