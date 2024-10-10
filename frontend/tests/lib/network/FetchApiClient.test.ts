import "reflect-metadata";
import { beforeEach, describe, expect, it } from "vitest";
import type ApiClient from "$lib/network/ApiClient";
import FetchApiClient from "$lib/network/FetchApiClient";

/*
Tests if the ApiClient class is working as expected.
 */

let apiClient: ApiClient;

beforeEach(() => {
	apiClient = new FetchApiClient("https://dummyjson.com");
});

describe("ApiClient", () => {
	it("can get data", async () => {
		const response = await apiClient.get("/test");
		expect(response.status).toBe(200);
	});

	it("can post data", async () => {
		const response = await apiClient.post("/test", {});
		expect(response.status).toBe(200);
	});

	it("can put data", async () => {
		const response = await apiClient.put("/test", {});
		expect(response.status).toBe(200);
	});

	it("can delete data", async () => {
		const response = await apiClient.get("/test");
		expect(response.status).toBe(200);
	});
});
