package com.wdzfa.udemy.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wdzfa.udemy.basic.bean.Student;

@RestController
public class StudentController {
    
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(
            1, 
            "Wadzifa", 
            "Rohmania"
        );
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "prima", "wibi"));
        students.add(new Student(2, "hesti", "wulandari"));
        students.add(new Student(3, "ramesh", "tutor"));

        return students;
    }

    // PathVariable
    // http://localhost:8080/students/1/wadzifatur/rohmania
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable (@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String fistName,
                                        @PathVariable("last-name") String lastName){
        return new Student(studentId, fistName, lastName);
    }

    // RequestParam Single
    // http://localhost:8080/students/query?id=1
    @GetMapping("students/query")
    public Student studentRequestParam(@RequestParam int id){
        return new Student(id, "wadzifa", "rohmania");
    }

    // RequestParam Multiple
    // http://localhost:8080/students/query/multiple?id=1&firstName=wadzi&lastName=rohma
    @GetMapping("students/query/multiple")
    public Student studentRequestParam(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    // POST Request
    // @PostMapping and @RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
}
