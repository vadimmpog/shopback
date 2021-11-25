package com.example.shopback.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Float sum;
    @CreationTimestamp
    private Date orderDate;
    @CreationTimestamp
    private Time orderTime;
    private Integer userId;

//    public void update(Order o){
//        if (this.sum == null || this.sum == 0) this.setSum(o.getSum());
//        if (this.orderDate == null) this.setOrderDate(o.getOrderDate());
//        if (this.orderTime == null) this.setOrderTime(o.getOrderTime());
//        if (this.userId == null || this.userId == 0) this.setUserId(o.getUserId());
//    }
}
