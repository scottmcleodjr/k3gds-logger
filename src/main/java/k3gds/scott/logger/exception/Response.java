package k3gds.scott.logger.exception;

import java.util.List;

/**
 * Response for Logger exception handler.
 * 
 * @author Scott McLeod Jr.
 */
public class Response {

  private List<String> messages;

  public List<String> getMessages() {
    return this.messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }
}
