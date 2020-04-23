<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">

<@macros.environment>
    <nav class="navbar navbar-light bg-light">
        <div class="row">
            <form class="form-inline ml-3"
                  method="post"
                  action="/deleteUser">
                <button class="btn btn-sm btn-outline-secondary"
                        type="submit">
                    Delete user
                </button>
            </form>
        </div>
    </nav>
</@macros.environment>