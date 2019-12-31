/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project;

import java.util.ArrayList;
import ai.project.domain.Class;
/**
 *
 * @author Attar Telecom
 */
public class Driver {

    public static final int POPULATION_SIZE=9;
    public static final double MUTATION_RATE=0.1;
    public static final double CROSSOVER_RATE=0.9;
    public static final int TOURNAMENT_SELECTION_SIZE=3;
    public static final int NUMB_OF_ELITE_SCHEDULES=1;
    private Data data;
    private int ClassNumb=1;
    private int scheduleNumb=0;
    
    public static void main(String[] args) {
        Driver driver=new Driver();
        driver.data=new Data();
        int generationNumber=0;
        driver.printAvailableData();
        System.out.println("> Generation #"+ ++generationNumber);
        System.out.print(" Schedule # |                                             ");
        System.out.print("Classes [dept,class,room,instructor,metting-time]        ");
        System.out.println("                         | Fitness | Conflicts");
        
        System.out.print("--------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        
        GeneticAlgorithm geneticAlgorithm=new GeneticAlgorithm(driver.data);
    
        Population population=new Population(Driver.POPULATION_SIZE,driver.data).sortByFitness();
        population.getSchedules().forEach(schedule->System.out.println("          "+driver.scheduleNumb++ +"     | "+schedule+" | "+String.format("%.5f", schedule.getFitness())+" | "+schedule.getNumOfConflicts()));
    
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        driver.ClassNumb=1;
        while(population.getSchedules().get(0).getFitness()!=1.0)
        {
        System.out.println("> Generation #"+ ++generationNumber);
        System.out.print(" Schedule # |                                             ");
        System.out.print("Classes [dept,class,room,instructor,metting-time]        ");
        System.out.println("                          | Fitness | Conflicts");
        
        System.out.print("--------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        
        population=geneticAlgorithm.evolve(population).sortByFitness();
        driver.scheduleNumb=0;
        population.getSchedules().forEach(schedule->System.out.println("          "+driver.scheduleNumb++ +"     |  | "+String.format("%.5f", schedule.getFitness())+" | "+schedule.getNumOfConflicts()));
    
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        driver.ClassNumb=1;
        }
    }
    private void printScheduleAsTable(Schedule schedule,int generation)
    {
        ArrayList<Class> classes=schedule.getClasses();
        System.out.print("\n                 ");
        System.out.print("-----------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
         for (int i = 0; i < classes.size(); i++) 
         {
            Class x=classes.get(i);
             int majorIndex=data.getDepts().indexOf(x.getDept());
             int coursesIndex=data.getCourses().indexOf(x.getCourse());
             int roomIndex=data.getRooms().indexOf(x.getRoom());
             int instructorIndex=Integer.parseInt(x.getInstructor().getId());
             int meetingTimeIndex=data.getMeetingTimes().indexOf(x.getMeetingTime());
             System.out.print("                 ");
             System.out.print(String.format("%1$02d", ClassNumb)+" | ");
             System.out.print(String.format("%1$4s", data.getDepts().get(majorIndex).getName())+" | ");
             System.out.print(String.format("%1$21s", data.getCourses().get(coursesIndex).getName()+" ("+data.getCourses().get(coursesIndex).getNumber()+", "+x.getCourse().getNoOfMaxStudents())+")       | ");             
             System.out.print(String.format("%1$10s", data.getRooms().get(roomIndex).getNumber()+" ("+x.getRoom().getSeatingCapacity())+")       | ");             
             System.out.print(String.format("%1$15s", data.getInstructors().get(instructorIndex-1).getName()+" ("+data.getInstructors().get(instructorIndex-1).getId()+")")+")       | ");             
             System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getTime()+"("+data.getMeetingTimes().get(meetingTimeIndex).getId()+")");
             
             ClassNumb++;
             
             
         }
         if (schedule.getFitness()==1) 
         {
             System.out.println("> Solution found in "+(generation+1)+" generations");
         }
        System.out.print("-----------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");

    }
             
    private void printAvailableData()
    {
        System.out.println("Available Departments ==>");
        data.getDepts().forEach(x->System.out.println("name: "+x.getName()+",courses: "+x.getCourses()));
    
        System.out.println("\nAvailable Courses ==>");
        data.getCourses().forEach(x->System.out.println("course #: "+x.getNumber()+",name:"+x.getName()+", max # of students: "+x.getNoOfMaxStudents()+", instuctors: "+x.getInstructors()));
    
        System.out.println("\nAvailable Rooms ==>");
        data.getRooms().forEach(x->System.out.println("room #: "+x.getNumber()+",max seating capacity:"+x.getSeatingCapacity()));
    
        System.out.println("\nAvailable Instructors ==>");
        data.getInstructors().forEach(x->System.out.println("id: "+x.getId()+",name:"+x.getName()));
    
        System.out.println("Available Meeting Times ==>");
        data.getMeetingTimes().forEach(x->System.out.println("id: "+x.getId()+",Meeting time:"+x.getTime()));
    
        System.out.print("--------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
    
    }
    
}
