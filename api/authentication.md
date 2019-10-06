# API FOR AUTHENTICATION

# Register

## Functionality

	- Client sends register request contains `user_id`, user details, `hashed_password` (hashed password + salt)

	- Server replies acknowledgement (ack) or negative acknowledgement (nak).

## Non-functionality

	- Limits number of registeration requests

# Login

## Functionality

	- Client sends login request contains `user_id`, `hashed_password`

	- Server replies with a `session_id` or nak.

## Non-functionality

	- `session_id` expires after 1 weeks

## Logout

	- Client sends logout request contains `session_id`

	- Server replies ack or nak.


# API

- Register

```
POST
/auth?type=register

params: json
- user_id
- user_details
- hashed_password

reply: 200
params: json
- status (in text)
```

- Login

```
POST
/auth?type=login

params: json
- user_id
- hashed_password

reply: 200
params: json
- session_id
```

- Logout

```
POST
/auth?type=logout

params: json
- session_id

reply: 200
params: json
- status (in text)
```
