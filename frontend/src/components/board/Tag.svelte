<!--
Tag

-->

<script lang="ts">
	import type TagDto from "$lib/dtos/board/TagDto";

	export let taskId: string;
	export let tagDto: TagDto;
	export let removeTag: (taskId: string, tagId: string) => void;
</script>


<span class="rounded p-1" style="background-color: {tagDto.color}">{tagDto.name}
	<button class="bg-red-600 px-2 rounded-full" on:click={() => removeTag(taskId, tagDto.id)}>X</button>
</span>
