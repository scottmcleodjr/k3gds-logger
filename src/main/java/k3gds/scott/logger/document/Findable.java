package k3gds.scott.logger.document;

/**
 * A interface for objects that can be found by a DAO template.
 * 
 * @author Scott McLeod Jr.
 */
public interface Findable {

  /**
   * Getter for the ID of the object.
   * 
   * @return The ID of the object.
   */
  String getId();
}
