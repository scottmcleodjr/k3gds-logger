package k3gds.scott.logger.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import k3gds.scott.logger.document.Contact;
import k3gds.scott.logger.document.Log;

/**
 * Database access class for Log documents.
 * 
 * @author Scott McLeod Jr.
 */
@Repository
public class LogDAO extends BaseDAO<Log> {

  @Override
  protected String getNotFoundMessage() {
    return "Log not found.";
  }

  @Override
  protected Class<Log> getDocumentClass() {
    return Log.class;
  }

  /**
   * {@inheritDoc} Calling this method will also delete any contacts that
   * reference this log.
   * 
   * @throws DocumentNotFoundException {@inheritDoc}
   */
  @Override
  public void deleteDocument(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("logId").is(id));
    template.remove(q, Contact.class);
    super.deleteDocument(id);
  }
}
