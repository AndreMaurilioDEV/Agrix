package com.betrybe.agrix.service;


import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Farm service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Create farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets farms.
   *
   * @return the farms
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Farm> getFarms() throws FarmNotFoundException {
    List<Farm> listFarms = farmRepository.findAll();
    if (listFarms.isEmpty()) {
      throw new FarmNotFoundException();
    }
    return listFarms;
  }

  /**
   * Gets farm per id.
   *
   * @param id the id
   * @return the farm per id
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm getFarmPerId(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

}
