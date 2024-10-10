import type UserDto from "$lib/dtos/user/UserDto";

export default interface UserRepository {
	getUser(id: string): Promise<UserDto>;

	getAllUsers(): Promise<UserDto[]>;

	userExists(id: string): Promise<boolean>;

	registerUser(username: string): Promise<UserDto>;
}