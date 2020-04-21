<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl">

<@macros.environment>
    <div>${token!}</div>
    <nav class="navbar navbar-light bg-light mb-5">
        <div class="row">
            <form class="form-inline ml-3"
                  method="post"
                  action="/projectCreation">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <button class="btn btn-sm btn-outline-secondary"
                        type="submit">
                    Create new project
                </button>
            </form>
            <form class="form-inline ml-3"
                  method="post"
                  action="/removeAllProjects">
                <input
                        type="hidden"
                        name="token"
                        value="${token}"/>
                <button
                        class="btn btn-sm btn-outline-secondary"
                        type="submit">
                    Delete all projects
                </button>
            </form>
        </div>
    </nav>

    <div class="row justify-content-between mb-3">
        <form class="form-inline ml-3"
              method="post"
              action="/projects">
            <input
                    type="hidden"
                    name="token"
                    value="${token}"/>
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
        </form>
        <form class="form-inline mr-3"
              method="post"
              action="/projects">
            <select class="custom-select" name="searchKey">
                <option value="">Choose search key...</option>
                <option value="name">Name</option>
                <option value="description">Description</option>
            </select>
            <input
                    type="hidden"
                    name="token"
                    value="${token}"/>
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
        </tr>
        </thead>
        <tbody id="projectsList">
        <#list projects as project>
            <tr id="projectRow" data-id="${project?counter}">
                <th scope="row">${project?counter}</th>
                <td><#if project.name?has_content>${project.name}</#if></td>
                <td><#if project.description?has_content>${project.description}</#if></td>
                <td><#if project.creationDate?has_content>${project.creationDate}</#if></td>
                <td><#if project.startDate?has_content>${project.startDate}</#if></td>
                <td><#if project.finishDate?has_content>${project.finishDate}</#if></td>
                <td><#if project.status?has_content>${project.status}</#if></td>
                <td><#if project.userId?has_content>${project.userId}</#if></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@macros.environment>