import java.util.ArrayList;
import java.util.Scanner;

public class Matty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> task = new ArrayList<>();
        System.out.println("Hello! I'm Matty\n" +
                "What can I do for you?\n");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                for (int i = 0; i < task.size(); i++) {
                    System.out.println( (i + 1) + "." + task.get(i));
                }
            } else {
                task.add(input);
                System.out.println("added: " + input);
            }
        }
        sc.close();
    }
}
