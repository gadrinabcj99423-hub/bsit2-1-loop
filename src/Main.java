import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Arrays to store up to 10 students
        int[] studentID = new int[10];
        String[] fullName = new String[10];
        int[] age = new int[10];
        String[] course = new String[10];
        double[] grade = new double[10];
        boolean[] enrolled = new boolean[10];

        int count = 0;
        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. View Statistics");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    if (count >= 10) {
                        System.out.println("Student list is full!");
                        break;
                    }

                    System.out.print("Enter Student ID: ");
                    studentID[count] = sc.nextInt();

                    sc.nextLine(); // consume newline

                    System.out.print("Enter Full Name: ");
                    fullName[count] = sc.nextLine();


                    do {
                        System.out.print("Enter Age: ");
                        age[count] = sc.nextInt();

                        if (age[count] <= 0) {
                            System.out.println("Age must be positive.");
                        }
                    } while (age[count] <= 0);

                    sc.nextLine();

                    System.out.print("Enter Course: ");
                    course[count] = sc.nextLine();


                    do {
                        System.out.print("Enter Grade (0-100): ");
                        grade[count] = sc.nextDouble();

                        if (grade[count] < 0 || grade[count] > 100) {
                            System.out.println("Grade must be between 0 and 100.");
                        }
                    } while (grade[count] < 0 || grade[count] > 100);

                    System.out.print("Is Enrolled? (true/false): ");
                    enrolled[count] = sc.nextBoolean();

                    count++;
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No student records found.");
                    } else {
                        System.out.println("\n===== STUDENT LIST =====");

                        for (int i = 0; i < count; i++) {

                            String standing;

                            if (grade[i] >= 90) {
                                standing = "Dean's Lister";
                            } else if (grade[i] >= 75) {
                                standing = "Passed";
                            } else {
                                standing = "Failed";
                            }

                            System.out.println("------------------------------");
                            System.out.println("ID: " + studentID[i]);
                            System.out.println("Name: " + fullName[i]);
                            System.out.println("Age: " + age[i]);
                            System.out.println("Course: " + course[i]);
                            System.out.println("Grade: " + grade[i]);
                            System.out.println("Enrolled: " + enrolled[i]);
                            System.out.println("Standing: " + standing);
                        }
                    }
                    break;


                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchID = sc.nextInt();

                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (studentID[i] == searchID) {
                            System.out.println("\nStudent Found!");
                            System.out.println("ID: " + studentID[i]);
                            System.out.println("Name: " + fullName[i]);
                            System.out.println("Age: " + age[i]);
                            System.out.println("Course: " + course[i]);
                            System.out.println("Grade: " + grade[i]);
                            System.out.println("Enrolled: " + enrolled[i]);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }
                    break;


                case 4:
                    if (count == 0) {
                        System.out.println("No student records available.");
                    } else {

                        double totalGrades = 0;
                        double highestGrade = grade[0];
                        String topStudent = fullName[0];

                        for (int i = 0; i < count; i++) {
                            totalGrades += grade[i];

                            if (grade[i] > highestGrade) {
                                highestGrade = grade[i];
                                topStudent = fullName[i];
                            }
                        }

                        double averageGrade = totalGrades / count;

                        System.out.println("\n===== CLASS STATISTICS =====");
                        System.out.println("Total Students: " + count);
                        System.out.printf("Average Grade: %.2f%n", averageGrade);
                        System.out.println("Top Student: " + topStudent);
                        System.out.println("Highest Grade: " + highestGrade);
                    }
                    break;


                case 5:
                    System.out.println("Thank you for using the Student Record Management System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}