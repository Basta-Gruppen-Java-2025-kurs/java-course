import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File source = new File("src/StudentScores.txt");
        Scanner sc = null;

        double sum = 0;
        StudentScore top = null, bottom = null;

        try {
            sc = new Scanner(source);
        } catch (Exception e) {
            System.out.println("Error opening file: " + e);
            System.exit(1);
        }
        int studentCount = sc.nextInt();
        sc.nextLine();
        List<StudentScore> scores = new ArrayList<>();
        for (int i = 0; i<studentCount; i++) {
            StudentScore nextScore = new StudentScore();
            nextScore.name = sc.nextLine();
            scores.add(nextScore);
        }
        for (StudentScore s: scores) {
            double score = sc.nextDouble();
            sum += score;
            s.score = score;
            if (bottom == null || bottom.score > score) {
                bottom = s;
            }
            if (top == null || top.score < score) {
                top = s;
            }
        }
        double mediumScore = sum/studentCount;
        System.out.println("Average score: " + mediumScore);
        System.out.println("Best score: " + top.name + " - " + top.score);
        System.out.println("Worst score: " + bottom.name + " - " + bottom.score);
        scores.sort(Comparator.comparingDouble(a -> a.score));
        System.out.println("\nBelow medium score:");
        boolean reachedMedium = false;
        for (StudentScore score : scores) {
            if (score.score > mediumScore && !reachedMedium) {
                System.out.println("\nAbove mediuim score:");
                reachedMedium = true;
            }
            System.out.println(score.name + " - " + score.score + " (" + score.rating() + ")");
        }
    }
}

class StudentScore {
    String name;
    double score;

    char rating() {
        return score <= 40 ? 'F' : score <= 50 ? 'E' : score <= 60 ? 'D' : score <= 70 ? 'C' : score <= 80 ? 'B' : 'A';
    }
}