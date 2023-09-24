# ItCountsServer
An application that allows users to track their expenses over the months.

## General Information
- Provide general information about your project here.
- What problem does it (intend to) solve?
- What is the purpose of your project?
- Why did you undertake it?
<!-- You don't have to answer all the questions - just the ones relevant to your project. -->

## Technologies Used
- Java 17
- Spring Boot 2.7.15
- Spring Security
- H2 Database

## Features
List the ready features here:
- Register, login (JWT)
- Add expense
- Delete expense
- Edit existing expense
- Schedule expense that will be added to the account each month on the specified date
- Get a summary of expenses between requested dates

## Setup
- Clone the repository into your favorite IDE
- Make sure that port 8070 is free to use as the App is running on that specific port
- Run ItCountsServerApplication.class
- You may register yourself as a user or use one of the existing. Please find the login and password below:
- `LOGIN: marekgeret81@onet.pl || PASSWORD: marek81`   
- `LOGIN: spammerjoanna@gmail.com || PASSWORD: haslo12`

## Endpoints

### Register

Method: `POST`\
URL: `/api/auth/register`\
Body: `true`\
Auth: `false`

Example body:
```
{
    "email": "email@gmail.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
}
```

### Login

Method: `POST`\
URL: `/api/auth/login`\
Body: `true`\
Auth: `false`

Example body:
```
{
    "email": "marekgeret81@onet.pl",
    "password": "marek81"
}
```
Example response:
```
{
    "type": "Bearer ",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJla2dlcmV0ODFAb25ldC5wbCIsImlhdCI6MTY5NTU2NDM1NSwiZXhwIjoxNjk1NTY3OTU1fQ.K03o2H4_7GLKKb81fceWHrEyBcKFLQ8DICLHZFsuLovZIL76VCCtU7IdSp7RJbKUQrb768bb-_gi7UyG3OP00w",
    "expiresAt": 1695567955000
}
```


## Usage
How does one go about using it?
Provide various use cases and code examples here.

`qweqwsdasad`
