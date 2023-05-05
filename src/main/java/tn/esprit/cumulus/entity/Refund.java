package tn.esprit.cumulus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="refund")
public class Refund {
    @Id
    @Column(name="refund_id", nullable = false, unique = true, length = 40)
    private String refund_id;
    @Column(name="reason", nullable = false)
    private String reason;
    @Column(name="status", nullable = false)
    private String status="pending";
    @Column(name="dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name="dateUpdated")
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Refund()
    {

    }
    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
