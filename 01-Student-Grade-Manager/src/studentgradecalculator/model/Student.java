package studentgradecalculator.model;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int rollNumber;
    private Map<String,Integer> marks;
    
    public Student(String name, int rollNumber,Map<String,Integer> marks){
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    public Map<String,Integer> getMarks(){
        return new HashMap<>(marks);
    }

    public String getName(){
        return name;
    }
    public int getRollNumber(){
        return rollNumber;
    }
}
