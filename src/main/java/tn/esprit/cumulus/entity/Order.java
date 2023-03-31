package tn.esprit.cumulus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @Column(name="order_id", nullable = false, unique = true)
    private String order_id;
    @Column(name="amount", nullable = false)
    private float amount;
    @Column(name="currency", nullable = false, length = 3)
    private String currency;
    @Column(name="status", nullable = false)
    private String status;
    @Column(name="type")
    private String type;
    @Column(name="dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name="dateUpdated")
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    @OneToOne(mappedBy = "order")
    private Refund refund;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
//    private Wallet wallet;

    public Order() {

    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    // Getters and setters


}
