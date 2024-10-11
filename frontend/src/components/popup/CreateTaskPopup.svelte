<!--
CreateTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";

	export let boardId: string;
	export let taskListId: string;
	export let close: () => void;
	export let createTask: (boardId: string, taskListId: string, title: string, description: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await createTask(boardId, taskListId, title, description);
		close();
	}

	let title = "Task";
	let description = "Task Description";
</script>

<Popup title="CreateTaskList" {close}>
	<form  on:submit={submit}>
		<div>
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
			<input class="card-themed" type="text" id="description" name="description" bind:value={description}/>
		</div>
		<div>
			<button type="submit">Create Task List</button>
		</div>
	</form>
</Popup>