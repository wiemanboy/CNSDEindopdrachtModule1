<!--
Popup

-->

<script lang="ts">
	export let title: string;
	export let close: () => void;
</script>

<div class="fixed z-50 top-0 left-0 w-screen h-screen flex justify-center bg-black bg-opacity-50">
	<div class="flex flex-col justify-center">
		<div class="front-themed p-2 rounded">
			<div class="flex">
				<h1 class="text-3xl">{title}</h1>
				<button class="ml-auto" on:click={close}>close</button>
			</div>
			<slot></slot>
		</div>
	</div>
</div>