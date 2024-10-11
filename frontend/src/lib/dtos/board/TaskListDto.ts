import type TaskDto from "$lib/dtos/board/TaskDto";

export default interface TaskListDto {
	id: string;
	title: string;
	tasks: TaskDto[];
}