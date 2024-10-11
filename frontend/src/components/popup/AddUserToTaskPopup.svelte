<!--
CreateUserPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";
	import type UserDto from "$lib/dtos/user/UserDto";

	export let boardId: string;
	export let taskId: string;
	export let close: () => void;
	export let addUser: (boardId: string, taskId: string, userId: string) => any;
	export let users: UserDto[];

	async function submit(userId: string) {
		await addUser(boardId, taskId, userId);
		close();
	}
</script>

<Popup title="Add collaborator" {close}>
	<div class="flex flex-col gap-2">
		<ul class="flex flex-col gap-2 mt-2">
			{#each users as user}
				<li>
					<button class="grow" on:click={() => submit(user.id)}>{user.username}</button>
				</li>
			{/each}
		</ul>
	</div>
</Popup>