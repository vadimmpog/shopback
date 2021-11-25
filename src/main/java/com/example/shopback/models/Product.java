package com.example.shopback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Float price;

    public void update(Product p){
        if (this.name == null || this.name.equals("")) this.setName(p.getName());
        if (this.description == null || this.description.equals("")) this.setDescription(p.getDescription());
        if (this.price == null || this.price == 0) this.setPrice(p.getPrice());
        if (this.quantity == null || this.quantity == 0) this.setQuantity(p.getQuantity());
    }
}
