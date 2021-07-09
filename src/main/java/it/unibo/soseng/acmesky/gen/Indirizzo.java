
package it.unibo.soseng.acmesky.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per indirizzo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="indirizzo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="comune" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="civico" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="via" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "indirizzo", propOrder = {
    "cap",
    "comune",
    "civico",
    "provincia",
    "via"
})
public class Indirizzo {

    protected int cap;
    @XmlElement(required = true)
    protected String comune;
    protected int civico;
    @XmlElement(required = true)
    protected String provincia;
    @XmlElement(required = true)
    protected String via;

    /**
     * Recupera il valore della proprietà cap.
     * 
     */
    public int getCap() {
        return cap;
    }

    /**
     * Imposta il valore della proprietà cap.
     * 
     */
    public void setCap(int value) {
        this.cap = value;
    }

    /**
     * Recupera il valore della proprietà comune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComune() {
        return comune;
    }

    /**
     * Imposta il valore della proprietà comune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComune(String value) {
        this.comune = value;
    }

    /**
     * Recupera il valore della proprietà civico.
     * 
     */
    public int getCivico() {
        return civico;
    }

    /**
     * Imposta il valore della proprietà civico.
     * 
     */
    public void setCivico(int value) {
        this.civico = value;
    }

    /**
     * Recupera il valore della proprietà provincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Imposta il valore della proprietà provincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Recupera il valore della proprietà via.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVia() {
        return via;
    }

    /**
     * Imposta il valore della proprietà via.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVia(String value) {
        this.via = value;
    }

}
