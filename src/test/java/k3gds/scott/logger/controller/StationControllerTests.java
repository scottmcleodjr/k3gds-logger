package k3gds.scott.logger.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import k3gds.scott.logger.dao.StationDAO;
import k3gds.scott.logger.document.Station;
import k3gds.scott.logger.exception.InvalidFieldException;

/**
 * Test cases for StationController class.
 * 
 * @author Scott McLeod Jr.
 */
public final class StationControllerTests {

  @Mock
  private StationDAO dao;

  @InjectMocks
  private StationController controller;

  private Station testStation;
  private final String TEST_ID = "TEST_ID";
  private final String TEST_ID_2 = "TEST_ID_2";

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    testStation = new Station();
  }

  @Test
  public void shouldAddNewStationWhenIdOmitted() {
    when(dao.addNewDocument(testStation)).thenReturn(testStation);
    controller.addNewDocument(testStation);
    verify(dao).addNewDocument(any());
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionIfNewStationIdPresent() {
    testStation.setId(TEST_ID);
    controller.addNewDocument(testStation);
  }

  @Test
  public void shouldUpdateStationWhenIdsPresentAndMatch() {
    testStation.setId(TEST_ID);
    when(dao.updateDocument(testStation)).thenReturn(testStation);
    controller.updateDocument(TEST_ID, testStation);
    verify(dao).updateDocument(testStation);
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionOnUpdateIfIdsDoNotMatch() {
    testStation.setId(TEST_ID);
    controller.updateDocument(TEST_ID_2, testStation);
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionOnUpdateIfStationIdNull() {
    controller.updateDocument(TEST_ID, testStation);
  }
}
