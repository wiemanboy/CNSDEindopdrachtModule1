import "reflect-metadata";
import { Container } from "inversify";
import types from "$lib/types";
import FetchApiClient from "$lib/network/FetchApiClient";
import { PUBLIC_API_BASE_URL } from "$env/static/public";
import ApiBoardRepository from "$lib/data/board/ApiBoardRepository";
import ApiTagRepository from "$lib/data/board/ApiTagRepository";
import ApiUserRepository from "$lib/data/user/ApiUserRepository";

const container = new Container();
container.bind(types.apiClient).toConstantValue(new FetchApiClient(PUBLIC_API_BASE_URL));

container.bind(types.boardRepository).toConstantValue(new ApiBoardRepository(container.get(types.apiClient)));
container.bind(types.tagRepository).toConstantValue(new ApiTagRepository(container.get(types.apiClient)));
container.bind(types.userRepository).toConstantValue(new ApiUserRepository(container.get(types.apiClient)));

export { container };
