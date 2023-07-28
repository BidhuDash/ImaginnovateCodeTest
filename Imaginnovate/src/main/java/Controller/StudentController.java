package Controller;

import Entity.Student;
import Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    public StudentRepository studentRepository;


    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {

        calculateStudentResult(student);
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/students/{id}/marks")
    public ResponseEntity<?> updateStudentMarks(
            @PathVariable Long id,
            @RequestParam @NotNull @Min(0) @Max(100) Integer marks1,
            @RequestParam @NotNull @Min(0) @Max(100) Integer marks2,
            @RequestParam @NotNull @Min(0) @Max(100) Integer marks3
    ) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setMarks1(marks1);
            student.setMarks2(marks2);
            student.setMarks3(marks3);

            calculateStudentResult(student);
            Student savedStudent = studentRepository.save(student);
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void calculateStudentResult(Student student) {
        int totalMarks = student.getMarks1() + student.getMarks2() + student.getMarks3();
        double averageMarks = totalMarks / 3.0;
        student.setTotal(totalMarks);
        student.setAverage(averageMarks);

        if (student.getMarks1() >= 35 && student.getMarks2() >= 35 && student.getMarks3() >= 35) {
            student.setResults("Pass");
        } else {
            student.setResults("Fail");
        }
    }


}
