import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);

        String key = sc.nextLine();
        String value = sc.nextLine();
        String encode = sc.nextLine();
        String decode = sc.nextLine();

        Map<Character, Character> map = new HashMap<>();

        for (Character a : key.toCharArray()) {
            for (Character b : value.toCharArray()) {
                map.put(a, b);
            }
        }

        StringBuilder encoded = new StringBuilder();
        for (Character a : encode.toCharArray()) {
            for(Map.Entry<Character, Character> b: map.entrySet()) {
                if(a.equals(b.getValue())) {
                    encoded.append(b.getKey());
                }
            }
        }

        StringBuilder decoded = new StringBuilder();
        for (Character a : decode.toCharArray()) {
            for(Map.Entry<Character, Character> b: map.entrySet()) {
                if(a.equals(b.getKey())) {
                    decoded.append(b.getValue());
                }
            }
        }

        System.out.println(encoded);
        System.out.println(decoded);
    }
}