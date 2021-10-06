/*
 * fornitoreDistanzeAPI
 * È l'API RESTful offerta dal *Fornitore delle distanze geografiche* che, come suggerisce il nome, vi racchiude la capability di calcolare la distanza tra due posizioni geografiche.
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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * DistanceResultDistance
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-10-06T12:12:43.665+02:00[Europe/Rome]")
public class DistanceResultDistance {
  @SerializedName("value")
  private BigDecimal value = null;

  @SerializedName("unit")
  private String unit = null;

  public DistanceResultDistance value(BigDecimal value) {
    this.value = value;
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(required = true, description = "")
  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public DistanceResultDistance unit(String unit) {
    this.unit = unit;
    return this;
  }

   /**
   * Get unit
   * @return unit
  **/
  @Schema(required = true, description = "")
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DistanceResultDistance distanceResultDistance = (DistanceResultDistance) o;
    return Objects.equals(this.value, distanceResultDistance.value) &&
        Objects.equals(this.unit, distanceResultDistance.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, unit);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DistanceResultDistance {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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
