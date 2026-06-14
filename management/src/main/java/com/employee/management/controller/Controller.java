package com.employee.management.controller;
// import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.employee.management.dto.StudentRequest;
import com.employee.management.dto.StudentResponse;
import com.employee.management.model.Student;
import com.employee.management.service.StudentServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor   // generates a constructor with required arguments(final fields) for dependency injection 
@RestController
@RequestMapping("/url")
public class Controller {
    
    // private ArrayList<Student> arr = new ArrayList<>();
    
    // public Controller(){
    //     arr.add(new Student(1,"A","B"));
    //     arr.add(new Student(2,"C","D"));
    //     arr.add(new Student(3,"E","F"));
    // }
    
    private final StudentServices studentservices;    // dependency injection 

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudent(){
        // return studentservices.getAllStudent();
        return ResponseEntity.ok(studentservices.getAllStudent());
    }

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody Student s){
        // studentservices.addStudent(s);
        // return "Student added successfully";
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(studentservices.addStudent(s));
        

    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Integer id,@RequestBody Student s){
        // return studentservices.updateStudent(id,s);
        StudentResponse s1 = studentservices.updateStudent(id,s);
        if(s1!=null){
            return ResponseEntity.ok(s1);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> fetchStudent(@PathVariable Integer id){
        // return studentservices.fetchStudent(id);
        StudentResponse s1 = studentservices.fetchStudent(id);
        if(s1!=null){
            return ResponseEntity.ok(s1);
        }
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<StudentResponse>> deleteStudent(@PathVariable Integer id){
        // int res = studentservices.deleteStudent(id);
        // if(res==1){
        //     return ResponseEntity.ok("Student data deleted");
        // }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        studentservices.deleteStudent(id);
        return ResponseEntity.ok(studentservices.getAllStudent());
    }

    // @GetMapping("/arr")
    // public ArrayList<Student> s1(){
    //     return arr;
    // }
    // @PostMapping("/arr")
    // public String s2(@RequestBody Student s){
    //     arr.add(s);
    //     return "Student Added Successfully";

    // }
    // @PutMapping("/arr/{id}")
    // public String s3(@PathVariable int id, @RequestBody Student s) {
    //     for (int i = 0; i < arr.size(); i++) {
    //         if (arr.get(i).getId() == id) {
    //             arr.set(i, s);
    //             return "Student updated successfully";
    //         }
    //     }
    //     return "Student not found";
    // }
    


}

