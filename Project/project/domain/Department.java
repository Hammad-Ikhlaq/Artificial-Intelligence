/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.domain;

import java.util.ArrayList;

/**
 *
Department of Course */
public class Department {
    private String name=null;
    private ArrayList<Course> courses;

    public Department(String name, ArrayList<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
