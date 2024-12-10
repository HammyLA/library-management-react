# library-management-react
 Library Management Web Application with a full-stack incorporation of a 3-tier architecture (React, SpringBoot JDBC, MySQL)

# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

# Setup

MySQL: https://dev.mysql.com/downloads/installer/

Maven 3.9.9: https://maven.apache.org/download.cgi tutorial: https://phoenixnap.com/kb/install-maven-windows

Java JDK 21: https://www.oracle.com/java/technologies/downloads/ tutorial: https://www.geeksforgeeks.org/download-and-install-java-development-kit-jdk-on-windows-mac-and-linux/

Nodejs : https://nodejs.org/en/download/package-manager

NOTE: MySQL Shell extension in VSCode can be particularly useful for running SQL setup scripts

Ensure you are able to access MySQL from your terminal, and that you can see versions of Maven, Java, and Node with the -version command in your terminal.
Look up tutorials for installation if you are having trouble.

Backend
1. go to /backend/src/main/java/com/lib/library_management_react/resources/, if not included create the application.properties file
2. The application.properties should be in this format utilizing the location of your database server (this project uses Aiven to source its MySQL database)

spring.application.name=library-management-react

spring.datasource.url=jdbc:mysql://{host}:{port}/{database_name}

spring.datasource.username= {username}

spring.datasource.password= {password}

The configuration for this portion in particular can be found in the report paper under "How JDBC is connected to the database". GitHub does not allow me to insert my API keys to the online database for safety purposes, so if accessing from github, take the configuration information from the paper.

3. Run create_schema.sql and initialize_schema.sql file located in the same folder of the application.properties file
4. Input "mvn clean install" to set up dependencies for the backend.
5. Start the springboot server with command "mvn spring-boot:run" in your IDE terminal, the default port for this server is localhost:8080

Frontend
1. Ensure you are in the frontend directory in your IDE terminal
2. Input the "npm i" command in the terminal to install dependencies needed to run the application
3. Start the application with "npm run dev" and use the provided link in the terminal in a web browser

# Acknowledgements

Saye was used as a source for default book inserts in schema.sql: https://www.sayebrand.com/blogs/stories/25famousbooks

Vondy used to generate landing page image: https://www.vondy.com/