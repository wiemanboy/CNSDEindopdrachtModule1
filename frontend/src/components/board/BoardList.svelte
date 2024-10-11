<!--
BoardList

-->

<script lang="ts">
    import {container} from '$lib/index';
    import types from '$lib/types';
    import type BoardRepository from '$lib/data/board/BoardRepository';
    import type BoardDto from '$lib/dtos/board/BoardDto';
    import {boardStore} from "$lib/store/store";

    const boardsRepository: BoardRepository = container.get(types.boardRepository)

    const boards:Promise<BoardDto[]> = boardsRepository.getBoards()

    async function selectBoard(id: string){
        boardStore.set
    }

</script>

<div>
    <h1>Board List</h1>
    {#await boards}
        <p>Waiting for boards</p>
    {:then boardlist}
        <ul>
            {#each boardlist as board}
                <li>
                    <button on:click={()=>selectBoard(board.id)}>{board.title}</button>
                </li>
            {/each}
        </ul>
    {:catch error}
        <p>An error has occurred during fetching</p>
    {/await}
</div>