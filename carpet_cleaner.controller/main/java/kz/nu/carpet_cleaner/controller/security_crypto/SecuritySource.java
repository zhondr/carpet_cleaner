package kz.nu.carpet_cleaner.controller.security_crypto;

import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public interface SecuritySource {

  Cipher getCipher();

  PublicKey getPublicKey();

  PrivateKey getPrivateKey();

  MessageDigest getMessageDigest();

  SecureRandom getRandom();

  int getBlockSize();

}
