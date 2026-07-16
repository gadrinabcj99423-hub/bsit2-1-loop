import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static double[] cutoff = {90, 80, 70, 60, 0};
    static char[] letter = {'A', 'B', 'C', 'D', 'F'};

    public static char letterFor(double grade) {
        for (int i = 0; i < cutoff.length; i++) {
            if (grade >= cutoff[i]) {
                return letter[i];
            }
        }
        return 'F';
    }

    public static void main(String[] args) {

        ArrayList<Student> List = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)) {

            int choice;

            while (true) {
                System.out.println("\n===== GRADE TRACKER MENU =====");
                System.out.println("1) Add Student");
                System.out.println("2) View Students");
                System.out.println("3) Class Average");
                System.out.println("4) Exit");
                System.out.print(" Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter student name: ");
                        String name = sc.next();

                        System.out.print("Enter grade: ");
                        double grade = sc.nextDouble();

                        List.add(new Student(name, grade));

                        System.out.println("Student added successfully!");
                        break;

                    case 2:
                        if (List.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            System.out.println("\nClass List:");
                            for (Student s : List) {
                                System.out.printf("%s - %.2f (%c)%n",
                                        s.name, s.grade, letterFor(s.grade));
                            }
                        }
                        break;

                    case 3:
                        if (List.isEmpty()) {
                            System.out.println("No students available to compute average.");
                        } else {
                            double total = 0;

                            for (Student s : List) {
                                total += s.grade;
                            }

                            double average = total / List.size();
                            System.out.printf("Class Average: %.2f%n", average);
                        }
                        break;

                    case 4:
                        System.out.println("Exiting program...");
                        return;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }
}

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}