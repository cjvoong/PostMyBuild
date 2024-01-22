package com.voongc.postmybuild.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

//A user can create many jobs
//A job has one builder assigned eventually
@Data
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Quote selectedQuote;
    @OneToOne
    @JsonManagedReference
    private Shortlist shortlist;
    @ManyToOne
    @JsonBackReference
    private User user;
    private JobStatus status = JobStatus.NEW;
}
