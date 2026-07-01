package com.abubakar.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Coupon {
  @jakarta.persistence.Id
  @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  private Long id;

  private String code;

  private Double discountPercentage;

  private LocalDate validityStartDate;

  private LocalDate validityEndDate;

  private double minimumOrderValue;

  private boolean isActive=true; 
 
  @ManyToMany(mappedBy = "usedCoupons")
  private Set<User> userByUsers=new HashSet<>();
  
 
}
