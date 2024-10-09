This is a readme for the user microservice. :)

### User Endpoints

- **Register User**
    - **URL:** `/api/users/register`
    - **Method:** `POST`
    - **Parameters:**
        - `username` (String) - name of the user
    - **Response:** `User`

- **Update User**
    - **URL:** `/api/users/{id}`
    - **Method:** `PUT`
    - **Parameters:**
        - `id` (UUID) - the UUID of the user
        - `username` (String) - name of the user
    - **Response:** `User`

- **Delete User**
    - **URL:** `/api/users/{id}`
    - **Method:** `DELETE`
    - **Parameters:**
        - `id` (UUID) - the UUID of the user

- **Get User**
    - **URL:** `/api/users/{id}`
    - **Method:** `GET`
    - **Parameters:**
        - `id` (UUID) - the UUID of the user
    - **Response:** `User`

- **Get All Users**
    - **URL:** `/api/users/`
    - **Method:** `GET`
    - **Response:** `List<User>`

- **Check if User Exists**
    - **URL:** `/api/users/{id}/exists`
    - **Method:** `GET`
    - **Parameters:**
        - `id` (UUID) - the UUID of the user
    - **Response:** `Boolean`