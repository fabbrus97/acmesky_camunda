
package it.unibo.soseng.acmesky.gen;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.unibo.soseng.acmesky.gen package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.unibo.soseng.acmesky.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Richiesta }
     * 
     */
    public Richiesta createRichiesta() {
        return new Richiesta();
    }

    /**
     * Create an instance of {@link Richiesta.Luoghi }
     * 
     */
    public Richiesta.Luoghi createRichiestaLuoghi() {
        return new Richiesta.Luoghi();
    }

    /**
     * Create an instance of {@link Richiesta.Data }
     * 
     */
    public Richiesta.Data createRichiestaData() {
        return new Richiesta.Data();
    }

    /**
     * Create an instance of {@link Richiesta.Ora }
     * 
     */
    public Richiesta.Ora createRichiestaOra() {
        return new Richiesta.Ora();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Registrazione }
     * 
     */
    public Registrazione createRegistrazione() {
        return new Registrazione();
    }

    /**
     * Create an instance of {@link RegistrazioneResponse }
     * 
     */
    public RegistrazioneResponse createRegistrazioneResponse() {
        return new RegistrazioneResponse();
    }

    /**
     * Create an instance of {@link Aeroporto }
     * 
     */
    public Aeroporto createAeroporto() {
        return new Aeroporto();
    }

    /**
     * Create an instance of {@link Indirizzo }
     * 
     */
    public Indirizzo createIndirizzo() {
        return new Indirizzo();
    }

}
