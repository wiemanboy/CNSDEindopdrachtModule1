<!--
BoardList

-->

<script lang="ts">
    import {container} from '$lib/index';
    import types from '$lib/types';
    import type BoardRepository from '$lib/data/board/BoardRepository';
    import type BoardDto from '$lib/dtos/board/BoardDto';

    const boardsRepository: BoardRepository = container.get(types.boardRepository)

    const boards:Promise<BoardDto[]> = boardsRepository.getBoards()

</script>

<div>
    <h1>Board List</h1>
    {#await boards}
        <p>Waiting for boards</p>
    {:then boardlist}
        <ul>
            {#each boardlist as board}
                <li>
                    <a href="/board?id={board.id}">{board.title}</a>
                </li>
            {/each}
        </ul>
    {:catch error}
        <p>{error.message}</p>
    {/await}
</div>