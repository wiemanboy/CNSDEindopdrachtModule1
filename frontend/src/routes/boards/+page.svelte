<!--
Board page

-->

<script lang="ts">
	import { userStore } from "$lib/store/store";
	import type UserDto from "$lib/dtos/user/UserDto";
	import { goto } from "$app/navigation";
	import { onDestroy, onMount } from "svelte";

	let unsubscribe = () => {};
	let user: UserDto;

	onMount(() => {
		unsubscribe = userStore.subscribe(value => {
			if (!value) {
				goto("/");
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
	<h1 class="flex justify-center mb-52 text-5xl">
		{user?.username}'s boards
	</h1>
</main>