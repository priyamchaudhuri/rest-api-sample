## REST API Sample
This code base is intend to demonstrate some of the REST API standards, technology usage and the development practice. We have considered the the library as a sample system to come up with the APIs.


## Prerequisites
You need the following packages to be installed:
* Java 7
* Tomcat 7
* Maven 3.0.5
* Git 2.6.4
* MySql 5.5.47

## Building from source
After completing the prerequisites, you can follow the instructions to build the project.

### check out sources

	git clone https://github.com/karasatishkumar/easylocate.git

once you clone the project change the tomcat webapp path in your parent pom.xml. So that your wars will be get copied to webapp.	

### compile and test, build all wars

		mvn clean install

One successfull built the project, you can start tomcat to test..

## Contributing
Pull requests are welcome. Expecting some android and i-os developer to contribute to this.

### Database Dump

		mysqldump -u root -p library > library.sql
		mysqldump -u root -p library < library.sql

## Staying in touch

## License

## Release



