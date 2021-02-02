public class GameOver {
    private static boolean stop = true;
    private static boolean start = false;

    public static boolean getStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        GameOver.stop = stop;
    }

    public static boolean getStart() {
        return start;
    }

    public static void setStart(boolean start) {
        GameOver.start = start;
    }
}
