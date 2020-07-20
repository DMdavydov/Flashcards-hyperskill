package flashcards;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileInput = null;
        String fileExport = null;

        Scanner scanner = new Scanner(System.in);
        Actions actions = new Actions();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-import":
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        System.out.println("Error : Нет значения для ключа -input");
                    } else {
                        fileInput = args[i + 1];
                    }
                    break;
                case "-export":
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        System.out.println("Error : Нет значения для ключа -export");
                    } else {
                        fileExport = args[i + 1];
                    }
                    break;
            }
        }


        String action = null;
        if(fileInput != null) {
            actions.importCard(fileInput);
        }
        while (!"exit".equals(action)) {
            System.out.println("Input the action (add, remove, import, export, ask, log, hardest card, reset stats, exit):");
            action = scanner.next();
            scanner.nextLine();
            switch (action) {
                case "add":
                    actions.add();;
                    break;
                case "remove":
                    actions.remove();
                    break;
                case "import":
                    actions.importCard(fileInput);
                    break;
                case "export":
                    actions.exportCard(fileExport);
                    break;
                case "ask":
                    actions.ask();
                    break;
                case "log":
                    actions.logging();
                    break;
                case "hardest card":
                    actions.hardestCard();
                    break;
                case "reset stats":
                    actions.resetStats();
                    break;

            }
        }
        System.out.println("Bye bye!");
        if(fileExport != null) {
            actions.exportCard(fileExport);
        }

    }
}