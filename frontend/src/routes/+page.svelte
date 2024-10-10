<script lang="ts">
	import { container } from "$lib/index";
	import type UserRepository from "$lib/data/user/UserRepository";
	import types from "$lib/types";

	const userRepository = container.get<UserRepository>(types.userRepository);

	function handleSubmit(event: Event) {
		event.preventDefault();
		userRepository.registerUser(username);
		//TODO: Redirect to the next page
	}

	let username = "";
</script>

<svelte:head>
	<title>Home</title>
</svelte:head>

<main class="flex justify-center flex-col p-2">
	<h1 class="flex justify-center mb-52 text-5xl">
		Welcome
	</h1>

	<div class="flex justify-center">
		<form on:submit={handleSubmit} class="flex p-2 grow flex-col front-themed max-w-[1000px] rounded gap-2">
			<input class="p-2 card-themed rounded" type="text" bind:value={username} placeholder="Who are you?">
			<button class="p-2 bg-purple-900 rounded" type="submit">Register</button>
		</form>
	</div>
</main>
