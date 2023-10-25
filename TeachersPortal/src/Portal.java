import java.util.*;
public class Portal {
    public static void main(String[] args) {
        Teacherportal portal = new Teacherportal();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Add Grade");
            System.out.println("4. edit Student Details");
            System.out.println("5. View Student Details");
            System.out.println("6. View Students list");
            System.out.println("7. View listed Courses");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    portal.addStudent(studentName);
                    break;

                case 2:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    portal.addCourse(courseName);
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int studentIdForGrade = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter course name: ");
                    String courseNameForGrade = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    int grade = scanner.nextInt();
                    portal.addGrade(studentIdForGrade, courseNameForGrade, grade);
                    break;

                case 4:
                    System.out.print("Enter student ID to edit: ");
                    int studentIdToEdit = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter new student name: ");
                    String newStudentName = scanner.nextLine();
                    portal.editStudentName(studentIdToEdit, newStudentName);
                    break;
                case 5:
                    System.out.print("Enter student ID to view details: ");
                    int studentIdToView = scanner.nextInt();
                    portal.viewStudentDetails(studentIdToView);
                    break;
                case 6:
                    portal.viewAllStudents();
                    break;
                case 7:
                    portal.viewAllCourses();
                    break;
                case 8:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
class Student{
    private int rollno;
    private String name;
    private Map<String, Integer> grades;

    public Student(int roll, String name){
        this.rollno=roll;
        this.name=name;
        this.grades=new HashMap<>();
    } public int getId() {
        return rollno;
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
        this.name = newName;
    }


    public void addGrade(String courseName, int grade) {
        grades.put(courseName, grade);
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }



}
class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Teacherportal{
    private List<Student> students;
    private List<Course> courses;
    private int id;
    public Teacherportal() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        id = 1;
    }
    public void addStudent(String name) {
        students.add(new Student(id, name));
        id++;
    }
    public void addCourse(String courseName) {
        courses.add(new Course(courseName));
    }
    public void addGrade(int studentId, String courseName, int grade) {
        Student student = getStudentById(studentId);
        if (student != null) {
            student.addGrade(courseName, grade);
        }
    }
    public void editStudentName(int studentId, String newName) {
        Student student = getStudentById(studentId);
        if (student != null) {
            student.setName(newName);
            System.out.println("Student name updated.");
        } else {
            System.out.println("Student not found.");
        }
    }


    public Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }
    public void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses are available.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : courses) {
                System.out.println(course.getName());
            }
        }
    }
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students are available.");
        } else {
            System.out.println("All Student Details:");
            for (Student student : students) {
                System.out.println("Student ID: " + student.getId());
                System.out.println("Student Name: " + student.getName());
                System.out.println("Grades:");
                for (Map.Entry<String, Integer> entry : student.getGrades().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("--------------");
            }
        }
    }

    public void viewStudentDetails(int studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Grades:");
            for (Map.Entry<String, Integer> entry : student.getGrades().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Student not found");
        }
    }

}
