package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.CropController;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param farmService       the farm service
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService,
                     FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create crop crop.
   *
   * @param idFarm the id farm
   * @param crop   the crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop createCrop(Long idFarm, Crop crop) throws
          FarmNotFoundException, CropNotFoundException {
    Farm farm = farmService.getFarmPerId(idFarm);
    if (farm == null) {
      throw new FarmNotFoundException();
    }
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * List crops list.
   *
   * @return the list
   * @throws CropNotFoundException the crop not found exception
   */
  public List<Crop> listCrops() throws CropNotFoundException {
    return cropRepository.findAll();
  }

  /**
   * List per id crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop listPerId(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * List per haverst date list.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the list
   */
  public List<Crop> listPerHaverstDate(LocalDate startDate, LocalDate endDate) {
    return cropRepository.findByHarvestDateBetween(startDate, endDate);
  }

  /**
   * Add fertilizer crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Crop addFertilizer(Long cropId, Long fertilizerId) throws
          CropNotFoundException, FertilizerNotFoundException {
    Optional<Crop> crop = cropRepository.findById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    Crop crop1 = crop.get();
    Fertilizer fertilizer = fertilizerService.listFertilizerPerId(fertilizerId);
    if (fertilizer == null) {
      throw new FertilizerNotFoundException();
    }
    crop1.getFertilizerList().add(fertilizer);
    return cropRepository.save(crop1);
  }

  /**
   * Gets crop fertilizer.
   *
   * @param cropId the crop id
   * @return the crop fertilizer
   * @throws CropNotFoundException the crop not found exception
   */
  public List<Fertilizer> getCropFertilizer(Long cropId) throws CropNotFoundException {
    Optional<Crop> crop = cropRepository.findById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    Crop crop1 = crop.get();
    return crop1.getFertilizerList();
  }

}
