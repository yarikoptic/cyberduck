/*
 * DRACOON API
 * REST Web Services for DRACOON<br>built at: 2020-09-09 08:12:59<br><br>This page provides an overview of all available and documented DRACOON APIs, which are grouped by tags.<br>Each tag provides a collection of APIs that are intended for a specific area of the DRACOON.<br><br><a title='Developer Information' href='https://developer.dracoon.com'>Developer Information</a>&emsp;&emsp;<a title='Get SDKs on GitHub' href='https://github.com/dracoon'>Get SDKs on GitHub</a><br><br><a title='Terms of service' href='https://www.dracoon.com/terms/general-terms-and-conditions/'>Terms of service</a>
 *
 * OpenAPI spec version: 4.23.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ch.cyberduck.core.sds.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import ch.cyberduck.core.sds.io.swagger.client.model.UpdateAuthTokenRestrictions;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Request model for updating general settings
 */
@Schema(description = "Request model for updating general settings")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-15T09:21:49.036118+02:00[Europe/Zurich]")
public class UpdateGeneralSettings {
  @JsonProperty("sharePasswordSmsEnabled")
  private Boolean sharePasswordSmsEnabled = null;

  @JsonProperty("cryptoEnabled")
  private Boolean cryptoEnabled = null;

  @JsonProperty("emailNotificationButtonEnabled")
  private Boolean emailNotificationButtonEnabled = null;

  @JsonProperty("eulaEnabled")
  private Boolean eulaEnabled = null;

  @JsonProperty("s3TagsEnabled")
  private Boolean s3TagsEnabled = null;

  @JsonProperty("authTokenRestrictions")
  private UpdateAuthTokenRestrictions authTokenRestrictions = null;

  @JsonProperty("hideLoginInputFields")
  private Boolean hideLoginInputFields = null;

  @JsonProperty("mediaServerEnabled")
  private Boolean mediaServerEnabled = null;

  @JsonProperty("weakPasswordEnabled")
  private Boolean weakPasswordEnabled = null;

  public UpdateGeneralSettings sharePasswordSmsEnabled(Boolean sharePasswordSmsEnabled) {
    this.sharePasswordSmsEnabled = sharePasswordSmsEnabled;
    return this;
  }

   /**
   * Allow sending of share passwords via SMS
   * @return sharePasswordSmsEnabled
  **/
  @Schema(description = "Allow sending of share passwords via SMS")
  public Boolean isSharePasswordSmsEnabled() {
    return sharePasswordSmsEnabled;
  }

  public void setSharePasswordSmsEnabled(Boolean sharePasswordSmsEnabled) {
    this.sharePasswordSmsEnabled = sharePasswordSmsEnabled;
  }

  public UpdateGeneralSettings cryptoEnabled(Boolean cryptoEnabled) {
    this.cryptoEnabled = cryptoEnabled;
    return this;
  }

   /**
   * Activation status of client-side encryption.  Can only be enabled once; disabling is not possible.
   * @return cryptoEnabled
  **/
  @Schema(description = "Activation status of client-side encryption.  Can only be enabled once; disabling is not possible.")
  public Boolean isCryptoEnabled() {
    return cryptoEnabled;
  }

  public void setCryptoEnabled(Boolean cryptoEnabled) {
    this.cryptoEnabled = cryptoEnabled;
  }

  public UpdateGeneralSettings emailNotificationButtonEnabled(Boolean emailNotificationButtonEnabled) {
    this.emailNotificationButtonEnabled = emailNotificationButtonEnabled;
    return this;
  }

   /**
   * Enable email notification button
   * @return emailNotificationButtonEnabled
  **/
  @Schema(description = "Enable email notification button")
  public Boolean isEmailNotificationButtonEnabled() {
    return emailNotificationButtonEnabled;
  }

  public void setEmailNotificationButtonEnabled(Boolean emailNotificationButtonEnabled) {
    this.emailNotificationButtonEnabled = emailNotificationButtonEnabled;
  }

  public UpdateGeneralSettings eulaEnabled(Boolean eulaEnabled) {
    this.eulaEnabled = eulaEnabled;
    return this;
  }

   /**
   * Each user has to confirm the EULA at first login.
   * @return eulaEnabled
  **/
  @Schema(description = "Each user has to confirm the EULA at first login.")
  public Boolean isEulaEnabled() {
    return eulaEnabled;
  }

  public void setEulaEnabled(Boolean eulaEnabled) {
    this.eulaEnabled = eulaEnabled;
  }

  public UpdateGeneralSettings s3TagsEnabled(Boolean s3TagsEnabled) {
    this.s3TagsEnabled = s3TagsEnabled;
    return this;
  }

   /**
   * &amp;#128640; Since v4.9.0  Defines if S3 tags are enabled
   * @return s3TagsEnabled
  **/
  @Schema(description = "&#128640; Since v4.9.0  Defines if S3 tags are enabled")
  public Boolean isS3TagsEnabled() {
    return s3TagsEnabled;
  }

  public void setS3TagsEnabled(Boolean s3TagsEnabled) {
    this.s3TagsEnabled = s3TagsEnabled;
  }

  public UpdateGeneralSettings authTokenRestrictions(UpdateAuthTokenRestrictions authTokenRestrictions) {
    this.authTokenRestrictions = authTokenRestrictions;
    return this;
  }

