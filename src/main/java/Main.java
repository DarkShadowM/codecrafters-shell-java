import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        boolean status = true;
        while(status){
         System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("exit 0")){
            status = false;
        }
        else {
            System.out.println(input + ": command not found");
        }

        }

    }
}
