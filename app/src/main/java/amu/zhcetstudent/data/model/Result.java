package amu.zhcetstudent.data.model;

import java.util.List;

public class Result {

    private String cpi;
    private String name;
    private String facultyNumber;
    private String enrolment;
    private String ec;
    private List<Subject> results = null;
    private String spi;
    private String user;
    private boolean error;
    private String message;

    public Result(String facultyNo, String enrollmentNo) {
        this.facultyNumber = facultyNo;
        this.enrolment = enrollmentNo;
    }

    public String getCpi() {
        return cpi;
    }

    public void setCpi(String cpi) {
        this.cpi = cpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(String enrolment) {
        this.enrolment = enrolment;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public List<Subject> getResults() {
        return results;
    }

    public void setResults(List<Subject> results) {
        this.results = results;
    }

    public String getSpi() {
        return spi;
    }

    public void setSpi(String spi) {
        this.spi = spi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

