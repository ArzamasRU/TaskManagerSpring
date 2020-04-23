<#macro authorization isRegisterForm>
    <#if isRegisterForm>
        <h5> Register new user </h5>
    <#else>
        <h5> Login </h5>
    </#if>

    <form
            <#if isRegisterForm>
                action="/registration"
            <#else>
                action="/login"
            </#if>
            method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Login:</label>
            <div class="col-sm-6">
                <input
                        type="text"
<#--                        name="username"-->
                        name="login"
                        class="form-control"
                        placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input
                        type="password"
                        name="password"
                        class="form-control"
                        placeholder="Password"/>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">User role:</label>
                <div class="col-sm-6">
                    <select class="custom-select" name="role">
                        <option value="">Choose role...</option>
                        <option value="ADMIN">ADMIN</option>
                        <option value="USER">USER</option>
                    </select>
                </div>
            </div>
            <button
                    class="btn btn-primary"
                    type="submit">
                Register new user
            </button>
        <#else>
            <button
                    class="btn btn-primary"
                    type="submit">
                Login
            </button>
        </#if>
    </form>
</#macro>


