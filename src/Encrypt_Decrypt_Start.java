import java.util.ArrayList;
import java.util.List;

public class Encrypt_Decrypt_Start {

    //TODO: 1.figure out sizes- tells that keys is 96 bytes but there is 48.
    //TODO: 2.blocks: only in reading part (written wierd on files) or in the add round key and atc..
    //TODO: 3. if 2 is true- change enc dec to work with block function of guy.


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
        byte [] msg=new byte[128*enc_dec_seperated.size()];
        int counter=0;
        for(int i=0; i<enc_dec_seperated.size();i++){
            for(int j=0;j<128;j++){
                msg[counter]=enc_dec_seperated.get(i)[j];
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
        List<byte[]> seprated=new ArrayList<>();
        int counter=text.length/128;
        int start=0;
        int end=31;
        for(int i=0;i<counter;i++){
            byte[] block=getBlockMsg(text,start,end);
            seprated.add(block);
            start+=32;
            end+=32;
        }
        return seprated;

    }

    /**
     *
     * @param pathToKeyFile
     * @return 3 keys from the keys file
     * @throws Exception
     */
    private static byte[][] getKeys(String pathToKeyFile) throws Exception {
        byte[] keys= RWFromFile.read(pathToKeyFile);
        if(keys.length==96) {
            byte[] key1 = getBlockMsg(keys, 0, 31);
            byte[] key2 = getBlockMsg(keys, 32, 63);
            byte[] key3 = getBlockMsg(keys, 64, 95);
            byte[][]keys_seperated={key1,key2,key3};
            return keys_seperated;
        }
        else{
            System.out.println(keys.length);
            throw new Exception("Keys File doesn't have the needed parameters- not 384 bits-96 bytes!");
        }
    }

    /**
     *
     * @param fullmsg
     * @param start
     * @param end
     * @return get 1 key from all keys
     */
    private static byte[] getBlockMsg(byte[] fullmsg, int start, int end) {
        byte [] block=new byte[32];
        int c=0;
        for(int i=start;i<=end;i++){
            block[c]=fullmsg[i];
        }
        return block;
    }

}
