// File: Gru.java
import java.io.*;
import java.util.*;

public class Gru {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 1) {
            System.err.println("Usage: java Gru <N>");
            System.exit(1);
        }

        int N = Integer.parseInt(args[0]);
        if (N <= 0) {
            System.err.println("N must be greater than 0.");
            System.exit(1);
        }

        List<Process> processes = new ArrayList<>();
        List<Process> toRestart = new ArrayList<>();
        Random random = new Random();
        long parentPid = ProcessHandle.current().pid();

        // Create N child processes
        for (int i = 0; i < N; i++) {
            int randomArg = 5 + random.nextInt(6); // Random number between 5 and 10
            ProcessBuilder pb = new ProcessBuilder("./minion", String.valueOf(randomArg));
            Process child = pb.start();

            System.out.printf("Gru[%d]: process created. PID %d.%n", parentPid, child.pid());
            processes.add(child);
        }

        // Wait for processes and check termination status
        for (Process process : processes) {
            long childPid = process.pid();
            int exitStatus = process.waitFor();
            System.out.printf("Gru[%d]: process terminated. PID %d. Exit status %d.%n", parentPid, childPid, exitStatus);

            if (exitStatus != 0) {
                System.out.printf("Gru[%d]: Restarting process PID %d.%n", parentPid, childPid);
                toRestart.add(process);
            }
        }

      // Restart failed processes with a retry limit
for (Process failedProcess : toRestart) {
    int retryCount = 0;
    boolean success = false;

    while (retryCount < 3 && !success) { // Retry up to 3 times
        int randomArg = 5 + random.nextInt(6); // Random number between 5 and 10
        Process restarted = new ProcessBuilder("./minion", String.valueOf(randomArg)).start();
        System.out.printf("Gru[%d]: restarted process. New PID %d.%n", parentPid, restarted.pid());

        // Wait for the restarted process and check its exit status
        long newChildPid = restarted.pid();
        int restartExitStatus = restarted.waitFor();
        System.out.printf("Gru[%d]: process terminated. PID %d. Exit status %d.%n", parentPid, newChildPid, restartExitStatus);

        if (restartExitStatus == 0) {
            success = true;
        } else {
            retryCount++;
        }
    }

    if (!success) {
        System.out.printf("Gru[%d]: Restarting process PID %d failed after %d attempts.%n", parentPid, failedProcess.pid(), retryCount);
    }
}

        System.exit(0);
    }
}
