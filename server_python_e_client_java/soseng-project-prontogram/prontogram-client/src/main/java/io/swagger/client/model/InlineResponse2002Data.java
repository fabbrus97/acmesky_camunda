/*
 * prontogramAPI
 * È l'API Restful offerta dall'applicazione di messaggistica *Prontogram* che vi racchiude la capability di inoltrare i messaggi circa le offerte inviate da ACMESky ai clienti interessati.
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.OfferMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * InlineResponse2002Data
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-05-20T16:03:31.473+02:00[Europe/Rome]")
public class InlineResponse2002Data {
  @SerializedName("offer")
  private OfferMessage offer = null;

  @SerializedName("date")
  private String date = null;

  @SerializedName("id")
  private Integer id = null;

  public InlineResponse2002Data offer(OfferMessage offer) {
    this.offer = offer;
    return this;
  }

   /**
   * Get offer
   * @return offer
  **/
  @Schema(description = "")
  public OfferMessage getOffer() {
    return offer;
  }

  public void setOffer(OfferMessage offer) {
    this.offer = offer;
  }

  public InlineResponse2002Data date(String date) {
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  @Schema(description = "")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public InlineResponse2002Data id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2002Data inlineResponse2002Data = (InlineResponse2002Data) o;
    return Objects.equals(this.offer, inlineResponse2002Data.offer) &&
        Objects.equals(this.date, inlineResponse2002Data.date) &&
        Objects.equals(this.id, inlineResponse2002Data.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offer, date, id);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2002Data {\n");
    
    sb.append("    offer: ").append(toIndentedString(offer)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
