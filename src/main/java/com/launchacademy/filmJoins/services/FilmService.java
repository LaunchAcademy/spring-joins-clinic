package com.launchacademy.filmJoins.services;

import com.launchacademy.filmJoins.models.*;
import com.launchacademy.filmJoins.repositories.FilmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
  private FilmRepository repo;
  private StudioService studioService;

  @Autowired
  public FilmService(FilmRepository repo, StudioService studioService) {
    this.studioService = studioService;
    this.repo = repo;
  }

  public List<Film> findAll() {
    return (List<Film>)repo.findAll();
  }

  public List<Film> findAllByName(String name) {
    return (List<Film>)repo.findAllByName(name);
  }

  public Film findByName(String name) {
    return repo.findByName(name);
  }

  public Film save(Film film) {
    return repo.save(film);
  }

  public Optional<Film> findById(int id) {
    return repo.findById(id);
  }

  public Film createFilm(FilmForm form) {
    Film film = new Film();
    film.setName(form.getName());
    Optional<Studio> studio = studioService.findById(form.getStudioId());
    if(studio.isPresent()) {
      film.setStudio(studio.get());
    }
    return repo.save(film);
  }
}
