package com.example.cumulusspringboot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatusComplaint status;
//    @ManyToOne
    @Enumerated(EnumType.STRING)
    private CategoryComplaint categorycomplaint;
    @ManyToOne
    private User user;
    private LocalDateTime createdAt; // add createdAt field
    @PrePersist // add PrePersist annotation to set createdAt value automatically
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
