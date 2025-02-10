package com.slavlend.Compiler.Libs;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmException;
import com.slavlend.Vm.VmInAddr;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/*
Библиотека криптографии для VM
 */
public class Crypto {

    /*
    Криптография AES
     */

    public String encryptAES(String data, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new VmException(new VmInAddr(-1), e.getMessage());
        }
    }

    public String decryptAES(String encryptedData, String key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decrypted);
        } catch (Exception e) {
            throw new VmException(Compiler.iceVm.getLastCallAddr(), e.getMessage());
        }
    }
}
