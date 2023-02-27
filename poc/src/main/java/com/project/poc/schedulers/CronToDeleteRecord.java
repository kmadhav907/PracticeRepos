package com.project.poc.schedulers;

import com.project.poc.model.EmployeeModel;
import com.project.poc.repositories.EmployeeRepo;
import com.project.poc.utilities.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class CronToDeleteRecord {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Scheduled(cron = "0 0 * * * *")
    public void deletedRecords() throws ParseException {
        System.out.println("Records get deleted after 90 Days");
        List<EmployeeModel> employeeModelList = employeeRepo.findByDeletedAtIsNotNull();
        for(EmployeeModel employeeModel: employeeModelList){
            if(GlobalUtils.isRecordOldThan90days(employeeModel.getDeletedAt())){
                employeeRepo.delete(employeeModel);
            }

        }
    }
}
