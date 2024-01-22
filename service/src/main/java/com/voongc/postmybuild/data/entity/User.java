//package com.voongc.postmybuild.data.entity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
////user can create many jobs
////a user has one or many address
//@Data
//@Entity
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String forename;
//    private String surname;
//    private String email;
//    private String password;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Address> addresses;
//    private String phoneNumber;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Job> jobs;
//}
