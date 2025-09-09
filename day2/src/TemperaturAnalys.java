import java.util.Scanner;

public class TemperaturAnalys {
    static double[] weekTemperature = new double[7];
    static String[] weekDays = {"måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag"};
    static double medel, högsta, lägsta, sum;
    static int högstaDag, lägstaDag, dagarÖver20;
    public static void run() {
        Scanner sc = new Scanner(System.in);
        dagarÖver20 = 0;
        sum = 0;
        for (int i = 0; i < weekTemperature.length; i++) {
            boolean inputCorrect = false;
            do {
                try {
                    System.out.println("Vad är temperaturen på " + weekDays[i] + "?");
                    double temp = weekTemperature[i] = sc.nextDouble();
                    inputCorrect = true;
                    if (i == 0) {
                        högsta = temp;
                        högstaDag = 0;
                        lägsta = temp;
                        lägstaDag =0;
                    } else {
                        if (högsta < temp) {
                            högsta = temp;
                            högstaDag = i;
                        }
                        if (lägsta > temp) {
                            lägsta = temp;
                            lägstaDag = i;
                        }
                    }
                    if (temp > 20) {
                        dagarÖver20++;
                    }
                    sum += temp;
                } catch (Exception e) {
                    System.out.println("Fel inmatning. (" + e + ") Försök igen.");
                    sc.nextLine();
                }
            } while(!inputCorrect);
        }
        medel = sum / weekTemperature.length;
        System.out.println("Medel temperatur i vecka: " + medel + "°C.");
        System.out.println("Högsta temperatur: " + högsta + "°C, på " + weekDays[högstaDag] + ".");
        System.out.println("Lägsta temperatur: " + lägsta + "°C, på " + weekDays[lägstaDag] + ".");
        System.out.println("Antal dagar över 20°C: " + dagarÖver20);
        System.out.println("Temperatur graf:");
        int zeroOffset = lägsta < 0 ? (int)(-lägsta/2) : 0;
        for (double temp: weekTemperature) {
            if (temp >= 0) {
                System.out.print(" ".repeat(zeroOffset));
                System.out.println("*".repeat((int)(temp/2)));
            } else {
                System.out.print(" ".repeat((int)((temp - lägsta)/2)));
                System.out.println("*".repeat((int)(-temp/2)));
            }
        }
    }
}
