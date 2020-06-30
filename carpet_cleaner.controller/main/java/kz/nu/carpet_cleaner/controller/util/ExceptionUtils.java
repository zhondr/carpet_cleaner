package kz.nu.carpet_cleaner.controller.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {


  public static String stackTraceToString(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    return sw.toString();
  }

}
