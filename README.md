# Nunam Assessment

### Basic Info 

- Framework Used: <b>Spring-Boot</b> (Project template generated using https://start.spring.io/)
- Build Tool: <b>Maven</b>
- Database Used: <b>PostgresQL</b>

### Database Info

![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/769d9eb0-8af3-4ddd-8d09-6add2af0470a)

- Vehicles: To store data of each vehicles required (4 Vehicles kept in sample data)
  - Primary Key: Vehicle Number
- Data: To store the data uploaded by the vehicles each minute.
  - Composite Primary Key: Vehicle Number, Date, Time
  - Foreign Key with Vehicles: Vehicle Number
- Statistics: To store the values calculated from the Data table.
  - Composite Primary Key: Vehicle Number, Data
  - Foreign Key with Vehicles: Vehicle Number

#### Assumptions made while adding sample data, and during calculations:
  - When an vehicle is in idle state, its speed will be 0 and it will not have moved from its previous location.
  - If a vehicle is in a state, it will be assumed to remain in that state for a whole minute, until the next data of the next minute is sent.

### Steps to install

- Install JDK 17+ and setup up JAVA_HOME environment variable with location of jdk, and add jdk/bin to PATH environment variable.
- Download the Apache Maven zip, unzip and add the bin folder to the PATH system environment variable.
- <u>If PGSQL not setup</u> 
  - Install PostgreSQL database, (and pgJDBC driver which comes with PGSQL setup tool). Remember the root username and password.
- Start the PostgreSQL service, and create a new database called nunam_assessment.
- Clone the repo.
- In the resources folder in ~/src/main/, open the application.properties file, and change the url of the database server, username and password.
- If you want to generate sample data first, uncommment the line of code in src/main/java/com.example.demo/DemoApplication.java.
- From a powershell terminal cd to the directory of the repo, and run the following command, when running for the first time, which will install dependencies and run the project.
  ```bash
  mvn clean install
  ```
- For running after the first install use command
  ```bash
  mvn spring-boot:run
  ```
### Steps to schedule to run every night

- Windows
  - We can schedule the spring project to run every night using the prebuild Windows Task Scheduler.
  - In the repo, there is a file called schedule.bat, which can be used to schedule the project in Windows.
  - Open the Task Scheduler by searching in Windows.
    
    ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/3f28f4b0-717f-4b03-adee-e96600a07958)

  - One the right, click Create Basic Task.
  
    ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/be979831-c8f8-486c-a7e0-7641205bd782)

  - Give a task name and click next.
  
    ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/d9a4f209-0454-4e7c-a2a4-694847e6d591)

  - Schedule daily.
  
    ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/71e55bd5-46b7-4379-a4fd-2b5543915a13)

  - Set scheduled time for project to run everyday.

    ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/03bb2a17-0d06-41e6-b850-28af7a5cab65)

  - Select start a program.
  
    ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/46c1434e-6ed0-49ec-af85-60a455c2c773)

  - Give location of the schedule.bat script.

  With the above steps followed, the project should be scheduled to run every night to calculate the data collected by the vehicles on that day, and add the calculated statistics to the other table.

  ### Working screenshots

  ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/b7e25285-cf16-4009-a469-780ce8b07ea4)

  1 day of data generated, 10 minutes per vehicle.

  ![image](https://github.com/tru69er/Nunam-Assessment/assets/75154468/798ed8cd-043b-4c86-bcc3-44f66aa390ca)

  Statistics of 4 vehicles generated from data above.
    
