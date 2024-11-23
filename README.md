# library-management-react
 Library Management Web Application with a full-stack incorporation of a 3-tier architecture (React, SpringBoot JDBC, MySQL)

# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

# Setup

MySQL 8.3.4: https://dev.mysql.com/downloads/mysql/8.4.html
Maven 3.9.9: https://maven.apache.org/download.cgi
Java JDK: https://www.oracle.com/java/technologies/downloads/
Nodejs : https://nodejs.org/en/download/package-manager

Ensure you are able to access MySQL from your terminal, and that you can see versions of Maven, Java, and Node with the -version command in your terminal.
Look up tutorials for installation if you are having trouble.

Backend
1. go to target/classes/application.properties, if not included create the application.properties file
2. The file should be in this format utilizing the location of your database server (this project uses Aiven to source its MySQL database)
spring.application.name=library-management-react
spring.datasource.url=jdbc:mysql://{host}:{port}/{database_name}
spring.datasource.username= {username}
spring.datasource.password= {password}
3. Run schema.sql file located in the same folder
4. Start the springboot server with command "mvn spring-boot:run" in your IDE terminal, the default port for this server is localhost:8080

Frontend
1. Ensure you are in the frontend directory in your IDE terminal
2. Run the "npm i" command to install dependencies needed to run the application
3. Start the application with "npm run dev" and use the provided link in a web browser
