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



## Register

### Description: `Register new user`
Method: `POST`\
URL: `/api/auth/register`\
Auth: `false`\
Body: `true`

| name                                               | type                              
| -------------------------------------------------- | -------------------------------- |
| email                                              | string                           |
| password                                           | string                           |

Example body:
```json
{
    "email": "email@gmail.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
}
```

## Login

### Description: `Login as user`
URL: `/api/auth/login`\
Method: `POST`\
Auth: `false`\
Body: `true`

| name                                               | type                              
| -------------------------------------------------- | -------------------------------- |
| email                                              | String                           |
| password                                           | String                           |


Example body:
```json
{
    "email": "marekgeret81@onet.pl",
    "password": "marek81"
}
```
Example response:
```json
{
    "type": "Bearer ",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJla2dlcmV0ODFAb25ldC5wbCIsImlhdCI6MTY5NTU2NDM1NSwiZXhwIjoxNjk1NTY3OTU1fQ.K03o2H4_7GLKKb81fceWHrEyBcKFLQ8DICLHZFsuLovZIL76VCCtU7IdSp7RJbKUQrb768bb-_gi7UyG3OP00w",
    "expiresAt": 1695567955000
}
```

## Account

#### Description: `Get user account`
URL: `/api/account`\
Method: `GET`\
Auth: `true`, `bearer`

Example response:
```json
{
    "accountId": 1,
    "ownerId": 1
}
```

## Expenses

### Description: `Get all expenses made by user`
URL: `/api/expenses`\
Method: `GET`\
Auth: `true`, `bearer`\
Query parameters: 

| name                                               | type                             | optional                        |
| -------------------------------------------------- | -------------------------------- |-------------------------------- |
| categoryId                                         | BigInteger                       | true                            |
| startDate                                          | Date ('YYYY-MM-'DD')             | true                            |
| endDate                                            | Date ('YYYY-MM-'DD')             | true                            |


### Description: `Add new expense`
URL: `/api/expense`\
Method: `POST`\
Auth: `true`, `bearer`\
Body: `true`

| name                                               | type                              
| -------------------------------------------------- | -------------------------------- |
| expenseCategoryId                                  | BigInteger                       |
| amount                                             | Double                           |
| spendDate                                          | Date ('YYYY-MM-'DD')             |
| info                                               | String                           |

Example response:
```json
{
    "id": 22,
    "expenseCategoryId": 2,
    "amount": 39.99,
    "insertedDate": 1695629670420,
    "spendDate": 1701734400000,
    "info": "Nice pumpkin candle from Ikea",
    "scheduled": false
}
```

### Description: `Get single expense by ID`
URL: `/api/expense/{id}`\
Method: `POST`\
Auth: `true`, `bearer`\
Path variables:

| name                                               | type                              
| -------------------------------------------------- | -------------------------------- |
| id                                                 | BigInteger                       |

```json
{
    "id": 1,
    "expenseCategoryId": 3,
    "amount": 907.42,
    "insertedDate": 1695630730396,
    "spendDate": 1694469600000,
    "info": null,
    "scheduled": false
}
```

### Description: `Edit existing expense by ID`
URL: `/api/expense/{id}`\
Method: `PUT`\
Auth: `true`, `bearer`\
Body: `true`

Example body:
```json
{
    "amount": 340.99,
    "expenseCategoryId": 4
}
```
Path variables:

| name                                               | type                             | optional                        |
| -------------------------------------------------- | -------------------------------- |-------------------------------- |
| id                                                 | BigInteger                       | false                           |


#### Description: `Delete existing expense by ID`
URL: `/api/expense/{id}`\
Method: `DELETE`\
Auth: `true`, `bearer`\
Path variables:

| name                                               | type                             | optional                        |
| -------------------------------------------------- | -------------------------------- |-------------------------------- |
| id                                                 | BigInteger                       | false                           |

## Schedule Expenses

#### Description: `The expense will be added to the user's account every month on the selected day`
URL: `/api/expense/schedule`\
Method: `POST`\
Auth: `true`, `bearer`\
Body: `true`\
| name                                               | type                              
| -------------------------------------------------- | -------------------------------- |
| amount                                             | Double                           |
| dayOfMonth                                         | Date ('YYYY-MM-'DD')             |
| expenseCategoryId                                  | BigInteger                       |


### Description: `Delete scheduled expense`
URL: `/api/expense/schedule/{id}`\
Method: `DELETE`\
Auth: `true`, `bearer`\
Path variables:

| name                                               | type                             | optional                        |
| -------------------------------------------------- | -------------------------------- |-------------------------------- |
| id                                                 | BigInteger                       | false                           |
