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
            handleExit();
        }
        else {
            String[] commands =  input.split(" ",2);
            String command = commands[0];
            String argument = commands.length > 1 ? commands[1] : "";

            if(command.equals(PreDefinedCommands.ECHO.getValue())){
                handleEcho(argument);
            }
            else if(command.equals(PreDefinedCommands.TYPE.getValue())){
                handleType(argument);
            }
            else {
                System.out.println(input + ": command not found");
            }
        }

        }

    }

    private static void handleType(String argument) {
        PreDefinedCommands preDefinedCommands = PreDefinedCommands.fromString(argument);
        if(preDefinedCommands != null){
            System.out.println(argument + " is a shell builtin");
        }
        else{
            System.out.println(argument + ": not found");
        }
    }

    private static void handleEcho(String argument) {
        System.out.println(argument);
    }

    private static void handleExit() {
        System.exit(0);
    }
}
