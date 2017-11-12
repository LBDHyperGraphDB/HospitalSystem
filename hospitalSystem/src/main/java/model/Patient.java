package model;

import java.lang.reflect.Field;
import java.util.Date;

public class Patient extends Person {
    private String patientHealthInsurance;
    private String patientDoctorCrm;

    public Patient() {

    }

    public Patient(String patientHealthInsurance) {
        this.patientHealthInsurance = patientHealthInsurance;
    }

    public Patient(String personCpf, String personName, String personAddress, String personGender,
            String personPhoneNumber, String personEmail, String personBithDate, String patientHealthInsurance, 
            String patientDoctorCrm) {
        super(personCpf, personName, personAddress, personGender, personPhoneNumber, personEmail, personBithDate);
        this.patientHealthInsurance = patientHealthInsurance;
        this.patientDoctorCrm = patientDoctorCrm;
    }

    public String getPatientHealthInsurance() {
        return patientHealthInsurance;
    }

    public void setPatientHealthInsurance(String patientHealthInsurance) {
        this.patientHealthInsurance = patientHealthInsurance;
    }
    
    public String getPatientDoctorCrm() {
        return patientDoctorCrm;
    }

    public void setPatientDoctorCrm(String patientDoctorCrm) {
        this.patientDoctorCrm = patientDoctorCrm;
    }

    public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getClass().getSuperclass().getDeclaredField(fieldName);
        field.set(this, value);
    }
}
