import java.util.Arrays;
import java.util.List;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        List<String> list = Arrays.asList(args);
        list.remove(0);
        switch (operator) {
            case "MAX":
                System.out.println(list.stream()
                        .mapToInt(Integer::valueOf)
                        .max()
                        .getAsInt()
                );
                break;
            case "MIN":
                System.out.println(list.stream()
                        .mapToInt(Integer::valueOf)
                        .min()
                        .getAsInt()
                );
                break;
            case "SUM":
                System.out.println(list.stream()
                        .mapToInt(Integer::valueOf)
                        .sum()
                );
                break;
        }
    }
}