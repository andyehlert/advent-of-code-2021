package utils;

import java.io.InputStream;

public class FileReader
{
   public static InputStream getFileFromResourceAsStream(String fileName)
   {
      InputStream inputStream = FileReader.class.getClassLoader().getResourceAsStream(fileName);
      if (inputStream == null)
      {
         throw new IllegalArgumentException("file not found! " + fileName);
      }
      else
      {
         return inputStream;
      }
   }
}
