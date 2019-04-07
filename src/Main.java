import java.io.File;

/**
 * This class will be the class that gets the command line and activates the correct command
 */
public class Main {

    public static void main(String[] args) {

        activate(args);//Executing the command line
    }


    /**
     * This function will execute the command
     * @param command - The given command
     */
    public static void activate(String [] command)
    {
        String [] importantCommand = new String [4];


        //Creating the important command array
        createImportentCommand(importantCommand,command);


        if(hackOrNot(importantCommand[0]))
        {
            Hack hack = new Hack(importantCommand);
            hack.hack();
        }
        else
        {
            // TODO: 03/04/2019 Summon encrypt/decrypt class
        }
    }


    /**
     * This function will take the given command and add to the buffer the relevant parts of the command
     * @param buffer - The buffer
     * @param command - The command
     */
    public static void createImportentCommand(String [] buffer, String [] command) {
        buffer[0] = command[3];
        String firstPathSign = "-i";
        String secondPathSign = "-o";

        if (hackOrNot(buffer[0])) {
            firstPathSign = "-c";
        }

        buffer[1] = "";
        buffer[2] = "";
        buffer[3] = "";

        int index = 5;


        while (!command[index].equals(firstPathSign)) {
            System.out.println(command[index]);
            buffer[1] = buffer[1] +" " + command[index];
            index++;
        }

        buffer[1]=buffer[1].substring(1);
        index++;

        while (!command[index].equals(secondPathSign)) {
            buffer[2] = buffer[2] + " " + command[index];
            index++;
        }

        buffer[2]=buffer[2].substring(1);
        index++;

        while (index < command.length) {
            buffer[3] = buffer[3] + " " + command[index];
            index++;
        }

        buffer[3]=buffer[3].substring(1);


    }

    /**
     * This function will determine whether the command is for hacking or for encryption/decryption
     * @param command - The command
     * @return - True if the command is for hack, false - for encryption/decryption
     */
    public static boolean hackOrNot(String command)
    {
        return command.equals("-b");

    }



}
