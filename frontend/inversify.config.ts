import "reflect-metadata";
import { Container } from "inversify";
import types from "$lib/types";
import FetchApiClient from "$lib/network/FetchApiClient";
import { PUBLIC_API_BASE_URL } from "$env/static/public";

const container = new Container();
container.bind(types.apiClient).toConstantValue(new FetchApiClient(PUBLIC_API_BASE_URL));

export { container };
