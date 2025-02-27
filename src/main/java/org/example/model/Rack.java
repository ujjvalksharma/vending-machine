package org.example.model;

import java.util.List;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Rack {
  int id;
  List<Product> products;
}
