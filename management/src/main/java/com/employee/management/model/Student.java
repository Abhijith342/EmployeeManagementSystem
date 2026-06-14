package com.employee.management.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String fname;
    String lname;
    private String phone;
    private String email;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="address_id",referencedColumnName = "id")
    private Address address;
    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole=UserRole.STUDENT;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    



   
    

    // Student(int id,String fname,String lname){

    //     this.id = id;
    //     this.fname = fname;
    //     this.lname = lname;

    // }
    // public int getId() {return id;}
    // public String getFname() { return fname;}
    // public String getLname(){return lname;}

    // public void setId(int id){
    //     this.id =id;
    // }
    // public void setFname(String fname){this.fname=fname;}
    // public void setLname(String lname){this.lname=lname;}
    


}
