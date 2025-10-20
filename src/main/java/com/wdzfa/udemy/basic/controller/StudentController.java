package com.wdzfa.udemy.basic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Using ResponseEntity
    @GetMapping("student/entity")
    public ResponseEntity<Student> getStudentWithEntity() {
        Student student = new Student(
            1, 
            "Wadzifa", 
            "Rohmania"
        );
        // return new ResponseEntity<>(student, HttpStatus.OK);
        // return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "wadzi")
                .body(student); // check header result by postman result page
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("students/list")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "prima", "wibi"));
        students.add(new Student(2, "hesti", "wulandari"));
        students.add(new Student(3, "ramesh", "tutor"));

        return students;
    }

    // Using ResponseEntity
    @GetMapping("students/list/entity")
    public ResponseEntity<List<Student>> getStudentsListEntity(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "prima", "wibi"));
        students.add(new Student(2, "hesti", "wulandari"));
        students.add(new Student(3, "ramesh", "tutor"));

        return ResponseEntity.ok(students);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // PathVariable
    // http://localhost:8080/students/1/wadzifatur/rohmania
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable (@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String fistName,
                                        @PathVariable("last-name") String lastName){
        return new Student(studentId, fistName, lastName);
    }

    // Using ResponseEntity
    @GetMapping("students/{id}/{first-name}/{last-name}/entity")
    public ResponseEntity<Student> studentPathVariableEntity (@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String fistName,
                                        @PathVariable("last-name") String lastName){
        Student student = new Student(studentId, fistName, lastName);
        return ResponseEntity.ok(student);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // RequestParam Single
    // http://localhost:8080/students/query?id=1
    @GetMapping("students/query")
    public Student studentRequestParam(@RequestParam int id){
        return new Student(id, "wadzifa", "rohmania");
    }

    // Using ResponseEntity
    @GetMapping("students/query/entity")
    public ResponseEntity<Student> studentRequestParamEntity(@RequestParam int id){
        Student student = new Student(id, "wadzifa", "rohmania");
        return ResponseEntity.ok(student);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // RequestParam Multiple
    // http://localhost:8080/students/query/multiple?id=1&firstName=wadzi&lastName=rohma
    @GetMapping("students/query/multiple")
    public Student studentRequestParam(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    // Using ResponseEntity
    @GetMapping("students/query/multiple/entity")
    public ResponseEntity<Student> studentRequestParamEntity(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

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

    // Using ResponseEntity
    @PostMapping("students/create/entity")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudentEntity(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // using ResponseEntity
    @PutMapping("students/{id}/update/entity")
    public ResponseEntity<Student> updateStudentEntity(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // HTTP Delete Request
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully";
    }

    //using ResponseEntity
    @DeleteMapping("students/{id}/delete/entity")
    public ResponseEntity<String> deleteStudentEntity(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
