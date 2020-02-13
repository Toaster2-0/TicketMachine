package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Datei {
	private String dateiName;
	private File datei;


	public String getDateiName() {
		return dateiName;
	}
	
	public Datei(String dateiName) {
		this.dateiName = dateiName;
		datei = new File(dateiName);
	}

	public void schreibe(String txt) throws Exception {
		schreibe(txt,false);
		
	}

	public void schreibe(String txt, boolean append) throws Exception{
		try (FileWriter outStream = new FileWriter(datei, append)) {
			outStream.write(txt);

		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Schreiben ", e);
		}

	}
	
	public boolean dateiExistiert() {
		return datei.exists();
	}
	
	public boolean dateiPfadErstellen() {
		return datei.mkdirs();
	}

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
	public static void main(String[] args) {
		
	}
}