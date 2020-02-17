package k3gds.scott.logger.dao;

import java.util.List;

import com.mongodb.client.result.DeleteResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import k3gds.scott.logger.document.Findable;
import k3gds.scott.logger.exception.DocumentNotFoundException;

/**
 * Abstract base database access class for Logger API.
 * 
 * @param <T> The document class of the DAO.
 * @author Scott McLeod Jr.
 */
public abstract class BaseDAO<T extends Findable> {

  @Autowired
  protected MongoTemplate template;

  protected abstract String getNotFoundMessage();

  protected abstract Class<T> getDocumentClass();

  /**
   * DAO method to return all documents.
   * 
   * @return A list of all documents.
   */
  public List<T> getAllDocuments() {
    return template.findAll(getDocumentClass());
  }

  /**
   * DAO method to return a document by ID.
   * 
   * @param id The ID of the document
   * @return The document matching the provided ID.
   * @throws DocumentNotFoundException If the document is not in the database.
   */
  public T getDocumentById(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("id").is(id));
    T out = template.findOne(q, getDocumentClass());
    if (out != null) {
      return out;
    } else {
      throw new DocumentNotFoundException(getNotFoundMessage());
    }
  }

  /**
   * DAO method to add a new document.
   * 
   * @param document The document to be saved.
   * @return The saved document.
   */
  public T addNewDocument(T document) {
    return template.save(document);
  }

  /**
   * DAO method to update an existing document by ID.
   * 
   * @param document The document to be saved.
   * @return The updated document.
   * @throws DocumentNotFoundException If the document is not in the database.
   */
  public T updateDocument(T document) {
    // Get ensure document is present or return 404
    getDocumentById(document.getId());
    return addNewDocument(document);
  }

  /**
   * DAO method to delete a document by ID.
   * 
   * @param id The ID of the document to be deleted.
   * @throws DocumentNotFoundException If the document is not in the database.
   */
  public void deleteDocument(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("id").is(id));
    DeleteResult result = template.remove(q, getDocumentClass());
    if (result.getDeletedCount() == 0) {
      throw new DocumentNotFoundException(getNotFoundMessage());
    }
  }
}
