package com.bytatech.ayoos.client.appointment.model;

import java.util.Objects;
import com.bytatech.ayoos.client.appointment.model.Link;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CommandResource
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-06T12:46:25.612+05:30[Asia/Kolkata]")

public class CommandResource   {
  @JsonProperty("links")
  @Valid
  private List<Link> links = null;

  @JsonProperty("nextTaskId")
  private String nextTaskId = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("taskName")
  private String taskName = null;

  @JsonProperty("trackingId")
  private String trackingId = null;

  public CommandResource links(List<Link> links) {
    this.links = links;
    return this;
  }

  public CommandResource addLinksItem(Link linksItem) {
    if (this.links == null) {
      this.links = new ArrayList<Link>();
    }
    this.links.add(linksItem);
    return this;
  }

  /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public CommandResource nextTaskId(String nextTaskId) {
    this.nextTaskId = nextTaskId;
    return this;
  }

  /**
   * Get nextTaskId
   * @return nextTaskId
  **/
  @ApiModelProperty(value = "")


  public String getNextTaskId() {
    return nextTaskId;
  }

  public void setNextTaskId(String nextTaskId) {
    this.nextTaskId = nextTaskId;
  }

  public CommandResource status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CommandResource taskName(String taskName) {
    this.taskName = taskName;
    return this;
  }

  /**
   * Get taskName
   * @return taskName
  **/
  @ApiModelProperty(value = "")


  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public CommandResource trackingId(String trackingId) {
    this.trackingId = trackingId;
    return this;
  }

  /**
   * Get trackingId
   * @return trackingId
  **/
  @ApiModelProperty(value = "")


  public String getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(String trackingId) {
    this.trackingId = trackingId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandResource commandResource = (CommandResource) o;
    return Objects.equals(this.links, commandResource.links) &&
        Objects.equals(this.nextTaskId, commandResource.nextTaskId) &&
        Objects.equals(this.status, commandResource.status) &&
        Objects.equals(this.taskName, commandResource.taskName) &&
        Objects.equals(this.trackingId, commandResource.trackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, nextTaskId, status, taskName, trackingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommandResource {\n");
    
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("    nextTaskId: ").append(toIndentedString(nextTaskId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    taskName: ").append(toIndentedString(taskName)).append("\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
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

