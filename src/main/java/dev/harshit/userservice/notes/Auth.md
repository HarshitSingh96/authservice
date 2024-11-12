### Authentication and Authorization
- Authentication is knowing the person who you are like name, email
- We also need password which is a secret thing that only the real person knows so that no other
  person impersonates with it.

- Authorization is the authority which decides whether a person has access to this resource or not,
  it is based on the role of the person


### Implementing authorization
- we use oauth for authorization
- It is a protocol used for authorization
- It is also used to do authentication

oauth 2.0 is a protocol used for authorization
There are 4 components in oauth

1. Authorization server
- The place that will grant permission of a particular thing to a particular application
- When we login on a app using google, we see a screen where the app asks for access to contacts, calendar, etc
  There we allow then, so now they're authorized to access these things from our google account.

2. Resource owner
- Who has final say where a client can access something
- Owner says the authorization server to allow access to a client to a resource

3. Client
- Client is the 3rd party application who wants authorization to access a resource

4. Resource server
- It is the resource server which actually has the data
- eg. gmail server, contacts server


### Flow:
1. Client asks the authorization server for the access
2. User gives access to the client for some resource
3. Client requests resource server for the resource of a particular user
4. resource server checks with auth server if the client is allowed for the data access

This happens via token
- When user allows the access, auth server gives a token to the client app
- Whenever a client wants to access a resource, it send that token to the resource server
- resource server checks the token with auth server and then gives the data or not

Now to validate if a token is valid or not and what all access an app has, resource server calls
auth server and it takes some more time to fetch details

What if token has all the details, and there comes JWT

### JWT
- Json web tokens has all the info required
- They allow to send the data in the token itself

So now if resource server calls auth server, it just validates the token and returns valid or invalid
The JWT token already has the info of what all resources are authorized to the client

JWT token has 3 parts: header, payload, signature
Now the process is:
1. Resource server asks auth server for validation of token
2. At resource server, if token is valid.. then
    - base64_decode(header)
    - base64_decode(payload)
    - check if signature == sha256(base64encoded(header). base64encoded(payload) + secret)
        - good token
        - otherwise token tampered
    - secret is something which is at the server, because of this secret.. the authenticity of token is maintained

This secret key can be stored in cache

Also JWT can be validated independently at resource server itself
we only call auth server to check if it is the current not expired token

Eg. Scaler allows 2 sessions..for 3rd session it remove 1 of the prev 2 tokens
So.. it might be a valid token but we call auth server to check if it is mapped with the user or not


### There are 3 main apis in auth service
1. Signup
- Here we take email, username, pass from user and store into the database
- We use HTTPs and also encrypt the pass at client side and then send over https just to be
  double safe


2. Login
- Here also user sents email and pass to the server
- server checks the email and pass from db
- If correct, it generates a token, saves it into the db and send it to the user

3. logout
- user send logout with token
- server removes the token from the session table


### How to store passwords ?
- Try to avoid seeing the real pass as much as possible

Possible ways:
1. store it as it is
- Issue is companies employees can see it and misuse it
- If DB gets compromised, again hacker will be able to see all users pass

2. Store the encrypted password
- Encrypt the pass and store it
- It is also not the best way as hacker can register itself with a common password
- If he gets access to the db, he can see the encrypted password
- Whichever pass is same as his encrypted pass, he knows the real pass of that user

3. Encryption + salt
- salt is a random string that keeps changing with time
- for eg. encrypt(pass + now()) and store in db
- Here it is impossible for 2 pass to match since time is always diff
- But how will we login ? pass + which time do we encrypt ?

4. Bcrypt algo
- It is the most common algo used
- It takes our pass + add some random salt and stores in the db
- When we login.. it checks whether with the pass we provided + all salt can we get the same
  encrypted pass or not






















