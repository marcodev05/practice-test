package com.tsk.practice;

import javax.persistence.*;

@Entity
@Table(name = "practices")
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int duration;
}
