import type UserDto from "$lib/dtos/user/UserDto";

export default interface UserRepository {
	getUser(id: number): Promise<UserDto>;

	getAllUsers(): Promise<UserDto[]>;

	userExists(id: number): Promise<boolean>;

	registerUser(username: string): Promise<void>;
}