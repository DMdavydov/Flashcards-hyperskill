package flashcards;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Actions actions = new Actions();
        Scanner scanner = new Scanner(System.in);

        String action = null;
        while (!"exit".equals(action)) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            action = scanner.next();
            switch (action) {
                case "add":
                    actions.add();
                    break;
                case "remove":
                    actions.remove();
                    break;
                case "import":
                    actions.importCard();
                    break;
                case "export":
                    actions.exportCard();
                    break;
                case "ask":
                    actions.ask();
                    break;
                case "exit":
                    actions.exit();
                    break;
            }
        }
    }
}