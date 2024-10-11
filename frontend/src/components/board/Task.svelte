<!--
Task

-->

<script lang="ts">
	import type TaskDto from "$lib/dtos/board/TaskDto";
	import Tag from "./Tag.svelte";

	export let taskDto: TaskDto;
	export let addTag: () => void;
</script>

<div class="card-themed p-2 rounded">
	<div class="flex">
		<h4 class="text-xl">{taskDto.title}</h4>
		<button class="ml-auto bg-purple-900 rounded p-2" on:click={addTag}>Add Tag</button>
	</div>
	<div>
		<p>{taskDto.description}</p>
	</div>
	<div>
		{#each taskDto.tags as tag}
			<Tag tagDto="{tag}" />
		{/each}
	</div>
</div>