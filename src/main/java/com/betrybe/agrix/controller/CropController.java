package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * List crops list.
   *
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
  public List<CropDto> listCrops() throws FarmNotFoundException {
    List<Crop> cropList = cropService.listCrops();
    return cropList.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Gets crop per id.
   *
   * @param id the id
   * @return the crop per id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getCropPerId(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(cropService.listPerId(id));
  }

  /**
   * Gets crop per harvest date.
   *
   * @param startDate the start date
   * @param endDate   the end date
   * @return the crop per harvest date
   */
  @GetMapping("/search")
  public List<CropDto> getCropPerHarvestDate(
          @RequestParam("start") LocalDate startDate,
          @RequestParam("end") LocalDate endDate
  ) {
    List<Crop> cropList = cropService.listPerHaverstDate(startDate, endDate);
    return cropList.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Add fertilizer response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   * @throws FarmNotFoundException the farm not found exception
   * @throws CropNotFoundException the crop not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizer(@PathVariable Long cropId,
                                              @PathVariable Long fertilizerId) throws
          FarmNotFoundException,
          CropNotFoundException {
    CropDto.fromEntity(cropService.addFertilizer(cropId, fertilizerId));
    return ResponseEntity.status(HttpStatus.CREATED).body(
                  "Fertilizante e plantação associados com sucesso!"
          );
  }

  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getCropFertilizer(@PathVariable Long cropId) throws
          CropNotFoundException {
    List<Fertilizer> fertilizers =  cropService.getCropFertilizer(cropId);
    return fertilizers.stream().map(FertilizerDto::fromEntity).toList();
  }
}
