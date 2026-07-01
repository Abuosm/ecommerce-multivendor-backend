package com.abubakar.model;

import java.util.List;

import lombok.Data;

@Data
public class Home {

  private List<HomeCategory> grid;

  private List<HomeCategory> electricCategories;

  private List<HomeCategory> shopByCategory;

    private List<HomeCategory> dealCategories;

  private List<Deal> deals;

}
