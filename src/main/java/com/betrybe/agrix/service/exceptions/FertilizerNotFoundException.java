package com.betrybe.agrix.service.exceptions;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends NotFoundExpection {
  /**
   * Instantiates a new Fertilizer not found exception.
   */
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
