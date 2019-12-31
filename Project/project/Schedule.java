/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project;

import ai.project.domain.Department;
import ai.project.domain.Instructor;
import ai.project.domain.Class;
import java.util.ArrayList;


public class Schedule {
        private ArrayList<Class> classes;
        private int classNum=0;
        private Data data;
        private int NumOfConflicts=0;
        private boolean IsFitnessChanged=true;
        private double fitness=-1;

    public Data getData() {
        return data;
    }

    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClasses());
    }
    /**
we are using data to get all departments
* then picking up courses in each department
* For each we make a class
* We randomly set meetingTime, Room and Instructor
 */
    public Schedule initialize() 
    {
        new ArrayList<Department>(data.getDepts()).forEach(dept->
                {
                    dept.getCourses().forEach(course->
                    {
                        Class newClass = new Class(classNum++,dept,course);
                        newClass.setMeetingTime(data.getMeetingTimes().get((int)(data.getMeetingTimes().size()*Math.random())));
                        newClass.setRoom(data.getRooms().get((int)(data.getRooms().size()*Math.random())));
                        newClass.setInstructor(course.getInstructors().get((int)(course.getInstructors().size()*Math.random())));
                        classes.add(newClass);
                    });
                });
        return this;
    }

    public int getNumOfConflicts() {
        return NumOfConflicts;
    }

    public ArrayList<Class> getClasses() {
        IsFitnessChanged=true;
        return classes;
    }
    
    public double getFitness(){
        if (IsFitnessChanged==true) {
            fitness=calculateFitness();
            IsFitnessChanged=false;
        }
        return fitness;
    }
    
    private double calculateFitness(){
        NumOfConflicts=0;
        classes.forEach(x->
        {
            if(x.getRoom().getSeatingCapacity()<x.getCourse().getNoOfMaxStudents())
            {
                   NumOfConflicts++;
            }
            classes.stream().filter(y->classes.indexOf(y)>=classes.indexOf(x)).forEach(y->
            {
                    /**if different classes have same meeting time*/
                if(x.getMeetingTime()==y.getMeetingTime()&&x.getId()!=y.getId())
                {
                   if(x.getRoom()==y.getRoom())
                   {
                       NumOfConflicts++;
                   }
                   if(x.getInstructor()==y.getInstructor())
                   {
                       NumOfConflicts++;
                   }
                }
            });
        });
        return 1/(double)(NumOfConflicts+1);
    }
    
    public String toString() 
    {
        String ReturnValue=new String();
        for (int x = 0; x < classes.size()-1; x++) 
        {
            ReturnValue+=classes.get(x)+",";
        }
        ReturnValue+=classes.get(classes.size()-1);
        return ReturnValue;
    }
}
