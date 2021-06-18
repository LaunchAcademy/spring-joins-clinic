package com.launchacademy.filmJoins.repositories;

import com.launchacademy.filmJoins.models.Film;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends
    CrudRepository<Film, Integer> {
  public List<Film> findAllByName(String name);
  public Film findByName(String name);
}
