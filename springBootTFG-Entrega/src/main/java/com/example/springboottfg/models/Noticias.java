package com.example.springboottfg.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "noticias")
public class Noticias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "titular", length = 60)
    private String titular;

    @Column(name = "cuerpo",length = 10000)
    private String cuerpo;

}
