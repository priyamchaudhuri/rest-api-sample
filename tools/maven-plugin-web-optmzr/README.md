## Web App Optimization Maven Plug-in
maven-plugin-web-optmzr maven plug-in has been created to optimize web projects for production environment. This plug-in internally use Yahoo UI Compressor to compress JavaScript and CSS. HTML files are being optimised putting everything in one line. Apart from compressing the files we rename the files(js, css) each time with a generated checksum from file content, which will certainly help you to utilize the browser cache efficiently. Every time the file content changes in your project, a new file name will be assigned. So no more problem of older JavaScripts, css with a new release.

This plug-in has been created to solve generic modern JavaScript projects. This may not be applicable for older projects. 

I am happy to accept feedbacks and suggestions to improve this to the next level. 

## Prerequisites
You need the following packages to be installed:
* Java 7
* Maven 3.0.5
* Git 2.6.4

## Building from source
After completing the prerequisites, you can follow the instructions to build the project.

### check out sources

	https://github.com/karasatishkumar/rest-api-sample/tree/master/tools/maven-plugin-web-optmzr

### compile and test, build all jar

		mvn clean install

One successfull built the project, you are ready to use the plugin in your web application..

##Plugin Usage
You can configure the plugin as below.(pom-example.xml is attached with the configuration)
	
	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<groupId>org.tat.api</groupId>
		<artifactId>javascript-sample</artifactId>
		<version>1.0-SNAPSHOT</version>
		<name>Sample java Script App to test plugins</name>
		<url>http://maven.apache.org</url>
	
		<parent>
			<groupId>org.tat.api</groupId>
			<artifactId>rest-api</artifactId>
			<version>1.0-SNAPSHOT</version>
			<relativePath>../</relativePath>
		</parent>
	
		<build>
			<plugins>
				<plugin>
					<groupId>org.tat.api</groupId>
					<artifactId>maven-plugin-web-optmzr</artifactId>
					<version>1.0-SNAPSHOT</version>
					<executions>
						<execution>
							<id>packaging-execution</id>
							<goals>
								<goal>optimize</goal>
							</goals>
						</execution>
					</executions>
	
					<configuration>
						<destDir>${basedir}/target/app</destDir><!--Directory to which you want to copy your optimized files-->
						<optFileSet>
							<directory>${basedir}</directory><!--Root directory of your source file-->
							<!--File extensions to be provided for optimization-->
							<includes>
								<include>js</include>
								<include>css</include>
								<include>html</include>
							</includes>
							<!--File extensions to be provided to exclude optimization-->
							<excludes>
								<exclude>gif</exclude>
								<exclude>png</exclude>
								<exclude>woff2</exclude>
								<exclude>woff</exclude>
								<exclude>otf</exclude>
								<exclude>eot</exclude>
								<exclude>ttf</exclude>
								<exclude>svg</exclude>
							</excludes>
						</optFileSet>
					</configuration>
				</plugin>
	
			</plugins>
		</build>
	</project>

Important things in this plug-in configurations are 
* Destination Directory
* Source Directory
* Include Criteria - currently only supported for js, css and html
* Exclude Criteria - images, fonts etc

## Contributing
Pull requests are welcomed. I am planning to compress all the image files as part of the next line up items.

## Staying in touch

## License

## Release



