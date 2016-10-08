## Web App Optimization Maven Plug-in
maven-plugin-web-optmzr maven plug in has been created to optimize web projects for production environment. This plugin internally use Yahoo UI Compressor to compress JavaScript and CSS. HTML files are being optimised putting everything in one line.


## Prerequisites
You need the following packages to be installed:
* Maven 3.0.5
* Git 2.6.4
* MySql 

## Building from source
After completing the prerequisites, you can follow the instructions to build the project.

### check out sources

	git clone https://github.com/karasatishkumar/rest-api-sample.git

once you clone the project change the tomcat webapp path in your parent pom.xml. So that your wars will be get copied to webapp.	

### compile and test, build all wars

		mvn clean install

One successfull built the project, you can start tomcat to test..

## Contributing
Pull requests are welcome. We have made this keeping oracle database in mind. But later this can be extended to work on any type of databases by just providing configuration. Currently it's not a proper way to specify the metadata to the API which build the query, latter this can be extened to use annotation based configuration to read metadata.

## Staying in touch

## License

## Release



