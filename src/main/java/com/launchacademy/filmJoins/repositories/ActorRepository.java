package com.launchacademy.filmJoins.repositories;

import com.launchacademy.filmJoins.models.Actor;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {
  public List<Actor> findAllByName(String name);
  public Actor findByName(String name);
}
