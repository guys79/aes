
public class Encryption {

    public byte[] EncryptAES(byte[] PlainText,byte[]k1,byte[]k2,byte[]k3){
        byte[] []cypher= OneToArrays(PlainText);
        //round 1
        cypher=ShiftRows(cypher);
        cypher=AddRoundKey(cypher,k1);
        //round 2
        cypher=ShiftRows(cypher);
        cypher=AddRoundKey(cypher,k2);
        //round 3
        cypher=ShiftRows(cypher);
        cypher=AddRoundKey(cypher,k3);

        return ArraysToOne(cypher);
    }

    /**
     *
     * @param text
     * this function shift rows - AES
     */
    private byte[][] ShiftRows(byte[][] text){
        byte[][]res=new byte[4][4];
        for (int i = 0; i <4 ; i++) {
            res[i]=ShiftLeft(text[i],i);
        }
        return res;
    }

    /**
     *  @param cypher
     * @param key
     */
    private byte[][] AddRoundKey(byte[][] cypher, byte[] key) {
        byte[]temp=ArraysToOne(cypher);
        for (int i = 0; i <temp.length ; i++) {
            temp[i]=(byte)(temp[i]^key[i]);
        }
        return OneToArrays(temp);
    }

    /**
     *
     * @param plainText- array of 16 bytes(128 bits)
     * @return 2 dim array of 4*4 bytes.
     */
    private byte[][] OneToArrays(byte[] plainText) {
        byte[][][]res= RWFromFile.createBlocks(plainText);
        return res[0];
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
                res[counter]=cypher_array[j][i];
                counter+=1;
            }
        }
        return res;
    }


    /**
     *
     * @param row the row to shift
     * @param counter how many time shify
     * @return row after shift left by counter times
     */
    private byte[] ShiftLeft(byte[] row, int counter)
    {
        while (counter > 0) {
            byte temp =row[0];
            for (int i = 0; i < row.length - 1; i++) {
                row[i] =row[i + 1];
            }
            row[row.length - 1] = temp;
            counter--;
        }
        return row;
    }


}
