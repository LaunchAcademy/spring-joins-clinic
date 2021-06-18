package com.launchacademy.filmJoins.services;

import com.launchacademy.filmJoins.models.Studio;
import com.launchacademy.filmJoins.repositories.StudioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {
  private StudioRepository repo;

  @Autowired
  public StudioService(StudioRepository repo) {
    this.repo = repo;
  }

  public List<Studio> findAll() {
    return (List<Studio>)repo.findAll();
  }

  public List<Studio> findAllByName(String name) {
    return (List<Studio>)repo.findAllByName(name);
  }

  public Studio findByName(String name) {
    return repo.findByName(name);
  }

  public void save(Studio studio) {
    repo.save(studio);
  }

  public Optional<Studio> findById(Integer id) {
    return repo.findById(id);
  }
}
