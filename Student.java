package studentmanagement;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student {
    private static final Logger logger = Logger.getLogger(Student.class.getName());

    private final String id;
    private final String name;
    private final List<Double> grades;
    private String pass = "unknown";
    private boolean honor = false;

    public Student(String id, String name) {
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Student ID and name must not be empty.");
            }
            throw new IllegalArgumentException("Student ID and name must not be empty.");
        }
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Double grade) {
        if (grade == null) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Grade cannot be null.");
            }
            return;
        }
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Invalid grade: %.2f. Grade must be between 0 and 100.", grade));
            }
        }
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0;
        double total = 0;
        for (Double grade : grades) {
            total += grade;
        }
        return total / grades.size();
    }

    public String getLetterGrade() {
        double average = calculateAverage();
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }

    public void evaluateStatus() {
        double avg = calculateAverage();
        honor = avg >= 90;
        pass = avg >= 60 ? "Passed" : "Failed";
    }

    public void removeGradeByIndex(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Removed grade at index: %d", index));
            }
        } else {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Index out of bounds: %d", index));
            }
        }
    }

    public void removeGradeByValue(double value) {
        if (grades.contains(value)) {
            grades.remove(value);
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Removed grade by value: %.2f", value));
            }
        } else {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Grade not found: %.2f", value));
            }
        }
    }

    public void reportCard() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(getSummaryReport());
        }
    }

    public String getSummaryReport() {
        evaluateStatus();
        return String.format("""
            ----- Student Summary Report -----
            ID: %s
            Name: %s
            Number of Grades: %d
            Average Grade: %.2f
            Letter Grade: %s
            Status: %s
            Honor Roll: %s
            """,
            id, name, grades.size(), calculateAverage(),
            getLetterGrade(), pass, honor ? "Yes" : "No"
        );
    }
}
