package kz.nu.carpet_cleaner.controller.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Set;
import org.fest.util.Sets;

public class StringBooleanDeserializer extends JsonDeserializer<Boolean> {

  private static Set<String> trueValues = Sets.newLinkedHashSet("Y", "TRUE", "1", "YES");

  @Override
  public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text = p.getText();

    if (text == null || "null".equalsIgnoreCase(text)) {
      return null;
    }

    return trueValues.contains(text.toUpperCase());
  }

}
