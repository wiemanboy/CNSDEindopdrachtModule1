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

<Popup title="CreateTaskList" {close}>
	<form  on:submit={submit}>
		<div>
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
		</div>
		<div>
			<button type="submit">Create Task List</button>
		</div>
	</form>
</Popup>