package com.abubakar.model;

import com.abubakar.domain.HomeCategorySection;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HomeCategory {
  @jakarta.persistence.Id
  @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  private Long id;

  private String name;

  private String image;

  private String categoryId;

  private HomeCategorySection section;
}
