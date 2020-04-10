package ru.lavrov.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.5
 * 2020-04-10T19:29:03.445+03:00
 * Generated source version: 3.3.5
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.lavrov.ru/", name = "ProjectEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByDescPartRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByDescPartResponse")
    @RequestWrapper(localName = "findAllByDescPart", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByDescPart")
    @ResponseWrapper(localName = "findAllByDescPartResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByDescPartResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.ProjectDTO> findAllByDescPart(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByStatusRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByStatusResponse")
    @RequestWrapper(localName = "findAllByStatus", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByStatus")
    @ResponseWrapper(localName = "findAllByStatusResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByStatusResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.ProjectDTO> findAllByStatus(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findProjectByNameRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findProjectByNameResponse")
    @RequestWrapper(localName = "findProjectByName", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindProjectByName")
    @ResponseWrapper(localName = "findProjectByNameResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindProjectByNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.lavrov.tm.endpoint.ProjectDTO findProjectByName(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/createByProjectNameRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/createByProjectNameResponse")
    @RequestWrapper(localName = "createByProjectName", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.CreateByProjectName")
    @ResponseWrapper(localName = "createByProjectNameResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.CreateByProjectNameResponse")
    public void createByProjectName(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/getProjectTasksRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/getProjectTasksResponse")
    @RequestWrapper(localName = "getProjectTasks", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.GetProjectTasks")
    @ResponseWrapper(localName = "getProjectTasksResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.GetProjectTasksResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.TaskDTO> getProjectTasks(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findOneRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findOneResponse")
    @RequestWrapper(localName = "findOne", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindOne")
    @ResponseWrapper(localName = "findOneResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindOneResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.lavrov.tm.endpoint.ProjectDTO findOne(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/removeRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/removeResponse")
    @RequestWrapper(localName = "remove", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.Remove")
    @ResponseWrapper(localName = "removeResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RemoveResponse")
    public void remove(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByNamePartRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByNamePartResponse")
    @RequestWrapper(localName = "findAllByNamePart", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByNamePart")
    @ResponseWrapper(localName = "findAllByNamePartResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByNamePartResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.ProjectDTO> findAllByNamePart(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/removeProjectByNameRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/removeProjectByNameResponse")
    @RequestWrapper(localName = "removeProjectByName", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RemoveProjectByName")
    @ResponseWrapper(localName = "removeProjectByNameResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RemoveProjectByNameResponse")
    public void removeProjectByName(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllResponse")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.ProjectDTO> findAll(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/removeAllRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/removeAllResponse")
    @RequestWrapper(localName = "removeAll", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RemoveAll")
    @ResponseWrapper(localName = "removeAllResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RemoveAllResponse")
    public void removeAll(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByFinishDateRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByFinishDateResponse")
    @RequestWrapper(localName = "findAllByFinishDate", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByFinishDate")
    @ResponseWrapper(localName = "findAllByFinishDateResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByFinishDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.ProjectDTO> findAllByFinishDate(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/renameProjectRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/renameProjectResponse")
    @RequestWrapper(localName = "renameProject", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RenameProject")
    @ResponseWrapper(localName = "renameProjectResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.RenameProjectResponse")
    public void renameProject(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByStartDateRequest", output = "http://endpoint.tm.lavrov.ru/ProjectEndpoint/findAllByStartDateResponse")
    @RequestWrapper(localName = "findAllByStartDate", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByStartDate")
    @ResponseWrapper(localName = "findAllByStartDateResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.FindAllByStartDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.lavrov.tm.endpoint.ProjectDTO> findAllByStartDate(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
