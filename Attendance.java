package com.example.report;

public class Attendance {

    String dept, subj, locate;

    public Attendance(){

    }

    public Attendance(String dept, String subj, String locate) {
        this.dept = dept;
        this.subj = subj;
        this.locate = locate;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }
}
