package org.room803.java;
import org.room803.data.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.ResultSet;
import java.sql.SQLException;
@RestController
public class AjaxController {

    @RequestMapping("/Logincheck")//登录检测
    public int loginCheck(@RequestBody Login login) throws SQLException {
        System.out.println(login);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        int i = sql.LoginCheck(login.getLogin_name(), login.getPassword());

        System.out.println(i);
        return i;//1
    }

    @RequestMapping("/renovate")//删除药物
    public String renovate(@RequestBody String a) throws SQLException {

        //由于@RestController注解，将list转成json格式返回

        SQL sql1 = new SQL();
        // 模拟表格封装成json测试，封装在source//.json
        ResultSet r1 = sql1.ShowTable("Ward");
        ToJson.resultSetToJson(r1, "Ward");
        ResultSet r2 = sql1.ShowTable("Patient");
        ToJson.resultSetToJson(r2, "Patient");
        ResultSet r3 = sql1.ShowTable("Medicine");
        ToJson.resultSetToJson(r3, "Medicine");
        ResultSet r4 = sql1.ShowTable("Doctor");
        ToJson.resultSetToJson(r4, "Doctor");
        ResultSet r5 = sql1.ShowTable("Med_instruments");
        ToJson.resultSetToJson(r5, "Med_instruments");
        ResultSet r6 = sql1.ShowTable("Nurse");
        ToJson.resultSetToJson(r6, "Nurse");
        System.out.println("success");

        return "success";


    }


    @RequestMapping("/Med_Instrumentsupdate")//更新医疗仪器
    public Med_Instruments Med_InstrumentsUpdate(@RequestBody Med_Instruments med_instruments) throws SQLException {
        System.out.println(med_instruments);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Med_Instruments", med_instruments.getMed_instruments_id(), med_instruments.getMed_instruments_name()
                , med_instruments.getMed_instruments_price(), med_instruments.getMed_instruments_quantity());
        return med_instruments;

    }

    @RequestMapping("/Med_Instrumentsdelete")//删除医疗仪器
    public Med_Instruments Med_InstrumentsDelete(@RequestBody Med_Instruments med_instruments) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Med_Instruments", "med_instruments_id", med_instruments.getMed_instruments_id());
        System.out.println(med_instruments);
        return med_instruments;
    }

    @RequestMapping("/Med_instrumentsinsert")
    public Med_Instruments Med_Instrumentsinsert(@RequestBody Med_Instruments med_Instruments) throws SQLException {
        System.out.println(med_Instruments);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Insert("Med_Instruments", med_Instruments.getMed_instruments_id(), med_Instruments.getMed_instruments_name(), med_Instruments.getMed_instruments_price(),
                med_Instruments.getMed_instruments_quantity());

        return med_Instruments;
    }


    @RequestMapping("/Warddelete")//删除病床
    public Ward WardDelete(@RequestBody Ward ward) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();

        System.out.println(ward);
        sql.delete("Ward", "Ward_id", ward.getWard_id());
        ResultSet r1 = sql.ShowTable("Ward");
        ToJson.resultSetToJson(r1, "Ward");
        return ward;
    }

    @RequestMapping("/Wardinsert")//增加病床
    public Ward Wardinsert(@RequestBody Ward ward) throws SQLException {
        System.out.println(ward);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Insert("Ward", ward.getWard_id(), ward.getWard_bednum(), ward.getPatient_date_start(),
                ward.getPatient_inhospital_id(), ward.getWard_dept());

        return ward;
    }

    @RequestMapping("/Wardupdate")//更新病床
    public Ward WardUpdate(@RequestBody Ward ward) throws SQLException {
        System.out.println(ward);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Ward", ward.getWard_id(), ward.getWard_bednum(), ward.getPatient_date_start(),
                ward.getPatient_inhospital_id(), ward.getWard_dept(),ward.getWard_doc_id(),ward.getWard_nurse_id());
        return ward;
    }


    @RequestMapping("/Medicineinsert")//增加药物
    public Medicine Medicineinsert(@RequestBody Medicine medicine) throws SQLException {
        System.out.println(medicine);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Insert("Medicine", medicine.getMedicine_id(), medicine.getMedicine_name(), medicine.getMedicine_price(),
                medicine.getMedicine_quantity());

        return medicine;
    }

    @RequestMapping("/Medicinedelete")//删除药物
    public Medicine MedicineDelete(@RequestBody Medicine medicine) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Medicine", "medicine_id", medicine.getMedicine_id());
        System.out.println(medicine);
        return medicine;
    }

    @RequestMapping("/Medicineupdate")//更新药物
    public Medicine MedicineUpdate(@RequestBody Medicine medicine) throws SQLException {
        System.out.println(medicine);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Medicine", medicine.getMedicine_id(), medicine.getMedicine_name(), medicine.getMedicine_price()
                , medicine.getMedicine_quantity());
        return medicine;

    }


    @RequestMapping("/Patientinsert")//增加病人
    public Patient patient(@RequestBody Patient patient) throws SQLException {
        System.out.println(patient);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Insert("Patient", patient.getPatient_id(), patient.getPatient_name(), patient.getPatient_gender(),
                patient.getPatient_date_start(), patient.getPatient_dept(), patient.getPatient_area(),
                patient.getPatient_doc_id(), patient.getPatient_age(), patient.getPatient_condition(), patient.getPatient_phone());

        return patient;
    }

    @RequestMapping("/Patientdelete")//删除病人
    public Patient PatientDelete(@RequestBody Patient patient) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Patient", "Patient_id", patient.getPatient_id());
        System.out.println(patient);
        return patient;
    }

    @RequestMapping("/Patientupdate")//更新病人
    public Patient PatientUpdate(@RequestBody Patient patient) throws SQLException {
        System.out.println(patient);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Patient", patient.getPatient_id(), patient.getPatient_name(), patient.getPatient_gender()
                , patient.getPatient_date_start(), patient.getPatient_dept(), patient.getPatient_area(),
                patient.getPatient_doc_id(), patient.getPatient_age(), patient.getPatient_condition(), patient.getPatient_phone());
        ResultSet r1 = sql.ShowTable("Patient");
        ToJson.resultSetToJson(r1, "Patient");
        return patient;
    }


    @RequestMapping("/Doctorupdate")//更新医生
    public Doctor DoctorUpdate(@RequestBody Doctor doctor) throws SQLException {
        System.out.println(doctor);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Doctor", doctor.getDoctor_id(), doctor.getDoctor_name(), doctor.getDoctor_gender()
                , doctor.getDoctor_dept(), doctor.getDoctor_phone());
        return doctor;
    }
    @RequestMapping("/Nurseupdate")//增加护士
    public Nurse NurseUpdate(@RequestBody Nurse nurse) throws SQLException {
        System.out.println(nurse);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("Nurse", nurse.getNurse_id(), nurse.getNurse_name(), nurse.getNurse_gender()
                , nurse.getNurse_dept(), nurse.getNurse_phone());
        return nurse;
    }

