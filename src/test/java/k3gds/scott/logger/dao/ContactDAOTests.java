package k3gds.scott.logger.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import k3gds.scott.logger.document.Contact;
import k3gds.scott.logger.document.Log;
import k3gds.scott.logger.document.Station;
import k3gds.scott.logger.exception.DocumentNotFoundException;

/**
 * Test cases for ContactDAO class.
 * 
 * @author Scott McLeod Jr.
 */
public final class ContactDAOTests {

  @Mock
  private MongoTemplate template;

  @Mock
  private LogDAO logDao;

  @Mock
  private StationDAO stationDao;

  @InjectMocks
  private ContactDAO contactDao;

  private Contact testContact;
  private final String testContactId = "TEST_CONTACT_ID";
  private final String testLogId = "TEST_LOG_ID";
  private final String testStationId = "TEST_STATION_ID";

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    testContact = new Contact();
    testContact.setId(testContactId);
    testContact.setLogId(testLogId);
    testContact.setStationId(testStationId);
  }

  @Test(expected = DocumentNotFoundException.class)
  public void shouldThrowExceptionIfContactNotFound() {
    when(template.findOne(any(), eq(Contact.class))).thenReturn(null);
    contactDao.getDocumentById(testContactId);
  }

  @Test
  public void shouldCallGetWhenUpdatingContact() {
    when(template.findOne(any(), eq(Contact.class))).thenReturn(testContact);
    when(template.save(any())).thenReturn(testContact);
    contactDao.updateDocument(testContact);
    verify(template).findOne(any(), eq(Contact.class));
  }

  @Test
  public void shouldGetLogAndStationObjectsOnAdd() {
    when(logDao.getDocumentById(any())).thenReturn(new Log());
    when(stationDao.getDocumentById(any())).thenReturn(new Station());
    when(template.save(any())).thenReturn(testContact);
    contactDao.addNewDocument(testContact);
    verify(logDao).getDocumentById(any());
    verify(stationDao).getDocumentById(any());
  }

  @Test
  public void shouldGetLogAndStationObjectsOnUpdate() {
    when(logDao.getDocumentById(any())).thenReturn(new Log());
    when(stationDao.getDocumentById(any())).thenReturn(new Station());
    when(template.save(any())).thenReturn(testContact);
    when(template.findOne(any(), eq(Contact.class))).thenReturn(testContact);
    contactDao.updateDocument(testContact);
    verify(logDao).getDocumentById(any());
    verify(stationDao).getDocumentById(any());
  }
}
