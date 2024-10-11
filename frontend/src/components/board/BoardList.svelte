<!--
BoardList

-->

<script lang="ts">
    import { container } from "$lib/index";
    import types from "$lib/types";
    import type BoardRepository from "$lib/data/board/BoardRepository";
    import type BoardDto from "$lib/dtos/board/BoardDto";

    const boardsRepository: BoardRepository = container.get(types.boardRepository)

    const boards:Promise<BoardDto[]> = boardsRepository.getBoards()

</script>

<div>
    {#await boards}
        <p>Waiting for boards</p>
    {:then boardList}
        <ul class="flex flex-col gap-2">
            {#each boardList as board}
                <li class="flex">
                    <a class="grow card-themed rounded p-2" href={`/board?id=${board.id}`}>{board.title}</a>
                </li>
            {/each}
        </ul>
    {:catch error}
        <p>An error has occurred while fetching board</p>
    {/await}
</div>