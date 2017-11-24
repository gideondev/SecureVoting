import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class ElGamal{

    private static  byte[] intToByteArray ( final int i ) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(i);
        dos.flush();
        return bos.toByteArray();
    }

    static byte[] encryptVote(int plainText,Key publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, IOException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] input = intToByteArray(plainText);
        Cipher cipher = Cipher.getInstance("ELGamal","BC");
        SecureRandom random = new SecureRandom();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, random);
        byte[] cipherText = cipher.doFinal(input);
        return cipherText;
    }

    static byte[] decryptVote(byte[] cipherText,Key privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("ELGamal","BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
    }

    public static String encodeKey(Key key)
    {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static Key decodeKey(String key)
    {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "ELGamal");
        return originalKey;
    }

//    public static void main(String[] args) throws Exception {
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("ELGamal","BC");
//        SecureRandom random = new SecureRandom();
//        generator.initialize(256, random);
//        KeyPair pair = generator.generateKeyPair();
//        Key pubKey = pair.getPublic();
//        Key privKey = pair.getPrivate();
//
////          Keys in string format
////        String puk = Base64.getEncoder().encodeToString(pubKey.getEncoded());
////        String prk = Base64.getEncoder().encodeToString(privKey.getEncoded());
////        System.out.println(puk);
////        System.out.println(prk);
//
//
//        byte[] cipher = encryptVote("This is insane",pubKey);
//        System.out.println(new String(cipher));
//        System.out.println("\n\n");
//
//        byte[] plain = decryptVote(cipher,privKey);
//        System.out.println(new String(plain));
//        System.out.println("\n\n");
//    }


}
