<#macro authorization isRegisterForm>
    <form
            method="post">
        <#if isRegisterForm>
            <h5> Register new user </h5>
        <#else>
            <h5> Login </h5>
        </#if>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-6">
                <input
                        type="text"
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
                    <input
                            type="text"
                            name="role"
                            class="form-control"
                            placeholder="User role"/>
                </div>
            </div>
            <button
                    class="btn btn-primary"
                    type="submit">
<#--                    value="/registration">-->
                Register new user
            </button>
        <#else>
            <button
                    class="btn btn-primary"
                    type="submit">
<#--                    value="/login">-->
                Login
            </button>
        </#if>
    </form>
</#macro>


