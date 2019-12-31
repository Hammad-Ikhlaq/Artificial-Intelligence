/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.domain;

import java.util.ArrayList;

/**
 *
Course to be scheduled
*/
public class Course {
    private String number=null;
    private String name=null;
    private ArrayList<Instructor> instructors;
    private int noOfMaxStudents;

    public Course(String number, String name, ArrayList<Instructor> instructors, int noOfMaxStudents) {
        this.number = number;
        this.name = name;
        this.instructors = instructors;
        this.noOfMaxStudents = noOfMaxStudents;
    }



    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getNoOfMaxStudents() {
        return noOfMaxStudents;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public String toString() {
        return name;
    }
}
