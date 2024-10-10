import { injectable } from "inversify";
import type UserDto from "$lib/dtos/user/UserDto";
import type ApiClient from "$lib/network/ApiClient";
import type UserRepository from "$lib/data/user/UserRepository";

@injectable()
class ApiUserRepository implements UserRepository {
	private apiClient: ApiClient;
	private baseUrl = "/users";

	constructor(apiClient: ApiClient) {
		this.apiClient = apiClient;
	}

	async getAllUsers(): Promise<UserDto[]> {
		const result = await this.apiClient.get(this.baseUrl);
		return result.json();
	}

	async getUser(id: number): Promise<UserDto> {
		const result = await this.apiClient.get(`${this.baseUrl}/${id}`);
		return result.json();
	}

	async registerUser(username: string): Promise<void> {
		const result = await this.apiClient.post(this.baseUrl, { username });
		return result.json();
	}

	async userExists(id: string): Promise<boolean> {
		const result = await this.apiClient.get(`${this.baseUrl}/exists/${id}`);
		return result.json();
	}

}

export default ApiUserRepository;