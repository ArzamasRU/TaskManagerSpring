
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
    private final static QName _DisplayUserData_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "displayUserData");
    private final static QName _DisplayUserDataResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "displayUserDataResponse");
    private final static QName _HelloMe_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "helloMe");
    private final static QName _HelloMeResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "helloMeResponse");
    private final static QName _LoginUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "loginUser");
    private final static QName _LoginUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "loginUserResponse");
    private final static QName _LogoutUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "logoutUser");
    private final static QName _LogoutUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "logoutUserResponse");
    private final static QName _RegisterUser_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "registerUser");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "registerUserResponse");
    private final static QName _UpdateLogin_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updateLogin");
    private final static QName _UpdateLoginResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updateLoginResponse");
    private final static QName _UpdatePassword_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updatePassword");
    private final static QName _UpdatePasswordResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "updatePasswordResponse");

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
     * Create an instance of {@link DisplayUserData }
     * 
     */
    public DisplayUserData createDisplayUserData() {
        return new DisplayUserData();
    }

    /**
     * Create an instance of {@link DisplayUserDataResponse }
     * 
     */
    public DisplayUserDataResponse createDisplayUserDataResponse() {
        return new DisplayUserDataResponse();
    }

    /**
     * Create an instance of {@link HelloMe }
     * 
     */
    public HelloMe createHelloMe() {
        return new HelloMe();
    }

    /**
     * Create an instance of {@link HelloMeResponse }
     * 
     */
    public HelloMeResponse createHelloMeResponse() {
        return new HelloMeResponse();
    }

    /**
     * Create an instance of {@link LoginUser }
     * 
     */
    public LoginUser createLoginUser() {
        return new LoginUser();
    }

    /**
     * Create an instance of {@link LoginUserResponse }
     * 
     */
    public LoginUserResponse createLoginUserResponse() {
        return new LoginUserResponse();
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
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DisplayUserData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "displayUserData")
    public JAXBElement<DisplayUserData> createDisplayUserData(DisplayUserData value) {
        return new JAXBElement<DisplayUserData>(_DisplayUserData_QNAME, DisplayUserData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisplayUserDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "displayUserDataResponse")
    public JAXBElement<DisplayUserDataResponse> createDisplayUserDataResponse(DisplayUserDataResponse value) {
        return new JAXBElement<DisplayUserDataResponse>(_DisplayUserDataResponse_QNAME, DisplayUserDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloMe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "helloMe")
    public JAXBElement<HelloMe> createHelloMe(HelloMe value) {
        return new JAXBElement<HelloMe>(_HelloMe_QNAME, HelloMe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloMeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "helloMeResponse")
    public JAXBElement<HelloMeResponse> createHelloMeResponse(HelloMeResponse value) {
        return new JAXBElement<HelloMeResponse>(_HelloMeResponse_QNAME, HelloMeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "loginUser")
    public JAXBElement<LoginUser> createLoginUser(LoginUser value) {
        return new JAXBElement<LoginUser>(_LoginUser_QNAME, LoginUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "loginUserResponse")
    public JAXBElement<LoginUserResponse> createLoginUserResponse(LoginUserResponse value) {
        return new JAXBElement<LoginUserResponse>(_LoginUserResponse_QNAME, LoginUserResponse.class, null, value);
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

}
