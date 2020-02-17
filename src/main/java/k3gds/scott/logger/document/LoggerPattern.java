package k3gds.scott.logger.document;

/**
 * Contains regex patterns used for validation in Logger documents.
 * 
 * @author Scott McLeod Jr.
 */
public final class LoggerPattern {

  public static final String CALL_SIGN = "[A-Z0-9][A-Z0-9/]{1,36}[A-Z0-9]";

  public static final String GRID_SQUARE = "^[A-R]{2}[0-9]{2}([a-x]{2}|$)$";

  public static final String FREQUENCY = "\\d{1,4}\\.\\d{3,5}";

  private LoggerPattern() {}
}
