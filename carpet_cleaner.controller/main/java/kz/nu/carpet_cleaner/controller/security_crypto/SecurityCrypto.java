package kz.nu.carpet_cleaner.controller.security_crypto;

public interface SecurityCrypto {

  boolean verifySignature(byte[] bytes, byte[] signature);

}
