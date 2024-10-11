<!--
CreateTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";


	export let close: () => void;
	export let createTag: (title: string, color: string) => any;

	async function submit(event: Event) {
		event.preventDefault();
		await createTag(title, color);
		close();
	}

	let title = "Tag";
	let color = "#ffffff";
</script>

<Popup title="CreateTaskList" {close}>
	<form  on:submit={submit}>
		<div>
			<label for="title">Title</label>
			<input class="card-themed" type="text" id="title" name="title" bind:value={title}/>
			<input class="card-themed" type="text" id="description" name="description" bind:value={color}/>
		</div>
		<div>
			<button type="submit">Create Task List</button>
		</div>
	</form>
</Popup>