import java.io.*;
import java.nio.file.Files;

/**
 * This class is responsible to read/write from a file that contains bytes
 */
public class RWFromFile {

    public static final int BLOCK_SIZE = 128;//Block size in bytes
    public static int BLOCK_SIZE_ROW = 4;//Number of rows in block
    public static int BLOCK_SIZE_COL = 4;//Number of cols in block
    /**
     * This function will read the bytes from the file in the given path and will return the content of the file as a byte array
     * @param path - the given path
     * @return - The byte array containing the content of the file
     */
    public static byte[] read(String path)
    {

        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;


    }

    /**
     * This function will write the bytes from the given byte array into the file in the given path
     * @param path -The given path
     * @param fileContent - The byte array
     */
    public static void write(String path,byte [] fileContent)
    {
        try (FileOutputStream fileOutputStream  = new FileOutputStream(path)) {
            fileOutputStream.write(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * This function will write the bytes from the given byte array into the file in the given path
     * @param path -The given path
     * @param fileContent - The byte array
     */
    public static void write(String path,byte [][][] fileContent)
    {
        try (FileOutputStream fileOutputStream  = new FileOutputStream(path)) {
            for(int i=0;i<fileContent.length;i++)
            {
                for(int j=0;j<fileContent[i][0].length;j++)
                {
                    for(int k=0;k<fileContent[i].length;k++)
                    {
                        fileOutputStream.write(fileContent[i][k][j]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * This function will divide the message into blocks
     * @param message - The message in sequence of bytes
     */

    public static byte[][][] createBlocks(byte[] message)
    {
        int blockSizeBytes = BLOCK_SIZE/8;
        int numOfBlocks = message.length / blockSizeBytes;

        byte[][][] arrayOfBlocks = new byte[numOfBlocks][][];

        for(int i=0;i<numOfBlocks;i++)
        {
            arrayOfBlocks[i] = new byte[BLOCK_SIZE_ROW][];
            for(int j=0;j<arrayOfBlocks[i].length;j++)
            {
                arrayOfBlocks[i][j] = new byte[BLOCK_SIZE_COL];
                for(int k=0;k<arrayOfBlocks[i][j].length;k++)
                {
                    //Specific cell
                    arrayOfBlocks[i][j][k] = message[i*(BLOCK_SIZE_COL*BLOCK_SIZE_ROW)+k*BLOCK_SIZE_ROW+j];
                }
            }
        }

        return arrayOfBlocks;
    }

}
