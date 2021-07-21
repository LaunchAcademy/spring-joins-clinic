package com.launchacademy.filmJoins.seeders;

import com.launchacademy.filmJoins.models.Actor;
import com.launchacademy.filmJoins.models.Film;
import com.launchacademy.filmJoins.services.ActorService;
import com.launchacademy.filmJoins.services.FilmService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorSeeder {

  private final FilmService filmService;
  private final ActorService actorService;

  @Autowired
  public ActorSeeder(FilmService filmService,
      ActorService actorService) {
    this.filmService = filmService;
    this.actorService = actorService;
  }

  public void seed() {
    if (actorService.findAll().size() == 0) {
      List<String> actorSeeds = new ArrayList<>();
      actorSeeds.add("Amy Adams");
      actorSeeds.add("Jeremy Renner");

      for (String name : actorSeeds) {
        List existingActors = actorService.findAllByName(name);
        if (existingActors.size() == 0) {
          Actor actor = new Actor();
          actor.setName(name);
          actorService.save(actor);
        }
      }
    }
    Film arrival = filmService.findByName("Arrival");
    Film julieAndJulia = filmService.findByName("Julie & Julia");
    Film ghostbusters = filmService.findByName("Ghostbusters");
    Actor adams = actorService.findByName("Amy Adams");
    Actor renner = actorService.findByName("Jeremy Renner");

    adams.addFilm(ghostbusters);
    adams.addFilm(julieAndJulia);
    adams.addFilm(arrival);

    renner.addFilm(arrival);

    List<Film> filmList = new ArrayList<>();
    filmList.add(arrival);
    filmList.add(ghostbusters);
    filmList.add(julieAndJulia);

    for (Film film : filmList) {
      filmService.save(film);
    }
  }
}
