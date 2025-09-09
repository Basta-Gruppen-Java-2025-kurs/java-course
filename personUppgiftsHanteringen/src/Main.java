public class Main {
    String förenamn, efternamn; // såklart, namn är sträng
    int ålder; // ålder i hela år, så det är int
    double längd; // längd i meter, kan vara bråkvärde
    double vikt; // vikt i kilo, kan vara bråkvärde
    boolean ärStudent; // bara ja eller nej, så det är boolean
    public static void main(String[] args) {
        System.out.println("hi");
        int b = 0;
        b= switch (b) {
            case 1 ->  2;
            default -> 1;
        };
    }
}
