package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getPatientById(@PathVariable(value = "id") Long patientId)
			throws ResourceNotFoundException {
		Student patient = studentRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + patientId));
		return ResponseEntity.ok().body(patient);
	}

	@PostMapping("students")
	public Student createPatient(@Valid @RequestBody Student student) {
		return studentRepository.save(student);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long patientId,
			@Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
		Student studentObj = studentRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + patientId));

		studentObj.setFirstName(studentDetails.getFirstName());
		studentObj.setLastName(studentDetails.getLastName());
		studentObj.setEmail(studentDetails.getEmail());
		final Student updatedPatient = studentRepository.save(studentObj);
		return ResponseEntity.ok(updatedPatient);
	}

	@DeleteMapping("/students/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {
		Student patient = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

		studentRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
