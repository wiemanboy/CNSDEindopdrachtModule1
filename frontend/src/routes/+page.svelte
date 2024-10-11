<script lang="ts">
	import { container } from "$lib/index";
	import type UserRepository from "$lib/data/user/UserRepository";
	import types from "$lib/types";
	import { userStore } from "$lib/store/store";
	import { goto } from "$app/navigation";
	import type UserDto from "$lib/dtos/user/UserDto";

	const userRepository = container.get<UserRepository>(types.userRepository);

	async function handleSubmit(event: Event) {
		event.preventDefault();
		const user = await userRepository.registerUser(username);
		login(user);
	}

	function login(user: UserDto) {
		userStore.set(user);
		goto("/boards");
	}

	let users: Promise<UserDto[]> = userRepository.getAllUsers();
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
		<div class="flex flex-col gap-4 front-themed max-w-[1000px] rounded p-2 grow">
			<h2 class="text-3xl border-b-2 border-gray-400">New here?</h2>
			<form on:submit={handleSubmit} class="flex flex-col gap-2">
				<input class="p-2 card-themed rounded" type="text" bind:value={username} placeholder="Who are you?">
				<button class="p-2 bg-purple-900 rounded" type="submit">Register</button>
			</form>
			{#await users}
				<p>Loading...</p>
			{:then users}
				{#if users.length > 0}
					<h2 class="text-3xl border-b-2 border-gray-400">Already registered:</h2>
					<ul class="flex flex-col gap-1">
						{#each users as user}
							<li class="flex card-themed rounded">
								<button class="grow p-2 text-left" on:click={() => login(user)}>
									{user.username}
								</button>
							</li>
						{/each}
					</ul>
				{:else}
					<p>No users found</p>
				{/if}
			{:catch error}
				<p>Error while retrieving users</p>
			{/await}
		</div>
	</div>
</main>
