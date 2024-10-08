package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create fertilizer fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * List fertilizer list.
   *
   * @return the list
   */
  public List<Fertilizer> listFertilizer() {
    return fertilizerRepository.findAll();
  }

  /**
   * List fertilizer per id fertilizer.
   *
   * @param id the id
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer listFertilizerPerId(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }
}
