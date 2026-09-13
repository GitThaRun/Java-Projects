import java.util.Scanner;

public class Student_Result_Management{
    private static final int MAX_STUDENTS = 50;

    private static boolean isIdAvailable(int[] studentId,int studentCount,int id){
        for(int i = 0;i < studentCount;i++){
            if(studentId[i] == id){
                return false;
            }
        }
        return true;
    }

    private static int addStudent(int[] studentId,String[] studentNames,int[] marks,int studentCount,Scanner sc){
        int id;

        while (true) {
            System.out.print("Enter Student ID: ");

            if (sc.hasNextInt()) {
                id = sc.nextInt();
                sc.nextLine();

                if (id > 0 && isIdAvailable(studentId, studentCount, id)) {
                    break;
                }
            } else {
                sc.nextLine();
            }

            System.out.println(
                    "Invalid or duplicate ID. Enter a unique positive integer."
            );
        }

        studentId[studentCount] = id;

        System.out.print("Enter Student Name : ");
        studentNames[studentCount] = sc.nextLine();

        int studentMarks;
        
        while(true){
            System.out.print("Enter Student Marks (0-100) : ");

            if(sc.hasNextInt()){
                studentMarks = sc.nextInt();
                sc.nextLine();

                if(studentMarks >= 0 && studentMarks <= 100){
                    marks[studentCount] = studentMarks;
                    break;
                }
            }
            else{
                sc.nextLine();
            }
            System.out.println("Invalid marks. Please enter a value between 0 and 100.");
        }
        return studentCount + 1;
    }

    private static void displayStudents(int[] studentId,String[] studentNames,int[] marks,int studentCount){

        System.out.println("-----------------------------------------------------");
        System.out.println("ID\tName\t\tMarks\tGrade\tStatus");
        System.out.println("-----------------------------------------------------");

        for(int i = 0;i < studentCount;i++){
            System.out.println(studentId[i] + "\t" + 
                               studentNames[i] + "\t\t" + 
                               marks[i] + "\t" + 
                               calculateGrade(marks[i]) + "\t" +
                               checkPassOrFail(marks[i]));
        }
        System.out.println("-----------------------------------------------------");

        System.out.printf("Class Average : %.2f%n",calculateAverage(marks, studentCount));
    }

    private static char calculateGrade(int marks){
        if(marks >= 90){
            return 'A';
        }
        else if(marks >= 80){
            return 'B';
        }
        else if(marks >= 70){
            return 'C';
        }
        else if(marks >= 60){
            return 'D';
        }
        else if(marks >= 40){
            return 'E';
        }
        else{
            return 'F';
        }
    }

    private static String checkPassOrFail(int marks){
        if(calculateGrade(marks) == 'F'){
            return "FAIL";
        }
        else{
            return "PASS";
        }
    }

    private static double calculateAverage(int[] marks,int studentCount){
        int total = 0;

        if(studentCount == 0){
            return 0.0;
        }
        for(int i = 0;i < studentCount;i++){
            total += marks[i];
        }
        return (double) total / studentCount;
    }

    private static void searchStudent(int[] studentId,String[] studentNames,int[] marks,int studentCount,int id){
        boolean isFound = false;
        for(int i = 0;i < studentCount;i++){

            if(studentId[i] == id){
                System.out.println("\nStudent Details : ");
                System.out.println("------------------------------------");

                System.out.println("ID : " + studentId[i]);
                System.out.println("Name : " + studentNames[i]);
                System.out.println("Marks : " + marks[i]);
                System.out.println("Grade : " + calculateGrade(marks[i]));
                System.out.println("Status : " + checkPassOrFail(marks[i]));

                 System.out.println("------------------------------------");
                isFound = true;
                break;
            }
        }
        if(!isFound){
            System.out.println("Student With " + id + " Not Found");
        }
    }

    private static void displayHighestMarks(int[] studentId,String[] studentNames,int[] marks,int studentCount){
        if(studentCount == 0){
            System.out.println("No students available.");
            return;
        }
        int max = 0;

        for(int i = 1;i < studentCount;i++){
            if(marks[i] > marks[max]){
                max = i;
            }
        }

        System.out.println("\nStudent With Highest Marks : ");
        System.out.println("------------------------------------");

        System.out.println("ID : " + studentId[max]);
        System.out.println("Name : " + studentNames[max]);
        System.out.println("Marks : " + marks[max]);
        System.out.println("Grade : " + calculateGrade(marks[max]));
        System.out.println("Status : " + checkPassOrFail(marks[max]));
        
         System.out.println("------------------------------------");
    }
    private static void displayLowestMarks(int[] studentId,String[] studentNames,int[] marks,int studentCount){
        if(studentCount == 0){
            System.out.println("No students available.");
            return;
        }
        int min = 0;

        for(int i = 1;i < studentCount;i++){
            if(marks[i] < marks[min]){
                min = i;
            }
        }

        System.out.println("\nStudent With Lowest Marks : ");
        System.out.println("------------------------------------");

        System.out.println("ID : " + studentId[min]);
        System.out.println("Name : " + studentNames[min]);
        System.out.println("Marks : " + marks[min]);
        System.out.println("Grade : " + calculateGrade(marks[min]));
        System.out.println("Status : " + checkPassOrFail(marks[min]));
        
        System.out.println("------------------------------------");
    }

