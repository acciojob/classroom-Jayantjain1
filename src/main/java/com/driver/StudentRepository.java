package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String, Student> stMap;
    private HashMap<String, Teacher> teMap;
    private HashMap<String, List<String>> teaStuMap;



    //Initialization is very important :

    public StudentRepository(){
        this.stMap = new HashMap<String, Student>();
        this.teMap = new HashMap<String, Teacher>();
        this.teaStuMap = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student std){
        stMap.put(std.getName(), std);
    }

    public void saveTeacher(Teacher teacher){
        teMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(stMap.containsKey(student)&&teMap.containsKey(teacher)){

            List<String> currentstudentsofteacher = new ArrayList<>();

            if(teaStuMap.containsKey(teacher))
                currentstudentsofteacher = teaStuMap.get(teacher);

            currentstudentsofteacher.add(student);

            teaStuMap.put(teacher,currentstudentsofteacher);

        }
    }
    public Student findStudent(String student){
        return stMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        return teMap.get(teacher);
    }

    public List<String> findStudentFromTeacher(String teacher){
        List<String> stdList = new ArrayList<String>();
        if(teaStuMap.containsKey(teacher)) stdList = teaStuMap.get(teacher);
        return stdList;
    }

    public List<String> findAllStudents(){
        return new ArrayList<>(stMap.keySet());
    }

    public void deleteTeacher(String teacher){

        List<String> std= new ArrayList<String>();
        if(teaStuMap.containsKey(teacher)){

            std = teaStuMap.get(teacher);


            for(String student: std){
                if(stMap.containsKey(student)){
                    stMap.remove(student);
                }
            }


            teaStuMap.remove(teacher);
        }


        if(teMap.containsKey(teacher)){
            teMap.remove(teacher);
        }
    }

    public void deleteAllTeacher(){

        HashSet<String> StSet = new HashSet<String>();


        teMap = new HashMap<>();


        for(String t: teaStuMap.keySet()){


            for(String student: teaStuMap.get(t)){
                StSet.add(student);
            }
        }


        for(String st: StSet){
            if(stMap.containsKey(st)){
                stMap.remove(st);
            }
        }

        teaStuMap = new HashMap<>();
    }
}