@RequestMapping("/Nurseinsert")//增加护士
public Nurse Nurseinsert(@RequestBody Nurse nurse) throws SQLException {
    System.out.println(nurse);
    //由于@RestController注解，将list转成json格式返回
    SQL sql = new SQL();
    sql.Insert("Nurse", nurse.getNurse_id(), nurse.getNurse_name(),nurse.getNurse_gender(),nurse.getNurse_dept(),nurse.getNurse_phone());
    return nurse;
}
    @RequestMapping("/Doctorinsert")//增加医生
    public Doctor Doctorinsert(@RequestBody Doctor doctor) throws SQLException {
        System.out.println(doctor);
        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Insert("Doctor", doctor.getDoctor_id(), doctor.getDoctor_name(), doctor.getDoctor_gender(), doctor.getDoctor_dept(),
                doctor.getDoctor_phone());

        return doctor;
    }

    @RequestMapping("/Doctordelete")//删除医生
    public Doctor DoctorDelete(@RequestBody Doctor doctor) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Doctor", "Doctor_id", doctor.getDoctor_id());
        System.out.println(doctor);
        return doctor;
    }
@RequestMapping("/Nursedelete")//删除护士
public Nurse NurseDelete(@RequestBody Nurse nurse) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.delete("Nurse", "Nurse_id", nurse.getNurse_id());
        System.out.println(nurse);
        return nurse;
}
    @RequestMapping("/ToOrderupdate")//订单更新
    public ToOrder ToOrderupdate(@RequestBody ToOrder toOrder) throws SQLException {

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        sql.Update("ToOrder", toOrder.getOrder_id(), toOrder.getMedicine_id(), toOrder.getDoctor_id(), toOrder.getMedicine_id()
                , toOrder.getMed_instruments_id(), toOrder.getOrder_time());

        System.out.println(toOrder);
        return toOrder;
    }

    @RequestMapping(value ="Doctorsearch",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")//医生查询
    public String Doctorsearch(@RequestBody String s1) throws SQLException {
String s2 = s1.substring(3);
int i = Integer.valueOf(s2).intValue();

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        ResultSet r1 =  sql.Search("Doctor",i);



        return ToJson.resultSetToJson(r1,"Temp");



    }
    @RequestMapping(value ="Nursesearch",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")//护士查询
    public String Nursesearch(@RequestBody String s1) throws SQLException {
String s2 = s1.substring(3);
int i = Integer.valueOf(s2).intValue();

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        ResultSet r1 =  sql.Search("Nurse",i);
        return ToJson.resultSetToJson(r1,"Temp");
    }
    @RequestMapping(value ="Wardsearch",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")//病人查询
    public String Wardsearch(@RequestBody String s1) throws SQLException {
        String s2 = s1.substring(3);
        int i = Integer.valueOf(s2).intValue();

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        ResultSet r1 =  sql.Search("Ward",i);



        return ToJson.resultSetToJson(r1,"Temp");



    }
    @RequestMapping(value ="Medicinesearch",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")//病人查询
    public String Medicinesearch(@RequestBody String s1) throws SQLException {
        String s2 = s1.substring(3);
        int i = Integer.valueOf(s2).intValue();

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        ResultSet r1 =  sql.Search("Medicine",i);



        return ToJson.resultSetToJson(r1,"Temp");



    }
    @RequestMapping(value ="Med_Instrumentssearch",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")//病人查询
    public String Med_Instrumentssearch(@RequestBody String s1) throws SQLException {
        String s2 = s1.substring(3);
        int i = Integer.valueOf(s2).intValue();

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        ResultSet r1 =  sql.Search("Med_Instruments",i);



        return ToJson.resultSetToJson(r1,"Temp");



    } @RequestMapping(value ="Patientsearch",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")//病人查询
    public String Patientsearch(@RequestBody String s1) throws SQLException {
        String s2 = s1.substring(3);
        int i = Integer.valueOf(s2).intValue();

        //由于@RestController注解，将list转成json格式返回
        SQL sql = new SQL();
        ResultSet r1 =  sql.Search("Patient",i);



        return ToJson.resultSetToJson(r1,"Temp");



    }


}
