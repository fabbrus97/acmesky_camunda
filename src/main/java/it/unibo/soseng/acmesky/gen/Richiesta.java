
package it.unibo.soseng.acmesky.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="luoghi"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="arrivo" type="{it.unibo.soseng.acmesky.wsdl.xsd}aeroporto"/&gt;
 *                   &lt;element name="partenza" type="{it.unibo.soseng.acmesky.wsdl.xsd}indirizzo"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="anno" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="giorno" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="mese" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ora"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="hh" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="mm" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="sid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "luoghi",
    "data",
    "ora",
    "sid"
})
@XmlRootElement(name = "richiesta")
public class Richiesta {

    @XmlElement(required = true)
    protected Richiesta.Luoghi luoghi;
    @XmlElement(required = true)
    protected Richiesta.Data data;
    @XmlElement(required = true)
    protected Richiesta.Ora ora;
    @XmlElement(required = true)
    protected String sid;

    /**
     * Recupera il valore della proprietà luoghi.
     * 
     * @return
     *     possible object is
     *     {@link Richiesta.Luoghi }
     *     
     */
    public Richiesta.Luoghi getLuoghi() {
        return luoghi;
    }

    /**
     * Imposta il valore della proprietà luoghi.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiesta.Luoghi }
     *     
     */
    public void setLuoghi(Richiesta.Luoghi value) {
        this.luoghi = value;
    }

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link Richiesta.Data }
     *     
     */
    public Richiesta.Data getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiesta.Data }
     *     
     */
    public void setData(Richiesta.Data value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietà ora.
     * 
     * @return
     *     possible object is
     *     {@link Richiesta.Ora }
     *     
     */
    public Richiesta.Ora getOra() {
        return ora;
    }

    /**
     * Imposta il valore della proprietà ora.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiesta.Ora }
     *     
     */
    public void setOra(Richiesta.Ora value) {
        this.ora = value;
    }

    /**
     * Recupera il valore della proprietà sid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSid() {
        return sid;
    }

    /**
     * Imposta il valore della proprietà sid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSid(String value) {
        this.sid = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="anno" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="giorno" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="mese" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "anno",
        "giorno",
        "mese"
    })
    public static class Data {

        protected int anno;
        protected int giorno;
        protected int mese;

        /**
         * Recupera il valore della proprietà anno.
         * 
         */
        public int getAnno() {
            return anno;
        }

        /**
         * Imposta il valore della proprietà anno.
         * 
         */
        public void setAnno(int value) {
            this.anno = value;
        }

        /**
         * Recupera il valore della proprietà giorno.
         * 
         */
        public int getGiorno() {
            return giorno;
        }

        /**
         * Imposta il valore della proprietà giorno.
         * 
         */
        public void setGiorno(int value) {
            this.giorno = value;
        }

        /**
         * Recupera il valore della proprietà mese.
         * 
         */
        public int getMese() {
            return mese;
        }

        /**
         * Imposta il valore della proprietà mese.
         * 
         */
        public void setMese(int value) {
            this.mese = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="arrivo" type="{it.unibo.soseng.acmesky.wsdl.xsd}aeroporto"/&gt;
     *         &lt;element name="partenza" type="{it.unibo.soseng.acmesky.wsdl.xsd}indirizzo"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "arrivo",
        "partenza"
    })
    public static class Luoghi {

        @XmlElement(required = true)
        protected Aeroporto arrivo;
        @XmlElement(required = true)
        protected Indirizzo partenza;

        /**
         * Recupera il valore della proprietà arrivo.
         * 
         * @return
         *     possible object is
         *     {@link Aeroporto }
         *     
         */
        public Aeroporto getArrivo() {
            return arrivo;
        }

        /**
         * Imposta il valore della proprietà arrivo.
         * 
         * @param value
         *     allowed object is
         *     {@link Aeroporto }
         *     
         */
        public void setArrivo(Aeroporto value) {
            this.arrivo = value;
        }

        /**
         * Recupera il valore della proprietà partenza.
         * 
         * @return
         *     possible object is
         *     {@link Indirizzo }
         *     
         */
        public Indirizzo getPartenza() {
            return partenza;
        }

        /**
         * Imposta il valore della proprietà partenza.
         * 
         * @param value
         *     allowed object is
         *     {@link Indirizzo }
         *     
         */
        public void setPartenza(Indirizzo value) {
            this.partenza = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="hh" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="mm" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "hh",
        "mm"
    })
    public static class Ora {

        protected int hh;
        protected int mm;

        /**
         * Recupera il valore della proprietà hh.
         * 
         */
        public int getHh() {
            return hh;
        }

        /**
         * Imposta il valore della proprietà hh.
         * 
         */
        public void setHh(int value) {
            this.hh = value;
        }

        /**
         * Recupera il valore della proprietà mm.
         * 
         */
        public int getMm() {
            return mm;
        }

        /**
         * Imposta il valore della proprietà mm.
         * 
         */
        public void setMm(int value) {
            this.mm = value;
        }

    }

}
