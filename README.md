# Fawry Clone

This project is a clone of the Fawry system that allows users to pay
for different services.

There are two types of users who can use this system:

1. Normal user
2. Admin

## Normal user

This user can use the services provided by the different
service providers available, they have a wallet which they
can use in payment. They can also make refund requests
for payment transactions they previously made.

## Admin

This user can respond to refund requests made by normal users,
they can add discounts to the available services, and see
all transactions made by normal users.

## Features

1. Users and admins can sign up using an email, a username
   and a password.
2. Users can search for service providers.
3. Users can pay for services.
4. Users can make refund requests.
5. Users can add funds to their wallet.
6. Admins can add discounts to services.
7. Admins can see all transactions made by normal users.
8. Admins can respond to refund requests made by normal users.

## ⚠️ Important notes ⚠️

- Initially, there are 3 service providers:
  - WE
  - Vodafone
  - Etisalat

Developers can add a new service provider easily
by following the simple steps below:

1. Go to the package ```eg.edu.cu.fcai.swe.fawry.provider.providers```.
2. Create a new class for the new provider to be added.
3. Make the provider class _extend_ the ```ServiceProvider``` class.
4. Create a constructor that takes the following parameters:
   - ```String id```
   - ```String name```
5. Implement the ```boolean handle(Map<String, Object> paymentForm)```
method, put all checks on form fields in it, and the calls
to the service provider's API.

****

- This system implements an **actual authentication
  system**, so when you create a user or admin account
  you will get a token in return (in the response body)
  that you will have to put in the **Authorization** header of requests to
  protected endpoints.

- If you want to bypass token validation, there is
  a **dummy user account** and a **dummy admin account** created
  initially that you can use by turning on _**debug mode**_.

To turn on debug mode, follow these simple steps:

1. Go to the class ```eg.edu.cu.fcai.swe.fawry.common.Validator```

2. Find this line: 

```
private static final boolean DEBUG_MODE = false;
```

3. Change the value of ```DEBUG_MODE``` to ```true```.

- The following endpoints are protected,
  which means you need to be an authenticated
  user or admin to access them.

Now, you should be able to access all endpoints without the need
to create user or admin accounts.

If ```DEBUG_MODE``` is set to ```false``` you will
need to be an authenticated admin to access the following
endpoints:

```
POST /discount/{{providerId}}
```

```
POST /discount
```

```
GET  /refund/all
```

```
POST /refund/response
```

```
GET  /transaction/all
```

And you will need to be an authenticated user to access the
following endpoints:

```
POST /pay/{{providerId}}
```

```
GET  /wallet
```

```
POST /wallet
```

```
GET  /refund/request
```

```
POST /refund/request
```

```
GET  /transaction
```
