import type TaskListDto from "$lib/dtos/board/TaskListDto";

export default interface BoardDto {
	id: number;
	title: string;
	collaboratorIds: string[];
	taskLists: TaskListDto[];
}