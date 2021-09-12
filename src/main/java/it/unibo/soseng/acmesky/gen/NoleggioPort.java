
package it.unibo.soseng.acmesky.gen;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "NoleggioPort", targetNamespace = "it.unibo.soseng.acmesky.wsdl.wsdl")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface NoleggioPort {


    /**
     * 
     * @param luoghi
     * @param data
     * @param ora
     * @param sid
     */
    @WebMethod(action = "richiesta")
    @Oneway
    @RequestWrapper(localName = "richiesta", targetNamespace = "it.unibo.soseng.acmesky.wsdl.xsd", className = "it.unibo.soseng.acmesky.gen.Richiesta")
    public void richiesta(
        @WebParam(name = "luoghi", targetNamespace = "")
        it.unibo.soseng.acmesky.gen.Richiesta.Luoghi luoghi,
        @WebParam(name = "data", targetNamespace = "")
        it.unibo.soseng.acmesky.gen.Richiesta.Data data,
        @WebParam(name = "ora", targetNamespace = "")
        it.unibo.soseng.acmesky.gen.Richiesta.Ora ora,
        @WebParam(name = "sid", targetNamespace = "")
        String sid);

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "login")
    @WebResult(name = "sid", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "it.unibo.soseng.acmesky.wsdl.xsd", className = "it.unibo.soseng.acmesky.gen.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "it.unibo.soseng.acmesky.wsdl.xsd", className = "it.unibo.soseng.acmesky.gen.LoginResponse")
    public String login(
        @WebParam(name = "password", targetNamespace = "")
        String password,
        @WebParam(name = "username", targetNamespace = "")
        String username);

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "registrazione")
    @WebResult(name = "text", targetNamespace = "")
    @RequestWrapper(localName = "registrazione", targetNamespace = "it.unibo.soseng.acmesky.wsdl.xsd", className = "it.unibo.soseng.acmesky.gen.Registrazione")
    @ResponseWrapper(localName = "registrazioneResponse", targetNamespace = "it.unibo.soseng.acmesky.wsdl.xsd", className = "it.unibo.soseng.acmesky.gen.RegistrazioneResponse")
    public String registrazione(
        @WebParam(name = "password", targetNamespace = "")
        String password,
        @WebParam(name = "username", targetNamespace = "")
        String username);

}
