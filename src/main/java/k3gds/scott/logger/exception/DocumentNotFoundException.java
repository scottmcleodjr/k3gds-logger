package k3gds.scott.logger.exception;

/**
 * Exception used to indicate a document is not found in the database.
 * 
 * @author Scott McLeod Jr.
 */
public class DocumentNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DocumentNotFoundException(String message) {
    super(message);
  }
}
