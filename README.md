## REST API Sample
This code base is intend to demonstrate some of the REST API standards, technology usage and the development practice. As this code base is to work purely on the technology exploration to make API development faster, we have taken a sample LIBRARY sytem to build API for. 


## Prerequisites
You need the following packages to be installed:
* Java 7
* Tomcat 7
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



