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
 * 2020-04-10T19:29:04+03:00
 * Generated source version: 3.3.5
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.lavrov.ru/", name = "GeneralCommandEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface GeneralCommandEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromXMLByJAXBRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromXMLByJAXBResponse")
    @RequestWrapper(localName = "dataFromXMLByJAXB", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromXMLByJAXB")
    @ResponseWrapper(localName = "dataFromXMLByJAXBResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromXMLByJAXBResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataFromXMLByJAXB(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromJSONByFasterXMLRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromJSONByFasterXMLResponse")
    @RequestWrapper(localName = "dataFromJSONByFasterXML", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromJSONByFasterXML")
    @ResponseWrapper(localName = "dataFromJSONByFasterXMLResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromJSONByFasterXMLResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataFromJSONByFasterXML(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/serializeRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/serializeResponse")
    @RequestWrapper(localName = "serialize", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.Serialize")
    @ResponseWrapper(localName = "serializeResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.SerializeResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean serialize(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromXMLByFasterXMLRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromXMLByFasterXMLResponse")
    @RequestWrapper(localName = "dataFromXMLByFasterXML", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromXMLByFasterXML")
    @ResponseWrapper(localName = "dataFromXMLByFasterXMLResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromXMLByFasterXMLResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataFromXMLByFasterXML(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromJSONByJAXBRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataFromJSONByJAXBResponse")
    @RequestWrapper(localName = "dataFromJSONByJAXB", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromJSONByJAXB")
    @ResponseWrapper(localName = "dataFromJSONByJAXBResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataFromJSONByJAXBResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataFromJSONByJAXB(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToJSONByJAXBRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToJSONByJAXBResponse")
    @RequestWrapper(localName = "dataToJSONByJAXB", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToJSONByJAXB")
    @ResponseWrapper(localName = "dataToJSONByJAXBResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToJSONByJAXBResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataToJSONByJAXB(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/deserializeRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/deserializeResponse")
    @RequestWrapper(localName = "deserialize", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.Deserialize")
    @ResponseWrapper(localName = "deserializeResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DeserializeResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean deserialize(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToJSONByFasterXMLRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToJSONByFasterXMLResponse")
    @RequestWrapper(localName = "dataToJSONByFasterXML", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToJSONByFasterXML")
    @ResponseWrapper(localName = "dataToJSONByFasterXMLResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToJSONByFasterXMLResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataToJSONByFasterXML(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToXMLByJAXBRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToXMLByJAXBResponse")
    @RequestWrapper(localName = "dataToXMLByJAXB", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToXMLByJAXB")
    @ResponseWrapper(localName = "dataToXMLByJAXBResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToXMLByJAXBResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataToXMLByJAXB(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToXMLByFasterXMLRequest", output = "http://endpoint.tm.lavrov.ru/GeneralCommandEndpoint/dataToXMLByFasterXMLResponse")
    @RequestWrapper(localName = "dataToXMLByFasterXML", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToXMLByFasterXML")
    @ResponseWrapper(localName = "dataToXMLByFasterXMLResponse", targetNamespace = "http://endpoint.tm.lavrov.ru/", className = "ru.lavrov.tm.endpoint.DataToXMLByFasterXMLResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean dataToXMLByFasterXML(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
