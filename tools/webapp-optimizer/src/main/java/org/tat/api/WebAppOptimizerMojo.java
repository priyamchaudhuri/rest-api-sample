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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Optimize the javescript of a web project
 * 
 * @goal optimize
 *
 * @phase process-classes
 */
public class WebAppOptimizerMojo
    extends AbstractMojo
{

	/*
	 * (non-Javadoc)
	 * @see org.apache.maven.plugin.AbstractMojo#execute()
	 * 
	 * Configuration Params
	 * sourceDirectory - Source Directory where all the javascript, html and jsp files are there
	 * destinationDirectory - Final desctination where all the files has to be copied
	 * fileSet - list of file extensions for compression and checksum genegation
	 */

    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
      
    }
}
