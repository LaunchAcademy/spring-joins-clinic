package com.launchacademy.filmJoins.models;

import com.launchacademy.filmJoins.services.FilmService;
import com.launchacademy.filmJoins.services.StudioService;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class FilmForm {
  @NotBlank
  private String name;
  @NotNull
  private Integer studioId;
  private StudioService studioService;
  private FilmService filmService;

  @Autowired
  public void setServices(StudioService studioService, FilmService filmService) {
    this.studioService = studioService;
    this.filmService = filmService;
  }

  public Film createFilm() {
    Film film = new Film();
    film.setName(this.name);
    System.out.println(this.studioId);
    System.out.println(studioService);
    Optional<Studio> studio = studioService.findById(this.studioId);
    System.out.println("made it past the findById");
    if(studio.isPresent()) {
      film.setStudio(studio.get());
    }
    return filmService.save(film);
  }
}
