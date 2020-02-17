package k3gds.scott.logger.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mongodb.client.result.DeleteResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import k3gds.scott.logger.document.Contact;
import k3gds.scott.logger.document.Station;
import k3gds.scott.logger.exception.DocumentNotFoundException;

/**
 * Test cases for StationDAO class.
 * 
 * @author Scott McLeod Jr.
 */
public final class StationDAOTests {

  @Mock
  private MongoTemplate template;

  @InjectMocks
  private StationDAO stationDao;

  private Station testStation;
  private final String TEST_ID = "TEST_ID";

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    testStation = new Station();
  }

  @Test(expected = DocumentNotFoundException.class)
  public void shouldThrowExceptionIfStationNotFound() {
    when(template.findOne(any(), eq(Station.class))).thenReturn(null);
    stationDao.getDocumentById(TEST_ID);
  }

  @Test
  public void shouldCallGetWhenUpdatingStation() {
    testStation.setId(TEST_ID);
    when(template.findOne(any(), eq(Station.class))).thenReturn(testStation);
    when(template.save(any())).thenReturn(testStation);
    stationDao.updateDocument(testStation);
    verify(template).findOne(any(), eq(Station.class));
  }

  @Test
  public void shouldDeleteContactWhenDeletingStation() {
    when(template.remove(any(), eq(Contact.class))).thenReturn(null);
    when(template.remove(any(), eq(Station.class))).thenReturn(new DeleteResult() {
      @Override
      public boolean wasAcknowledged() {
        return false;
      }

      @Override
      public long getDeletedCount() {
        return 1; // Return 1 to prevent exception
      }
    });
    stationDao.deleteDocument(TEST_ID);
    verify(template).remove(any(), eq(Contact.class));
  }
}
