import java.util.Scanner;
import java.util.ArrayList;
public class Student {

    private String studentId;
    private String fullName;
    private String program;
    private int yearLevel;

    public void Student(String studentId, String fullName, String program, int yearLevel) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.program = program;
        this.yearLevel = yearLevel;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProgram() {
        return program;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public String describe() {
        return studentId + " | " + fullName + " | " + program + " | Year " + yearLevel;
    }
}
public class Course {

    private String courseCode;
    private String title;
    private int units;
    private int capacity;
    private int enrolledCount;

    public Course(String courseCode, String title, int units, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.units = units;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getUnits() {
        return units;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public boolean isFull() {
        return enrolledCount >= capacity;
    }

    public void addOneEnrollee() {
        if (!isFull()) {
            enrolledCount++;
        }
    }
}

public static void main(String[] args){
    try(Scanner sc = new Scanner(System.in){
        ArrayList<String> Student = new ArrayList<>();
        ArrayList<String> courses = new ArrayList<>();

    do{

            String menu = """
                ==== LICEO ENROLLMENT SYSTEM (CLI) ====
                [1] Register Student
                [2] Add Course Offering
                [3] Enroll Student to Course
                [4] View All Students
                [4] View All Students
                [5] View All Courses
                [6] View Student Load (Courses + Total Units)
                [0] Exit
                ---------------------------------------------------
                """;
            System.out.println(menu);
            System.out.print("Please select your choices: ");
            int options = sc.nextInt();

            sc.nextLine();

            switch (options){
                case 1:
                    System.out.println("You choose the Register Student");


                case 2:
                    System.out.println("Add Course Offering");

                case 3:
                    System.out.println(" Enroll Student to Courseg");

                case 4:
                    System.out.println("View All Students");

                case 5:
                    System.out.println("View All Courses");

                case 6:
                    System.out.println("View Student Load (Courses + Total Units)");

                case 0:
                    System.out.println("Thank you for using the enrollment System");

                    break;

                default:
                    System.out.println("Invalid input");
            }
        }while(options != 3);
    }
}