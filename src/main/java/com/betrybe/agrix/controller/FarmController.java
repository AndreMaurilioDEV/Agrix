package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   * @param cropService the crop service
   */
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create new farm farm dto.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createNewFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(farmService.createFarm(farmCreationDto.toEntity()));
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getFarms();
    return allFarms.stream().map(FarmDto::fromEntity).toList();
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmService.getFarmPerId(id));
  }

  /**
   * Crop farm crop dto.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto cropFarm(@PathVariable Long farmId, @RequestBody Crop crop)
          throws FarmNotFoundException {
    Crop created = cropService.createCrop(farmId, crop);
    return CropDto.fromEntity(created);
  }

  /**
   * List crop farm list.
   *
   * @param farmId the farm id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> listCropFarm(@PathVariable Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.getFarmPerId(farmId);
    if (farm == null) {
      throw new FarmNotFoundException();
    }
    return cropService.listCrops().stream()
            .filter(crop -> crop.getFarm().getId()
                    .equals(farmId))
            .map(CropDto::fromEntity)
            .toList();
  }
}
