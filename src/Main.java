import Hack.Hack;

/**
 * This class will be the class that gets the command line and activates the correct command
 */
public class Main {

    public static void main(String[] args) {
        //activate(args[0]);//Executing the command line

    }

/*
    public static boolean test()
    {
        byte [] a =RWFromFile.read("d:\\documents\\users\\guysc\\Downloads\\AES3_test_files\\message_short");
        RWFromFile.write("d:\\documents\\users\\guysc\\Downloads\\AES3_test_files\\test",a);
        byte [] b =RWFromFile.read("d:\\documents\\users\\guysc\\Downloads\\AES3_test_files\\test");


        if(a.length!=b.length)
            return false;
        for(int i=0;i<a.length;i++)
        {
            if(a[i]!=b[i])
                return false;
        }
        return true;
    }
    public static void print (byte[]arr)
    {
        System.out.println(arr.length +" length");
        for(int i=0;i<arr.length;i++)
        {
            System.out.println(arr[i]);
        }

    }*/

    /**
     * This function will execute the command
     * @param command - The given command
     */
    public static void activate(String command)
    {
        if(hackOrNot(command))
        {
            // TODO: 03/04/2019 Summon hack class 
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
        String prefix = "Java –jar aes.jar -b";
        return prefix.equals(command.substring(0,prefix.length()));

    }

    public static void Test(){
        byte b1,b2;
        b1=0;
        b2=1;
        byte b3= (byte) ((byte)b1^b2);
        System.out.println(b1^b2);
        System.out.println(b2^b1);
        System.out.println(b1^b1);
        System.out.println(b2^b2);
    }
}
