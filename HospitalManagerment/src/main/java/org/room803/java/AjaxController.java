package com.hospital.java;

import com.hospital.Data.Patient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;

@RestController
public class AjaxController {

    @RequestMapping("/delete")//删除
    public Patient PatientDelete(@RequestBody Patient patient) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Patient","Patient_id", patient.getPatient_id());
        System.out.println(patient);
        return patient;
    }

    @RequestMapping("/update")//更新
    public Patient ajax3(@RequestBody Patient patient) throws SQLException {
        System.out.println(patient);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
   sql.Update("Patient",patient.getPatient_id(), patient.getPatient_name(), patient.getPatient_gender()
   , patient.getPatient_date_start(), patient.getPatient_dept(), patient.getPatient_area(),
           patient.getPatient_doc_id(), patient.getPatient_age(), patient.getPatient_condition(), patient.getPatient_phone());
        return patient;
    }

}