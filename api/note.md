## API FOR NOTES

# View note

## Functionality

	- Client sends GET request with `session_id`

	- Server replies all the notes belong to that user, rejects with `session_id` expired
	
## Non-functionality

	- 

# Create note

## Functionality

	- Client sends POST request with `session_id` and note

	- Server replies ack / nak

## Non-functionality

	- 

# Edit note

## Functionality

	- Client sends PUT request with `session_id` and note

	- Server replies ack / nak

## Non-functionality

	- 

# Delete note 

## Functionality

	- Client sends DELETE request with `session_id` and note

	- Server replies ack / nak

## Non-functionality

	- 
