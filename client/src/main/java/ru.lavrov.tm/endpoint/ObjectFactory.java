
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

    private final static QName _Calculate_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "calculate");
    private final static QName _CalculateResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "calculateResponse");
    private final static QName _GetUrl_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUrl");
    private final static QName _GetUrlResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "getUrlResponse");
    private final static QName _Hello_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "hello");
    private final static QName _HelloMe_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "helloMe");
    private final static QName _HelloMeResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "helloMeResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://endpoint.tm.lavrov.ru/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.lavrov.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Calculate }
     * 
     */
    public Calculate createCalculate() {
        return new Calculate();
    }

    /**
     * Create an instance of {@link CalculateResponse }
     * 
     */
    public CalculateResponse createCalculateResponse() {
        return new CalculateResponse();
    }

    /**
     * Create an instance of {@link GetUrl }
     * 
     */
    public GetUrl createGetUrl() {
        return new GetUrl();
    }

    /**
     * Create an instance of {@link GetUrlResponse }
     * 
     */
    public GetUrlResponse createGetUrlResponse() {
        return new GetUrlResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
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
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Calculate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "calculate")
    public JAXBElement<Calculate> createCalculate(Calculate value) {
        return new JAXBElement<Calculate>(_Calculate_QNAME, Calculate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "calculateResponse")
    public JAXBElement<CalculateResponse> createCalculateResponse(CalculateResponse value) {
        return new JAXBElement<CalculateResponse>(_CalculateResponse_QNAME, CalculateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUrl")
    public JAXBElement<GetUrl> createGetUrl(GetUrl value) {
        return new JAXBElement<GetUrl>(_GetUrl_QNAME, GetUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUrlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "getUrlResponse")
    public JAXBElement<GetUrlResponse> createGetUrlResponse(GetUrlResponse value) {
        return new JAXBElement<GetUrlResponse>(_GetUrlResponse_QNAME, GetUrlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.lavrov.ru/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
