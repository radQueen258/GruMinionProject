// package gru_minion;

import java.util.Random;

public class Minion {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Minion <S>");
            System.exit(1);
        }

        int S = Integer.parseInt(args[0]);
        int pid = (int) ProcessHandle.current().pid();
        int ppid = (int) ProcessHandle.current().parent().orElseThrow().pid();

        System.out.printf("Minion[%d]: created. Parent PID %d.%n", pid, ppid);

        try {
            Thread.sleep(S * 1000L); // Sleep for S seconds
        } catch (InterruptedException e) {
            System.err.println("Minion interrupted during sleep.");
        }

        Random random = new Random();
        int exitStatus = random.nextBoolean() ? 0 : 1;

        System.out.printf("Child[%d]: before terminated. Parent PID %d. Exit status %d.%n", pid, ppid, exitStatus);
        System.exit(exitStatus);
    }
}
