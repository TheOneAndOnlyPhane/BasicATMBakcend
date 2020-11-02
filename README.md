# BasicATMBakcend
REST API for a basic ATM machine. The model is an Account entity which contains an **id**, **owner** and **balance**.

The following assumptions were made in the scope of this application:
* ATM machine has unlimited money
* user is logged in
* account balance can be negative (there are situations when an automatic payment to a bank for a credit for example might take your balance below 0)

# Available endpoints
* **GET: "/accounts"** -> returns a list of all the accounts in the DB
* **GET: "/accounts/{id}"** -> returns the account with the specified ID
* **PUT: "/accounts/{id}"** -> update the account at the specified ID
* **GET: "accounts/{id}/balance"** -> returns the balance for the account with the specified ID
* **PUT: "accounts/{id}/balance"** -> update the balance for the account with the specified ID. A **funds** parameter is required in order to specify the amount.