/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * This class represents the population of schedule
 */
public class Population {
    private ArrayList<Schedule> schedules;

    public Population(int size,Data data) {
        schedules = new ArrayList<Schedule>(size);
        IntStream.range(0, size).forEach(x->schedules.add(new Schedule(data).initialize()));
    }
    
    public Population sortByFitness() {
        schedules.sort((schedule1,schedule2)-> {
           int ReturnValue=0;
            if (schedule1.getFitness()>schedule2.getFitness())
            {
                return -1;
            }
            else if (schedule1.getFitness()<schedule2.getFitness()) 
            {
                return 1;
            }
            return ReturnValue;
        });
        return this;
    }
    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
}
