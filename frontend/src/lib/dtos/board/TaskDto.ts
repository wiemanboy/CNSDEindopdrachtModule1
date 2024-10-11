import type TagDto from "$lib/dtos/board/TagDto";

export default interface TaskDto {
	id: string;
	title: string;
	description: string;
	collaboratorIds: string[];
	tags: TagDto[];
}