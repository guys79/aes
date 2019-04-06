/**
 * This class will be the class that gets the command line and activates the correct command
 */
public class Main {

    public static void main(String[] args) {


        //activate(args);//Executing the command line
        //TestEncryption();
        TestEqual();

    }

    private static void TestEqual() {
        String folder="C:\\Users\\AMIT MOSHE\\Desktop\\אוניברסיטה\\סמסטר ו\\אבטחת מידע\\עבודה 1";
        byte[] a1=RWFromFile.read(folder+"\\test");
        byte[] a2=RWFromFile.read(folder+"\\cipher_long");
        for(int i=0;i<a1.length;i++)
            System.out.println(a1[i]==a2[i]);
    }

    private static void TestEncryption() {
        String folder="C:\\Users\\AMIT MOSHE\\Desktop\\אוניברסיטה\\סמסטר ו\\אבטחת מידע\\עבודה 1";
        String []args=new String[4];
        args[0]="-e";
        args[1]=folder+"\\key_long";
        args[2]=folder+"\\message_long";
        args[3]=folder+"\\test";
        try {
            Encrypt_Decrypt_Start.Start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            try {
                Encrypt_Decrypt_Start.Start(importedCommand);
            } catch (Exception e) {
                e.printStackTrace();
            }//TODO where to catch and what to do?
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
