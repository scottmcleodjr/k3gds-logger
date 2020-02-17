package k3gds.scott.logger.exception;

/**
 * Exception used to indicate an invalid document field value.
 * 
 * @author Scott McLeod Jr.
 */
public class InvalidFieldException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidFieldException(String message) {
    super(message);
  }
}
