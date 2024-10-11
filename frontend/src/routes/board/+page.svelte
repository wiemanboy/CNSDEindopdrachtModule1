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

	let selectedTaskListId: string;
	let selectedTaskId: string;

	function closePopUps() {
		showCreateTaskListPopup = false;
		showCreateTaskPopup = false;
		showCreateTagPopup = false;
		showAddTagPopup = false;
		showAddCollaboratorPopup = false;
		showAddCollaboratorToTaskPopup = false;
		showEditTaskPopup = false;
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

	function showCreateTask(taskListId: string) {
		showCreateTaskPopup = true;
		selectedTaskListId = taskListId;
	}

	function showCreateTag() {
		showCreateTagPopup = true;
	}

	function showAddTag(taskId: string) {
		showAddTagPopup = true;
		selectedTaskId = taskId;
	}

	function showAddCollaborator() {
		showAddCollaboratorPopup = true;
	}

	function showAddCollaboratorToTask(taskId: string) {
		showAddCollaboratorToTaskPopup = true;
		selectedTaskId = taskId;
	}

	function showEditTask(taskId: string) {
		showEditTaskPopup = true;
		selectedTaskId = taskId;
	}

	async function createTaskList(boardId: string, title: string) {
		await boardRepository.addTaskList(boardId, title);
		refresh();
	}

	async function createTask(boardId: string, taskListId: string, title: string, description: string) {
		await boardRepository.addTask(boardId, taskListId, title, description);
		refresh();
	}

	async function createTag(title: string, color: string) {
		await tagRepository.createTag(title, color);
		refresh();
	}

	async function addTag(boardId: string, taskId: string, tagId: string) {
		await boardRepository.addTag(boardId, taskId, tagId);
		refresh();
	}

	async function removeTag(taskId: string, tagId: string) {
		//TODO: remove tag
		refresh();
	}

	async function addCollaboratorToTask(boardId: string, taskId: string, userId: string) {
		//TODO: add collaborator to task
		refresh();
	}

	async function addCollaborator(boardId: string, userId: string) {
		await boardRepository.addCollaborator(boardId, userId);
		refresh();
	}

	async function editTask(boardId: string, taskId: string, title: string, description: string) {
		//TODO: edit task
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
		/>
	{/if}
	{#if (showCreateTaskListPopup)}
		<CreateTaskListPopup boardId="{id}" close="{closePopUps}" {createTaskList} />
	{/if}
	{#if (showCreateTaskPopup)}
		<CreateTaskPopup boardId="{id}" taskListId="{selectedTaskListId}" close="{closePopUps}" {createTask} />
	{/if}
	{#if (showEditTaskPopup)}
		<EditTaskPopup boardId="{id}" taskId="{selectedTaskId}" close="{closePopUps}" {editTask} />
	{/if}
	{#if (showCreateTagPopup)}
		<CreateTagPopup close="{closePopUps}" {createTag} />
	{/if}
	{#if (showAddTagPopup)}
		<AddTagPopup boardId="{id}" taskId="{selectedTaskId}" close="{closePopUps}" {addTag} tags="{tags}"/>
	{/if}
	{#if (showAddCollaboratorPopup)}
		<AddUserPopup boardId="{id}" close="{closePopUps}" addUser="{addCollaborator}" {users} />
	{/if}
	{#if (showAddCollaboratorToTaskPopup)}
		<AddUserToTaskPopup boardId="{id}" taskId="{selectedTaskId}" close="{closePopUps}" addUser="{addCollaboratorToTask}" {users} />
	{/if}
</div>

