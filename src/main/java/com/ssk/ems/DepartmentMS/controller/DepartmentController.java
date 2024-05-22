package com.ssk.ems.DepartmentMS.controller;

import com.ssk.ems.DepartmentMS.dto.DepartmentDTO;
import com.ssk.ems.DepartmentMS.exception.RecordNotFoundException;
import com.ssk.ems.DepartmentMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/ems")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departmentByempId/{empId}")
    public ResponseEntity<DepartmentDTO> getDepartmentByEmpId(@PathVariable Long empId) {
        try {
            DepartmentDTO departmentDTO = departmentService.getDepartmentByEmpId(empId);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<DepartmentDTO> responseEntity = new ResponseEntity<DepartmentDTO>(
                    departmentDTO,
                    httpHeaders,
                    HttpStatus.OK);
            System.out.println(responseEntity.getBody().toString());

            return responseEntity;
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentsByDeptId(@PathVariable long id) {
        try {
            DepartmentDTO departmentDTO = departmentService.getDepartmentsByDeptId(Long.valueOf(id));

            return new ResponseEntity<DepartmentDTO>(departmentDTO, new HttpHeaders(), HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        List<DepartmentDTO> departmentDTOList = departmentService.getAllDepartments();
        return new ResponseEntity<List<DepartmentDTO>>(departmentDTOList,  HttpStatus.OK);
    }

    @PostMapping(value = "/departments", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO employee)
            throws RecordNotFoundException, RecordNotFoundException {
        try{
        DepartmentDTO created = departmentService.createDepartment(employee);
        return new ResponseEntity<DepartmentDTO>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable long id, @RequestBody
            DepartmentDTO departmentDTO) throws RecordNotFoundException {
        try {
            DepartmentDTO updated = departmentService.updateDepartment(Long.valueOf(id), departmentDTO);
            return new ResponseEntity<DepartmentDTO>(updated, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<DepartmentDTO>(departmentDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
           // throw new RecordNotFoundException("No Record Found", ex);
        }
    }


    @DeleteMapping("/departments/{id}")
    public HttpStatus deleteDepartmentById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        departmentService.deleteDepartmentById(id);

        return HttpStatus.FORBIDDEN;
    }
}
