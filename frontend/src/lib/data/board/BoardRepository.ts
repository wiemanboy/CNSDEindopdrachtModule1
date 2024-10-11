import type BoardDto from "$lib/dtos/board/BoardDto";

export default interface BoardRepository {
	getBoard(id: string): Promise<BoardDto>;

	getBoards(): Promise<BoardDto[]>;

	createBoard(title: string): Promise<BoardDto>;

	addTaskList(boardId: string, title: string): Promise<BoardDto>;

	addTask(boardId: string, taskListId: string, title: string, description: string): Promise<BoardDto>;

	addTag(boardId: string, taskId: string, tagId: string): Promise<BoardDto>;

	addCollaborator(boardId: string, userId: string): Promise<BoardDto>;

	moveTask(boardId: string, taskId: string, targetTaskListId: string): Promise<BoardDto>;

	removeTag(boardId: string, taskId: string, tagId: string): Promise<BoardDto>;

	updateTask(boardId: string, taskId: string, title: string, description: string): Promise<BoardDto>;
	
	addCollaboratorToTask(boardId: string, taskId: string, userId: string): Promise<BoardDto>;
}