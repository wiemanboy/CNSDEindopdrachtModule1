<!--
Board Page

-->

<script lang="ts">
	import { onMount } from "svelte";
	import { goto } from "$app/navigation";
	import { container } from "$lib/index";
	import type BoardRepository from "$lib/data/board/BoardRepository";
	import types from "$lib/types";
	import type BoardDto from "$lib/dtos/board/BoardDto";
	import Board from "../../components/board/Board.svelte";
	import CreateTaskListPopup from "../../components/popup/CreateTaskListPopup.svelte";
	import type TagRepository from "$lib/data/board/TagRepository";
	import CreateTaskPopup from "../../components/popup/CreateTaskPopup.svelte";
	import CreateTagPopup from "../../components/popup/CreateTagPopup.svelte";
	import AddTagPopup from "../../components/popup/AddTagPopup.svelte";
	import type TagDto from "$lib/dtos/board/TagDto";
	import AddUserToTaskPopup from "../../components/popup/AddUserToTaskPopup.svelte";
	import AddUserPopup from "../../components/popup/AddUserPopup.svelte";
	import type UserDto from "$lib/dtos/user/UserDto";
	import type UserRepository from "$lib/data/user/UserRepository";
	import EditTaskPopup from "../../components/popup/EditTaskPopup.svelte";
	import MoveTaskPopup from "../../components/popup/MoveTaskPopup.svelte";
	import type TaskListDto from "$lib/dtos/board/TaskListDto";
	import type TaskDto from "$lib/dtos/board/TaskDto";

	const boardRepository = container.get<BoardRepository>(types.boardRepository);
	const tagRepository = container.get<TagRepository>(types.tagRepository);
	const userRepository = container.get<UserRepository>(types.userRepository);

	let error: string;
	let id: string;
	let board: BoardDto;
	let tags: TagDto[] = [];
	let users: UserDto[] = [];

	let showCreateTaskListPopup = false;
	let showCreateTaskPopup = false;
	let showCreateTagPopup = false;
	let showAddTagPopup = false;
	let showAddCollaboratorPopup = false;
	let showAddCollaboratorToTaskPopup = false;
	let showEditTaskPopup = false;
	let showMoveTaskPopup = false;

	let selectedTaskList: TaskListDto;
	let selectedTask: TaskDto;

	function closePopUps() {
		showCreateTaskListPopup = false;
		showCreateTaskPopup = false;
		showCreateTagPopup = false;
		showAddTagPopup = false;
		showAddCollaboratorPopup = false;
		showAddCollaboratorToTaskPopup = false;
		showEditTaskPopup = false;
		showMoveTaskPopup = false;
	}

	function refresh() {
		boardRepository.getBoard(id)
			.then((data) => {
				board = data;
			})
			.catch((err) => {
				error = err;
			});
		tagRepository.getTags()
			.then((data) => {
				tags = data;
			})
			.catch((err) => {
				error = err;
			});
		userRepository.getAllUsers()
			.then((data) => {
				users = data;
			});
	}

	function showCreateTaskList() {
		showCreateTaskListPopup = true;
	}

	function showCreateTask(taskList: TaskListDto) {
		showCreateTaskPopup = true;
		selectedTaskList = taskList;
	}

	function showCreateTag() {
		showCreateTagPopup = true;
	}

	function showAddTag(task: TaskDto) {
		showAddTagPopup = true;
		selectedTask = task;
	}

	function showAddCollaborator() {
		showAddCollaboratorPopup = true;
	}

	function showAddCollaboratorToTask(task: TaskDto) {
		showAddCollaboratorToTaskPopup = true;
		selectedTask = task;
	}

	function showEditTask(task: TaskDto) {
		showEditTaskPopup = true;
		selectedTask = task;
	}

	function showMoveTask(task: TaskDto) {
		showMoveTaskPopup = true;
		selectedTask = task;
	}

	async function createTaskList(title: string) {
		await boardRepository.addTaskList(board.id, title);
		refresh();
	}

	async function createTask(title: string, description: string) {
		await boardRepository.addTask(board.id, selectedTaskList.id, title, description);
		refresh();
	}

	async function createTag(title: string, color: string) {
		await tagRepository.createTag(title, color);
		refresh();
	}

	async function addTag(tagId: string) {
		await boardRepository.addTag(board.id, selectedTask.id, tagId);
		refresh();
	}

	async function removeTag(tagId: string) {
		await boardRepository.removeTag(board.id, selectedTask.id, tagId);
		refresh();
	}

	async function addCollaboratorToTask(userId: string) {
		await boardRepository.addCollaboratorToTask(board.id, selectedTask.id, userId);
		refresh();
	}

	async function addCollaborator(userId: string) {
		await boardRepository.addCollaborator(board.id, userId);
		refresh();
	}

	async function editTask(title: string, description: string) {
		await boardRepository.updateTask(board.id, selectedTask.id, title, description);
		refresh();
	}

	async function moveTask(taskListId: string) {
		await boardRepository.moveTask(board.id, taskListId, selectedTask.id);
		refresh();
	}


	onMount(() => {
		const searchParams = new URLSearchParams(window.location.search);
		id = searchParams.get("id") || "";
		if (!id || id === "") {
			goto("/boards");
			return;
		}
		refresh();
	});
</script>


<div>
	{#if (error !== undefined)}
		<p>Error while fetching board</p>
	{:else if (board === undefined)}
		<p>Loading...</p>
	{:else}
		<Board
			boardDto="{board}"
			createTaskList="{showCreateTaskList}"
			createTask="{showCreateTask}"
			createTag="{showCreateTag}"
			addTag="{showAddTag}"
			addCollaborator="{showAddCollaborator}"
			addCollaboratorToTask="{showAddCollaboratorToTask}"
			editTask="{showEditTask}"
			{removeTag}
			moveTask="{showMoveTask}"
		/>
	{/if}
	{#if (showCreateTaskListPopup)}
		<CreateTaskListPopup close="{closePopUps}" {createTaskList} />
	{/if}
	{#if (showCreateTaskPopup)}
		<CreateTaskPopup close="{closePopUps}" {createTask} />
	{/if}
	{#if (showEditTaskPopup)}
		<EditTaskPopup task="{selectedTask}" close="{closePopUps}" {editTask} />
	{/if}
	{#if (showCreateTagPopup)}
		<CreateTagPopup close="{closePopUps}" {createTag} />
	{/if}
	{#if (showAddTagPopup)}
		<AddTagPopup close="{closePopUps}" {addTag} tags="{tags}"/>
	{/if}
	{#if (showAddCollaboratorPopup)}
		<AddUserPopup close="{closePopUps}" addUser="{addCollaborator}" {users} />
	{/if}
	{#if (showAddCollaboratorToTaskPopup)}
		<AddUserToTaskPopup close="{closePopUps}" addUser="{addCollaboratorToTask}" {users} />
	{/if}
	{#if (showMoveTaskPopup)}
		<MoveTaskPopup taskId="{selectedTask.id}" taskLists="{board.taskLists}" close="{closePopUps}" {moveTask} />
	{/if}
</div>

