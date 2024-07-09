import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, ShortBufferException {
        String Vi64 = "S5mLAmz3pMcKJJX4W7wh9w==";
        String Key64 = "bUiJbptycIuEVockM28n+A==";
        String Open64 = "OD9EuA9rDKeCvi/wILRkkd4gBq8NSJMaCkBbYjri5FpS+OX6nn0cL6zbZJds7kjG";
        //Раскодируем
        byte[] ViBytes = Base64.getDecoder().decode(Vi64);
        byte[] keyBytes = Base64.getDecoder().decode(Key64);
        byte[] OpenBytes = Base64.getDecoder().decode(Open64);
        //Создаем ключ
        SecretKey newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/ISO10126Padding");
        c.init(Cipher.DECRYPT_MODE, newKey, new IvParameterSpec(ViBytes));
        byte[] encryptedData = c.doFinal(OpenBytes);
        System.out.println(new String(encryptedData));
    }
}
