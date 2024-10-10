import { writable } from "svelte/store";
import type UserDto from "$lib/dtos/user/UserDto";

export const userStore = writable<UserDto>();
