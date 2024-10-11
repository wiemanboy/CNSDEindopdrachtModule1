<!--
Board

-->

<script lang="ts">
	import type BoardDto from "$lib/dtos/board/BoardDto";
	import TaskList from "./TaskList.svelte";

	export let boardDto: BoardDto;
	export let createTaskList: () => void;
	export let createTask: (taskListId: string) => void;
	export let createTag: () => void;
	export let addTag: (taskId: string) => void;
	export let addCollaborator: () => void;
	export let addCollaboratorToTask: (taskId: string) => void;
</script>

<div class="flex flex-col gap-2 p-2 min-h-screen">
	<div class="flex gap-2">
		<h2 class="text-5xl">{boardDto.title}</h2>
		<button class="ml-auto bg-purple-900 rounded p-2" on:click={createTaskList}>Create Task List</button>
		<button class="bg-purple-900 rounded p-2" on:click={addCollaborator}>Add Collaborator</button>
		<button class="bg-purple-900 rounded p-2" on:click={createTag}>Create Tag</button>
	</div>
	<div class="flex gap-2 grow">
		{#each boardDto.taskLists as taskList}
			<TaskList taskListDto="{taskList}" {createTask} {addTag} {addCollaboratorToTask}/>
		{/each}
	</div>
</div>