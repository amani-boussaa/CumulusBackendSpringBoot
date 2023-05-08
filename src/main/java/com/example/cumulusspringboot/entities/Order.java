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
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
//    @JsonIgnore
    private User user;

    @ManyToOne(optional = true)
//    @JoinColumn(name = "course_id", nullable = true)
//    @JsonIgnore
    private Course course;

//
//    public Order() {
//
//    }
//
//    public String getOrder_id() {
//        return order_id;
//    }
//
//    public void setOrder_id(String order_id) {
//        this.order_id = order_id;
//    }
//
//    public float getAmount() {
//        return amount;
//    }
//
//    public void setAmount(float amount) {
//        this.amount = amount;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public LocalDateTime getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(LocalDateTime dateCreated) {
//        this.dateCreated = dateCreated;
//    }
//
//    public LocalDateTime getDateUpdated() {
//        return dateUpdated;
//    }
//
//    public void setDateUpdated(LocalDateTime dateUpdated) {
//        this.dateUpdated = dateUpdated;
//    }
//
//    public Refund getRefund() {
//        return refund;
//    }
//
//    public void setRefund(Refund refund) {
//        this.refund = refund;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }
//
//    // Getters and setters


}
