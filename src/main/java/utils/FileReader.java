package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReader
{
   public static BufferedReader getFileFromResourceAsBufferedReader(String fileName)
   {
      InputStream inputStream = FileReader.class.getClassLoader().getResourceAsStream(fileName);
      if (inputStream == null)
      {
         throw new IllegalArgumentException("file not found! " + fileName);
      }
      else
      {
         InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
         BufferedReader reader = new BufferedReader(streamReader);
         return reader;
      }
   }
}
