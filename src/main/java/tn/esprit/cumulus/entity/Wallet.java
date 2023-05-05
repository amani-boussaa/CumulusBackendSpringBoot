package tn.esprit.cumulus.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="wallet")
public class Wallet {

    @Id
    @Column(name="wallet_id", nullable = false, unique = true)
    private String wallet_id;
    @Column(name="balance")
    private float balance= 0.00F;
    @Column(name="coins")
    private int coins=0;
    @Column(name="currency", nullable = false, length = 3)
    private String currency="GBP";
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Wallet() {
        // this.currency = "USD";
    }
    public Wallet(String wallet_id, float balance, int coins, String currency, String payment_method) {
        this.wallet_id = wallet_id;
        this.balance = balance;
        this.coins = coins;
        this.currency = currency;
        this.payment_method = payment_method;

    }
    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
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

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }
}
