import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static File currentDirectory = new File(System.getProperty("user.dir"));

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

            if(input.startsWith(PreDefinedCommands.ECHO.getValue())){
                handleEcho(argument);
            }
            else if(input.startsWith(PreDefinedCommands.TYPE.getValue())){
                handleType(argument);
            }
            else if(command.equals(PreDefinedCommands.PWD.getValue())){
                handlePwd();

            }
            else if(command.equals(PreDefinedCommands.CD.getValue())){
                handleCD(argument);
            }
            else {
                handleRunProgram(command, argument);

            }
        }

        }
        scanner.close();
    }

    private static void handleCD(String pathArg) {

            Path path = Paths.get(pathArg);
            if(!path.isAbsolute()){
                System.out.println("cd: " + pathArg + ": is not an absolute path");
                return;
            }
            File file = new  File(pathArg);
            if(!file.exists()){
                System.out.println("cd: " + pathArg + ": No such file or directory");
                return;
            }
            if(!file.isDirectory()){
                System.out.println("cd: " + pathArg + ": No such file or directory");
                return;
            }

        currentDirectory = file;

    }

    private static void handlePwd() throws IOException {
        System.out.println(currentDirectory.getCanonicalPath());
    }

    private static void handleRunProgram(String command, String argument) {
        List<String> commands = new ArrayList<>();
        try {
        commands.add(command);
        commands.addAll(Arrays.asList(argument.split(" ")));
        ProcessBuilder pb = new ProcessBuilder(commands);
            pb.directory(currentDirectory);
            pb.inheritIO(); // inherit stdin, stdout, stderr
            Process proc = pb.start();
            proc.waitFor();
        } catch (IOException e) {
            System.out.println(command + ": command not found");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


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
