
package it.unibo.soseng.acmesky.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aeroporto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aeroporto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aeroporto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aeroporto", propOrder = {
    "aeroporto"
})
public class Aeroporto {

    @XmlElement(required = true)
    protected String aeroporto;

    /**
     * Recupera il valore della proprietà aeroporto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAeroporto() {
        return aeroporto;
    }

    /**
     * Imposta il valore della proprietà aeroporto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAeroporto(String value) {
        this.aeroporto = value;
    }

}
