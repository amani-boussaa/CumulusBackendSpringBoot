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
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="wallet_id", nullable = false, unique = true)
    private String wallet_id;
    @Column(name="balance")
    private float balance= 0.00F;
    @Column(name="coins")
    private int coins=0;
    @Column(name="currency", nullable = false, length = 3)
    private String currency="USD";
    @Column(name="payment_method", nullable = true)
    private String payment_method;
    @Column(name="subscription", nullable = true)
    private String subscription="None";
    @Column(name="dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name="dateUpdated")
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @OneToOne
    private User user;


}
