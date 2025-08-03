package com.zzzrbn.taskuserservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="userrecords")
public class Userrecord{
	
    @Id
    //@ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //@JsonIgnore
    private Long id;

    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phoneNumber")
    private String phoneNumber;
    
    @Column(name = "company_id")
    private Long companyId;

//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    //@JsonIgnore
//    //@JsonManagedReference
//    @JsonBackReference
    
    @Transient
    private Company company;                     
    
}
