package model;

import java.lang.reflect.Field;
import java.util.Date;

public class Nurse extends Person {
    private String nurseQualification;
    private String nurseCoren;

    public Nurse() {

    }

    public Nurse(String nurseQualification, String nurseCoren) {
        this.nurseQualification = nurseQualification;
        this.nurseCoren = nurseCoren;
    }

    public Nurse(String personCpf, String personName, String personAddress, String personGender,
            String personPhoneNumber, String personEmail, Date personBithDate,
            String nurseQualification, String nurseCoren) {
        super(personCpf, personName, personAddress, personGender, personPhoneNumber, personEmail, personBithDate);
        this.nurseQualification = nurseQualification;
        this.nurseCoren = nurseCoren;
    }

    public String getNurseQualification() {
        return nurseQualification;
    }

    public void setNurseQualification(String nurseQualification) {
        this.nurseQualification = nurseQualification;
    }

    public String getNurseCoren() {
        return nurseCoren;
    }

    public void setNurseCoren(String nurseCoren) {
        this.nurseCoren = nurseCoren;
    }

    public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getClass().getSuperclass().getDeclaredField(fieldName);
        field.set(this, value);
    }
}
