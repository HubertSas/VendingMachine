public class settings {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearScreenWait() {
        try {
            Thread.sleep(1200);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}