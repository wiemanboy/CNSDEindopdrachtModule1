<!--
Task

-->

<script lang="ts">
	import type TaskDto from "$lib/dtos/board/TaskDto";
	import Tag from "./Tag.svelte";

	export let taskDto: TaskDto;
	export let addTag: (Task: TaskDto) => void;
	export let addCollaboratorToTask: (task: TaskDto) => void;
	export let editTask: (task: TaskDto) => void;
	export let removeTag: (taskId: string, tagId: string) => void;
	export let moveTask: (task: TaskDto) => void;
</script>

<div class="card-themed p-2 rounded flex flex-col gap-2">
	<div class="flex gap-2">
		<h4 class="text-xl">{taskDto.title}</h4>
		<button class="ml-auto bg-purple-900 rounded p-2" on:click={() => addCollaboratorToTask(taskDto)}>Add Collaborator</button>
		<button class=" bg-purple-900 rounded p-2" on:click={() => addTag(taskDto)}>Add Tag</button>
		<button class="bg-purple-900 rounded p-2" on:click={() => editTask(taskDto)}>Edit Task</button>
		<button class="bg-purple-900 rounded p-2" on:click={() => moveTask(taskDto)}>Move Task</button>
	</div>
	<div>
		<p>{taskDto.description}</p>
	</div>
	<ul class="flex gap-2">
		{#each taskDto.tags as tag}
			<li>
				<Tag taskId="{taskDto.id}" tagDto="{tag}" {removeTag}/>
			</li>
		{/each}
	</ul>
</div>