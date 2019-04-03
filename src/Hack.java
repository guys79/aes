/**
 * This class will hack the AES*3
 */
public class Hack {

    private String messagePath;//The path to the plain text message
    private String cipherPath;//The path to the encrypted message
    private String outputPath;//The path of the output
    private byte [] message;//The message
    private byte [] cipher;//The ciphered message
    /**
     * The constructor of the class
     * @param command - The command
     */
    public Hack(String command)
    {
       setCommand(command);
    }

    /**
     * This function will set the hack's parameters using the given command
     * @param command - The given command
     */
    public void setCommand(String command)
    {
        int indexPlainTextPath = command.indexOf("–m")+2;
        int indexCipher = command.indexOf("–c")+2;
        int indexOutput = command.indexOf("-o")+2;

        //Get the paths
        this.messagePath = command.substring(indexPlainTextPath,indexCipher-2);
        this.cipherPath = command.substring(indexCipher,indexOutput-2);
        this.outputPath = command.substring(indexOutput);

        //Get the messages
        this.message = RWFromFile.read(this.messagePath);
        this.cipher = RWFromFile.read(this.cipherPath);

    }


}
