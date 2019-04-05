
public class Decryption {
    public byte[] DecryptAES(byte[] Cypher,byte[]k1,byte[]k2,byte[]k3){
        byte[]plaintext=Cypher;
        //round 1
        AddRoundKey(plaintext,k3);
        plaintext=ShiftRows_reversed(plaintext);
        //round 2
        AddRoundKey(plaintext,k2);
        ShiftRows_reversed(plaintext);
        //round 3
        AddRoundKey(plaintext,k1);
        ShiftRows_reversed(plaintext);


        return plaintext;
    }

    /**
     *
     * @param text
     * @return shift rows to text- aes
     */
    private byte[] ShiftRows_reversed(byte[] text) {
        byte[][] text_arrays=OneToArrays(text);
        for (int i = 0; i <4 ; i++) {
            text_arrays[i]=ShiftRight(text_arrays[i],i);
        }
        return ArraysToOne(text_arrays);
    }


    /**
     *
     * @param text
     * @param key
     * cypher xor key
     */
    private void AddRoundKey(byte[] text, byte[] key) {
        for (int i = 0; i <text.length ; i++) {
            text[i]=(byte)(text[i]^key[i]);
        }
    }
    /**
     *
     * @param row the row to shift
     * @param counter how many time shify
     * @return row after shift right by counter times
     */
    private byte[] ShiftRight(byte[] row, int counter) {
        while (counter > 0) {
            byte temp =row[row.length-1];
            for (int i = row.length-2; i > 0; i--) {
                row[i+1] =row[i];
            }
            row[0] = temp;
            counter--;
        }
        return row;
    }
    /**
     *
     * @param plainText- array of 16 bytes(128 bits)
     * @return 2 dim array of 4*4 bytes.
     */
    private byte[][] OneToArrays(byte[] plainText) {
        byte[][]res= new byte[4][4];
        int counter=0;
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                res[i][j]=plainText[counter];
                counter+=1;
            }

        }
        return res;
    }

    /**
     *
     * @param cypher_array 2 dim array of 4*4 bytes
     * @return 1 dim array of 16 bytes
     */
    private byte[] ArraysToOne(byte[][] cypher_array) {
        byte [] res=new byte[16];
        int counter=0;
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                res[counter]=cypher_array[i][j];
                counter+=1;
            }
        }
        return res;
    }
}
