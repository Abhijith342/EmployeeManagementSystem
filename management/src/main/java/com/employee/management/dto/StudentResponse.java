package com.employee.management.dto;
import com.employee.management.model.UserRole;

import lombok.Data;

@Data

public class StudentResponse {
    Integer id;
    String fname;
    String lname;
    private String phone;
    private String email;
    private UserRole userRole;
    private AddressDTO address;
   
}
