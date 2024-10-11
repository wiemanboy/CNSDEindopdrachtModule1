<!--
TaskList

-->

<script lang="ts">
	import Task from "./Task.svelte";
	import type TaskListDto from "$lib/dtos/board/TaskListDto.js";

	export let taskListDto: TaskListDto;
	export let createTask: () => void;
	export let addTag: () => void;
</script>

<div class="flex flex-col grow front-themed p-2 rounded gap-2">
	<div class="flex border-b pb-2 border-gray-400">
		<h3 class="text-3xl">{taskListDto.title}</h3>
		<button class="ml-auto bg-purple-900 rounded p-2" on:click={createTask}>Create Task</button>
	</div>
	<div class="flex flex-col gap-2">
		{#each taskListDto.tasks as task}
			<Task taskDto="{task}" {addTag} />
		{/each}
	</div>
</div>