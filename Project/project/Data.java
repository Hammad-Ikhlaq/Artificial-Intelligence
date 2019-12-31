/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.project;

import ai.project.domain.Course;
import ai.project.domain.Department;
import ai.project.domain.Instructor;
import ai.project.domain.MeetingTime;
import ai.project.domain.Room;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 *
 * This class will have all data with which we will initialize
 */
public class Data {
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> depts;
    private ArrayList<MeetingTime> meetingTimes;
    private int numberOfClasses=0;

    public Data() {
        initialize();
    }
    
    
    private Data initialize()
    {
        List<Room> roomsList;
        
        Room room1=new Room("CS-1",39);
        Room room2=new Room("CS-2",54);
        Room room3=new Room("CS-3",54);
        Room room4=new Room("CS-4",54);
        Room room5=new Room("CS-5",54);
        Room room6=new Room("CS-6",54);
        Room room7=new Room("CS-7",54);
        Room room8=new Room("CS-8",39);
        Room room9=new Room("CS-9",54);
        Room room10=new Room("CS-10",54);
        Room room11=new Room("CS-11",54);
        Room room12=new Room("CS-12",54);
        Room room13=new Room("CS-13",54);
        Room room14=new Room("CS-14",54);
        Room room15=new Room("E&M-1",54);
        Room room16=new Room("E&M-2",39);
        Room room17=new Room("E&M-3",54);
        Room room18=new Room("E&M-4",54);
        Room room19=new Room("E&M-6",54);
        Room room20=new Room("E&M-7",54);
        Room room21=new Room("E&M-8",54);
        Room room22=new Room("E&M-9",54);
        Room room23=new Room("E&M-10",54);
        Room room24=new Room("E&M-11",54);
        Room room25=new Room("E&M-12",54);
        Room room26=new Room("E&M-13",54);
        Room room27=new Room("E&M-14",54);
        Room room28=new Room("E&M-15",54);
        Room room29=new Room("E&M-17",54);
        Room room30=new Room("Auditorium",96);        
        Room room31=new Room("CE-1",54);
        Room room32=new Room("CE-2",54);
        Room room33=new Room("CE-3",54);
        Room room34=new Room("CE-11",54);
        Room room35=new Room("CE-12",54);
        Room room36=new Room("CE-13",54);
        Room room37=new Room("CE-21",46);
        Room room38=new Room("CE-22",46);        
        Room room39=new Room("CE-23",46);
        Room room40=new Room("CS-15",48);        
        Room room41=new Room("CS-16",48);
        Room room42=new Room("Seminar Hall",105);
        Room room43=new Room("E&M-16",39);
        Room room44=new Room("CS Lab 5",54);
        Room room45=new Room("CS Lab 6",54);
        Room room46=new Room("CS Lab 7",54);
        
        rooms=new ArrayList<Room>(Arrays.asList(room1,room2,room3,room4,room5,room6,room7,room8,room9,room10,room11,room12,
                room13,room14,room15,room16,room17,room18,room19,room20,room21,room22,room23,room24,room25,room26,room27,
                room28,room29,room30,
                room31,room32,room33,room34,room35,room36,room37,room38,room39,room40,
                room41,room42,room43,room44,room45,room46));
        
        MeetingTime meetingTime1=new MeetingTime("MT1","MWF 08:00 - 09:30");
        MeetingTime meetingTime2=new MeetingTime("MT2","MWF 09:30 - 11:00");
        MeetingTime meetingTime3=new MeetingTime("MT3","MWF 11:00 - 12:30");
        MeetingTime meetingTime4=new MeetingTime("MT4","MWF 12:30 - 02:00");
        MeetingTime meetingTime5=new MeetingTime("MT5","MWF 02:00 - 03:30");
        MeetingTime meetingTime6=new MeetingTime("MT6","MWF 03:30 - 05:00");
        MeetingTime meetingTime7=new MeetingTime("MT7","TTH 08:00 - 09:30");
        MeetingTime meetingTime8=new MeetingTime("MT8","TTH 09:30 - 11:00");
        MeetingTime meetingTime9=new MeetingTime("MT9","TTH 11:00 - 12:30");
        MeetingTime meetingTime10=new MeetingTime("MT10","TTH 12:30 - 02:00");
        MeetingTime meetingTime11=new MeetingTime("MT11","TTH 02:00 - 03:30");
        MeetingTime meetingTime12=new MeetingTime("MT12","TTH 03:30 - 05:00");
        
        meetingTimes=new ArrayList<MeetingTime>(Arrays.asList(meetingTime1,meetingTime2,meetingTime3,meetingTime4,
                meetingTime5,meetingTime6,meetingTime7,meetingTime8,
                meetingTime9,meetingTime10,meetingTime11,meetingTime12));
        
        instructors=new ArrayList<Instructor>();
        courses=new ArrayList<Course>();
        try
        {
            BufferedReader buf = new BufferedReader(new FileReader("C:\\Users\\Attar Telecom\\Documents\\NetBeansProjects\\AI project\\src\\ai\\project\\RFile.txt"));
            String lineJustFetched = null;
            String[] wordsArray;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    wordsArray = lineJustFetched.split("\t");
                    String cid= wordsArray[0];
                    String cname= wordsArray[1];
                    String teacher= wordsArray[2];
                    String dept= wordsArray[3];
                    int n= instructors.size();
                   
                    Instructor instructor=new Instructor(Integer.toString(n+1),teacher);
                    boolean flag=false;
                    for (int i = 0; i < n; i++) 
                    {
                        if (instructors.get(i).getName().equals(teacher)) {
                            flag=true;
                            instructor.setId(instructors.get(i).getId());
                        }
                    }
                    if (!flag) 
                    {
                        instructors.add(instructor);
                    }
                    Course course=new Course(cid,cname,new ArrayList<Instructor>(Arrays.asList(instructor)),30);
                    courses.add(course);
                }
            
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        Department dept1= new Department("CS",courses);
        
        depts=new ArrayList<Department>();
        
        depts=new ArrayList<Department>(Arrays.asList(dept1));
             
        depts.forEach(x->numberOfClasses+= x.getCourses().size()); 
        
        return this;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }
}
