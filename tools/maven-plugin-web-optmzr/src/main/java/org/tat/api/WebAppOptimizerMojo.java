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
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

@Mojo(name = "optimize",
        defaultPhase = LifecyclePhase.PACKAGE,
        requiresOnline = false, requiresProject = true,
        threadSafe = false)
public class WebAppOptimizerMojo extends AbstractMojo {

	@Parameter(property = "destDir", required = true)
	private File destDir;

	@Parameter(property = "optFileSet", required = true)
	private FileSet optFileSet;

	private Map<String, String> checkSumMap = new HashMap<String, String>();

	private List<File> outputFiles = new ArrayList<File>();

	private static Logger logger = Logger.getLogger(WebAppOptimizerMojo.class
			.getName());

	public void execute() throws MojoExecutionException, MojoFailureException {

		if (destDir == null)
			throw new MojoExecutionException("Please provide a destination directory");
		if (optFileSet == null)
			throw new MojoExecutionException(
					"Please profile some basic configuration for which you need optimizaion");

		getLog().debug("destDir : " + destDir);
		getLog().debug("fileSet : " + optFileSet);

		try {

			Collection<File> optIncludefiles = this.getFileList(
					optFileSet.getDirectory(), optFileSet.getIncludes());
			Collection<File> optExcludefiles = this.getFileList(
					optFileSet.getDirectory(), optFileSet.getExcludes());
			File srcDir = new File(optFileSet.getDirectory());


			/*
			 * First compress all the files in the include list and copy to the destination folder.
			 */
			this.compressAndCopy(optIncludefiles, srcDir);

			/*
			 * Second go each of the files and replace the file name with the checksum filename
			 */
			this.manageReference(checkSumMap, outputFiles);
			
			/*
			 * Third copy all the files to the respective folder in the exclude list
			 */
			this.copyFiles(optExcludefiles, srcDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void copyFiles(Collection<File> optExcludefiles, File srcDir) throws IOException {
		getLog().info("----Copy Exclude----");
		for (File file : optExcludefiles) {
			getLog().info(
					"Source Path : "
							+ file.getCanonicalPath());
			String destFileName = file
					.getCanonicalPath()
					.replace(srcDir.getCanonicalPath(),
							destDir.getCanonicalPath());
			getLog().info("Destination Path : " + destFileName);
			File destFile = new File(destFileName);
			FileUtils.copyFile(file, destFile);
		}
		
	}

	private void compressAndCopy(Collection<File> optIncludefiles, File srcDir)
			throws IOException {
		getLog().info("----Compress And Copy File----");
		for (File file : optIncludefiles) {
			if (FilenameUtils.getExtension(file.getName())
					.equalsIgnoreCase("html")) {
				getLog().info(
						"Optimizing the html file : "
								+ file.getCanonicalPath());
				String content = FileUtils.readFileToString(file, "UTF-8");
				String optContent = content.replaceAll("\\s+", " ")
						.replaceAll("> <", "><");
				String destFileName = file.getCanonicalPath().replace(
						srcDir.getCanonicalPath(),
						destDir.getCanonicalPath());
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
				getLog().debug(
						"FileName : " + file.getName() + "CheckSum : "
								+ checkSum);
				checkSumMap.put(file.getName(), checkSum);
				String destFileName = file
						.getCanonicalPath()
						.replace(srcDir.getCanonicalPath(),
								destDir.getCanonicalPath())
						.replace(file.getName(), checkSum);
				getLog().info("Output Path : " + destFileName);
				outputFiles.add(new File(destFileName));
				Options o = new Options(); // use defaults
				this.compressCss(file.getCanonicalPath(), destFileName, o);
			} else if (FilenameUtils.getExtension(file.getName())
					.equalsIgnoreCase("js")) {
				getLog().info(
						"Optimizing the css file : "
								+ file.getCanonicalPath());
				String checkSum = this.getCheckSum(file);
				getLog().debug(
						"FileName : " + file.getName() + "CheckSum : "
								+ checkSum);
				checkSumMap.put(file.getName(), checkSum);
				String destFileName = file
						.getCanonicalPath()
						.replace(srcDir.getCanonicalPath(),
								destDir.getCanonicalPath())
						.replace(file.getName(), checkSum);
				getLog().info("Output Path : " + destFileName);
				outputFiles.add(new File(destFileName));
				Options o = new Options(); // use defaults
				this.compressJavaScript(file.getCanonicalPath(),
						destFileName, o);
			} else {
				getLog().info(
						"Invalid File for Optimization : This need to be added in the exclude list :"
								+ file.getCanonicalPath());
			}
		}
	}

	private void manageReference(Map<String, String> checkSumMap,
			List<File> outputFiles) throws IOException {
		getLog().info("----Process Dependency----");
		for (File file : outputFiles) {
			getLog().info("File : " + file.getCanonicalPath());
			String content = FileUtils.readFileToString(file, "UTF-8");
			// getLog().debug("File content before : " + content);
			for (Map.Entry<String, String> entry : checkSumMap.entrySet()) {
				content = content.replaceAll(entry.getKey(), entry.getValue());
			}
			// getLog().debug("File content after : " + content);
			FileUtils.writeStringToFile(file, content, "UTF-8");
		}
	}

	private String getCheckSum(File file) throws IOException {
		Long checksum = FileUtils.checksumCRC32(file);
		return Long.toHexString(checksum) + "."
				+ FilenameUtils.getExtension(file.getName());
	}

	/**
	 * @param fileSet
	 * @return
	 * @throws MojoExecutionException
	 */
	private Collection<File> getFileList(String path, List<String> extensions)
			throws MojoExecutionException {
		if (path == null) {
			throw new MojoExecutionException("Path to the directory is null");
		}
		File directory = new File(path);
		if (!directory.exists()) {
			throw new MojoExecutionException("Provided path is not exist");
		}
		if (!directory.isDirectory()) {
			throw new MojoExecutionException("Provided path is not a directory");
		}
		return FileUtils
				.listFiles(directory, extensions.toArray(new String[optFileSet
						.getIncludes().size()]), true);
	}

	public void compressJavaScript(String inputFilename, String outputFilename,
			Options o) throws IOException, EvaluatorException {
		Reader in = null;
		Writer out = null;
		try {
			in = new InputStreamReader(FileUtils.openInputStream(new File(
					inputFilename)), o.charset);

			JavaScriptCompressor compressor = new JavaScriptCompressor(in,
					new YuiCompressorErrorReporter());
			in.close();
			in = null;

			out = new OutputStreamWriter(FileUtils.openOutputStream(new File(
					outputFilename)), o.charset);
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
			in = new InputStreamReader(FileUtils.openInputStream(new File(
					inputFilename)), o.charset);

			CssCompressor compressor = new CssCompressor(in);
			in.close();
			in = null;

			out = new OutputStreamWriter(FileUtils.openOutputStream(new File(
					outputFilename)), o.charset);
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
