/**
 * This class will be the class that gets the command line and activates the correct command
 */
public class Main {

    public static void main(String[] args) {
       Test();
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
