package studentmanagement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            Student student = new Student("20212320", "Jair");
            student.addGrade(95.0);
            student.addGrade(88.5);
            student.addGrade(76.0);
            student.addGrade(61.0);
            student.evaluateStatus();

            student.removeGradeByIndex(2);
            student.removeGradeByValue(88.5);
            student.removeGradeByIndex(10);    // Out of bounds
            student.removeGradeByValue(101.0); // Invalid value

            student.reportCard();

        } catch (IllegalArgumentException e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.severe("Error: " + e.getMessage());
            }
        }
    }
}
