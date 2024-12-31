package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;
import com.slavlend.Logger.PolarLogger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/*
Библиотека для работы с криптографией
 */
public class crypto {
    public PolarValue encrypt(PolarValue data, PolarValue secret) {
        // энкриптер
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, toKey(secret));
            return new PolarValue(Base64.getEncoder().encodeToString(cipher.doFinal(data.asString().getBytes())));
        } catch (NoSuchAlgorithmException e) {
            PolarLogger.Crash("Cryptography Error. It's a bug, send it to the developers! (NoSuchAlgo)", secret.instantiateAddress);
        } catch (NoSuchPaddingException e) {
            PolarLogger.Crash("Cryptography Error. It's a bug, send it to the developers! (NoSuchPadding)", secret.instantiateAddress);
        } catch (InvalidKeyException e) {
            PolarLogger.Crash("Cryptography Error. Invalid key! (Java): ", secret.instantiateAddress);
        } catch (IllegalBlockSizeException e) {
            PolarLogger.Crash("Cryptography Error. Illegal Block Size! (Java): ", secret.instantiateAddress);
        } catch (BadPaddingException e) {
            PolarLogger.Crash("Cryptography Error. Bad Padding! (Java): ", secret.instantiateAddress);
        }
        // ничего не возвращаем в случае ошибки
        return null;
    }

    public PolarValue decrypt(PolarValue data, PolarValue secret) {
        // энкриптер
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, toKey(secret));
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data.asString()));
            return new PolarValue(new String(decryptedBytes));
        } catch (NoSuchAlgorithmException e) {
            PolarLogger.Crash("Cryptography Error. It's a bug, send it to the developers! (NoSuchAlgo)"
                    + e.getCause().getMessage(), secret.instantiateAddress);
        } catch (NoSuchPaddingException e) {
            PolarLogger.Crash("Cryptography Error. It's a bug, send it to the developers! (NoSuchPadding)"
                    + e.getCause().getMessage(), secret.instantiateAddress);
        } catch (InvalidKeyException e) {
            PolarLogger.Crash("Cryptography Error. Invalid key! (Java): "
                    + e.getCause().getMessage(), secret.instantiateAddress);
        } catch (IllegalBlockSizeException e) {
            PolarLogger.Crash("Cryptography Error. Illegal Block Size! (Java): "
                    + e.getCause().getMessage(), secret.instantiateAddress);
        } catch (BadPaddingException e) {
            PolarLogger.Crash("Cryptography Error. Bad Padding! (Java): "
                    + e.getCause().getMessage(), secret.instantiateAddress);
        }
        // ничего не возвращаем в случае ошибки
        return null;
    }

    private SecretKey toKey(PolarValue bytesKey) {
        try {
            byte[] keyBytes = bytesKey.asString().getBytes(StandardCharsets.UTF_8);

            // Обрезка ключа до нужной длины (AES-128, 192, 256)
            if (keyBytes.length > 32) {
                PolarLogger.Crash("Cryptography Error. Key is too long!", bytesKey.instantiateAddress);
                return null;
            }

            // Обрезка или добавление до нужной длины
            byte[] trimmedKey;
            if (keyBytes.length < 16) {
                trimmedKey = new byte[16];
                System.arraycopy(keyBytes, 0, trimmedKey, 0, keyBytes.length);
            } else if (keyBytes.length < 24) {
                trimmedKey = new byte[24];
                System.arraycopy(keyBytes, 0, trimmedKey, 0, keyBytes.length);
            } else {
                trimmedKey = new byte[32];
                System.arraycopy(keyBytes, 0, trimmedKey, 0, keyBytes.length);
            }

            return new SecretKeySpec(trimmedKey, "AES");
        } catch (Exception e) {
            PolarLogger.Crash("Cryptography Error. Invalid key (Java): " + e.getCause().getMessage(), bytesKey.instantiateAddress);
        }
        return null;
    }
}
