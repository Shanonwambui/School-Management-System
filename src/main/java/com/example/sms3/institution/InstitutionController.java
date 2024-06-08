package com.example.sms3.institution;


import com.example.sms3.student.Student;
import com.example.sms3.student.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institution")
public class InstitutionController {
    private final InstitutionService institutionService;
    private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);


    @Autowired
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }


    @GetMapping
    public List<Institution> getAllInstitutions() {

        List<Institution> institutions = institutionService.getAllInstitutions();
        logger.info("Institutions: {}", institutions);
        return institutions;
    }

    @GetMapping("/sortByName")
    public List<Institution> sortInstitutionsByName(@RequestParam String sortOrder){
        return institutionService.sortInstitutionsByName(sortOrder);
    }

    @GetMapping("/search")
    public List<Institution> searchInstitutions(@RequestParam String searchTerm){
        return institutionService.searchInstitutions(searchTerm);
    }


    @GetMapping("/institutionsWithStudents")
    public ResponseEntity<List<Institution>> getAllInstitutionsWithStudents(@RequestParam(required = false) String name) {
        List<Institution> institutions = institutionService.getAllInstitutionsWithStudents(name);
        return ResponseEntity.ok(institutions);
    }


    @GetMapping("/studentsByCourse")
    public ResponseEntity<List<Student>> getAllStudentsByCourse(@RequestParam String courseName) {
        List<Student> students = institutionService.getAllStudentsByCourse(courseName);
        return ResponseEntity.ok(students);
    }
    @PostMapping
    public ResponseEntity<?> createInstitution(@RequestBody Institution institution){
        return institutionService.createInstitution(institution);
    }
    @PutMapping("/{id}")
    public Institution updateInstitutionName(@PathVariable Long id, @RequestBody String newName) {
        return institutionService.updateInstitutionName(id, newName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteInstitution(@PathVariable Long id){
        institutionService.deleteInstitution(id);
        return ResponseEntity.ok().build();
    }

}