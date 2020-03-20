
package ru.lavrov.tm.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.lavrov.tm.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "deleteUser");
    private final static QName _DeleteUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "deleteUserResponse");
    private final static QName _GetUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUser");
    private final static QName _GetUserProjects_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUserProjects");
    private final static QName _GetUserProjectsResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUserProjectsResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUserResponse");
    private final static QName _GetUserTasks_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUserTasks");
    private final static QName _GetUserTasksResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUserTasksResponse");
    private final static QName _LogoutUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "logoutUser");
    private final static QName _LogoutUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "logoutUserResponse");
    private final static QName _Project_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "project");
    private final static QName _RegisterUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "registerUser");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "registerUserResponse");
    private final static QName _Task_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "task");
    private final static QName _UpdateLogin_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updateLogin");
    private final static QName _UpdateLoginResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updateLoginResponse");
    private final static QName _UpdatePassword_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updatePassword");
    private final static QName _UpdatePasswordResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updatePasswordResponse");
    private final static QName _User_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "user");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.lavrov.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteUser }
     * 
     */
    public DeleteUser createDeleteUser() {
        return new DeleteUser();
    }

    /**
     * Create an instance of {@link DeleteUserResponse }
     * 
     */
    public DeleteUserResponse createDeleteUserResponse() {
        return new DeleteUserResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetUserProjects }
     * 
     */
    public GetUserProjects createGetUserProjects() {
        return new GetUserProjects();
    }

    /**
     * Create an instance of {@link GetUserProjectsResponse }
     * 
     */
    public GetUserProjectsResponse createGetUserProjectsResponse() {
        return new GetUserProjectsResponse();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetUserTasks }
     * 
     */
    public GetUserTasks createGetUserTasks() {
        return new GetUserTasks();
    }

    /**
     * Create an instance of {@link GetUserTasksResponse }
     * 
     */
    public GetUserTasksResponse createGetUserTasksResponse() {
        return new GetUserTasksResponse();
    }

    /**
     * Create an instance of {@link LogoutUser }
     * 
     */
    public LogoutUser createLogoutUser() {
        return new LogoutUser();
    }

    /**
     * Create an instance of {@link LogoutUserResponse }
     * 
     */
    public LogoutUserResponse createLogoutUserResponse() {
        return new LogoutUserResponse();
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link UpdateLogin }
     * 
     */
    public UpdateLogin createUpdateLogin() {
        return new UpdateLogin();
    }

    /**
     * Create an instance of {@link UpdateLoginResponse }
     * 
     */
    public UpdateLoginResponse createUpdateLoginResponse() {
        return new UpdateLoginResponse();
    }

    /**
     * Create an instance of {@link UpdatePassword }
     * 
     */
    public UpdatePassword createUpdatePassword() {
        return new UpdatePassword();
    }

    /**
     * Create an instance of {@link UpdatePasswordResponse }
     * 
     */
    public UpdatePasswordResponse createUpdatePasswordResponse() {
        return new UpdatePasswordResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "deleteUser")
    public JAXBElement<DeleteUser> createDeleteUser(DeleteUser value) {
        return new JAXBElement<DeleteUser>(_DeleteUser_QNAME, DeleteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "deleteUserResponse")
    public JAXBElement<DeleteUserResponse> createDeleteUserResponse(DeleteUserResponse value) {
        return new JAXBElement<DeleteUserResponse>(_DeleteUserResponse_QNAME, DeleteUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserProjects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUserProjects")
    public JAXBElement<GetUserProjects> createGetUserProjects(GetUserProjects value) {
        return new JAXBElement<GetUserProjects>(_GetUserProjects_QNAME, GetUserProjects.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserProjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUserProjectsResponse")
    public JAXBElement<GetUserProjectsResponse> createGetUserProjectsResponse(GetUserProjectsResponse value) {
        return new JAXBElement<GetUserProjectsResponse>(_GetUserProjectsResponse_QNAME, GetUserProjectsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserTasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUserTasks")
    public JAXBElement<GetUserTasks> createGetUserTasks(GetUserTasks value) {
        return new JAXBElement<GetUserTasks>(_GetUserTasks_QNAME, GetUserTasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserTasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUserTasksResponse")
    public JAXBElement<GetUserTasksResponse> createGetUserTasksResponse(GetUserTasksResponse value) {
        return new JAXBElement<GetUserTasksResponse>(_GetUserTasksResponse_QNAME, GetUserTasksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "logoutUser")
    public JAXBElement<LogoutUser> createLogoutUser(LogoutUser value) {
        return new JAXBElement<LogoutUser>(_LogoutUser_QNAME, LogoutUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "logoutUserResponse")
    public JAXBElement<LogoutUserResponse> createLogoutUserResponse(LogoutUserResponse value) {
        return new JAXBElement<LogoutUserResponse>(_LogoutUserResponse_QNAME, LogoutUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Project }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "project")
    public JAXBElement<Project> createProject(Project value) {
        return new JAXBElement<Project>(_Project_QNAME, Project.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Task }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "task")
    public JAXBElement<Task> createTask(Task value) {
        return new JAXBElement<Task>(_Task_QNAME, Task.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "updateLogin")
    public JAXBElement<UpdateLogin> createUpdateLogin(UpdateLogin value) {
        return new JAXBElement<UpdateLogin>(_UpdateLogin_QNAME, UpdateLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "updateLoginResponse")
    public JAXBElement<UpdateLoginResponse> createUpdateLoginResponse(UpdateLoginResponse value) {
        return new JAXBElement<UpdateLoginResponse>(_UpdateLoginResponse_QNAME, UpdateLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "updatePassword")
    public JAXBElement<UpdatePassword> createUpdatePassword(UpdatePassword value) {
        return new JAXBElement<UpdatePassword>(_UpdatePassword_QNAME, UpdatePassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "updatePasswordResponse")
    public JAXBElement<UpdatePasswordResponse> createUpdatePasswordResponse(UpdatePasswordResponse value) {
        return new JAXBElement<UpdatePasswordResponse>(_UpdatePasswordResponse_QNAME, UpdatePasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

}
