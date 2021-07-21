package com.launchacademy.filmJoins.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="actors")
@Getter
@Setter
@NoArgsConstructor
public class Actor {
  @Id
  @SequenceGenerator(name="actor_generator", sequenceName = "actors_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="actor_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @Column(name="name", nullable=false)
  private String name;

  @ManyToMany(mappedBy="actors", fetch = FetchType.EAGER)
  @JsonIgnoreProperties("actors")
  private Set<Film> films = new HashSet<Film>();

  public void addFilm(Film film) {
    this.films.add(film);
    film.getActors().add(this);
  }

  public void removeFilm(Film film) {
    this.films.remove(film);
    film.getActors().remove(this);
  }
}
