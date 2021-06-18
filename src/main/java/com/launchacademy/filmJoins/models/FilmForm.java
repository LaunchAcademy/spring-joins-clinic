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
}
