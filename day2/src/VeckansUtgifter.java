import java.util.Scanner;

public class VeckansUtgifter {
    public static void run() {
        double [] utgifter = new double[7];
        int dyrasteDag = 0, dagsÖver100 = 0;
        double total = 0;
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<utgifter.length; i++) {
            boolean correctInput = false;
            do {
                System.out.println("Vad var dina utgifter på " + Week.days[i] + "?");
                try {
                    double utgift = utgifter[i] = sc.nextDouble();
                    correctInput = true;
                    if (utgift > utgifter[dyrasteDag]) {
                        dyrasteDag = i;
                    }
                    total += utgift;
                    if (utgift > 100) {
                        dagsÖver100++;
                    }
                } catch (Exception e) {
                    System.out.println("Fel inmatning. (" + e + ") Försök igen.");
                    sc.nextLine();
                }
            } while (!correctInput);
        }
        System.out.println("Totala utgifter: " + total + " kr");
        System.out.println("Den dyraste dagen: " + Week.days[dyrasteDag] + ", utgift: " + utgifter[dyrasteDag] + " kr");
        System.out.println("Antal dagar med utgifter över 100 kr: " + dagsÖver100);
    }
}
