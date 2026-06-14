package com.employee.management.dto;

import lombok.Data;

@Data


public class StudentRequest {
    Integer id;
    String fname;
    String lname;
    private String phone;
    private String email;

    private AddressDTO address;



}
