<!--
MoveTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";
	import type TaskListDto from "$lib/dtos/board/TaskListDto";

	export let boardId: string;
	export let taskId: string;
	export let taskLists: TaskListDto[];
	export let close: () => void;
	export let moveTask: (boardId: string, taskId: string, taskListId: string) => any;

	async function submit(taskListId: string) {
		await moveTask(boardId, taskListId, taskId);
		close();
	}
</script>

<Popup title="Move Task" {close}>
	<div class="flex flex-col gap-2">
		<ul class="flex flex-col gap-2 mt-2">
			{#each taskLists as taskList}
				<li>
					<button class="grow" on:click={() => submit(taskList.id)}>{taskList.title}</button>
				</li>
			{/each}
		</ul>
	</div>
</Popup>