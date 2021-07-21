package com.launchacademy.filmJoins.services;

import com.launchacademy.filmJoins.models.Actor;
import com.launchacademy.filmJoins.models.Studio;
import com.launchacademy.filmJoins.repositories.ActorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
  private ActorRepository repo;

  @Autowired
  public ActorService(ActorRepository repo) {
    this.repo = repo;
  }

  public List<Actor> findAll() {
    return (List<Actor>)repo.findAll();
  }

  public List<Actor> findAllByName(String name) {
    return (List<Actor>)repo.findAllByName(name);
  }

  public Actor findByName(String name) {
    return repo.findByName(name);
  }

  public void save(Actor actor) {
    repo.save(actor);
  }

  public Optional<Actor> findById(Integer id) {
    return repo.findById(id);
  }
}
