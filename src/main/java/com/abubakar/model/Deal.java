package com.abubakar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class Deal {

  @jakarta.persistence.Id
  @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  private Long id;

  private Integer dicount;

  @OneToOne
  private HomeCategory category;

  
 

}
