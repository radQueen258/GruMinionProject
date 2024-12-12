# GRU MINION Project

## Introduction
The GRU MINION project is a demonstration of inter-process communication and process management in a Linux environment using Java. The project consists of two main programs:

1. **Gru**: The parent process responsible for creating and managing child processes.
2. **Minion**: The child process that performs specific tasks assigned by Gru.

This project illustrates the use of system calls like `fork()`, `execve()`, `wait()`, and others through Java's wrapper functions, enabling the management of multiple processes and their interactions.

---

## Functionality

### Gru Program
- Reads the number of child processes (N) to create from the command line.
- Spawns N child processes.
- Waits for each child process to complete execution.
- Restarts any child process that exits with a failure status (exit status 1).
- Logs the process creation, termination, and restart information.

### Minion Program
- Outputs its creation details (including its PID and parent PID).
- Sleeps for a random duration (between 5 and 10 seconds).
- Exits with a random status (either 0 or 1).
- Logs its termination status and related information.

---

## Prerequisites

- **Operating System**: Linux (tested on Ubuntu)
- **Java Version**: Java Development Kit (JDK) 8 or higher
- **Build Tool**: Java Compiler (javac)
- **Git**: For version control and collaboration

---

## How to Run the Program

### 1. Clone the Repository
Clone the project from GitHub:
```bash
git clone https://github.com/radQueen258/GruMinionProject.git
cd GruMinionProject
```

### 2. Compile the Java Files
Compile both Gru and Minion:
```bash
javac Gru.java Minion.java
```

### 3. Run the Gru Program
Execute the Gru program with the desired number of child processes (N > 0):
```bash
java Gru <number_of_processes>
```
For example, to create 5 child processes:
```bash
java Gru 5
```

### 4. Observe the Output
The program will output logs about process creation, termination, and restart status directly to the terminal.

---

## Example Output
```plaintext
Gru[21072]: process created. PID 21090.
Gru[21072]: process created. PID 21102.
Gru[21072]: process created. PID 21106.
Gru[21072]: process created. PID 21114.
Gru[21072]: process created. PID 21117.
Gru[21072]: process terminated. PID 21090. Exit status 1.
Gru[21072]: Restarting process PID 21090.
Gru[21072]: process terminated. PID 21102. Exit status 0.
Gru[21072]: restarted process. New PID 21343.
Gru[21072]: process terminated. PID 21343. Exit status 0.
```

---

## Project Structure
- `Gru.java`: The parent process code.
- `Minion.java`: The child process code.
- `README.md`: Project documentation.

---

## Key Features
- Demonstrates inter-process communication.
- Implements process creation, monitoring, and restarting mechanisms.
- Utilizes system calls like `fork()`, `execve()`, and `wait()` in Java.

---

## Notes
- Ensure both `Gru.java` and `Minion.java` are located in the same directory when executing.
- The `Minion` program must be compiled before running the `Gru` program.
- Logs are output to the standard terminal and can be redirected if needed.

---

## License
This project is open-source and available under the MIT License. See the `LICENSE` file for more details.

---

## Author
Created by radQueen258 as part of an exploration of Linux system programming concepts in Java.

