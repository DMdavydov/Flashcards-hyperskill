package flashcards;

import java.io.*;
import java.util.*;

public class Actions {

    protected Scanner scanner = new Scanner(System.in);
    protected Map<String, String> flashcards = new LinkedHashMap<>();

    public void add() {
        System.out.println("The card:");
        String term = scanner.nextLine();
        if (flashcards.containsKey(term)) {
            System.out.printf("The card \"%s\" already exists.\n", term);
            return;
        }

        System.out.println("The definition of the card:");
        String def = scanner.nextLine();
        if (flashcards.containsValue(def)) {
            System.out.printf("The definition \"%s\" already exists.\n", def);
            return;
        }

        flashcards.put(term, def);
        System.out.println("The pair (\"" + term + "\":\"" + def + "\") has been added.");
    }

    public void remove() {
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (flashcards.remove(term) == null) {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
            return;
        }
        System.out.println("The card has been removed.");
    }

    public void exportCard() {
        System.out.println("File name:");

        File file = new File(scanner.nextLine());
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (var entry : flashcards.entrySet()) {
                printWriter.println(entry.getKey());
                printWriter.println(entry.getValue());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(flashcards.size() + " cards have been saved.");
    }

    public void importCard() {
        System.out.println("File name:");
        String fileName = scanner.nextLine();

        int count = 0;

        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                flashcards.put(scanner.nextLine(), scanner.nextLine());
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not Found");
        }
        if (count > 0) {
            System.out.println(count + " cards have been loaded.");
        }
    }

    public void ask() {

        int quantity;

        System.out.println("How many times to ask?");
        try {
            quantity = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
            return;
        }

        scanner.nextLine();
        List<String> keys = new ArrayList<>(flashcards.keySet());

        for (int i = 0; i < quantity; i++) {
            Random random = new Random();

            String randomKey = keys.get(random.nextInt(keys.size()));
            String randomValue = flashcards.get(randomKey);

            System.out.println("Print definition of \"" + randomKey + "\":");
            String answer = scanner.nextLine();
            if (!randomValue.equals(answer) && flashcards.containsValue(answer)) {
                System.out.println("Wrong answer. The correct one is \"" + randomValue + "\", you've just written the definition of \"" + getKeyFromMap(flashcards, answer) + "\".");
            } else if (randomValue.equals(answer)) {
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer. The correct one is \"" + randomValue + "\".");
            }
        }
    }

    public <K, V> K getKeyFromMap(Map<K, V> card, V description) {
        for (Map.Entry<K, V> entry : card.entrySet()) {
            if (entry.getValue().equals(description)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void exit() {
        System.out.println("Bye bye!");
    }
}
