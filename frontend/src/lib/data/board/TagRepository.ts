import type TagDto from "$lib/dtos/board/TagDto";

export default interface TagRepository {
	getTags(): Promise<TagDto[]>;
	getTag(id: string): Promise<TagDto>;
	createTag(name: string, color: string): Promise<TagDto>;
}