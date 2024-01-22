//package com.voongc.postmybuild.data.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
////A job has a shortlist
//@Data
//@Entity
//public class Shortlist {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @OneToMany(mappedBy = "shortlist", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Quote> quotes;
//    @OneToOne
//    @JsonBackReference
//    private Job job;
//}
