package Hack;
/**
 * This class will hack the AES*3
 */
public class Hack {

    private String messagePath;//the path to the plain text message
    private String cipherPath;//The path to the encrypted message
    private String outputPath;//The path of the output

    /**
     * The constructor of the class
     * @param command - The command
     */
    public Hack(String command)
    {
        int indexPlainTextPath = command.indexOf("–m")+2;
        int indexCipher = command.indexOf("–c")+2;
        int indexOutput = command.indexOf("-o")+2;

        this.messagePath = command.substring(indexPlainTextPath,indexCipher-2);
        this.cipherPath = command.substring(indexCipher,indexOutput-2);
        this.outputPath = command.substring(indexOutput);
       // print();
    }
    public void print()
    {
        // TODO: 03/04/2019 Delete this function




        System.out.println(this.messagePath);
        System.out.println(this.cipherPath);
        System.out.println(this.outputPath);
    }
}
