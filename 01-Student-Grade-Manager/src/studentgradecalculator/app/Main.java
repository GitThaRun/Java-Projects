package studentgradecalculator.app;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import studentgradecalculator.service.StudentService;
import studentgradecalculator.model.Student;
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        while(true){
            System.out.println("Select Your Option : ");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3.Search Student By Roll Number");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter Your Name : ");
                    String name = sc.nextLine();

                    System.out.println("Enter your Roll Number : ");
                    int roll = sc.nextInt();

                    Map<String,Integer> marks = new HashMap<>();

                    System.out.println("Enter How Many Subjects : ");
                    int n = sc.nextInt();
                    
                    for(int i = 0;i < n;i++){
                    System.out.println("Enter Subject Name : ");
                    String subject = sc.next();

                    System.out.println("Enter Marks : ");
                    int mark = sc.nextInt();

                    if(mark < 0 || mark > 100){
                        System.out.println("Invalid Marks");
                        i--;
                        continue;
                    }

                    marks.put(subject,mark);
                    }
                    Student s = new Student(name,roll,marks);
                    service.addStudent(s);

                    System.out.println("Student Added Successfully");
                    break;
            
                case 2:
                    List<Student> list = service.getAllStudents();
                    
                    if(list.isEmpty()){
                        System.out.println("No Students Available");
                        break;
                    }

                    for(Student li : list){
                        System.out.println("\nStudent Details : \n");
                        System.out.println("Name : " +li.getName());
                        System.out.println("Roll Number : " +li.getRollNumber());
                        System.out.println("Marks : " +li.getMarks());

                        System.out.println("Total : " +service.calculateTotal(li));
                        System.out.printf("Average : %.2f\n",service.calculateAverage(li));
                        System.out.println("Grade : " +service.calculateGrade(li));

                        System.out.println("____________________________________");
                    }
                    break;

                case 3:
                    
                    System.out.println("Enter Roll Number : ");
                    int rollNum = sc.nextInt();
                    
                    Student student = service.findByRollNumber(rollNum);
                    if(student == null){
                        System.out.println("Student Not Found");
                    }

                    else{
                        System.out.println("Name : " +student.getName());
                        System.out.println("Roll Num : " +student.getRollNumber());
                        System.out.println("Marks : " +student.getMarks());

                        System.out.println("__________________________________");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
}
