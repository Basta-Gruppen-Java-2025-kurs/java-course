import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentScores {
    public static void run() {
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
        for (int i = 0; i < studentCount; i++) {
            StudentScore nextScore = new StudentScore();
            nextScore.name = sc.nextLine();
            scores.add(nextScore);
        }
        for (StudentScore s : scores) {
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
        double mediumScore = sum / studentCount;
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
            score.printWithScore();
        }
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nSearch for student (empty line to quit):");
            String request = input.nextLine();
            if (request.length() < 1) {
                break;
            }
            List<StudentScore> found = scores.stream().filter(s -> s.name.contains(request)).collect(Collectors.toList());
            if (found.size() < 1) {
                System.out.println("No matching names found");
            } else {
                for (StudentScore score : found) {
                    score.printWithScore();
                }
            }
        }
        System.out.println("Good bye.");
    }
}

class StudentScore {
    String name;
    double score;

    char rating() {
        return score <= 40 ? 'F' : score <= 50 ? 'E' : score <= 60 ? 'D' : score <= 70 ? 'C' : score <= 80 ? 'B' : 'A';
    }

    void printWithScore() {
        System.out.println(name + " - " + score + " (" + rating() + ")");
    }
}