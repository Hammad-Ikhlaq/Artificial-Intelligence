/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.domain;

/**
 *
Representing teacher teaching course */
public class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    public String toString() {
        return name;
    }
       
    public void setId(String id) {
        this.id=id;
    }
}
