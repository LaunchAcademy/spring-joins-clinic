package com.launchacademy.filmJoins.seeders;

import com.launchacademy.filmJoins.models.Studio;
import com.launchacademy.filmJoins.services.StudioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StudioSeeder {
  private StudioService studioService;

  public StudioSeeder(StudioService studioService) {
    this.studioService = studioService;
  }

  public void seed() {
    if (this.studioService.findAll().size() == 0) {
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
    }
  }
}
