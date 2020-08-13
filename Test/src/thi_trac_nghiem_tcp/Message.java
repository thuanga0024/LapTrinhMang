package thi_trac_nghiem_tcp;

import java.io.Serializable;
import java.util.List;

class Message implements Serializable {

    private List<Exam> listExam;

    public Message() {
    }

    public Message(List<Exam> listExam) {
        this.listExam = listExam;
    }

    public List<Exam> getListExam() {
        return listExam;
    }

    public void setListExam(List<Exam> listExam) {
        this.listExam = listExam;
    }  	
}