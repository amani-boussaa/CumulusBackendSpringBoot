package com.example.cumulusspringboot.entities;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="refund")
public class Refund implements Serializable {
    @Id
    @Column(name="refund_id", length = 40)
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


}
