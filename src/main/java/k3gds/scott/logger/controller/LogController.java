package k3gds.scott.logger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import k3gds.scott.logger.dao.BaseDAO;
import k3gds.scott.logger.dao.LogDAO;
import k3gds.scott.logger.document.Log;

/**
 * Log controller for Logger API.
 * 
 * @author Scott McLeod Jr.
 */
@RestController
@RequestMapping(value = "/logs")
public class LogController extends BaseController<Log> {

  @Autowired
  private LogDAO dao;

  @Override
  BaseDAO<Log> getDao() {
    return this.dao;
  }
}
