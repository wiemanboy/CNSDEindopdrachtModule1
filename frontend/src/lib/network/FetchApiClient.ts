import { injectable } from "inversify";
import type ApiClient from "$lib/network/ApiClient";

@injectable()
class FetchApiClient implements ApiClient {
	public readonly baseUrl: string;

	constructor(baseUrl: string) {
		this.baseUrl = baseUrl;
	}

	get(url: string, queryParams?: Record<string, any>): Promise<Response> {
		return fetch(`${this.baseUrl}${url}${this.buildQueryString(queryParams)}`);
	}

	post(url: string, data?: unknown, queryParams?: Record<string, any>): Promise<Response> {
		return fetch(`${this.baseUrl}${url}${this.buildQueryString(queryParams)}`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		});
	}

	put(url: string, data?: unknown, queryParams?: Record<string, any>): Promise<Response> {
		return fetch(`${this.baseUrl}${url}${this.buildQueryString(queryParams)}`, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		});
	}

	delete(url: string, queryParams?: Record<string, any>): Promise<Response> {
		return fetch(`${this.baseUrl}${url}${this.buildQueryString(queryParams)}`, {
			method: "DELETE",
		});
	}

	private buildQueryString(queryParams?: Record<string, string>): string {
		if (!queryParams) {
			return "";
		}
		const queryString = new URLSearchParams(queryParams).toString();
		return queryString ? `?${queryString}` : "";
	}
}

export default FetchApiClient;
