import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExamProcessingSystem {
    private static final String[] SUBJECTS = {"Math", "English", "Science", "History", "Geography"};
    private static Student[] students = new Student[10]; // At least 10 students
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readStudentDetails();
        readSubjectScores();
        calculateAverageAndGrade();
        saveToFile();
        displayReport();
    }

    // Read student names and IDs
    private static void readStudentDetails() {
        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter Student " + (i+1) + " Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Student " + (i+1) + " ID:");
            String id = scanner.nextLine();
            students[i] = new Student(name, id);
        }
    }

    // Read scores for each subject
    private static void readSubjectScores() {
        for (Student student : students) {
            System.out.println("\nEnter Scores for " + student.getName() + ":");
            double[] scores = new double[SUBJECTS.length];
            for (int i = 0; i < SUBJECTS.length; i++) {
                System.out.print(SUBJECTS[i] + ": ");
                scores[i] = scanner.nextDouble();
                scanner.nextLine(); // Clear input buffer
            }
            student.setScores(scores);
        }
    }

    // Calculate average and grade for all students
    private static void calculateAverageAndGrade() {
        for (Student student : students) {
            student.calculateAverage();
            student.calculateGradeAndRecommendation();
        }
    }
    private static void saveToFile() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students_database.txt"))) {
            // Write header (optional)
            writer.write("Name,ID,Math,English,Science,History,Geography,Average,Grade,Recommendation,Date");
            writer.newLine();

            // Write student data
            for (Student student : students) {
                String line = String.format("%s,%s,", student.getName(), student.getId());
                for (double score : student.getScores()) {
                    line += String.format("%.1f,", score);
                }
                line += String.format("%.1f,%s,%s,%s",
                        student.getAverage(), student.getGrade(), student.getRecommendation(), formattedDate);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("\nData saved to students_database.txt!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Display report card
    private static void displayReport() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        System.out.println("\n========== REPORT CARDS ==========");
        for (Student student : students) {
            System.out.println("\nDate: " + formattedDate);
            System.out.println("Name: " + student.getName());
            System.out.println("ID: " + student.getId());
            System.out.println("Subjects:");
            for (int i = 0; i < SUBJECTS.length; i++) {
                System.out.printf("%-10s: %.1f%n", SUBJECTS[i], student.getScores()[i]);
            }
            System.out.printf("Average: %.1f%n", student.getAverage());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("Recommendation: " + student.getRecommendation());
            System.out.println("-----------------------------");
        }
    }
}