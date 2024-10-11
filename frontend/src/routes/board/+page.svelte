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

	const boardRepository = container.get<BoardRepository>(types.boardRepository);

	let error: string;
	let id: string;
	let board: BoardDto;

	function createTaskList() {
		console.log("createTaskList");
	}

	function createTask() {
		console.log("createTask");
	}

	function createTag() {
		console.log("createTag");
	}

	function addTag() {
		console.log("addTag");
	}

	onMount(() => {
		const searchParams = new URLSearchParams(window.location.search);
		id = searchParams.get("id") || "";
		if (!id || id === "") {
			goto("/boards");
			return;
		}
		boardRepository.getBoard(id)
			.then((data) => {
				board = data;
			})
			.catch((err) => {
				error = err;
			});
	});
</script>


<div>
	{#if (error !== undefined)}
		<p>Error while fetching board</p>
	{:else if (board === undefined)}
		<p>Loading...</p>
	{:else}
		<Board boardDto="{board}" {createTaskList} {createTask} {createTag} {addTag} />
	{/if}
</div>

