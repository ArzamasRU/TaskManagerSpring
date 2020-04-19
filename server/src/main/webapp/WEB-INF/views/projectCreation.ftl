<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">

<@macros.environment>
    <form
            method="post"
            action="/projectCreation">
        <input
                type="hidden"
                name="token"
                value="${token}"/>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-6">
                <input
                        type="text"
                        name="name"
                        class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Description:</label>
            <div class="col-sm-6">
                <input
                        type="text"
                        name="description"
                        class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Creation date:</label>
            <div class="col-sm-6">
                <input class="form-control" type="date" value="2020-01-01" name="creationDate"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Start date:</label>
            <div class="col-sm-6">
                <input class="form-control" type="date" value="2020-01-01" name="startDate"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Finish date:</label>
            <div class="col-sm-6">
                <input class="form-control" type="date" value="2020-01-01" name="finishDate"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Status:</label>
            <div class="col-sm-6">
                <select class="custom-select" name="status">
                    <option value="">Choose status...</option>
                    <option value="PLANNED">PLANNED</option>
                    <option value="IN_PROCESS">IN_PROCESS</option>
                    <option value="DONE">DONE</option>
                </select>
            </div>
        </div>


        <button
                type="submit"
                class="btn btn-primary">Create new project</button>
    </form>
</@macros.environment>