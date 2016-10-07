package org.tat.api;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.model.FileSet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Optimize the javescript of a web project
 * 
 * @goal optimize
 * 
 * @phase package
 */
public class WebAppOptimizerMojo extends AbstractMojo {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.AbstractMojo#execute()
	 * 
	 * Configuration Params sourceDirectory - Source Directory where all the
	 * javascript, html and jsp files are there destinationDirectory - Final
	 * desctination where all the files has to be copied fileSet - list of file
	 * extensions for compression and checksum genegation
	 */

	/**
	 * @parameter
	 */
	private File sourceDirectory;

	/**
	 * @parameter
	 */
	private File destinationDirectory;

	/**
	 * @parameter
	 */
	private FileSet fileSet;
	
	private Map<String, String> checkSumMap = new HashMap<String, String>();
	
	private List<File> outputFiles = new ArrayList<File>();

	private static Logger logger = Logger.getLogger(WebAppOptimizerMojo.class
			.getName());

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("sourceDirectory : " + sourceDirectory);
		getLog().info("destinationDirectory : " + destinationDirectory);
		getLog().info("fileSet : " + fileSet);

		// copy all the content to the destination directory
		try {

			Collection<File> files = this.getFileList(fileSet);

			getLog().info("No Of files : " + files.size());

			getLog().info("----Compress File----");
			for (File file : files) {
				if (FilenameUtils.getExtension(file.getName())
						.equalsIgnoreCase("html")) {
					getLog().info(
							"Optimizing the html file : "
									+ file.getCanonicalPath());
					String content = FileUtils.readFileToString(file, "UTF-8");
					String optContent = content.replaceAll("\\s+", " ")
							.replaceAll("> <", "><");
					String destFileName = file.getCanonicalPath().replace(
							sourceDirectory.getCanonicalPath(),
							destinationDirectory.getCanonicalPath());
					getLog().info("Output Path : " + destFileName);
					File destFile = new File(destFileName);
					outputFiles.add(destFile);
					FileUtils.writeStringToFile(destFile, optContent, "UTF-8");
				} else if (FilenameUtils.getExtension(file.getName())
						.equalsIgnoreCase("css")) {
					getLog().info(
							"Optimizing the css file : "
									+ file.getCanonicalPath());
					String checkSum = this.getCheckSum(file);
					getLog().debug("FileName : " + file.getName() + "CheckSum : " + checkSum);
					checkSumMap.put(file.getName(), checkSum);
					String destFileName = file.getCanonicalPath().replace(
							sourceDirectory.getCanonicalPath(),
							destinationDirectory.getCanonicalPath()).replace(file.getName(), checkSum);
					getLog().info("Output Path : " + destFileName);
					outputFiles.add(new File(destFileName));
					Options o = new Options(); // use defaults
					this.compressCss(file.getCanonicalPath(),
							destFileName, o);
				} else {
					getLog().info(
							"Optimizing the css file : "
									+ file.getCanonicalPath());
					String checkSum = this.getCheckSum(file);
					getLog().debug("FileName : " + file.getName() + "CheckSum : " + checkSum);
					checkSumMap.put(file.getName(), checkSum);
					String destFileName = file.getCanonicalPath().replace(
							sourceDirectory.getCanonicalPath(),
							destinationDirectory.getCanonicalPath()).replace(file.getName(), checkSum);
					getLog().info("Output Path : " + destFileName);
					outputFiles.add(new File(destFileName));
					Options o = new Options(); // use defaults
					this.compressJavaScript(file.getCanonicalPath(),
							destFileName, o);
				}
			}
			
			//Finally resolve the dependency
			this.processDependency(checkSumMap, outputFiles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void processDependency(Map<String, String> checkSumMap, List<File> outputFiles) throws IOException{
		getLog().info("----Process Dependency----");
		for (File file : outputFiles) {
			getLog().info("File : " + file.getCanonicalPath());
			String content = FileUtils.readFileToString(file, "UTF-8");
			getLog().debug("File content before : " + content);
			for (Map.Entry<String, String> entry : checkSumMap.entrySet()) {
				content = content.replaceAll(entry.getKey(), entry.getValue());
			}
			getLog().debug("File content after : " + content);
			FileUtils.writeStringToFile(file, content, "UTF-8");
		}
	}
	
	private String getCheckSum(File file) throws IOException{
		Long checksum = FileUtils.checksumCRC32(file);
		return Long.toHexString(checksum) + "." + FilenameUtils.getExtension(file.getName());
	}

	/**
	 * @param fileSet
	 * @return
	 * @throws MojoExecutionException
	 */
	private Collection<File> getFileList(FileSet fileSet)
			throws MojoExecutionException {
		if (fileSet == null)
			return null;
		if (fileSet.getDirectory() == null)
			return null;
		File directory = new File(fileSet.getDirectory());
		if (!directory.exists()) {
			return null;
		}
		return FileUtils.listFiles(
				directory,
				fileSet.getIncludes().toArray(
						new String[fileSet.getIncludes().size()]), true);
	}

	public void compressJavaScript(String inputFilename, String outputFilename,
			Options o) throws IOException, EvaluatorException {
		Reader in = null;
		Writer out = null;
		try {
			in = new InputStreamReader(FileUtils.openInputStream(new File(inputFilename)), o.charset);

			JavaScriptCompressor compressor = new JavaScriptCompressor(in,
					new YuiCompressorErrorReporter());
			in.close();
			in = null;

			out = new OutputStreamWriter(FileUtils.openOutputStream(new File(outputFilename)),
					o.charset);
			compressor.compress(out, o.lineBreakPos, o.munge, o.verbose,
					o.preserveAllSemiColons, o.disableOptimizations);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	public void compressCss(String inputFilename, String outputFilename,
			Options o) throws IOException, EvaluatorException {
		Reader in = null;
		Writer out = null;
		try {
			in = new InputStreamReader(FileUtils.openInputStream(new File(inputFilename)), o.charset);

			CssCompressor compressor = new CssCompressor(in);
			in.close();
			in = null;

			out = new OutputStreamWriter(FileUtils.openOutputStream(new File(outputFilename)),
					o.charset);
			compressor.compress(out, -1);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}
	private class Options {
		public String charset = "UTF-8";
		public int lineBreakPos = -1;
		public boolean munge = true;
		public boolean verbose = true;
		public boolean preserveAllSemiColons = false;
		public boolean disableOptimizations = false;
	}

	private class YuiCompressorErrorReporter implements ErrorReporter {
		public void warning(String message, String sourceName, int line,
				String lineSource, int lineOffset) {
			if (line < 0) {
				logger.log(Level.WARNING, message);
			} else {
				logger.log(Level.WARNING, line + " : " + lineOffset + " : "
						+ message);
			}
		}

		public void error(String message, String sourceName, int line,
				String lineSource, int lineOffset) {
			if (line < 0) {
				logger.log(Level.SEVERE, message);
			} else {
				logger.log(Level.SEVERE, line + " : " + lineOffset + " : "
						+ message);
			}
		}

		public EvaluatorException runtimeError(String message,
				String sourceName, int line, String lineSource, int lineOffset) {
			error(message, sourceName, line, lineSource, lineOffset);
			return new EvaluatorException(message);
		}
	}
}
