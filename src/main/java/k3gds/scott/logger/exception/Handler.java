package k3gds.scott.logger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles exceptions thrown in the Logger to return appropriate HTTP response
 * codes from API.
 * 
 * @author Scott McLeod Jr.
 */
@ControllerAdvice
public class Handler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    Response response = new Response();
    List<String> messages = new ArrayList<String>();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      messages.add(fieldError.getDefaultMessage());
    }
    response.setMessages(messages);
    return response;
  }

  @ExceptionHandler(DocumentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody Response handleDocumentNotFoundException(DocumentNotFoundException e) {
    return handleCustomException(e);
  }

  @ExceptionHandler(InvalidFieldException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody Response handleInvalidFieldException(InvalidFieldException e) {
    return handleCustomException(e);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody Response handleOtherException(Exception e) {
    return handleCustomException(e);
  }

  private Response handleCustomException(Exception e) {
    Response response = new Response();
    List<String> messages = new ArrayList<String>();
    messages.add(e.getMessage());
    response.setMessages(messages);
    return response;
  }
}
