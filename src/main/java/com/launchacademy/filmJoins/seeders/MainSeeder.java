package com.launchacademy.filmJoins.seeders;

import com.launchacademy.filmJoins.services.ActorService;
import com.launchacademy.filmJoins.services.FilmService;
import com.launchacademy.filmJoins.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {
  private StudioSeeder studioSeeder;
  private FilmSeeder filmSeeder;
  private ActorSeeder actorSeeder;

  @Autowired
  public MainSeeder(StudioSeeder studioSeeder, FilmSeeder filmSeeder, ActorSeeder actorSeeder) {
    this.studioSeeder = studioSeeder;
    this.filmSeeder = filmSeeder;
    this.actorSeeder = actorSeeder;
  }

  @Override
  public void run(String... args) throws Exception {
    studioSeeder.seed();
    filmSeeder.seed();
    actorSeeder.seed();
  }
}
