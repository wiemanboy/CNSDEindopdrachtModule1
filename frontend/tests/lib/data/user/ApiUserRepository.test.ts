import "reflect-metadata";
import type UserRepository from "$lib/data/user/UserRepository";
import { beforeEach, describe, expect, it, vi } from "vitest";
import ApiUserRepository from "$lib/data/user/ApiUserRepository";
import type UserDto from "$lib/dtos/user/UserDto";


let userRepository: UserRepository;

const mockApiClient = {
	get: vi.fn(),
	post: vi.fn(),
	put: vi.fn(),
	delete: vi.fn(),
};

beforeEach(() => {
	userRepository = new ApiUserRepository(mockApiClient);
});

describe("UserRepository", () => {
	it("gets a user", async () => {
		mockApiClient.get.mockReturnValueOnce(new Response(JSON.stringify({
			id: "uuid",
			username: "username",
		} as UserDto), {}));

		const user = await userRepository.getUser("uuid");
		expect(user).toBeDefined();
	});

	it("registers a user", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({}), {}));

		await userRepository.registerUser("username");
	});

	it("checks if a user exists", async () => {
		mockApiClient.get.mockReturnValueOnce(new Response(JSON.stringify(true), {}));

		const exists = await userRepository.userExists("uuid");
		expect(exists).toBe(true);
	});
});