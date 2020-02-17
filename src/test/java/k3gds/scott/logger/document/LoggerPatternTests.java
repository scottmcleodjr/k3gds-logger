package k3gds.scott.logger.document;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Test cases for LoggerPattern class regexes.
 * 
 * @author Scott McLeod Jr.
 */
public final class LoggerPatternTests {

  @Test
  public void shouldMatchValidCallsign() {
    assert ("HB0/K3GDS/QRP".matches(LoggerPattern.CALL_SIGN));
  }

  @Test
  public void shouldNotMatchInvalidCallsign() {
    assertFalse("/5R".matches(LoggerPattern.CALL_SIGN));
  }

  @Test
  public void shouldMatchValidLongGridSquare() {
    assert ("FN00bl".matches(LoggerPattern.GRID_SQUARE));
  }

  @Test
  public void shouldMatchValidShortGridSquare() {
    assert ("FN00".matches(LoggerPattern.GRID_SQUARE));
  }

  @Test
  public void shouldNotMatchInvalidGridSquare() {
    assertFalse("F012xx".matches(LoggerPattern.GRID_SQUARE));
  }

  @Test
  public void shouldMatchValidFrequency() {
    assert ("14.313".matches(LoggerPattern.FREQUENCY));
  }

  @Test
  public void shouldNotMatchInvalidFrequency() {
    assertFalse("13.2.1".matches(LoggerPattern.FREQUENCY));
  }
}
