<!--
CreateTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";

	export let boardId: string;
	export let taskId: string;
	export let close: () => void;
	export let addTag: (boardId: string, taskId: string, tagId: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await addTag(boardId, taskId, tagId);
		close();
	}

	let title = "Task List";
	let tagId = "Tag";
</script>

<Popup title="CreateTaskList" {close}>
	<form  on:submit={submit}>
		<div>
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
			<input class="card-themed" type="text" id="description" name="description" bind:value={title}/>
		</div>
		<div>
			<button type="submit">Create Task List</button>
		</div>
	</form>
</Popup>