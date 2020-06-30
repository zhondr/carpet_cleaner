package kz.nu.carpet_cleaner.register.util;

import java.util.Objects;

public class Nvl {

  public static <T> T nvl(T left, T right) {
    if (Objects.isNull(left)) {
      return right;
    }
    return left;
  }

}
