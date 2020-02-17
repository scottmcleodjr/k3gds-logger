package k3gds.scott.logger.document;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Log document class.
 * 
 * @author Scott McLeod Jr.
 */
@Document
public class Log implements Findable {

  @Id
  private String id;

  @NotNull(message = "Log name is required.")
  private String name;

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

  public Map<String, String> getUserFields() {
    return this.userFields;
  }

  public void setUserFields(Map<String, String> userFields) {
    this.userFields = userFields;
  }
}
