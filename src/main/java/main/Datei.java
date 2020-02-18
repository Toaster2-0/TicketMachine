package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Datei {
	private String dateiName;
	private File datei;

	/**returns the abstract pathname
	 * 
	 * @return
	 */
	public String getDateiName() {
		return dateiName;
	}
	/**is used to generate new files and write
	 * you need to insert the abstract pathname
	 * 
	 */
	public Datei(String dateiName) {
		this.dateiName = dateiName;
		datei = new File(dateiName);
	}

	/**
	 * writes the String in a new file
	 * @param txt
	 * @throws Exception
	 */
	public void schreibe(String txt) throws Exception {
		schreibe(txt,false);
		
	}

	/**
	 * writes the string in a new file if append then it appends the existing file
	 * @param txt
	 * @param append
	 * @throws Exception
	 */
	public void schreibe(String txt, boolean append) throws Exception{
		try (FileWriter outStream = new FileWriter(datei, append)) {
			outStream.write(txt);

		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Schreiben ", e);
		}

	}
	/**
	 * returns if the file exists in the abstract pathname
	 * @return
	 */
	public boolean dateiExistiert() {
		return datei.exists();
	}
	
	/**creates the folderstructure for the given path
	 * 
	 */
	public boolean dateiPfadErstellen() {
		return datei.mkdirs();
	}
	
	/**returns a string of the file to read
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lese() throws Exception {
		StringBuffer inhalt = new StringBuffer();
		BufferedReader reader = null;
		// einlesen der Datei
		try (FileReader inStream = new FileReader(datei)) {
			reader = new BufferedReader(inStream);
			String zeile = "";
			while ((zeile = reader.readLine()) != null) // bis alles drin ist
			{
				if (inhalt.length() > 0) {
					inhalt.append("\n");
				}
				inhalt.append(zeile);
			}
		}
		// Etwas schief gegangen?
		catch (IOException e) {
			throw new Exception("Fehler beim Lesen", e);
		}

		return inhalt.toString();
	

	}

}