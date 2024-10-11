<!--
Tag

-->

<script lang="ts">
	import type TagDto from "$lib/dtos/board/TagDto";

	export let tagDto: TagDto;
</script>


<span class="rounded p-1" style="background-color: {tagDto.color}">{tagDto.name}</span>
