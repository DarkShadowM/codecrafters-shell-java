import java.util.Arrays;
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
            System.exit(0);
        }
        else {
            String[] commands =  input.split(" ",2);
            String command = commands[0];
            String argument = commands.length > 1 ? commands[1] : "";

            if(command.equals("echo")){
                System.out.println(argument);
            }
            else {
                System.out.println(input + ": command not found");
            }
        }

        }

    }
}
