---

# Banco De Los Andes Transactional System

## General Description
This project implements a transactional system for the Banco De Los Andes, designed to efficiently manage banking operations, ensuring the integrity, security, and scalability of the data. It was developed as part of the Transactional Systems course at the Universidad de los Andes.

## Members
- Laura Valentina Ceron Pulgarin - 202214973 (l.ceronp@uniandes.edu.co)
- Franklin Smith Fernandez Romero - 202215103 (f.fernandezr@uniandes.edu.co)
- Andres Mateo Chilito Avella - 202214992 (a.chilitoa@uniandes.edu.co)

## Features
- **UML Conceptual Model**: Design of entities such as Users, Accounts, Loans, and Banking Operations, showing a flexible and intuitive system architecture.
- **Entity-Relationship (E/R) Model**: Detailed representation of the database schema, ensuring data integrity through appropriate relationships and constraints.
- **Data Model**: Implementation details for various entities, including primary and foreign key constraints.
- **Normalization Levels**: Explains how the database design adheres to normalization standards up to the Boyce-Codd Normal Form (BCNF), ensuring efficient data storage without redundancies.
- **Security and Privacy Measures**: Emphasizes data privacy and operational integrity, with specific rules for user roles, account management, and transaction validations.

## Prerequisites
- SQL Database Management System (DBMS)

## Setup and Installation
1. Install a compatible SQL Database Management System.
2. Configure the database schema using the scripts provided in the `/db` directory.

## Usage
To use the system, if you have the Spring plugin in your development environment, we recommend accessing the application properties file and configuring your credentials according to the provided specifications. Afterwards, you can run the application using the tools provided by the integrated development environment.

If you do not have the Spring plugin, you can run the 'AplicacionBanco' class directly from your IDE or using the provided build tools, ensuring that you have all the necessary dependencies and the correct configuration for proper operation.

Remember that it is essential to follow good security and configuration management practices to ensure a proper and secure deployment of the application.

## Testing
To ensure the quality of both the bank's website code and its database, tests were carried out throughout the development process. These tests covered different aspects, including unit tests, integration tests, and acceptance tests.

In unit tests, each code component was individually evaluated to verify its correct functioning according to the specifications. Automated testing tools were used to ensure comprehensive coverage of all possible cases. (Although there are some minor failures that are not within the RF).

Integration tests focused on the interaction between the different system components, ensuring that they worked together efficiently and without conflicts. End-to-end tests were conducted to simulate real-use situations and verify the functionality of the system as a whole.

Additionally, performance and security tests were conducted to evaluate the system's ability to handle high workloads and protect the confidential data of users.

Finally, acceptance tests were conducted with real or simulated users to validate that the application met the client's requirements and provided a satisfactory experience for the end users.

The testing process was carried out iteratively throughout the development cycle, allowing for the timely identification and correction of any problems or defects. This ensured that both the bank's website and its database met the necessary RF.

## Contributions
Contributions to this project are welcome. Please read our contribution guidelines for more details on how to propose improvements, report bugs, or submit pull requests.

## License
This project is released under the MIT license, which allows reuse with few restrictions.

## Acknowledgments
We thank the Universidad de los Andes for providing the academic framework and necessary resources for the development of this project, especially the professor and monitor who were key in the development.

---
