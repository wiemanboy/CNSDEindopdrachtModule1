# Board Service

Swagger ui is available at `/api/boards/docs`

This service uses Board as an aggregate root, so all Task related actions go through it.

## Known bugs

Moving a Task to a different TaskList only works if the target TaskList is newer than the source TaskList, this is
likely due to the order the TaskLists are being saved as it gives a `unique key constraint` error.