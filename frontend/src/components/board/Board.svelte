<!--
Board

-->

<script lang="ts">
	import type BoardDto from "$lib/dtos/board/BoardDto";
	import TaskList from "./TaskList.svelte";
	import type TaskListDto from "$lib/dtos/board/TaskListDto";
	import type TaskDto from "$lib/dtos/board/TaskDto";

	// This could have been done better by using slots so it doesn't have to hot-potato the functions
	export let boardDto: BoardDto;
	export let createTaskList: () => void;
	export let createTask: (taskList: TaskListDto) => void;
	export let createTag: () => void;
	export let addTag: (task: TaskDto) => void;
	export let addCollaborator: () => void;
	export let addCollaboratorToTask: (task: TaskDto) => void;
	export let editTask: (task: TaskDto) => void;
	export let removeTag: (taskId: string, tagId: string) => void;
	export let moveTask: (task: TaskDto) => void;
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
			<TaskList taskListDto="{taskList}" {createTask} {addTag} {addCollaboratorToTask} {removeTag} {editTask} {moveTask}/>
		{/each}
	</div>
</div>