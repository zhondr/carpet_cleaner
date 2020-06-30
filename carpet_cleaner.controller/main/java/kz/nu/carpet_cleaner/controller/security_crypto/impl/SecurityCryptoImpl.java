package kz.nu.carpet_cleaner.controller.security_crypto.impl;

import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import kz.nu.carpet_cleaner.controller.security_crypto.SecurityCrypto;
import kz.nu.carpet_cleaner.controller.security_crypto.SecuritySource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecurityCryptoImpl implements SecurityCrypto {

  private final SecuritySource securitySource;

  @Override
  public boolean verifySignature(byte[] bytes, byte[] signature) {
    if (signature == null || bytes == null) {
      return false;
    }
    try {

      final byte[] hash1 = securitySource.getMessageDigest().digest(bytes);

      Cipher cipher = securitySource.getCipher();
      cipher.init(Cipher.DECRYPT_MODE, securitySource.getPublicKey());

      final byte[] hash2 = cipher.doFinal(signature);

      if (hash1.length != hash2.length) {
        return false;
      }

      for (int i = 0, n = hash1.length; i < n; i++) {
        if (hash1[i] != hash2[i]) {
          return false;
        }
      }

      return true;

    } catch (BadPaddingException | IllegalBlockSizeException | ArrayIndexOutOfBoundsException e) {

      return false;

    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    }
  }

}
