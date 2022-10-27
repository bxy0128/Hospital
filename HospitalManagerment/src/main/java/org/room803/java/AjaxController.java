package org.room803.java;


import org.room803.data.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class AjaxController {


    @RequestMapping("/Patientupdate")//更新病人
    public Patient PatientUpdate(@RequestBody Patient patient) throws SQLException {
        System.out.println(patient);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Patient", patient.getPatient_id(), patient.getPatient_name(), patient.getPatient_gender()
                , patient.getPatient_date_start(), patient.getPatient_dept(), patient.getPatient_area(),
                patient.getPatient_doc_id(), patient.getPatient_age(), patient.getPatient_condition(), patient.getPatient_phone());
        ResultSet r1 =  sql.ShowTable("Patient");
        ToJson.resultSetToJson(r1,"Patient");
        return patient;
    }

    @RequestMapping("/Doctorupdate")//更新医生
    public Doctor DoctorUpdate(@RequestBody Doctor doctor) throws SQLException {
        System.out.println(doctor);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Doctor", doctor.getDoctor_id(), doctor.getDoctor_name(), doctor.getDoctor_gender()
                , doctor.getDoctor_dept_id(), doctor.getDoctor_phone());
        return doctor;
    }

    @RequestMapping("/Wardupdate")//更新病床
    public Ward WardUpdate(@RequestBody Ward ward) throws SQLException {
        System.out.println(ward);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Ward", ward.getWard_id(), ward.getPatient_inhospital_id(),
                ward.getWard_dept_id(), ward.getWard_bednum(), ward.getPatient_date_start());
        return ward;
    }

    @RequestMapping("/Medicineupdate")//更新药物
    public Medicine MedicineUpdate(@RequestBody Medicine medicine) throws SQLException {
        System.out.println(medicine);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Medicine", medicine.getMedicine_id(), medicine.getMedicine_name(), medicine.getMedicine_price()
                , medicine.getMedicine_quantify());
        return medicine;

    }

    @RequestMapping("/Med_Instrumentsupdate")//更新医疗仪器
    public Med_Instruments Med_InstrumentsUpdate(@RequestBody Med_Instruments med_instruments) throws SQLException {
        System.out.println(med_instruments);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Medicine", med_instruments.getMed_instruments_id(), med_instruments.getMed_instruments_name()
                , med_instruments.getMed_instruments_price(), med_instruments.getMed_instruments_quantity());
        return med_instruments;

    }


    @RequestMapping("/Patientdelete")//删除病人
    public Patient PatientDelete(@RequestBody Patient patient) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Patient", "Patient_id", patient.getPatient_id());
        System.out.println(patient);
        return patient;
    }
    @RequestMapping("/Doctordelete")//删除医生
    public Doctor DoctorDelete(@RequestBody Doctor doctor) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Doctor", "Doctor_id", doctor.getDoctor_id());
        System.out.println(doctor);
        return doctor;
    }
    @RequestMapping("/Warddelete")//删除病床
    public Ward WardDelete(@RequestBody Ward ward) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Ward", "Ward_id", ward.getWard_id());
        System.out.println(ward);
        return ward;
    }
    @RequestMapping("/Medicinedelete")//删除药物
    public Medicine MedicineDelete(@RequestBody Medicine medicine) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("medicine", "medicine_id", medicine.getMedicine_id());
        System.out.println(medicine);
        return medicine;
    }
    @RequestMapping("/Med_Instrumentsdelete")//删除医疗仪器
    public Med_Instruments Med_InstrumentsDelete(@RequestBody Med_Instruments med_instruments) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("med_instruments", "med_instruments_id", med_instruments.getMed_instruments_id());
        System.out.println(med_instruments);
        return med_instruments;
    }
}
