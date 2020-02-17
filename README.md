# K3GDS Logger

The K3GDS Logger is a simple REST server built with Spring Boot and MongoDB for amateur radio contact logging.  This backend could be paired with lightweight GUI programs that suit the specific needs of the operator. 

I began this project with a few use cases in mind.  DXpeditions and contesting teams both have a need to share a common log between multiple users in real time.  While tools currently exist, they present several limitations. 

After spending some time with the project, I'm less certain this approach is the best solution.  Future development may be limited, or I may approach again from a different angle. 

## Getting Started

- Clone the repository with `git clone https://gitlab.com/s.mcleodjr/k3gds-logger.git; cd k3gds-logger`.
- Setup a Mongo database for the logger to store contacts in.
- Configure the MongoDB host and authentication information in the src/main/resources/application.yml file.  I have used environment variables for these settings during development, which you will see in the file.
- Build the project with `mvn package`.
- Run the logger with `java -jar target/logger-0.0.1-SNAPSHOT.jar`.

## Documentation

The logger includes three document types: Logs, Stations, and Contacts.  For each of these documents, the API provides ways to retrieve, add, update, and delete documents.  While the logger aims to be minimally restrictive, it also provides mechanisms to ensure that fields with known formats (such as maidenhead grid squares or call signs) are entered correctly.  

A more complete document spec and API reference is available [here](REFERENCE.md).
