package k3gds.scott.logger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import k3gds.scott.logger.dao.BaseDAO;
import k3gds.scott.logger.dao.StationDAO;
import k3gds.scott.logger.document.Station;

/**
 * Station controller for Logger API.
 * 
 * @author Scott McLeod Jr.
 */
@RestController
@RequestMapping(value = "/stations")
public class StationController extends BaseController<Station> {

  @Autowired
  private StationDAO dao;

  @Override
  BaseDAO<Station> getDao() {
    return this.dao;
  }
}