    private static int countPassedStudents(int[] marks,int studentCount){
        int count = 0;
        for(int i = 0;i < studentCount;i++){
            if(marks[i] >= 40){
                count++;
            }
        }
        return count;
    }

    private static int countFailedStudents(int[] marks,int studentCount){
        int count = 0;
        for(int i = 0;i < studentCount;i++){
            if(marks[i] < 40){
                count++;
            }
        }
        return count;
    }

    private static void displayRankings(int[] studentId,String[] studentNames,int[] marks,int studentCount){
        if(studentCount == 0){
            System.out.println("No students available.");
            return;
        }
        for(int i = 0;i < studentCount;i++){
            for(int j = i + 1;j < studentCount;j++){

                if(marks[j] > marks[i]){
                    int tempMarks = marks[i];
                    marks[i] = marks[j];
                    marks[j] = tempMarks;

                    int tempId = studentId[i];
                    studentId[i] = studentId[j];
                    studentId[j] = tempId;

                    String tempNames = studentNames[i];
                    studentNames[i] = studentNames[j];
                    studentNames[j] = tempNames;
                }
            }
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Rank\tID\tName\t\tMarks\tGrade\tStatus");
        System.out.println("------------------------------------------------------------");
        
        for(int i = 0;i < studentCount;i++){
            System.out.println(
                (i + 1) + "\t" +
                studentId[i] + "\t" +
                studentNames[i] + "\t\t" +
                marks[i] + "\t" +
                calculateGrade(marks[i]) + "\t" +
                checkPassOrFail(marks[i])
            );
        }
        System.out.println("------------------------------------------------------------");
    }
    public static void main(String[] args) {
        int[] studentId = new int[MAX_STUDENTS];
        String[] studentNames = new String[MAX_STUDENTS];
        int[] marks = new int[MAX_STUDENTS];

        int studentCount = 0;
        int choice;
        try (Scanner sc = new Scanner(System.in)) {
            
            while(true){
               
                System.out.println("\n========================================");
                System.out.println("   STUDENT RESULT MANAGEMENT SYSTEM");
                System.out.println("==========================================");
                System.out.println("1. Add Student");
                System.out.println("2. Display Students");
                System.out.println("3. Search Student");
                System.out.println("4. Display Highest Marks");
                System.out.println("5. Display Lowest Marks");
                System.out.println("6. Display Rankings");
                System.out.println("7. Display Class Average");
                System.out.println("8. Display Passed Student Count");
                System.out.println("9. Display Failed Student Count");
                System.out.println("10. Exit");
                System.out.println("==========================================");

                System.out.print("Enter your choice: ");

                if(!sc.hasNextInt()){
                    System.out.println("Invalid Choice.Enter a Number From 1 to 10");
                    sc.nextLine();
                }
                choice = sc.nextInt();
                sc.nextLine();

                switch(choice){
                    case 1:
                        if(studentCount < MAX_STUDENTS){
                            studentCount = addStudent(studentId, studentNames, marks, studentCount, sc);
                        }
                        else{
                            System.out.println("Maximum Limit reached");
                        }
                        break;
                    case 2:
                        displayStudents(studentId, studentNames, marks, studentCount);
                        break;
                    case 3:
                        if(studentCount == 0){
                            System.out.println("No students available");
                        }
                        else{
                            System.out.print("Enter Student ID to search : ");
                            int id = sc.nextInt();
                            sc.nextLine();

                            searchStudent(studentId, studentNames, marks, studentCount, id);
                        }
                        break;
                    case 4:
                        displayHighestMarks(studentId, studentNames, marks, studentCount);
                        break;
                    case 5:
                        displayLowestMarks(studentId, studentNames, marks, studentCount);
                        break;
                    case 6:
                        displayRankings(studentId, studentNames, marks, studentCount);
                    case 7:
                        if(studentCount == 0){
                            System.out.println("No students available.");
                        }
                        else{
                            System.out.printf("Class Average : %.2f%n", calculateAverage(marks, studentCount));
                        }
                        break;
                    case 8:
                        System.out.print("Passed Students : " + countPassedStudents(marks, studentCount));
                        break;
                    case 9:
                        System.out.println("Failed Students : " + countFailedStudents(marks, studentCount));
                        break;
                    case 10:
                        System.out.println("Exiting Program...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select 1 to 10.");
                }
            }
        }
    }
}