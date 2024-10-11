import {writable} from "svelte/store";
import type UserDto from "$lib/dtos/user/UserDto";
import type BoardDto from "$lib/dtos/board/BoardDto";

export const userStore = writable<UserDto>();
export const boardStore = writable<BoardDto>();