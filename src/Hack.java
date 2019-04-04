/**
 * This class will hack the AES*3
 */
public class Hack {

    private String messagePath;//The path to the plain text message
    private String cipherPath;//The path to the encrypted message
    private String outputPath;//The path of the output
    private byte [][][] message;//The message in blocks
    private byte [][][] cipher;//The ciphered message in blocks
    private final int BLOCK_SIZE = 128;//Block size in bytes
    private final int BLOCK_SIZE_ROW = 4;//Number of rows in block
    private final int BLOCK_SIZE_COL = 4;//Number of cols in block
    private byte [][] key1;//The first key
    private byte [][] key2;//The second key
    private byte [][] key3;//The third key


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

        //Init
        this.key1 = new byte[this.BLOCK_SIZE_ROW][this.BLOCK_SIZE_COL];
        this.key2 = new byte[this.BLOCK_SIZE_ROW][this.BLOCK_SIZE_COL];
        this.key3 = new byte[this.BLOCK_SIZE_ROW][this.BLOCK_SIZE_COL];

        for(int i=0;i<this.BLOCK_SIZE_COL;i++)
        {
            for(int j=0;j<this.BLOCK_SIZE_ROW;j++)
            {
                this.key1[i][j] = 0;
                this.key1[i][j] = 1;

            }
        }

        int indexPlainTextPath = command.indexOf("–m")+3;
        int indexCipher = command.indexOf("–c")+3;
        int indexOutput = command.indexOf("-o")+3;

        //Get the paths
        this.messagePath = command.substring(indexPlainTextPath,indexCipher-4);
        this.cipherPath = command.substring(indexCipher,indexOutput-4);
        this.outputPath = command.substring(indexOutput);

        //Get the messages
        byte [] message = RWFromFile.read(this.messagePath);
        byte [] cipher = RWFromFile.read(this.cipherPath);

        //Creating the blocks
        this.message = createBlocks(message);
        this.cipher = createBlocks(cipher);

        printArray(message);
        printArray2(this.message);
        System.out.println();
        printArray(cipher);
        printArray2(this.cipher);

    }

    /**
     * This function will divide the message into blocks
     * @param message - The message in sequence of bytes
     */
    public byte[][][] createBlocks(byte[] message)
    {
        int blockSizeBytes = this.BLOCK_SIZE/8;
        int numOfBlocks = message.length / blockSizeBytes;

        byte[][][] arrayOfBlocks = new byte[numOfBlocks][][];

        for(int i=0;i<numOfBlocks;i++)
        {
            arrayOfBlocks[i] = new byte[this.BLOCK_SIZE_ROW][];
            for(int j=0;j<arrayOfBlocks[i].length;j++)
            {
                arrayOfBlocks[i][j] = new byte[this.BLOCK_SIZE_COL];
                for(int k=0;k<arrayOfBlocks[i][j].length;k++)
                {
                    //Specific cell
                    arrayOfBlocks[i][j][k] = message[i*(this.BLOCK_SIZE_COL+this.BLOCK_SIZE_ROW)+j*this.BLOCK_SIZE_COL+k];
                }
            }
        }

        return arrayOfBlocks;
    }


    private void printArray(byte [] a)
    {
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    private void printArray2(byte [][][] a)
    {
        for(int i=0;i<a.length;i++) {
            for(int j=0;j<a[i].length;j++) {
                for(int k=0;k<a[i][j].length;k++) {
                    System.out.print(a[i][j][k]+" ");
                }
                System.out.println();
            }
            System.out.println();

        }
    }
    
    public void cipher()
    {
        boolean isAllZero=true;
        boolean isAllone=true;


        // TODO: 4/4/2019  
    }

}
