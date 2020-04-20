<#include "securityVars.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand"
       href="/">TaskManager</a>
    <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse"
         id="navbarSupportedContent">
        <#if token?has_content>
            <form action="/account"
                  method="post">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <div class="col-sm">
                    <button
                            class="btn btn-sm btn-outline-secondary"
                            type="submit">
                        Account
                    </button>
                </div>
            </form>
            <form action="/projects"
                  method="post">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <div class="col-sm">
                    <button
                            class="btn btn-sm btn-outline-secondary"
                            type="submit">
                        Projects
                    </button>
                </div>
            </form>
            <form action="/tasks"
                  method="post">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <div class="col-sm">
                    <button
                            class="btn btn-sm btn-outline-secondary"
                            type="submit">
                        Tasks
                    </button>
                </div>
            </form>

            <div class="col-sm mr-auto"></div>
            <form
                    action="/logout"
                    method="get">
                <button
                        class="btn btn-primary"
                        type="submit">
                    Logout
                </button>
            </form>
        <#else>
            <div class="col-sm mr-auto"></div>
            <form
                    action="/login"
                    method="post">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <div class="col-sm mr-auto">
                    <button
                            class="btn btn-primary"
                            type="submit">
                        Login
                    </button>
                </div>
            </form>
            <form
                    action="/registration"
                    method="post">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <div class="col-sm">
                    <button
                            class="btn btn-primary"
                            type="submit">
                        Register
                    </button>
                </div>
            </form>
        </#if>
    </div>
</nav>