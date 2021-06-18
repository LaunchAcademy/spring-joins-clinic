package com.launchacademy.filmJoins.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="studios")
@NoArgsConstructor
@Getter
@Setter
public class Studio {
  @Id
  @SequenceGenerator(name="studio_generator", sequenceName = "studios_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="studio_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @Column(name="name", nullable=false)
  private String name;

  @OneToMany(mappedBy="studio")
  @JsonIgnoreProperties("studio")
  private Set<Film> films;
}
