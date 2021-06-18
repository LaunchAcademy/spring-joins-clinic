package com.launchacademy.filmJoins.controllers;

import com.launchacademy.filmJoins.models.Film;
import com.launchacademy.filmJoins.models.FilmForm;
import com.launchacademy.filmJoins.services.FilmService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/films")
public class FilmsApiController {
  private FilmService service;

  @Autowired
  public FilmsApiController(FilmService service) {
    this.service = service;
  }

  @GetMapping
  public List<Film> getIndex() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity create(@Valid @RequestBody FilmForm filmForm, BindingResult bindingResult) {
    System.out.println("IN POST MAPPING");
    if(bindingResult.hasErrors()) {
      Map<String, List> errorMap = new HashMap<>();
      errorMap.put("errors", bindingResult.getAllErrors());
      return new ResponseEntity<Map<String, List>>(errorMap, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    else {
      return new ResponseEntity<Film>(filmForm.createFilm(), HttpStatus.CREATED);
    }
  }

  @GetMapping("/{id}")
  public Film getShow(@PathVariable int id)
  {
    return service.findById(id).orElseThrow(() -> new FilmNotFoundException());
  }

  @NoArgsConstructor
  private class FilmNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class FilmNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(FilmsApiController.FilmNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(FilmsApiController.FilmNotFoundException ex) {
      return ex.getMessage();
    }
  }
}
