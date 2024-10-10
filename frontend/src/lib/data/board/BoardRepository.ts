import type BoardDto from "$lib/dtos/board/BoardDto";

export default interface BoardRepository {
	getBoard(id: string): Promise<BoardDto>;

	getBoards(): Promise<BoardDto[]>;

	createBoard(title: string): Promise<BoardDto>;

	addTaskList(boardId: string, title: string): Promise<void>;

	addTask(boardId: string, taskListId: string, title: string, description: string): Promise<void>;

	addTag(boardId: string, taskId: string, tagId: string): Promise<void>;

	addCollaborator(boardId: string, userId: string): Promise<void>;

	moveTask(boardId: string, taskId: string, targetTaskListId: string): Promise<void>;
}