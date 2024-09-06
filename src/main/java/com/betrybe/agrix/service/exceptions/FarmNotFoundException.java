package com.betrybe.agrix.service.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

/**
 * The type Farm not found exception.
 */
public class FarmNotFoundException extends NotFoundExpection {
  /**
   * Instantiates a new Farm not found exception.
   */
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
