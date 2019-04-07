import java.util.ArrayList;
import java.util.List;

public class Encrypt_Decrypt_Start {

    //TODO: 1. change in en/dec to turn into blocks with guy's function, then call to the triply array in place 1
    //TODO: 2. add function to turn blocks into array in order of columns


    /**
     *
     * @param args-[ -e/-d] [<path-to-key-file >] [<path-to-input-file>] [<path-to-output-file>]
     * @throws Exception
     *this function write to outputfile the output from encryption/decryption with aes algorithm
     *  with keys and message from user
     */
    public static void Start(String[] args) throws Exception {
        //[ -e/-d] [<path-to-key-file >] [<path-to-input-file>] [<path-to-output-file>]
        boolean toEncrypt=false;
        if(args[0].contains("e"))
            toEncrypt=true;
        //keys
        byte[][] keys=getKeys(args[1]);
        //input
        byte[] text=RWFromFile.read(args[2]);

        //encrypt/decrypt
        List<byte[]> text_seperted=CutToBlocks(text);
        List<byte[]> enc_dec_seperated=new ArrayList<>();
        Encryption encryption=new Encryption();
        Decryption decryption=new Decryption();
        for(int i=0; i<text_seperted.size();i++){
            byte [] enc_dec=null;
            if(toEncrypt)
                enc_dec=encryption.EncryptAES(text_seperted.get(i),keys[0],keys[1],keys[2]);
            else
                enc_dec=decryption.DecryptAES(text_seperted.get(i),keys[0],keys[1],keys[2]);
            enc_dec_seperated.add(enc_dec);
        }
        //write to output file
        Write_enc_dec(enc_dec_seperated,args[3]);

    }

    /**
     *
     * @param enc_dec_seperated- list of blocks after encryption/decryption
     * @param fullPathToOutputFile
     * this function writes to output file the enc_dec msg
     */
    private static void Write_enc_dec(List<byte[]> enc_dec_seperated, String fullPathToOutputFile) {
        //get the blocks to 1 array
        byte [] msg=new byte[16*enc_dec_seperated.size()];
        int counter=0;
        for(int i=0; i<enc_dec_seperated.size();i++){
            for(int j=0;j<16;j++){
                msg[counter]=enc_dec_seperated.get(i)[j];
                counter+=1;
            }
        }
        //write the full msg
        RWFromFile.write(fullPathToOutputFile,msg);
    }

    /**
     *
     * @param text
     * @return text cut to 128 byte blocks
     */
    private static List<byte[]> CutToBlocks(byte[] text) {
        byte[][][] ret= RWFromFile.createBlocks(text);
        List<byte[]>blocks=new ArrayList<>();
        for(int i=0;i<ret.length;i++){
            byte[]block=ArraysToOne(ret[i]);
            blocks.add(block);
        }
        return blocks;
    }
    /**
     *
     * @param cypher_array 2 dim array of 4*4 bytes
     * @return 1 dim array of 16 bytes
     */
    private static byte[] ArraysToOne(byte[][] cypher_array) {
        byte [] res=new byte[16];
        int counter=0;
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                res[counter]=cypher_array[j][i];
                counter+=1;
            }
        }
        return res;
    }

    /**
     *
     * @param pathToKeyFile
     * @return 3 keys from the keys file
     * @throws Exception
     */
    private static byte[][] getKeys(String pathToKeyFile) throws Exception {
        byte[] keys= RWFromFile.read(pathToKeyFile);
        if(keys.length==48){
            byte[][][]keys_blocks=RWFromFile.createBlocks(keys);
            byte[] key1 = ArraysToOne(keys_blocks[0]);
            byte[] key2 = ArraysToOne(keys_blocks[1]);
            byte[] key3 = ArraysToOne(keys_blocks[2]);
            byte[][]keys_seperated={key1,key2,key3};
            return keys_seperated;
        }
        else{
            System.out.println(keys.length);
            throw new Exception("Keys File doesn't have the needed parameters- not 384 bits-48 bytes!");
        }
    }
}
