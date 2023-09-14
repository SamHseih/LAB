package ccu.pllab.tcgen.DataWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataReader {
	private static InputStreamReader isr = null;
	private static BufferedReader br = null;

	public static String readInfo(String fileName, String fileType, String fileDes, String folderName) {
		try {
			File folder = new File(fileDes + "\\" + folderName);
			File file = new File(fileDes + "\\" + folderName + "\\" + fileName + "." + fileType);

			if (!folder.exists() || !file.exists()) {
				return "";
			}

			isr = new InputStreamReader(new FileInputStream(file));
			br = new BufferedReader(isr);
			String line = "";
			String text = "";
			line = br.readLine();
			while (line != null) {
				text += line + "\n";
				line = br.readLine();
			}

			return text;

		} catch (IOException e) {
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
			}
		}

		return "";
	}
}
