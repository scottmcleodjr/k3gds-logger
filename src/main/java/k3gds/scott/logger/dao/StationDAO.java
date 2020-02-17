package k3gds.scott.logger.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import k3gds.scott.logger.document.Contact;
import k3gds.scott.logger.document.Station;

/**
 * Database access class for Station documents.
 * 
 * @author Scott McLeod Jr.
 */
@Repository
public class StationDAO extends BaseDAO<Station> {

  @Override
  protected String getNotFoundMessage() {
    return "Station not found.";
  }

  @Override
  protected Class<Station> getDocumentClass() {
    return Station.class;
  }

  /**
   * {@inheritDoc} Calling this method will also delete any contacts that
   * reference this station.
   * 
   * @throws DocumentNotFoundException {@inheritDoc}
   */
  @Override
  public void deleteDocument(String id) {
    Query q = new Query();
    q.addCriteria(Criteria.where("stationId").is(id));
    template.remove(q, Contact.class);
    super.deleteDocument(id);
  }
}
