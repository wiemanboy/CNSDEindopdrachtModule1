<!--
Tag

-->

<script lang="ts">
	import type TagDto from "$lib/dtos/board/TagDto";

	export let tagDto: TagDto;
</script>

<div>
	<span style="background-color: {tagDto.color}">{tagDto.name}</span>
</div>