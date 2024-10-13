<!--
CreateTaskListPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";

	export let close: () => void;
	export let createBoard: (title: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await createBoard(title);
		close();
	}

	let title = "New Board";
</script>

<Popup title="Create Board" {close}>
	<form class="flex flex-col gap-2" on:submit={submit}>
		<div class="flex flex-col">
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
		</div>
		<div>
			<button class="bg-purple-900 p-2" type="submit">Create Board</button>
		</div>
	</form>
</Popup>