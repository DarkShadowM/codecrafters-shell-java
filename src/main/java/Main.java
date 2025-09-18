import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        while(status){
         System.out.print("$ ");

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
        scanner.close();
    }






    private static void handleType(String argument) {
        PreDefinedCommands preDefinedCommands = PreDefinedCommands.fromString(argument);
        String path_commands = System.getenv("PATH");
        String[] path_command = path_commands.split(File.pathSeparator);

        if(preDefinedCommands != null){
            System.out.println(argument + " is a shell builtin");
        }
        else{
            boolean fileExists = false;
            for(String command : path_command){
                File file = new File(command,argument);
                if(file.exists() && file.canExecute()){
                    fileExists = true;
                    System.out.println(argument + " is "+file.getAbsolutePath());
                }
            }
            if(!fileExists){
                System.out.println(argument + ": not found");

            }
        }
    }

    private static void handleEcho(String argument) {
        System.out.println(argument);
    }

    private static void handleExit() {
        System.exit(0);
    }
}
