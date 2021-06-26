
## About <a href="https://github.com/kamronbekrustamov/Online-Restaurant"><b>Giardino Online Restaurant</b></a>

Giardino is a web application created for online restuarant management. It is made using Spring Boot and Vue Frameworks by Kamronbek Rustamov
- Simple
- User-friendly
- Open source

PS: **Giardino** name is chosen randomly.

## How to clone and work on your local machine or use it on your host?

- Pre-requirements:
1. Install <a href="https://www.apachefriends.org/index.html">XAMPP</a>
2. Install <a href="https://www.oracle.com/java/technologies/javase-jdk16-downloads.html">JDK 16</a>
3. Install <a href="https://nodejs.org/en/">Node JS</a>

- Steps to run the software on your local machine:
1. Clone this repository to your computer.
2. In XAMPP, run Apache and MySQL, and open PhpMyAdmin
3. In PhpMyAdmin, create a database called `spring`
4. Open PowerShell and navigate to the projcet folder.
5. Run 
```
mvnw spring-boot:run
```
6. Go back to PhpMyAdmin and import `src/main/resources/spring.sql` in `spring ` database
7. Open another Powershell, and navigate to `src/main/frontend` folder
8. Run
```
npm run serve
```
9. Go to `http://localhost:3000` and enjoy

<hr>

## Usernames and Password to use

```
email=admin@gmail.com, password=password (ADMIN)
email=kamronbek@gmail.com, password=password (USER)
```
<hr>

## How to use it as a Client

- Go to `http://localhost:3000` and sign in as a User
- You will forwarded to Home Page after succesfull login
- You can navigate to other pages from there

<hr>

## How to use it as an Administrator

- Go to `http://localhost:3000` and sign in as an Admin
- You will forwarded to Admin Pages after succesfull login
- You can navigate to other pages from there
