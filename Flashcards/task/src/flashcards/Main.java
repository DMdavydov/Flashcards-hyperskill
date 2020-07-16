package flashcards;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the action (add, remove, import, export, ask, exit):");

        switch (scanner.next()) {
            case "add":
                break;
            case "remove":
                break;
            case "import":
                break;
            case "export":
                break;
            case "ask":
                break;
            case "exit":
                break;
        }

        System.out.println("Input the number of cards:");
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> flashcards = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            System.out.printf("The card #%d:\n", i + 1);
            String term = scanner.nextLine();
            while (flashcards.containsKey(term)) {
                System.out.printf("The card \"%s\" already exists. Try again:\n", term);
                term = scanner.nextLine();
            }

            System.out.printf("The definition of the card #%d:\n", i + 1);
            String def = scanner.nextLine();
            while (flashcards.containsValue(def)) {
                System.out.printf("The definition \"%s\" already exists. Try again:\n", def);
                def = scanner.nextLine();
            }

            flashcards.put(term, def);
        }


        for (String term : flashcards.keySet()) {

            System.out.printf("Print the definition of \"%s\":\n", term);
            String input = scanner.nextLine();

            if (input.equals(flashcards.get(term))) {
                System.out.println("Correct answer.");
            } else if (flashcards.containsValue(input)){
                System.out.printf("Wrong answer. The correct one is \"%s\",", flashcards.get(term));
                String otherTerm  = "";
                for (String s : flashcards.keySet()) {
                    if (flashcards.get(s).equals(input)) {
                        otherTerm = s;
                    }
                }
                System.out.printf("you've just written the definition of \"%s\".\n", otherTerm);
            } else {
                System.out.printf("Wrong answer. The correct one is \"%s\".\n", flashcards.get(term));
            }

        }
    }
}