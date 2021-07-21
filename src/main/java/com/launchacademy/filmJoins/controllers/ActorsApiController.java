package com.launchacademy.filmJoins.controllers;

import com.launchacademy.filmJoins.models.Actor;
import com.launchacademy.filmJoins.services.ActorService;
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
@RequestMapping("/api/v1/actors")
public class ActorsApiController {
  private ActorService actorService;

  @Autowired
  public ActorsApiController(ActorService actorService) {
    this.actorService = actorService;
  }

  @GetMapping
  public List<Actor> getIndex() {
    return actorService.findAll();
  }

  @GetMapping("/{id}")
  public Actor getShow(@PathVariable int id)
  {
    return actorService.findById(id).orElseThrow(() -> new ActorNotFoundException());
  }

  @NoArgsConstructor
  private class ActorNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class ActorNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ActorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(ActorNotFoundException ex) {
      return ex.getMessage();
    }
  }
}
