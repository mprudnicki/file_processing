import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcesser {
	private static final Logger LOGGER = Logger.getLogger(FileProcesser.class.getName());
	
	public static String process(String filepath){
		try {
			
			//file variables
			FileReader fileReader = new FileReader(filepath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//loaded 'lines from file' variables
			String line;
			String[] parts;
			
			//counting users
			int usersCount = 0;
			
			//variables for processing data lines
			StringBuilder oldestUser = new StringBuilder("");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = new Date();
			Date dataBuffer = new Date();
			Date localDate = new Date();
			//loop for processing text document
			while((line = bufferedReader.readLine()) != null){
				try{
					parts = line.split(",");
					birthDate = dateFormat.parse(parts[2]);
					if(dataBuffer == null)
						dataBuffer = birthDate;
					
					if(dataBuffer.after(birthDate))
						if(parts.length > 3)
							if(parts[3].matches("(\\s?)(\\+\\d{1,3})?(\\s?)(\\(\\d*\\))?[0-9]{9}")){
								dataBuffer = birthDate;
								oldestUser.setLength(0);
								oldestUser.append(parts[0]).append(",").append(parts[1])
														   .append(", ").append(PeriodCalculator.calculateYears(localDate, dataBuffer))
														   .append(",").append(parts[3]);
						}
						
				} catch (ArrayIndexOutOfBoundsException e){
					//LOGGER.log(Level.WARNING, "NO DATA FOUND IN A SPECIFIC COLUMN (FILEPROCESSER)", e);
				} catch (ParseException e) {
					//LOGGER.log(Level.WARNING, "ERROR PARSING DATA (FILEPROCESSER) ", e);
				}
				usersCount++;
				
			}
			bufferedReader.close();
			return "Users: " + usersCount + " Oldest: " + oldestUser;
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "FILEPROCESSER CLASS ERROR WHILE EXECUTING PROGRAMME: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "FILEPROCESSER CLASS ERROR WHILE EXECUTING PROGRAMME: ", e);
			e.printStackTrace();
		}
		return null; 
		
	
	}
	
	
}
