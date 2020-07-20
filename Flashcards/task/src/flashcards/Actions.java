package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Actions {

    private final Map<String, String> cards = new LinkedHashMap<>();
    private final Map<String, Integer> errors = new LinkedHashMap<>();
    private final ArrayList<String> log = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
    Scanner scanner = new Scanner(System.in);

    public void add() {
        System.out.println("The card :");
        String term = scanner.nextLine();
        log.add(term);
        if (cards.containsKey(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            return;
        }
        System.out.println("The definition of the card :");
        String definition = scanner.nextLine();
        log.add(definition);
        if (cards.containsValue(definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }
        cards.put(term, definition);
        errors.put(term, 0);
        System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    public void remove() {
        String term;
        System.out.println("The card:");
        term = scanner.nextLine();
        log.add(term);
        if (cards.containsKey(term)) {
            cards.remove(term);
            errors.remove(term);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + term + "\", there is no such card.");
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

    public void ask() {

        int quantity;
        int errorCounter;

        System.out.println("How many times to ask?");
        try {
            quantity = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Wrong input");
            return;
        }

        scanner.nextLine();
        List<String> keys = new ArrayList<>(cards.keySet());

        for (int i = 0; i < quantity; i++) {
            Random random = new Random();

            String randomKey = keys.get(random.nextInt(keys.size()));
            String randomValue = cards.get(randomKey);
            errorCounter = errors.get(randomKey);

            System.out.println("Print definition of \"" + randomKey + "\":");
            String answer = scanner.nextLine();
            log.add(answer);
            if (!randomValue.equals(answer) && cards.containsValue(answer)) {
                System.out.println("Wrong answer. The correct one is \"" + randomValue + "\", you've just written the definition of \"" + getKeyFromMap(cards, answer) + "\".");
                errorCounter += 1;
            } else if (randomValue.equals(answer)) {
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer. The correct one is \"" + randomValue + "\".");
                errorCounter += 1;
            }
            errors.put(randomKey, errorCounter);
        }
    }

    public void exportCard(String fileName) {
        System.out.println("File name:");
        fileName = scanner.nextLine();
        log.add(fileName);
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (Map.Entry<String, String> entry : cards.entrySet()) {
                printWriter.println(entry.getKey());
                printWriter.println(entry.getValue());
                printWriter.println(errors.get(entry.getKey()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(cards.size() + " cards have been saved.");
    }

    public void importCard(String fileName) {

        System.out.println("File name:");
        fileName = scanner.nextLine();

        int error;
        int count = 0;

        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String term = scanner.nextLine();
                String definition = scanner.nextLine();
                error = Integer.parseInt(scanner.nextLine());
                cards.put(term, definition);
                errors.put(term, error);
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        if (count > 0) {
            System.out.println(count + " cards have been loaded.");
        }
    }

    public void hardestCard() {
        int maxError = 0;
        int maxErrorCount = 0;
        stringBuilder.append("The hardest card is ");
        for (Map.Entry<String, Integer> entry : errors.entrySet()) {
            if (entry.getValue() > 0) {
                maxError = (maxError < entry.getValue()) ? entry.getValue() : maxError;
            }
        }
        for (Map.Entry<String, Integer> entry : errors.entrySet()) {
            if (entry.getValue() > 0) {
                if (entry.getValue() == maxError) {
                    stringBuilder.append("\"").append(entry.getKey()).append("\"").append(",");
                    maxErrorCount += 1;
                }
            }
        }
        if (maxErrorCount > 1) {
            stringBuilder.insert(16, "s").delete(18, 20).insert(18, "are");
        } else {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(".");
        }
        if (maxError > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(". You have ").append(maxError).append(" errors answering them.");
        } else {
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append("There are no cards with errors.");
        }
        System.out.println(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
    }

    public void logging() {
        System.out.println("File name:");
        String fileName = scanner.nextLine();
        log.add(fileName);
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (String entry : log) {
                printWriter.println(entry);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("The log has been saved.%n");
    }

    public void resetStats() {
        errors.replaceAll((k, v) -> 0);
        System.out.println("Card statistics has been reset.");
    }
}
