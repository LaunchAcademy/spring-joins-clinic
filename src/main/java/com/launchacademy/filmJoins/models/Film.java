package com.launchacademy.filmJoins.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="films")
@NoArgsConstructor
@Getter
@Setter
public class Film {
  @Id
  @SequenceGenerator(name="film_generator", sequenceName = "films_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="film_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @Column(name="name", nullable=false)
  private String name;

  @ManyToOne
  @JoinColumn(name="studio_id", nullable=false)
  @JsonIgnoreProperties("films")
  private Studio studio;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name="castings",
      joinColumns = {
          @JoinColumn(name="film_id", nullable=false)
      },
      inverseJoinColumns = {
          @JoinColumn(name="actor_id", nullable=false)
      }
  )
  @JsonIgnoreProperties("films")
  private Set<Actor> actors;
}

