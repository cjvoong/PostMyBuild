//package com.voongc.postmybuild.data.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
////A job has many quotes
//@Data
//@Entity
//public class Quote {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private float price;
//    @ManyToOne
//    @JsonBackReference
//    private Builder builder;
//    private String description;
//    private Date expiry;
//    @ManyToOne
//    @JsonBackReference
//    private Shortlist shortlist;
//    @OneToOne
//    @JsonBackReference
//    private Job job;
//}
