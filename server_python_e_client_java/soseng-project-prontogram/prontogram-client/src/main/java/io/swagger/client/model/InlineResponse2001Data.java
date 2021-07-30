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
import io.swagger.client.model.InlineResponse2001DataMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * InlineResponse2001Data
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-05-20T16:03:31.473+02:00[Europe/Rome]")
public class InlineResponse2001Data {
  @SerializedName("message")
  private List<InlineResponse2001DataMessage> message = null;

  public InlineResponse2001Data message(List<InlineResponse2001DataMessage> message) {
    this.message = message;
    return this;
  }

  public InlineResponse2001Data addMessageItem(InlineResponse2001DataMessage messageItem) {
    if (this.message == null) {
      this.message = new ArrayList<InlineResponse2001DataMessage>();
    }
    this.message.add(messageItem);
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @Schema(description = "")
  public List<InlineResponse2001DataMessage> getMessage() {
    return message;
  }

  public void setMessage(List<InlineResponse2001DataMessage> message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2001Data inlineResponse2001Data = (InlineResponse2001Data) o;
    return Objects.equals(this.message, inlineResponse2001Data.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001Data {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
