import java.io.*;
import java.nio.file.Files;

/**
 * This class is responsible to read/write from a file that contains bytes
 */
public class RWFromFile {
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

}
