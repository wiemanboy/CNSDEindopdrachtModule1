<!--
CreateTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";

	export let close: () => void;
	export let createTask: (title: string, description: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await createTask(title, description);
		close();
	}

	let title = "Task";
	let description = "Task Description";
</script>

<Popup title="Create Task" {close}>
	<form class="flex flex-col gap-2" on:submit={submit}>
		<div class="flex flex-col">
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
			<label for="description">Description</label>
			<textarea class="card-themed" id="description" name="description" bind:value={description}/>
		</div>
		<div>
			<button class="bg-purple-900 p-2" type="submit">Create Task List</button>
		</div>
	</form>
</Popup>