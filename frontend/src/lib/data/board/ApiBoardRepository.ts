import { injectable } from "inversify";
import type ApiClient from "$lib/network/ApiClient";
import type BoardRepository from "$lib/data/board/BoardRepository";
import type BoardDto from "$lib/dtos/board/BoardDto";

@injectable()
class ApiBoardRepository implements BoardRepository {
	private apiClient: ApiClient;
	private baseUrl = "/boards";

	constructor(apiClient: ApiClient) {
		this.apiClient = apiClient;
	}

	async addCollaborator(boardId: string, userId: string): Promise<void> {
		const result = await this.apiClient.post(`${this.baseUrl}/${boardId}/collaborators`, { userId });
		return result.json();
	}

	async addTag(boardId: string, taskId: string, tagId: string): Promise<void> {
		const result = await this.apiClient.post(`${this.baseUrl}/${boardId}/tags`, {
			taskId,
			tagId,
		});
		return result.json();
	}

	async addTask(boardId: string, taskListId: string, title: string, description: string): Promise<void> {
		const result = await this.apiClient.post(`${this.baseUrl}/${boardId}/tasks`, {
			taskListId,
			title,
			description,
		});
		return result.json();
	}

	async addTaskList(boardId: string, title: string): Promise<void> {
		const result = await this.apiClient.post(`${this.baseUrl}/${boardId}/task-lists`, { title });
		return result.json();
	}

	async createBoard(title: string): Promise<BoardDto> {
		const result = await this.apiClient.post(`${this.baseUrl}/`, { title });
		return result.json();
	}

	async getBoard(id: string): Promise<BoardDto> {
		const result = await this.apiClient.get(`${this.baseUrl}/${id}`);
		return result.json();
	}

	async getBoards(): Promise<BoardDto[]> {
		const result = await this.apiClient.get(`${this.baseUrl}/`);
		return result.json();
	}

	async moveTask(boardId: string, taskId: string, targetTaskListId: string): Promise<void> {
		const result = await this.apiClient.put(`${this.baseUrl}/${boardId}/move-task`, {
			taskId,
			targetTaskListId,
		});
		return result.json();
	}
}

export default ApiBoardRepository;