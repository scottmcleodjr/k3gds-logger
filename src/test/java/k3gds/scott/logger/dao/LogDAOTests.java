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

import k3gds.scott.logger.dao.LogDAO;
import k3gds.scott.logger.document.Contact;
import k3gds.scott.logger.document.Log;
import k3gds.scott.logger.exception.DocumentNotFoundException;

/**
 * Test cases for LogDAO class.
 * 
 * @author Scott McLeod Jr.
 */
public final class LogDAOTests {

  @Mock
  private MongoTemplate template;

  @InjectMocks
  private LogDAO logDao;

  private Log testLog;
  private final String TEST_ID = "TEST_ID";

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    testLog = new Log();
  }

  @Test(expected = DocumentNotFoundException.class)
  public void shouldThrowExceptionIfLogNotFound() {
    when(template.findOne(any(), eq(Log.class))).thenReturn(null);
    logDao.getDocumentById(TEST_ID);
  }

  @Test
  public void shouldCallGetWhenUpdatingLog() {
    testLog.setId(TEST_ID);
    when(template.findOne(any(), eq(Log.class))).thenReturn(testLog);
    when(template.save(any())).thenReturn(testLog);
    logDao.updateDocument(testLog);
    verify(template).findOne(any(), eq(Log.class));
  }

  @Test
  public void shouldDeleteContactWhenDeletingLog() {
    when(template.remove(any(), eq(Contact.class))).thenReturn(null);
    when(template.remove(any(), eq(Log.class))).thenReturn(new DeleteResult() {
      @Override
      public boolean wasAcknowledged() {
        return false;
      }

      @Override
      public long getDeletedCount() {
        return 1; // Return 1 to prevent exception
      }
    });
    logDao.deleteDocument(TEST_ID);
    verify(template).remove(any(), eq(Contact.class));
  }
}
