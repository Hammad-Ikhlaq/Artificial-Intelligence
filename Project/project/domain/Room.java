/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project.domain;

/**
 *
Room where the course will be taught */
public class Room {
        private String number;
    private int seatingCapacity;

    public Room(String number, int seatingCapacity) {
        this.number = number;
        this.seatingCapacity = seatingCapacity;
    }

    public String getNumber() {
        return number;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }
}
