export default interface ApiClient {
	get(url: string, queryParams?: Record<string, any>): Promise<Response>;

	post(url: string, data?: unknown, queryParams?: Record<string, any>): Promise<Response>;

	put(url: string, data?: unknown, queryParams?: Record<string, any>): Promise<Response>;

	delete(url: string, queryParams?: Record<string, any>): Promise<Response>;
}
