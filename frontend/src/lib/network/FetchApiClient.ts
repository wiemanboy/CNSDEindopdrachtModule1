import { injectable } from "inversify";
import type ApiClient from "$lib/network/ApiClient";

@injectable()
class FetchApiClient implements ApiClient {
	public readonly baseUrl: string;

	constructor(baseUrl: string) {
		this.baseUrl = baseUrl;
	}

	get(url: string): Promise<Response> {
		return fetch(this.baseUrl + url);
	}

	post(url: string, data: unknown): Promise<Response> {
		return fetch(this.baseUrl + url, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		});
	}

	put(url: string, data: unknown): Promise<Response> {
		return fetch(this.baseUrl + url, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		});
	}

	delete(url: string): Promise<Response> {
		return fetch(this.baseUrl + url, {
			method: "DELETE",
		});
	}
}

export default FetchApiClient;
