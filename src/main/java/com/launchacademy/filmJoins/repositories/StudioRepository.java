package com.launchacademy.filmJoins.repositories;

import com.launchacademy.filmJoins.models.Studio;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends
    CrudRepository<Studio, Integer> {

  public List<Studio> findAllByName(String name);
  Studio findByName(String name);
}
