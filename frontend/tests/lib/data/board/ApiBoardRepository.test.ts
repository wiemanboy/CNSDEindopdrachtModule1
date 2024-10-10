import "reflect-metadata";
import { beforeEach, describe, expect, it, vi } from "vitest";
import type BoardRepository from "$lib/data/board/BoardRepository";
import ApiBoardRepository from "$lib/data/board/ApiBoardRepository";

let boardRepository: BoardRepository;

const mockApiClient = {
	get: vi.fn(),
	post: vi.fn(),
	put: vi.fn(),
	delete: vi.fn(),
};

beforeEach(() => {
	boardRepository = new ApiBoardRepository(mockApiClient);
});


describe("BoardRepository", () => {
	it("gets boards", async () => {
		mockApiClient.get.mockReturnValueOnce(new Response(JSON.stringify([{ id: "uuid", name: "name" }]), {}));

		const boards = await boardRepository.getBoards();
		expect(boards).toBeDefined();
	});

	it("creates a board", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({ id: "uuid", name: "name" }), {}));

		const board = await boardRepository.createBoard("name");
		expect(board).toBeDefined();
	});

	it("gets a board", async () => {
		mockApiClient.get.mockReturnValueOnce(new Response(JSON.stringify({ id: "uuid", name: "name" }), {}));

		const board = await boardRepository.getBoard("uuid");
		expect(board).toBeDefined();
	});

	it("adds a collaborator", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({}), {}));

		await boardRepository.addCollaborator("uuid", "uuid");
	});

	it("adds a tag", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({}), {}));

		await boardRepository.addTag("uuid", "uuid", "uuid");
	});

	it("adds a task", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({}), {}));

		await boardRepository.addTask("uuid", "uuid", "title", "description");
	});

	it("adds a task list", async () => {
		mockApiClient.post.mockReturnValueOnce(new Response(JSON.stringify({}), {}));

		await boardRepository.addTaskList("uuid", "title");
	});

	it("moves a task", async () => {
		mockApiClient.put.mockReturnValueOnce(new Response(JSON.stringify({}), {}));

		await boardRepository.moveTask("uuid", "uuid", "uuid");
	});

});