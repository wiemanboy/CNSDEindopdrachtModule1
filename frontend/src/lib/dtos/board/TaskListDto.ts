import type TaskDto from "$lib/dtos/board/TaskDto";

export default interface TaskListDto {
	id: number;
	title: string;
	tasks: TaskDto[];
}