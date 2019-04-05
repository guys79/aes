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
        String [] importedCommand = new String [4];
        importedCommand[0]=command[3];
        importedCommand[1]=command[5];
        importedCommand[2]=command[7];
        importedCommand[3]=command[9];


        if(hackOrNot(importedCommand[0]))
        {
            Hack hack = new Hack(importedCommand);
            hack.hack();
        }
        else
        {
            // TODO: 03/04/2019 Summon encrypt/decrypt class
        }
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
