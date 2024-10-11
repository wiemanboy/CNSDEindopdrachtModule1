<!--
CreateTaskListPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";

	export let boardId: string;
	export let close: () => void;
	export let createTaskList: (boardId: string, title: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await createTaskList(boardId, title);
		close();
	}

	let title = "Task List";
</script>

<Popup title="Create Task List" {close}>
	<form class="flex flex-col gap-2" on:submit={submit}>
		<div class="flex flex-col">
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
		</div>
		<div>
			<button class="bg-purple-900 p-2" type="submit">Create Task List</button>
		</div>
	</form>
</Popup>