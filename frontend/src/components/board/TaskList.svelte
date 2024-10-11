<!--
TaskList

-->

<script lang="ts">
	import Task from "./Task.svelte";
	import type TaskListDto from "$lib/dtos/board/TaskListDto.js";

	export let taskListDto: TaskListDto;
	export let createTask: (taskListId: string) => void;
	export let addTag: (taskId: string) => void;
	export let addCollaboratorToTask: (taskId: string) => void;
	export let editTask: (taskId: string) => void;
	export let removeTag: (taskId: string, tagId: string) => void;
	export let moveTask: (taskId: string) => void;
</script>

<div class="flex flex-col grow front-themed p-2 rounded gap-2">
	<div class="flex border-b pb-2 border-gray-400">
		<h3 class="text-3xl">{taskListDto.title}</h3>
		<button class="ml-auto bg-purple-900 rounded p-2" on:click={() => createTask(taskListDto.id)}>Create Task
		</button>
	</div>
	<div class="flex flex-col gap-2">
		{#each taskListDto.tasks as task}
			<Task taskDto="{task}" {addTag} {addCollaboratorToTask} {removeTag} {editTask} {moveTask}/>
		{/each}
	</div>
</div>