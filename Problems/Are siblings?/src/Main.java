import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

class Siblings {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("/Users/dmitry_davydov1/Downloads/dataset_91065.txt"));

        int count = 0;
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            if(a == 0) {
                break;
            }
            if (a % 2 == 0) {
                count++;
            }
        }
        System.out.println(count);
    }
//    public static boolean areSiblings(File f1, File f2) {
//        // implement me
//        return f1.getParent().equals(f2.getParent());
//    }
}