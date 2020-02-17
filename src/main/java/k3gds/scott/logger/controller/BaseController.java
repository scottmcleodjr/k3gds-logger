package k3gds.scott.logger.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import k3gds.scott.logger.dao.BaseDAO;
import k3gds.scott.logger.document.Findable;
import k3gds.scott.logger.exception.InvalidFieldException;

/**
 * Abstract base controller for Logger API.
 * 
 * @param <T> The document class of the controller.
 * @author Scott McLeod Jr.
 */
public abstract class BaseController<T extends Findable> {

  abstract BaseDAO<T> getDao();

  /**
   * Controller GET method for "" and "/" API calls.
   * 
   * @return A list of all documents.
   */
  @GetMapping(value = { "", "/" })
  public List<T> getAllDocuments() {
    return getDao().getAllDocuments();
  }

  /**
   * Controller GET method for "/{ID}" API calls.
   * 
   * @param id The ID of the document.
   * @return The document matching the provided ID.
   */
  @GetMapping(value = "/{id}")
  public T getDocumentById(@PathVariable String id) {
    return getDao().getDocumentById(id);
  }

  /**
   * Controller POST method for "" and "/" API calls.
   * 
   * @param document The document to be saved.
   * @return The saved document.
   * @throws InvalidFieldException If the document ID is provided.
   */
  @PostMapping(value = { "", "/" })
  public T addNewDocument(@Valid @RequestBody T document) {
    if (document.getId() != null) {
      throw new InvalidFieldException("New document ID must be omitted.");
    }
    return getDao().addNewDocument(document);
  }

  /**
   * Controller PUT method for "/{ID}" API calls.
   * 
   * @param id       The ID of the document to be saved.
   * @param document The document to be saved.
   * @return The updated document.
   * @throws InvalidFieldException If the document ID is omitted.
   * @throws InvalidFieldException If the document ID does not match the ID
   *                               parameter.
   */
  @PutMapping(value = "/{id}")
  public T updateDocument(@PathVariable String id, @Valid @RequestBody T document) {
    if (document.getId() == null) {
      throw new InvalidFieldException("Document ID is required.");
    } else if (!document.getId().equals(id)) {
      throw new InvalidFieldException("URL ID does not match JSON.");
    }
    return getDao().updateDocument(document);
  }

  /**
   * Controller DELETE method for "/{ID}" API calls.
   * 
   * @param id The ID of the document to be deleted.
   */
  @DeleteMapping(value = "/{id}")
  public void deleteDocument(@PathVariable String id) {
    getDao().deleteDocument(id);
  }
}
