package com.launchacademy.filmJoins.seeders;

import com.launchacademy.filmJoins.models.Film;
import com.launchacademy.filmJoins.models.Studio;
import com.launchacademy.filmJoins.services.FilmService;
import com.launchacademy.filmJoins.services.StudioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmSeeder {
  private StudioService studioService;
  private FilmService filmService;

  @Autowired
  public FilmSeeder(StudioService studioService,
      FilmService filmService) {
    this.studioService = studioService;
    this.filmService = filmService;
  }

  public void seed() {
    if (filmService.findAll().size() == 0) {
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
    }
  }
}
