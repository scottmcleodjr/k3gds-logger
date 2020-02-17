package k3gds.scott.logger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import k3gds.scott.logger.dao.BaseDAO;
import k3gds.scott.logger.dao.ContactDAO;
import k3gds.scott.logger.document.Contact;

/**
 * Contact controller for Logger API.
 * 
 * @author Scott McLeod Jr.
 */
@RestController
@RequestMapping(value = "/contacts")
public class ContactController extends BaseController<Contact> {

  @Autowired
  private ContactDAO dao;

  @Override
  BaseDAO<Contact> getDao() {
    return this.dao;
  }
}
