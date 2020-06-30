package kz.nu.carpet_cleaner.controller.security_crypto;

public interface SecuritySourceConfig {

  String secureRandomAlgorithm();

  String messageDigestAlgorithm();

  String keyPairGeneratorAlgorithm();

  String cipherAlgorithm();

  String keyFactoryAlgorithm();

}
