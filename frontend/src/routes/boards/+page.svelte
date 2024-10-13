<!--
Board page

-->

<script lang="ts">
	import { userStore } from "$lib/store/store";
	import type UserDto from "$lib/dtos/user/UserDto";
	import { onDestroy, onMount } from "svelte";
	import BoardList from "../../components/board/BoardList.svelte";
	import CreateBoardPopup from "../../components/popup/CreateBoardPopup.svelte";
	import types from "$lib/types";
	import type BoardRepository from "$lib/data/board/BoardRepository";
	import { container } from "../../../inversify.config";
	import { goto } from "$app/navigation";

	const boardRepository = container.get<BoardRepository>(types.boardRepository);

	let unsubscribe = () => {
	};
	let user: UserDto;

	let showCreateBoardPopup = false;

	function closePopUps() {
		showCreateBoardPopup = false;
	}

	function openCreateBoardPopup() {
		showCreateBoardPopup = true;
	}

	async function createBoard(title: string) {
		const board = await boardRepository.createBoard(title);
		goto(`/board/?id=${board.id}`);
	}


	onMount(() => {
		unsubscribe = userStore.subscribe(value => {
			if (!value) {
				// goto("/");
			}
			user = value;
		});
	});

	onDestroy(() => {
		unsubscribe();
	});
</script>

<svelte:head>
	<title>Board</title>
</svelte:head>

<main class="flex justify-center flex-col p-2">
	<h1 class="flex justify-center text-5xl">
		{user?.username}'s boards
	</h1>
	<div class="flex justify-center ">
		<button class="max-w-52 bg-purple-900 p-2 rounded mb-52" on:click={openCreateBoardPopup}>
			Create new board
		</button>
	</div>
	<BoardList />
	{#if showCreateBoardPopup}
		<CreateBoardPopup close={closePopUps} createBoard={createBoard} />
	{/if}
</main>