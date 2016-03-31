/**
 * Project: A00892244Gis
 * File: ReportWriter.java
 * Date: Feb 24, 2016
 * Time: 7:14:30 PM
 */

package a00892244.io;

import java.io.FileNotFoundException;
import java.util.Formatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00892244.utils.ApplicationException;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class FileWriter {
	private String filename;
	private String text;
	private Formatter output;

	private static final Logger LOG = LogManager.getLogger(FileWriter.class);

	public FileWriter() {
		super();
	}

	/**
	 * @param filename
	 * @param reportText
	 * @throws ApplicationException
	 */
	public FileWriter(String filename, String text) {
		super();
		setFilename(filename);
		setText(text);
	}

	/**
	 * 
	 * @throws ApplicationException
	 */
	public void writeReport() throws ApplicationException {
		LOG.info(text);
		writeFile(filename, text);
	}

	/**
	 * 
	 * @param filename
	 * @param text
	 * @throws ApplicationException
	 */
	public void writeFile(String filename, String text) throws ApplicationException {
		LOG.info(text);
		try {
			output = new Formatter(filename);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e.getMessage());
		}

		output.format(text);

		if (output != null) {
			try {
				output.close();
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

}
