package com.example.cumulusspringboot.entities.oubaid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contentType;

    private String type;

    private Long size;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;
}