   /**
   * Get authTokenRestrictions
   * @return authTokenRestrictions
  **/
  @Schema(description = "")
  public UpdateAuthTokenRestrictions getAuthTokenRestrictions() {
    return authTokenRestrictions;
  }

  public void setAuthTokenRestrictions(UpdateAuthTokenRestrictions authTokenRestrictions) {
    this.authTokenRestrictions = authTokenRestrictions;
  }

  public UpdateGeneralSettings hideLoginInputFields(Boolean hideLoginInputFields) {
    this.hideLoginInputFields = hideLoginInputFields;
    return this;
  }

   /**
   * &amp;#128679; Deprecated since v4.13.0  Defines if login fields should be hidden
   * @return hideLoginInputFields
  **/
  @Schema(description = "&#128679; Deprecated since v4.13.0  Defines if login fields should be hidden")
  public Boolean isHideLoginInputFields() {
    return hideLoginInputFields;
  }

  public void setHideLoginInputFields(Boolean hideLoginInputFields) {
    this.hideLoginInputFields = hideLoginInputFields;
  }

  public UpdateGeneralSettings mediaServerEnabled(Boolean mediaServerEnabled) {
    this.mediaServerEnabled = mediaServerEnabled;
    return this;
  }

   /**
   * &amp;#128679; Deprecated since v4.12.0  Determines if the media server is enabled
   * @return mediaServerEnabled
  **/
  @Schema(description = "&#128679; Deprecated since v4.12.0  Determines if the media server is enabled")
  public Boolean isMediaServerEnabled() {
    return mediaServerEnabled;
  }

  public void setMediaServerEnabled(Boolean mediaServerEnabled) {
    this.mediaServerEnabled = mediaServerEnabled;
  }

  public UpdateGeneralSettings weakPasswordEnabled(Boolean weakPasswordEnabled) {
    this.weakPasswordEnabled = weakPasswordEnabled;
    return this;
  }

   /**
   * &amp;#128679; Deprecated since v4.14.0  Allow weak password  * A weak password has to fulfill the following criteria:     * is at least 8 characters long     * contains letters and numbers  * A strong password has to fulfill the following criteria in addition:     * contains at least one special character     * contains upper and lower case characters  Please use &#x60;PUT /system/config/policies/passwords&#x60; API to change configured password policies.
   * @return weakPasswordEnabled
  **/
  @Schema(description = "&#128679; Deprecated since v4.14.0  Allow weak password  * A weak password has to fulfill the following criteria:     * is at least 8 characters long     * contains letters and numbers  * A strong password has to fulfill the following criteria in addition:     * contains at least one special character     * contains upper and lower case characters  Please use `PUT /system/config/policies/passwords` API to change configured password policies.")
  public Boolean isWeakPasswordEnabled() {
    return weakPasswordEnabled;
  }

  public void setWeakPasswordEnabled(Boolean weakPasswordEnabled) {
    this.weakPasswordEnabled = weakPasswordEnabled;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateGeneralSettings updateGeneralSettings = (UpdateGeneralSettings) o;
    return Objects.equals(this.sharePasswordSmsEnabled, updateGeneralSettings.sharePasswordSmsEnabled) &&
        Objects.equals(this.cryptoEnabled, updateGeneralSettings.cryptoEnabled) &&
        Objects.equals(this.emailNotificationButtonEnabled, updateGeneralSettings.emailNotificationButtonEnabled) &&
        Objects.equals(this.eulaEnabled, updateGeneralSettings.eulaEnabled) &&
        Objects.equals(this.s3TagsEnabled, updateGeneralSettings.s3TagsEnabled) &&
        Objects.equals(this.authTokenRestrictions, updateGeneralSettings.authTokenRestrictions) &&
        Objects.equals(this.hideLoginInputFields, updateGeneralSettings.hideLoginInputFields) &&
        Objects.equals(this.mediaServerEnabled, updateGeneralSettings.mediaServerEnabled) &&
        Objects.equals(this.weakPasswordEnabled, updateGeneralSettings.weakPasswordEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharePasswordSmsEnabled, cryptoEnabled, emailNotificationButtonEnabled, eulaEnabled, s3TagsEnabled, authTokenRestrictions, hideLoginInputFields, mediaServerEnabled, weakPasswordEnabled);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateGeneralSettings {\n");
    
    sb.append("    sharePasswordSmsEnabled: ").append(toIndentedString(sharePasswordSmsEnabled)).append("\n");
    sb.append("    cryptoEnabled: ").append(toIndentedString(cryptoEnabled)).append("\n");
    sb.append("    emailNotificationButtonEnabled: ").append(toIndentedString(emailNotificationButtonEnabled)).append("\n");
    sb.append("    eulaEnabled: ").append(toIndentedString(eulaEnabled)).append("\n");
    sb.append("    s3TagsEnabled: ").append(toIndentedString(s3TagsEnabled)).append("\n");
    sb.append("    authTokenRestrictions: ").append(toIndentedString(authTokenRestrictions)).append("\n");
    sb.append("    hideLoginInputFields: ").append(toIndentedString(hideLoginInputFields)).append("\n");
    sb.append("    mediaServerEnabled: ").append(toIndentedString(mediaServerEnabled)).append("\n");
    sb.append("    weakPasswordEnabled: ").append(toIndentedString(weakPasswordEnabled)).append("\n");
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
