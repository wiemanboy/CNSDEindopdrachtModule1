import "reflect-metadata";
import type TagRepository from "$lib/data/board/TagRepository";
import { beforeEach, describe, expect, it, vi } from "vitest";
import ApiTagRepository from "$lib/data/board/ApiTagRepository";

let tagRepository: TagRepository;

const mockApiClient = {
	get: vi.fn(),
	post: vi.fn(),
	put: vi.fn(),
	delete: vi.fn(),
};

beforeEach(() => {
	tagRepository = new ApiTagRepository(mockApiClient);
});

describe("TagRepository", () => {
	it("gets tags", async () => {
		mockApiClient.get.mockReturnValueOnce(new Response(JSON.stringify([{ id: "uuid", name: "name", color: "color" }]), {}));

		const tags = await tagRepository.getTags();
		expect(tags).toBeDefined();
	});

	it("creates a tag", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({ id: "uuid", name: "name", color: "color" }), {}));

		const tag = await tagRepository.createTag("name", "color");
		expect(tag).toBeDefined();
	});

	it("gets a tag", async () => {
		mockApiClient.get.mockReturnValueOnce(new Response(JSON.stringify({ id: "uuid", name: "name", color: "color" }), {}));

		const tag = await tagRepository.getTag("uuid");
		expect(tag).toBeDefined();
	});
});