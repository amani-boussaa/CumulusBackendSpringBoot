package com.example.cumulusspringboot.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24; // 1 day

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public PasswordResetToken(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }
}


