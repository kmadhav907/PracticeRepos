package com.project.poc.controller;

import com.project.poc.dto.EmployeeRequest;
import com.project.poc.dto.EmployeeResponse;
import com.project.poc.model.EmployeeModel;
import com.project.poc.repositories.EmployeeRepo;
import com.project.poc.utilities.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmployees(){
         return ResponseEntity.ok(new EmployeeResponse("success", employeeRepo.findByDeletedAtIsNull()));
    }


    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        EmployeeModel employeeModel = new EmployeeModel(
                null,
                employeeRequest.getEmployeeName(),
                GlobalUtils.formatDateAndTime(LocalDateTime.now()),
                null,
                null
                );
        employeeRepo.save(employeeModel);
        return ResponseEntity.ok(new EmployeeResponse("success", "Saved successfully"));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        Optional<EmployeeModel>  optionalEmployeeModel = employeeRepo.findById(employeeRequest.getEmployeeId());
        if(optionalEmployeeModel.isPresent()){
            EmployeeModel employeeModel = optionalEmployeeModel.get();
            employeeModel.setEmployeeName(employeeRequest.getEmployeeName());
            employeeModel.setUpdatedAt(GlobalUtils.formatDateAndTime(LocalDateTime.now()));
            employeeRepo.save(employeeModel);
            return ResponseEntity.ok(new EmployeeResponse("success", "updated successfully"));
        }else {
            return ResponseEntity.status(404).body(new EmployeeResponse("error", "Data is not found"));
        }
    }
    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@RequestBody EmployeeRequest employeeRequest){
        Optional<EmployeeModel> employeeModelOptional = employeeRepo.findByEmployeeName(employeeRequest.getEmployeeName());
        if(employeeModelOptional.isPresent()){
            EmployeeModel employeeModel = employeeModelOptional.get();
            employeeModel.setDeletedAt(GlobalUtils.formatDateAndTime(LocalDateTime.now()));
            employeeRepo.save(employeeModel);
            return ResponseEntity.ok(new EmployeeResponse("success", "deleted successfully"));
        }else {
            return  ResponseEntity.status(404).body(new EmployeeResponse("error", "Data is not found"));
        }
    }
}
