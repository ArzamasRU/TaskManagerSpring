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
        <#if user??>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item"><a
                            class="nav-link"
                            href="/account">Account</a></li>
                <li class="nav-item"><a
                            class="nav-link"
                            href="/projects">Projects</a></li>
                <li class="nav-item"><a
                            class="nav-link"
                            href="/tasks">Tasks</a></li>
            </ul>

            <div class="col-sm mr-auto"></div>
            <form
                    action="/logout"
                    method="post">
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
                    method="get">
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
                    method="get">
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