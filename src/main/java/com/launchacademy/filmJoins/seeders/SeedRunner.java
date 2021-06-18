package com.launchacademy.filmJoins.seeders;

import com.launchacademy.filmJoins.models.Actor;
import com.launchacademy.filmJoins.models.Film;
import com.launchacademy.filmJoins.models.Studio;
import com.launchacademy.filmJoins.services.ActorService;
import com.launchacademy.filmJoins.services.FilmService;
import com.launchacademy.filmJoins.services.StudioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedRunner implements CommandLineRunner {
  private StudioService studioService;
  private FilmService filmService;
  private ActorService actorService;

  @Autowired
  public SeedRunner(StudioService studioService, FilmService filmService, ActorService actorService) {
    this.studioService = studioService;
    this.filmService = filmService;
    this.actorService = actorService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<String> studioSeeds = new ArrayList<>();
    studioSeeds.add("FilmNation Entertainment, LLC");
    studioSeeds.add("Columbia Pictures");

    for(String name : studioSeeds) {
      List existingStudios = studioService.findAllByName(name);
      if(existingStudios.size() == 0) {
        Studio studio = new Studio();
        studio.setName(name);
        studioService.save(studio);
      }
    }

    Studio filmNation = studioService.findByName("FilmNation Entertainment, LLC");
    Studio columbia = studioService.findByName("Columbia Pictures");

    Map<String, Studio> filmSeeds = new HashMap<>();
    filmSeeds.put("Arrival", filmNation);
    filmSeeds.put("Julie & Julia", columbia);
    filmSeeds.put("Ghostbusters", columbia);

    for(String name : filmSeeds.keySet()) {
      List existingFilms = filmService.findAllByName(name);
      if(existingFilms.size() == 0) {
        Film film = new Film();
        film.setName(name);
        film.setStudio(filmSeeds.get(name));
        filmService.save(film);
      }
    }

    List<String> actorSeeds = new ArrayList<>();
    actorSeeds.add("Amy Adams");
    actorSeeds.add("Jeremy Renner");

    for(String name : actorSeeds) {
      List existingActors = actorService.findAllByName(name);
      if(existingActors.size() == 0) {
        Actor actor = new Actor();
        actor.setName(name);
        actorService.save(actor);
      }
    }

    Film arrival = filmService.findByName("Arrival");
    Film julieAndJulia = filmService.findByName("Julie & Julia");
    Film ghostbusters = filmService.findByName("Ghostbusters");

    Actor adams = actorService.findByName("Amy Adams");
    Actor renner = actorService.findByName("Jeremy Renner");

//    Set<Actor> existingArrivalActors = arrival.getActors();


    Set<Actor> arrivalActors = new HashSet<>();
    arrivalActors.add(adams);
    arrivalActors.add(renner);
    Set<Actor> jAndJActors = new HashSet<>();
    jAndJActors.add(adams);

//    arrival.setActors(arrivalActors);
//    julieAndJulia.setActors(jAndJActors);
    arrival.getActors().addAll(arrivalActors);
    Set<Actor> originalActors = arrival.getActors();
    originalActors.addAll(arrivalActors);
    arrival.setActors(originalActors);
    julieAndJulia.getActors().addAll(jAndJActors);
    filmService.save(arrival);
    filmService.save(julieAndJulia);
//    Set<Film> adamsFilms = new HashSet<>();
//    adamsFilms.add(ghostbusters);
//    adams.getFilms().addAll(adamsFilms);
//    actorService.save(adams);
  }
}
