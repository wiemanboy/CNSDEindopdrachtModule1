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
	let color = "#ff0000";
</script>

<Popup title="Create Tag" {close}>
	<form class="flex flex-col gap-2" on:submit={submit}>
		<div class="flex flex-col">
			<label for="title">Title</label>
			<input class="card-themed rounded p-2" type="text" id="title" name="title" bind:value={title}/>
			<label for="color">Color</label>
			<input class="card-themed rounded-xl" type="color" id="color" name="color" bind:value={color}/>
		</div>
			<button class="bg-purple-900 rounded p-2 grow" type="submit">Create Task List</button>
	</form>
</Popup>