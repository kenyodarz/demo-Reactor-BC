package com.example.demoreactorbc.infrastructure.web.in.shared;

import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.RandomStringUtils;

public final class IdGeneratorUtil {

  private IdGeneratorUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static String generateRandomAlphanumericId(String prefix, int length)
      throws NoSuchAlgorithmException {
    // Validación del prefijo
    boolean isBlankPrefix = prefix == null || prefix.trim().isEmpty();
    if (!isBlankPrefix && prefix.length() > 50) {
      throw new IllegalArgumentException("Prefix length must be less than 50.");
    }
    if (length <= 0 || length > 200) {
      throw new IllegalArgumentException("Length must be greater than 0 and less than 200.");
    }

    // Generación del ID aleatorio
    String randomAlphanumericId = RandomStringUtils.randomAlphanumeric(length);

    // Si hay prefijo, se lo concatena al ID generado
    return isBlankPrefix ? randomAlphanumericId : prefix.concat(randomAlphanumericId);
  }

}
