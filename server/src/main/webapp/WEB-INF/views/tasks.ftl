<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">

<@macros.environment>
    <nav class="navbar navbar-light bg-light mb-5">
        <form
              method="get"
              action="/taskCreation">
            <button class="btn btn-sm btn-outline-secondary"
                    type="submit">
                Create new task
            </button>
        </form>
        <form
              method="post"
              action="/removeAllTasks">
            <input
                    type="hidden"
                    name="token"
                    value="${token}"/>
            <button
                    class="btn btn-sm btn-outline-secondary"
                    type="submit">
                Delete all tasks
            </button>
        </form>
    </nav>

    <div class="form-group row">
        <form
                method="get"
                action="/tasks"
                class="form-inline">
            <div class="form-group row">
                <select class="custom-select" name="sortKey">
                    <option value="">Choose sort key...</option>
                    <option value="startDate">Start date</option>
                    <option value="finishDate">Finish date</option>
                    <option value="status">Status</option>
                </select>
                <button
                        type="submit"
                        class="btn btn-primary ml-2">
                    Sort
                </button>
            </div>
        </form>
        <div class="col-sm mr-auto"></div>
        <form
                method="get"
                action="/tasks"
                class="form-inline">
            <div class="form-group row-md-6 ">
                <select class="custom-select" name="searchKey">
                    <option value="">Choose search key...</option>
                    <option value="name">Name</option>
                    <option value="description">Description</option>
                </select>
                <input
                        type="text"
                        name="searchKeyValue"
                        class="form-control ml-2"
                        placeholder="${searchKeyValue!}"/>
                <button
                        type="submit"
                        class="btn btn-primary ml-2">
                    Find
                </button>
            </div>
        </form>
    </div>


    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Creation date</th>
            <th scope="col">Start date</th>
            <th scope="col">Finish date</th>
            <th scope="col">Status</th>
            <th scope="col">User Id</th>
            <th scope="col">Project Id</th>
        </tr>
        </thead>
        <tbody id="tasksList">
        <#list tasks as task>
            <tr id="taskRow" data-id="${task?counter}">
                <th scope="row">${task?counter}</th>
                <td><#if task.name?has_content>${task.name}<#else>null</#if></td>
                <td><#if task.description?has_content>${task.description}<#else>null</#if></td>
                <td><#if task.creationDate?has_content>${task.creationDate}<#else>null</#if></td>
                <td><#if task.startDate?has_content>${task.startDate}<#else>null</#if></td>
                <td><#if task.finishDate?has_content>${task.finishDate}<#else>null</#if></td>
                <td><#if task.status?has_content>${task.status}<#else>null</#if></td>
                <td><#if task.userId?has_content>${task.userId}<#else>null</#if></td>
                <td><#if task.projectId?has_content>${task.projectId}<#else>null</#if></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@macros.environment>