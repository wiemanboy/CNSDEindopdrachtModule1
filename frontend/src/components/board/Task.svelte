<!--
Task

-->

<script lang="ts">
	import type TaskDto from "$lib/dtos/board/TaskDto";
	import Tag from "./Tag.svelte";

	export let taskDto: TaskDto;
	export let addTag: (TaskId: string) => void;
	export let addCollaboratorToTask: (taskId: string) => void;
</script>

<div class="card-themed p-2 rounded flex flex-col gap-2">
	<div class="flex gap-2">
		<h4 class="text-xl">{taskDto.title}</h4>
		<button class="ml-auto bg-purple-900 rounded p-2" on:click={() => addCollaboratorToTask(taskDto.id)}>Add Collaborator</button>
		<button class=" bg-purple-900 rounded p-2" on:click={() => addTag(taskDto.id)}>Add Tag</button>
	</div>
	<div>
		<p>{taskDto.description}</p>
	</div>
	<ul class="flex gap-2">
		{#each taskDto.tags as tag}
			<li>
				<Tag tagDto="{tag}" />
			</li>
		{/each}
	</ul>
</div>