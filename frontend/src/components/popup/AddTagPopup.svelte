<!--
CreateTaskPopup

-->

<script lang="ts">
	import Popup from "./Popup.svelte";
	import type TagDto from "$lib/dtos/board/TagDto";

	export let boardId: string;
	export let taskId: string;
	export let close: () => void;
	export let addTag: (boardId: string, taskId: string, tagId: string) => any;
	export let tags: TagDto[];

	async function submit(tagId: string) {
		await addTag(boardId, taskId, tagId);
		close();
	}
</script>

<Popup title="CreateTaskList" {close}>
	<div class="flex flex-col gap-2">
		<ul class="flex flex-col gap-2 mt-2">
			{#each tags as tag}
				<li style="background-color: {tag.color}">
					<button class="grow" on:click={() => submit(tag.id)}>{tag.name}</button>
				</li>
			{/each}
		</ul>
	</div>
</Popup>