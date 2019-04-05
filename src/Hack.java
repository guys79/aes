import java.util.concurrent.atomic.AtomicReference;

/**
 * This class will hack the AES*3
 */
public class Hack {


    private String outputPath;//The path of the output
    private byte [][][] message;//The message in blocks
    private byte [][][] cipher;//The ciphered message in blocks
    private byte [][] key1;//The first key
    private byte [][] key2;//The second key
    private byte [][] key3;//The third key


    /**
     * The constructor of the class
     * @param command - The command
     */
    public Hack(String [] command)
    {
       setCommand(command);
    }

    /**
     * This function will set the hack's parameters using the given command
     * @param command - The given command
     */
    public void setCommand(String [] command)
    {

        //Init
        this.key1 = new byte[RWFromFile.BLOCK_SIZE_ROW][RWFromFile.BLOCK_SIZE_COL];
        this.key2 = new byte[RWFromFile.BLOCK_SIZE_ROW][RWFromFile.BLOCK_SIZE_COL];
        this.key3 = new byte[RWFromFile.BLOCK_SIZE_ROW][RWFromFile.BLOCK_SIZE_COL];

        for(int i=0;i<RWFromFile.BLOCK_SIZE_COL;i++)
        {
            for(int j=0;j<RWFromFile.BLOCK_SIZE_ROW;j++)
            {
                this.key1[i][j] = 0;
                this.key2[i][j] = (byte)255;

            }
        }


        //Get the paths
        String messagePath = command[1];
        String cipherPath = command[2];
        this.outputPath = command[3];

        //Get the messages
        byte [] message = RWFromFile.read(messagePath);
        byte [] cipher = RWFromFile.read(cipherPath);


        //Creating the blocks
        this.message = RWFromFile.createBlocks(message);
        this.cipher = RWFromFile.createBlocks(cipher);



    }

    /**
     * This function will hack the encryption
     */
    public void hack()
    {
        AtomicReference<Boolean> isAllZero = new AtomicReference<>(true);
        AtomicReference<Boolean> isAllOne = new AtomicReference<>(true);

        byte [][] mBlock = this.message[0];//The first block of the message
        byte [][] cBlock = this.cipher[0];//The first block of the ciphered message

        for(int i=0;i<mBlock.length;i++)
        {
            for(int j=0;j<mBlock[i].length;j++)
            {

                updateThirdKeyCell(mBlock,cBlock,i,j,isAllZero,isAllOne);
            }
        }

        //In case the keys are different
        if(isAllOne.get())
        {
            this.key3[0][0] = (byte)1;
            this.key1[0][0]=(byte)255;
        }
        if(isAllZero.get())
        {
            this.key2[0][0] = (byte)254;
            this.key3[0][0]=(byte)1;
        }

        //Writing to output file
        writeToOutput();

    }

    /**
     * This function will write the keys to the file
     */
    private void writeToOutput()
    {
        byte[][][] keys = new byte[3][][];
        keys[0]=this.key1;
        keys[1]=this.key2;
        keys[2]=this.key3;
        RWFromFile.write(this.outputPath,keys);
    }

    /**
     * This function will use the two give keys, the ciphered message and the original message to create a third key.
     * The combination of the three keys will hack the encryption.
     * This function will update only one cell in the third key
     * @param mBlock - A block fom the original message
     * @param cBlock - the ciphered mBlock
     * @param row - The row index of the original message
     * @param col - The col index of the original message
     * @param isAllZero - True iff all the cells in the third key are 0
     * @param isAllOne - True iff all the cells in the third key are 1
     */
    private void updateThirdKeyCell(byte [][]mBlock, byte [][]cBlock, int row, int col, AtomicReference<Boolean> isAllZero,AtomicReference<Boolean> isAllOne)
    {

        int col1,col2=col,col3=col;

        col1 = col-row;
        if(col1<0)
            col1 = RWFromFile.BLOCK_SIZE_COL +col1;

        for(int i=0;i<2;i++)
        {
            col2 = col2-row;
            if(col2<0)
                col2 = RWFromFile.BLOCK_SIZE_COL +col2;
        }

        for(int i=0;i<3;i++)
        {
            col3 = col3-row;
            if(col3<0)
                col3 = RWFromFile.BLOCK_SIZE_COL +col3;
        }




        this.key3[row][col3] = xor(xor(xor(cBlock[row][col3],mBlock[row][col]),this.key1[row][col1]),this.key2[row][col2]);

        if(this.key3[row][col3]!=(byte)0)
            isAllOne.set(false);
        if(this.key3[row][col3]!=(byte)255)
            isAllZero.set(false);
    }

    /**
     * This function will perform bitwise xor between two bytes
     * @param byte1 - The first byte
     * @param byte2 - The second byte
     * @return - The outcome of the xor
     */
    private byte xor(byte byte1,byte byte2)
    {
        return (byte) ((int)byte1 ^ (int)byte2);
    }


}
