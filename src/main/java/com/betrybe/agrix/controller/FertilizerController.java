package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create new fertilizer fertilizer dto.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createNewFertilizer(@RequestBody FertilizerCreationDto
                                                     fertilizerCreationDto) {
    return FertilizerDto.fromEntity(
            fertilizerService.createFertilizer(fertilizerCreationDto.toEntity()));
  }

  /**
   * List fertilizers list.
   *
   * @return the list
   */
  @GetMapping
  @Secured("ROLE_ADMIN")
  public List<FertilizerDto> listFertilizers() {
    List<Fertilizer> fertilizerList = fertilizerService.listFertilizer();
    return fertilizerList.stream().map(FertilizerDto::fromEntity).toList();
  }

  /**
   * List fertilizer per id fertilizer dto.
   *
   * @param id the id
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public FertilizerDto listFertilizerPerId(@PathVariable Long id)
          throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(fertilizerService.listFertilizerPerId(id));
  }
}
