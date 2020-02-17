package k3gds.scott.logger.document;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Station document class.
 * 
 * @author Scott McLeod Jr.
 */
@Document
public class Station implements Findable {

  @Id
  private String id;

  @NotNull(message = "Station name is required.")
  private String name;

  private String operatorName;

  @NotNull(message = "Operator call sign is required.")
  @Pattern(regexp = LoggerPattern.CALL_SIGN, message = "Call format is invalid.")
  private String operatorCall;

  @NotNull(message = "Grid square is required.")
  @Pattern(regexp = LoggerPattern.GRID_SQUARE, message = "Grid format is invalid.")
  private String gridSquare;

  private Map<String, String> userFields;

  @Override
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOperatorName() {
    return this.operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public String getOperatorCall() {
    return this.operatorCall;
  }

  public void setOperatorCall(String operatorCall) {
    this.operatorCall = operatorCall;
  }

  public String getGridSquare() {
    return this.gridSquare;
  }

  public void setGridSquare(String gridSquare) {
    this.gridSquare = gridSquare;
  }

  public Map<String, String> getUserFields() {
    return this.userFields;
  }

  public void setUserFields(Map<String, String> userFields) {
    this.userFields = userFields;
  }
}
