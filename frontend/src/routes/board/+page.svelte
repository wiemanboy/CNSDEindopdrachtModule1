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

	const boardRepository = container.get<BoardRepository>(types.boardRepository);
	const tagRepository = container.get<TagRepository>(types.tagRepository);

	let error: string;
	let id: string;
	let board: BoardDto;

	let showCreateTaskListPopup = false;
	let showCreateTaskPopup = false;
	let showCreateTagPopup = false;
	let showAddTagPopup = false;

	let selectedTaskListId: string;
	let selectedTaskId: string;

	function closePopUps() {
		showCreateTaskListPopup = false;
		showCreateTaskPopup = false;
		showCreateTagPopup = false;
		showAddTagPopup = false;
	}

	function refresh() {
		console.log("refresh");
		boardRepository.getBoard(id)
			.then((data) => {
				board = data;
			})
			.catch((err) => {
				error = err;
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
		/>
	{/if}
	{#if (showCreateTaskListPopup)}
		<CreateTaskListPopup boardId="{id}" close="{closePopUps}" {createTaskList} />
	{/if}
	{#if (showCreateTaskPopup)}
		<CreateTaskPopup boardId="{id}" taskListId="{selectedTaskListId}" close="{closePopUps}" {createTask} />
	{/if}
	{#if (showCreateTagPopup)}
		<CreateTagPopup close="{closePopUps}" {createTag} />
	{/if}
	{#if (showAddTagPopup)}
		<AddTagPopup boardId="{id}" taskId="{selectedTaskId}" close="{closePopUps}" {addTag} />
	{/if}
</div>

