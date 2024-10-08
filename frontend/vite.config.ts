import {sveltekit} from "@sveltejs/kit/vite";
import Icons from "unplugin-icons/vite";
import {configDefaults, coverageConfigDefaults, defineConfig} from "vitest/config";

export default defineConfig({
	plugins: [sveltekit(), Icons({ compiler: "svelte", autoInstall: true })],
	test: {
		include: ["tests/**/*.{test,spec}.{js,ts}"],
		exclude: [...configDefaults.exclude, "tests/**/*.{e2e,integration}.{test,spec}.{js,ts}"],
		coverage: {
			reportsDirectory: "test-results/coverage",
			exclude: [...coverageConfigDefaults.exclude, "**/*.config.{js,ts}", "**/i18n.ts"],
		},
	},
	server: {
		fs: {
			allow: ["./locales", "./inversify.config.ts"],
		},
	},
});
