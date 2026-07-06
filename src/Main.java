import java.util.ArrayList;
import java.util.Scanner;


interface Exportable {

    String toCsv();

    default void printExport() {
        System.out.println(toCsv());
    }
}


abstract class User implements Exportable {

    private final int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String role();

    public abstract String permissions();

    public void display() {
        System.out.printf(
                "[%d] %-12s %-24s %-8s %s%n",
                id,
                name,
                email,
                role(),
                permissions()
        );
    }

    @Override
    public String toCsv() {
        return id + "," + name + "," + email + "," + role();
    }
}


class Admin extends User {

    public Admin(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String role() {
        return "ADMIN";
    }

    @Override
    public String permissions() {
        return "create, read, update, delete";
    }
}


class Teacher extends User {

    private String department;

    public Teacher(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String role() {
        return "TEACHER";
    }

    @Override
    public String permissions() {
        return "read, update grades";
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + department;
    }
}


class Student extends User {

    private String course;

    public Student(int id, String name, String email, String course) {
        super(id, name, email);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String role() {
        return "STUDENT";
    }

    @Override
    public String permissions() {
        return "read only";
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + course;
    }
}


class UserManager {

    private ArrayList<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);

        System.out.println(
                "Added: " + user.getName() +
                        " (" + user.role() + ")"
        );
    }

    public void listAll() {

        if (users.isEmpty()) {
            System.out.println("No users yet.");
            return;
        }

        String header = "ID NAME EMAIL ROLE PERMISSIONS";

        System.out.println(header);
        System.out.println("-".repeat(69));

        for (User u : users) {
            u.display();
        }

        System.out.println("Total users: " + users.size());
    }

    public User findById(int id) {

        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public boolean deleteById(int id) {

        User found = findById(id);

        if (found == null) {
            return false;
        }

        users.remove(found);

        return true;
    }

    public void exportAll() {

        if (users.isEmpty()) {
            System.out.println("Nothing to export.");
            return;
        }

        System.out.println("--- CSV EXPORT ---");

        for (User u : users) {
            u.printExport();
        }

        System.out.println("--- END OF EXPORT ---");
    }

    public int count() {
        return users.size();
    }
}


public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            UserManager manager = new UserManager();


            manager.add(
                    new Admin(
                            1,
                            "Razz",
                            "razz@liceo.edu.ph"
                    )
            );

            manager.add(
                    new Teacher(
                            2,
                            "Maria",
                            "maria@liceo.edu.ph",
                            "CIT"
                    )
            );

            manager.add(
                    new Student(
                            3,
                            "Ana",
                            "ana@liceo.edu.ph",
                            "BSIT"
                    )
            );

            int choice;

            do {
                String choices = """
                        [1] Add User
                        [2] List All Users
                        [3] Search User by id
                        [4] Delete User by id 
                        [5] Export All users
                        [6] Exit
                        """;
                System.out.println(choices);
                System.out.print("Please Enter your choices: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:

                        String menu = """
                                ==== ADD USER ====
                                
                                [1] Admin
                                [2] Teacher
                                [3] Student
                                """;

                        System.out.println(menu);
                        System.out.print("Please choose the user type: ");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();


                        if (manager.findById(id) != null) {
                            System.out.println("ID already exists.");
                            break;
                        }

                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter email: ");
                        String email = sc.nextLine();

                        if (type == 1) {

                            manager.add(
                                    new Admin(
                                            id,
                                            name,
                                            email
                                    )
                            );

                        } else if (type == 2) {

                            System.out.print("Enter department: ");
                            String department = sc.nextLine();

                            manager.add(
                                    new Teacher(
                                            id,
                                            name,
                                            email,
                                            department
                                    )
                            );

                        } else if (type == 3) {

                            System.out.print("Enter course: ");
                            String course = sc.nextLine();

                            manager.add(
                                    new Student(
                                            id,
                                            name,
                                            email,
                                            course
                                    )
                            );

                        } else {

                            System.out.println("Invalid user type.");
                        }

                        break;

                    case 2:

                        System.out.println();

                        manager.listAll();

                        break;

                    case 3:

                        System.out.println();
                        System.out.println("--- SEARCH USER ---");

                        System.out.print("Enter ID to search: ");
                        int searchId = sc.nextInt();
                        sc.nextLine();

                        User found = manager.findById(searchId);

                        if (found == null) {

                            System.out.println("User not found.");

                        } else {

                            System.out.println("User found:");
                            System.out.println(
                                    "ID: " + found.getId()
                            );
                            System.out.println(
                                    "Name: " + found.getName()
                            );
                            System.out.println(
                                    "Email: " + found.getEmail()
                            );
                            System.out.println(
                                    "Role: " + found.role()
                            );
                            System.out.println(
                                    "Permissions: " + found.permissions()
                            );
                        }

                        break;

                    case 4:

                        System.out.println();
                        System.out.println("--- DELETE USER ---");

                        System.out.print("Enter ID to delete: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();

                        if (manager.deleteById(deleteId)) {

                            System.out.println("User deleted successfully.");

                        } else {

                            System.out.println("User not found.");
                        }

                        break;

                    case 5:

                        System.out.println();

                        manager.exportAll();

                        break;

                    case 6:

                        System.out.println();
                        System.out.println("Thank you for using the system!");

                        break;

                    default:

                        System.out.println("Invalid option. Please try again.");
                }

            } while (choice != 6);
        }
    }
}