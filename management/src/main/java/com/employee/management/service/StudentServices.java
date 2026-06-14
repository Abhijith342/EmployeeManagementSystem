package com.employee.management.service;

// import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.employee.management.dto.StudentResponse;
import com.employee.management.model.Student;
import com.employee.management.model.Address;
import com.employee.management.repository.StudentRepository;
import com.employee.management.dto.AddressDTO;
// import com.employee.management.dto.StudentRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServices {
    // private ArrayList<Student> arr = new ArrayList<>();

    // public StudentServices(){
    //     arr.add(new Student(1,"A","B"));
    //     arr.add(new Student(2,"C","D"));
    //     arr.add(new Student(3,"E","F"));
    // }

    // public ArrayList<Student> getAllStudent(){
    //     return arr;
    // }

    // public String addStudent(Student s){
    //     arr.add(s);
    //     return "Student added successfully";
    // }
    // public String updateStudent(int id, Student s){
    //     // boolean isPresent = false;

    //     // for (int i = 0; i < arr.size(); i++) {
    //     //     if (arr.get(i).getId() == id) {
    //     //         arr.set(i, s);
    //     //         isPresent = true;
    //     //         break;
    //     //     }
    //     // }
    //     // return isPresent;

    //     Optional<Student> isPresent = arr.stream()
    //     .filter((Student s1)->s1.getId()==id)
    //     .findFirst();

    //     if(isPresent.isPresent()){
    //         Student student = isPresent.get();
    //         student.setFname(s.getFname());
    //         student.setLname(s.getLname());
    //         return "Student updated successfully";
    //     }
    //     return "Student not updated";

    // }
    // public Student fetchStudent(int id){
    //     // for(int i=0;i<arr.size();i++){
    //     //     if(arr.get(i).getId() == id){
    //     //         return arr.get(i);
    //     //     }
    //     // }
    //     // return null;
    //     return arr.stream()
    //     .filter((Student s)->s.getId()==xxxx id)
    //     .findFirst().orElse(null);
    // }

    // public int deleteStudent(int id){
    //     for(int i =0;i<arr.size();i++){
    //         if(arr.get(i).getId() == id){
    //             arr.remove(i);
    //             return 1;
    //         }
    //     }
    //     return 0;
    // }
    
    private final StudentRepository studentrepo;
    
    public List<StudentResponse> getAllStudent(){
        return studentrepo.findAll().stream()
        .map(this::mapTOUSerResponse)
        .collect(Collectors.toList());
    }

    public StudentResponse addStudent(Student s){
        studentrepo.save(s);
        return mapTOUSerResponse(s);
        
    }
    public StudentResponse updateStudent(Integer id, Student s){
        
        // Optional<Student> isPresent = studentrepo.findById(id);
        

        // if(isPresent.isPresent()){
        //     Student student = isPresent.get();
        //     student.setFname(s.getFname());
        //     student.setLname(s.getLname());
        //     student.setAddress(s.getAddress());
        //     student.setEmail(s.getEmail());
        //     student.setPhone(s.getPhone());
        //     studentrepo.save(s);
        //     return student;
        // }
        // return null;

        Student student = studentrepo.findById(id).orElse(null);
        if(student == null){
            return null;
        }
        student.setFname(s.getFname());
        student.setLname(s.getLname());
        // student.setAddress(s.getAddress());
        student.setEmail(s.getEmail());
        student.setPhone(s.getPhone());
        if(s.getAddress()!=null){
            Address address = student.getAddress();
            if(address == null){
                address = new Address();
            }
            address.setCity(s.getAddress().getCity());
            address.setState(s.getAddress().getState());
            address.setStreet(s.getAddress().getStreet());
            address.setZipcode(s.getAddress().getZipcode());
            address.setCountry(s.getAddress().getCountry());
            student.setAddress(address);
        }
    
        Student updatedStudent = studentrepo.save(student);
        return mapTOUSerResponse(updatedStudent);
        

    }
    public StudentResponse fetchStudent(Integer id){
        
        return studentrepo.findById(id).map(this::mapTOUSerResponse).orElse(null);
    }

    public StudentResponse deleteStudent(Integer id){
        // Optional<Student> isPresent = studentrepo.findById(id);
        // if(isPresent.isPresent()){
        //     studentrepo.delete(isPresent.get());
        //     return 1;
        // }
        // return 0;
        Student student = studentrepo.findById(id).orElse(null);
        if(student==null) return null;
        studentrepo.delete(student);
        return mapTOUSerResponse(student);
    }
    private StudentResponse mapTOUSerResponse(Student s){
        StudentResponse response = new StudentResponse();
        response.setId(s.getId());
        response.setFname(s.getFname());
        response.setLname(s.getLname());
        response.setEmail(s.getEmail());
        
        response.setPhone(s.getPhone());
        response.setUserRole(s.getUserRole());
        if(s.getAddress()!=null){
            // response.setAddress(s.getAddress());
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(s.getAddress().getStreet());
            addressDTO.setCity(s.getAddress().getCity());
            addressDTO.setState(s.getAddress().getState());
            addressDTO.setCountry(s.getAddress().getCountry());
            addressDTO.setZipcode(s.getAddress().getZipcode());
            addressDTO.setId(s.getAddress().getId());
            response.setAddress(addressDTO);
        }
        return response;

    }
    
}
