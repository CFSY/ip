import AlanExceptions.AlanException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alan {
    private final Scanner input;
    private final Formatter formatter;
    private final Executor executor;
    private final List<Task> taskList;

    public Alan() {
        this.input = new Scanner(System.in);
        this.formatter = new Formatter();
        this.executor = new Executor();
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Create new instance of Alan and run him
        Alan alan = new Alan();
        alan.start();
    }

    private void start() {
        greet();
        run();
    }

    private void run() {
        System.out.println("How may I be of service?");

        label:
        while (true) {
            String userInput = input.nextLine();
            String command = userInput.split(" ", 2)[0];

            try {
                switch (command) {
                    case "bye":
                        break label;
                    case "list":
                        executor.excList(taskList);
                        break;
                    case "event":
                        executor.excEvent(taskList, userInput);
                        break;
                    case "deadline":
                        executor.excDeadline(taskList, userInput);
                        break;
                    case "todo":
                        executor.excTodo(taskList, userInput);
                        break;
                    case "mark":
                        executor.excMark(taskList, userInput);
                        break;
                    case "unmark":
                        executor.excUnmark(taskList, userInput);
                        break;
                    case "delete":
                        executor.excDelete(taskList, userInput);
                        break;
                    case "help":
                        // TODO: 18/8/22
                        break;
                    default:
                        System.out.println(formatter.invalid());
                        break;
                }
            } catch (AlanException exception) {
                executor.excException(exception.getMessage());
            }
        }

        System.out.println(formatter.basic("Goodbye! See you soon!") );
    }

    // Prints a greeting
    private void greet() {
        final String logo = " $$$$$$\\  $$\\        $$$$$$\\  $$\\   $$\\\n"
                + "$$  __$$\\ $$ |      $$  __$$\\ $$$\\  $$ |\n"
                + "$$ /  $$ |$$ |      $$ /  $$ |$$$$\\ $$ |\n"
                + "$$$$$$$$ |$$ |      $$$$$$$$ |$$ $$\\$$ |\n"
                + "$$  __$$ |$$ |      $$  __$$ |$$ \\$$$$ |\n"
                + "$$ |  $$ |$$ |      $$ |  $$ |$$ |\\$$$ |\n"
                + "$$ |  $$ |$$$$$$$$\\ $$ |  $$ |$$ | \\$$ |\n"
                + "\\__|  \\__|\\________|\\__|  \\__|\\__|  \\__|\n";

        System.out.println(getTimeGreeting() +
                "!\nMy name is\n\n" + logo);
    }

    // Checks hour of day and returns appropriate greeting
    private String getTimeGreeting() {
        int hour = java.time.LocalTime.now().getHour();
        String greeting = hour < 12
                ? "Morning"
                : hour < 18
                ? "Afternoon"
                : "Evening";
        return "\nGood " + greeting;
    }
}




