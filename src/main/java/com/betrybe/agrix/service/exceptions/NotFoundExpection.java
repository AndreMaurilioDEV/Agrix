package com.betrybe.agrix.service.exceptions;

/**
 * The type Not found expection.
 */
public class NotFoundExpection extends RuntimeException {
  /**
   * Instantiates a new Not found expection.
   *
   * @param message the message
   */
  public NotFoundExpection(String message) {
    super(message);
  }
}
