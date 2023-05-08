package com.example.cumulusspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import lombok.*;
import java.io.Serializable;

//@Entity
//@AllArgsConstructor
//@Table(name="orders")

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="order_id", nullable = false, unique = true)
    private String order_id;
    @Column(name="amount")
    private float amount= 0.00F;
    @Column(name="currency",length = 3)
    private String currency;
    @Column(name="status")
    private String status="succeeded";
    @Column(name="type")
    private String type;

    @JsonIgnore
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Refund refund;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;



}
