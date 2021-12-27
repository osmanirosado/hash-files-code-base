import java.util.concurrent.TimeUnit;

/*
 Using code from https://howtodoinjava.com/java/date-time/execution-elapsed-time/
 */
public class ElapsedTimeExample {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        methodToTime();

        long endTime = System.nanoTime();

        long durationInNano = (endTime - startTime);
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);

        System.out.println(durationInNano);
        System.out.println(durationInMillis);
    }

    private static void methodToTime() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
