package k3gds.scott.logger.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import k3gds.scott.logger.dao.ContactDAO;
import k3gds.scott.logger.document.Contact;
import k3gds.scott.logger.exception.InvalidFieldException;

/**
 * Test cases for ContactController class.
 * 
 * @author Scott McLeod Jr.
 */
public final class ContactControllerTests {

  @Mock
  private ContactDAO dao;

  @InjectMocks
  private ContactController controller;

  private Contact testContact;
  private final String TEST_ID = "TEST_ID";
  private final String TEST_ID_2 = "TEST_ID_2";

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    testContact = new Contact();
  }

  @Test
  public void shouldAddNewContactWhenIdOmitted() {
    when(dao.addNewDocument(testContact)).thenReturn(testContact);
    controller.addNewDocument(testContact);
    verify(dao).addNewDocument(any());
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionIfNewContactIdPresent() {
    testContact.setId(TEST_ID);
    controller.addNewDocument(testContact);
  }

  @Test
  public void shouldUpdateContactWhenIdsPresentAndMatch() {
    testContact.setId(TEST_ID);
    when(dao.updateDocument(testContact)).thenReturn(testContact);
    controller.updateDocument(TEST_ID, testContact);
    verify(dao).updateDocument(testContact);
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionOnUpdateIfIdsDoNotMatch() {
    testContact.setId(TEST_ID);
    controller.updateDocument(TEST_ID_2, testContact);
  }

  @Test(expected = InvalidFieldException.class)
  public void shouldThrowExceptionOnUpdateIfContactIdNull() {
    controller.updateDocument(TEST_ID, testContact);
  }
}
