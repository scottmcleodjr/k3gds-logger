package k3gds.scott.logger.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import k3gds.scott.logger.dao.LogDAO;
import k3gds.scott.logger.document.Log;
import k3gds.scott.logger.exception.InvalidFieldException;

/**
 * Test cases for LogController class.
 * 
 * @author Scott McLeod Jr.
 */
public final class LogControllerTests {

  @Mock
  private LogDAO dao;

  @InjectMocks
  private LogController controller;

  private Log testLog;
  private final String TEST_ID = "TEST_ID";
  private final String TEST_ID_2 = "TEST_ID_2";

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    testLog = new Log();
  }

  @Test
  public void shouldAddNewLogWhenIdOmitted() {
    when(dao.addNewDocument(testLog)).thenReturn(testLog);
    controller.addNewDocument(testLog);
    verify(dao).addNewDocument(any());
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionIfNewLogIdPresent() {
    testLog.setId(TEST_ID);
    controller.addNewDocument(testLog);
  }

  @Test
  public void shouldUpdateLogWhenIdsPresentAndMatch() {
    testLog.setId(TEST_ID);
    when(dao.updateDocument(testLog)).thenReturn(testLog);
    controller.updateDocument(TEST_ID, testLog);
    verify(dao).updateDocument(testLog);
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionOnUpdateIfIdsDoNotMatch() {
    testLog.setId(TEST_ID);
    controller.updateDocument(TEST_ID_2, testLog);
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionOnUpdateIfLogIdNull() {
    controller.updateDocument(TEST_ID, testLog);
  }
}
