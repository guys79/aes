import java.io.File;

/**
 * This class will be the class that gets the command line and activates the correct command
 */
public class Main {

    public static void main(String[] args) {

        activate(args);//Executing the command line
    }



    }

    private static void TestDecryption() {
        String folder="C:\\Users\\AMIT MOSHE\\Desktop\\אוניברסיטה\\סמסטר ו\\אבטחת מידע\\עבודה 1";
        String []args=new String[4];
        args[0]="-d";
        args[1]=folder+"\\key_short";
        args[2]=folder+"\\cipher_short";
        args[3]=folder+"\\test2";
        try {
            Encrypt_Decrypt_Start.Start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void TestEqual() {
        String folder="C:\\Users\\AMIT MOSHE\\Desktop\\אוניברסיטה\\סמסטר ו\\אבטחת מידע\\עבודה 1";
        byte[] a1=RWFromFile.read(folder+"\\test2");
        byte [] a3=RWFromFile.read(folder+"\\message_short");

        byte[] a2=RWFromFile.read(folder+"\\cipher_short");
        byte[] a22=RWFromFile.read(folder+"\\test");
        byte [] a4=RWFromFile.read(folder+"\\key_short");
        boolean f=true;

        for(int i=0;i<a1.length;i++){
            if(a1[i]!=a3[i]) {
                System.out.println("false!");
                f=false;
            }
            if(a2[i]!=a22[i]) {
                System.out.println("false!");
                f=false;
            }
        }
        System.out.println("the answer is:"+f);

    }

    private static void TestEncryption() {
        String folder="C:\\Users\\AMIT MOSHE\\Desktop\\אוניברסיטה\\סמסטר ו\\אבטחת מידע\\עבודה 1";
        String []args=new String[4];
        args[0]="-e";
        args[1]=folder+"\\key_short";
        args[2]=folder+"\\message_short";
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
            try {
                Encrypt_Decrypt_Start.Start(importedCommand);
            } catch (Exception e) {
                e.printStackTrace();
            }//TODO where to catch and what to do?
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
