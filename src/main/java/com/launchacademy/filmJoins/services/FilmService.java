package com.launchacademy.filmJoins.services;

import com.launchacademy.filmJoins.models.Film;
import com.launchacademy.filmJoins.repositories.FilmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
  private FilmRepository repo;

  @Autowired
  public FilmService(FilmRepository repo) {
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
}
