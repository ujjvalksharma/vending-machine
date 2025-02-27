package org.example.model;

import org.example.constant.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
  int id;
  ProductType productType;
  int price;
}
