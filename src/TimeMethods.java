import java.util.Scanner;
import java.lang.StringBuilder;

public class TimeMethods {
    public static Scanner scanner = new Scanner(System.in);

    public static void pauseFor(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForEnter() {
        System.out.println("Press anything to continue... ");
        String ans = scanner.nextLine();
    }
    public static void loadingBar() {
        for (int i = 0; i <= 100; i++) {
            System.out.print("\r" + getLoadingBar(i));
            pauseFor(10);
        }
    }

    public static String getLoadingBar(int percent) {
        StringBuilder loadBar = new StringBuilder("[");
        int filled = percent / 10;
        int noFilled = 10 - filled;
        for (int i = 0; i < filled; i++) {
            loadBar.append("--");
        }
        for (int i = 0; i < noFilled; i++) {
            loadBar.append(" ");
        }
        loadBar.append("]");
        return loadBar.toString();
    }

    public static void veryLongPause () {
        pauseFor(4000);
    }

    public static void longPause () {
        pauseFor(2000);
    }

    public static void mediumPause () {
        pauseFor(1500);
    }

    public static void shortPause () {
        pauseFor(1000);
    }

    public static void veryShortPause () {
        pauseFor(500);
    }
}
