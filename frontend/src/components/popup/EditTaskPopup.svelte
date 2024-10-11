<!--
CreateTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";
	import type TaskDto from "$lib/dtos/board/TaskDto";

	export let task: TaskDto;
	export let close: () => void;
	export let editTask: (title: string, description: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await editTask(title, description);
		close();
	}

	let title = task.title;
	let description = task.description;
</script>

<Popup title="Edit Task" {close}>
	<form class="flex flex-col gap-2" on:submit={submit}>
		<div class="flex flex-col">
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
			<label for="description">Description</label>
			<textarea class="card-themed" id="description" name="description" bind:value={description}/>
		</div>
		<div>
			<button class="bg-purple-900 p-2" type="submit">Edit Task</button>
		</div>
	</form>
</Popup>