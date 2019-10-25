package com.bytatech.ayoos.client.patient.model;

import java.util.Objects;
import com.bytatech.ayoos.client.patient.model.AddressLine;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Patient
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-15T13:42:07.174+05:30[Asia/Kolkata]")
//@Document(indexName="patient")
public class Patient   {
  @JsonProperty("addressLines")
  @Valid
  private List<AddressLine> addressLines = null;

  @JsonProperty("createdDate")
  private LocalDate createdDate = null;

  @JsonProperty("dmsId")
  private String dmsId = null;

  @JsonProperty("dob")
  private LocalDate dob = null;

  @JsonProperty("gender")
  private String gender = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("image")
  private byte[] image = null;

  @JsonProperty("imageContentType")
  private String imageContentType = null;

  @JsonProperty("location")
 // @GeoPointField
  private String location = null;

  @JsonProperty("patientCode")
  private String patientCode = null;

  @JsonProperty("phoneNumber")
  private Long phoneNumber = null;

  public Patient addressLines(List<AddressLine> addressLines) {
    this.addressLines = addressLines;
    return this;
  }

  public Patient addAddressLinesItem(AddressLine addressLinesItem) {
    if (this.addressLines == null) {
      this.addressLines = new ArrayList<AddressLine>();
    }
    this.addressLines.add(addressLinesItem);
    return this;
  }

  /**
   * Get addressLines
   * @return addressLines
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<AddressLine> getAddressLines() {
    return addressLines;
  }

  public void setAddressLines(List<AddressLine> addressLines) {
    this.addressLines = addressLines;
  }

  public Patient createdDate(LocalDate createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public Patient dmsId(String dmsId) {
    this.dmsId = dmsId;
    return this;
  }

  /**
   * Get dmsId
   * @return dmsId
  **/
  @ApiModelProperty(value = "")


  public String getDmsId() {
    return dmsId;
  }

  public void setDmsId(String dmsId) {
    this.dmsId = dmsId;
  }

  public Patient dob(LocalDate dob) {
    this.dob = dob;
    return this;
  }

  /**
   * Get dob
   * @return dob
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public Patient gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(value = "")


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Patient id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Patient image(byte[] image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public Patient imageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
    return this;
  }

  /**
   * Get imageContentType
   * @return imageContentType
  **/
  @ApiModelProperty(value = "")


  public String getImageContentType() {
    return imageContentType;
  }

  public void setImageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
  }

  public Patient location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Patient patientCode(String patientCode) {
    this.patientCode = patientCode;
    return this;
  }

  /**
   * Get patientCode
   * @return patientCode
  **/
  @ApiModelProperty(value = "")


  public String getPatientCode() {
    return patientCode;
  }

  public void setPatientCode(String patientCode) {
    this.patientCode = patientCode;
  }

  public Patient phoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "")


  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patient patient = (Patient) o;
    return Objects.equals(this.addressLines, patient.addressLines) &&
        Objects.equals(this.createdDate, patient.createdDate) &&
        Objects.equals(this.dmsId, patient.dmsId) &&
        Objects.equals(this.dob, patient.dob) &&
        Objects.equals(this.gender, patient.gender) &&
        Objects.equals(this.id, patient.id) &&
        Objects.equals(this.image, patient.image) &&
        Objects.equals(this.imageContentType, patient.imageContentType) &&
        Objects.equals(this.location, patient.location) &&
        Objects.equals(this.patientCode, patient.patientCode) &&
        Objects.equals(this.phoneNumber, patient.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressLines, createdDate, dmsId, dob, gender, id, image, imageContentType, location, patientCode, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Patient {\n");
    
    sb.append("    addressLines: ").append(toIndentedString(addressLines)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    dmsId: ").append(toIndentedString(dmsId)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    imageContentType: ").append(toIndentedString(imageContentType)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    patientCode: ").append(toIndentedString(patientCode)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
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

