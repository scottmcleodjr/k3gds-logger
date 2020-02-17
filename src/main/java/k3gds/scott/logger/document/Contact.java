package k3gds.scott.logger.document;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Contact (QSO) document class.
 * 
 * @author Scott McLeod Jr.
 */
@Document
public class Contact implements Findable {

  @Id
  private String id;

  @NotNull(message = "Contact date and time is required.")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
  private Date dateTime;

  @NotNull(message = "DX call sign is required.")
  @Pattern(regexp = LoggerPattern.CALL_SIGN, message = "DX call sign format is invalid.")
  private String dxCall;

  @NotNull(message = "Frequency is requied.")
  @Pattern(regexp = LoggerPattern.FREQUENCY, message = "Frequency format is invalid.")
  private String frequency;

  @NotNull
  private String mode;

  /* Using manually managed references here to avoid large embedded doc sizes and
   * dbRef, See:
   * https://docs.mongodb.com/manual/reference/database-references/#manual-references
   */
  @NotNull(message = "Station ID is required.")
  private String stationId;

  @NotNull(message = "Log ID is required.")
  private String logId;

  private String rstR;

  private String rstS;

  private Map<String, String> userFields;

  @Override
  public String getId() {
    return this.id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public Date getDateTime() {
    return this.dateTime;
  }

  public void setDateTime(final Date dateTime) {
    this.dateTime = dateTime;
  }

  public String getDxCall() {
    return this.dxCall;
  }

  public void setDxCall(final String dxCall) {
    this.dxCall = dxCall;
  }

  public String getFrequency() {
    return this.frequency;
  }

  public void setFrequency(final String frequency) {
    this.frequency = frequency;
  }

  public String getMode() {
    return this.mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getStationId() {
    return this.stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getLogId() {
    return this.logId;
  }

  public void setLogId(String logId) {
    this.logId = logId;
  }

  public String getRstR() {
    return this.rstR;
  }

  public void setRstR(String rstR) {
    this.rstR = rstR;
  }

  public String getRstS() {
    return this.rstS;
  }

  public void setRstS(String rstS) {
    this.rstS = rstS;
  }

  public Map<String, String> getUserFields() {
    return this.userFields;
  }

  public void setUserFields(Map<String, String> userFields) {
    this.userFields = userFields;
  }
}
