package k3gds.scott.logger.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import k3gds.scott.logger.document.Contact;

/**
 * Database access class for Contact documents.
 * 
 * @author Scott McLeod Jr.
 */
@Repository
public class ContactDAO extends BaseDAO<Contact> {

  @Autowired
  private LogDAO logDao;

  @Autowired
  private StationDAO stationDAO;

  @Override
  protected String getNotFoundMessage() {
    return "Contact not found.";
  }

  @Override
  protected Class<Contact> getDocumentClass() {
    return Contact.class;
  }

  /**
   * {@inheritDoc}
   * 
   * @throws DocumentNotFoundException If the station is not found in the
   *                                   database.
   * @throws DocumentNotFoundException If the log is not found in the database.
   */
  @Override
  public Contact addNewDocument(Contact contact) {
    // Make sure the log and station exist before you save
    logDao.getDocumentById(contact.getLogId());
    stationDAO.getDocumentById(contact.getStationId());
    return super.addNewDocument(contact);
  }
}
