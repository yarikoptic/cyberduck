/*
 * Storegate.Web
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v4
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ch.cyberduck.core.storegate.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 */
@ApiModel(description = "")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-09-16T20:27:33.275+02:00")
public class EmailSubscriptionsRequest {
  @JsonProperty("newsletter")
  private Boolean newsletter = null;

  @JsonProperty("loginNotification")
  private Boolean loginNotification = null;

  @JsonProperty("backupReports")
  private Boolean backupReports = null;

  @JsonProperty("backupAlerts")
  private Boolean backupAlerts = null;

  public EmailSubscriptionsRequest newsletter(Boolean newsletter) {
    this.newsletter = newsletter;
    return this;
  }

   /**
   * Newsletter subscription
   * @return newsletter
  **/
  @ApiModelProperty(value = "Newsletter subscription")
  public Boolean isNewsletter() {
    return newsletter;
  }

  public void setNewsletter(Boolean newsletter) {
    this.newsletter = newsletter;
  }

  public EmailSubscriptionsRequest loginNotification(Boolean loginNotification) {
    this.loginNotification = loginNotification;
    return this;
  }

   /**
   * Login Notification Subscription
   * @return loginNotification
  **/
  @ApiModelProperty(value = "Login Notification Subscription")
  public Boolean isLoginNotification() {
    return loginNotification;
  }

  public void setLoginNotification(Boolean loginNotification) {
    this.loginNotification = loginNotification;
  }

  public EmailSubscriptionsRequest backupReports(Boolean backupReports) {
    this.backupReports = backupReports;
    return this;
  }

   /**
   * Backup Report Subscription
   * @return backupReports
  **/
  @ApiModelProperty(value = "Backup Report Subscription")
  public Boolean isBackupReports() {
    return backupReports;
  }

  public void setBackupReports(Boolean backupReports) {
    this.backupReports = backupReports;
  }

  public EmailSubscriptionsRequest backupAlerts(Boolean backupAlerts) {
    this.backupAlerts = backupAlerts;
    return this;
  }

   /**
   * Backup Alerts Subscription
   * @return backupAlerts
  **/
  @ApiModelProperty(value = "Backup Alerts Subscription")
  public Boolean isBackupAlerts() {
    return backupAlerts;
  }

  public void setBackupAlerts(Boolean backupAlerts) {
    this.backupAlerts = backupAlerts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailSubscriptionsRequest emailSubscriptionsRequest = (EmailSubscriptionsRequest) o;
    return Objects.equals(this.newsletter, emailSubscriptionsRequest.newsletter) &&
        Objects.equals(this.loginNotification, emailSubscriptionsRequest.loginNotification) &&
        Objects.equals(this.backupReports, emailSubscriptionsRequest.backupReports) &&
        Objects.equals(this.backupAlerts, emailSubscriptionsRequest.backupAlerts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newsletter, loginNotification, backupReports, backupAlerts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailSubscriptionsRequest {\n");
    
    sb.append("    newsletter: ").append(toIndentedString(newsletter)).append("\n");
    sb.append("    loginNotification: ").append(toIndentedString(loginNotification)).append("\n");
    sb.append("    backupReports: ").append(toIndentedString(backupReports)).append("\n");
    sb.append("    backupAlerts: ").append(toIndentedString(backupAlerts)).append("\n");
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

