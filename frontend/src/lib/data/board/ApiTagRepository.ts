import { injectable } from "inversify";
import type ApiClient from "$lib/network/ApiClient";
import type TagRepository from "$lib/data/board/TagRepository";
import type TagDto from "$lib/dtos/board/TagDto";

@injectable()
class ApiTagRepository implements TagRepository {
	private apiClient: ApiClient;
	private baseUrl = "/tags";

	constructor(apiClient: ApiClient) {
		this.apiClient = apiClient;
	}

	async getTags(): Promise<TagDto[]> {
		const result = await this.apiClient.get(this.baseUrl);
		return result.json();
	}

	async createTag(name: string, color: string): Promise<TagDto> {
		const result = await this.apiClient.post(this.baseUrl, { name, color });
		return result.json();
	}

	async getTag(id: string): Promise<TagDto> {
		const result = await this.apiClient.get(`${this.baseUrl}/${id}`);
		return result.json();
	}
}

export default ApiTagRepository;