import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SaveScore {
	public static String readTextFile(String fileName) {
		  
		  String returnValue = "";
		  FileReader file = null;
		  
		  try {
		    file = new FileReader(fileName);
		    BufferedReader reader = new BufferedReader(file);
		    String line = "";
		    while ((line = reader.readLine()) != null) {
		      returnValue += line + "\n";
		    }
		  } catch (Exception e) {
		      throw new RuntimeException(e);
		  } finally {
		    if (file != null) {
		      try {
		        file.close();
		      } catch (IOException e) {
		        // Ignore issues during closing 
		      }
		    }
		  }
		  return returnValue;
}
	}
