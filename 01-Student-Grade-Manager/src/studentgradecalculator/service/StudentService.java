package studentgradecalculator.service;

import studentgradecalculator.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> studentsList = new ArrayList<>();
    
    public void addStudent(Student student){
        if(student != null){
            studentsList.add(student);
        }
    }

    public List<Student> getAllStudents(){
        return new ArrayList<>(studentsList);
    }

    public int calculateTotal(Student student){
        if(student == null || student.getMarks() == null){
            return 0;
        }
        int totalSum = 0;
        for(Integer mark : student.getMarks().values()){
            totalSum += mark;
        }
        return totalSum;
    }

    public double calculateAverage(Student student){
        if(student == null || student.getMarks() == null || student.getMarks().size() == 0){
            return 0;
        }

        int size = student.getMarks().size();

        if(size == 0){
            return 0;
        }
        
        int total = calculateTotal(student);
        return (double) total / size;
    }

    public char calculateGrade(Student student){

        if(student == null){
            return 'F';
        }
        double average = calculateAverage(student);

        if(average >= 90){
            return 'A';
        }
        else if(average >= 75){
            return 'B';
        }
        else if(average >= 50){
            return 'C';
        }
        else{
            return 'F';
        }
    }
    public Student findByRollNumber(int rollNumber){
        for(Student student : studentsList){
            
            if(student.getRollNumber() == rollNumber){
                return student;
            }
        }
        return null;
    }
}
