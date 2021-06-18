package com.launchacademy.filmJoins.controllers;

import com.launchacademy.filmJoins.models.Studio;
import com.launchacademy.filmJoins.services.StudioService;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/studios")
public class StudiosApiController {
  private StudioService service;

  @Autowired
  public StudiosApiController(StudioService service) {
    this.service = service;
  }

  @GetMapping
  public List<Studio> getIndex() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Studio getShow(@PathVariable int id)
  {
    return service.findById(id).orElseThrow(() -> new StudioNotFoundException());
  }

  @NoArgsConstructor
  private class StudioNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class StudioNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StudioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(StudioNotFoundException ex) {
      return ex.getMessage();
    }
  }
}
