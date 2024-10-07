import js from "@eslint/js";
import prettier from "eslint-config-prettier";
import simpleImportSort from "eslint-plugin-simple-import-sort";
import svelte from "eslint-plugin-svelte";
import globals from "globals";
import ts from "typescript-eslint";

/** @type {import("eslint").Linter.FlatConfig[]} */
export default [
	js.configs.recommended,
	...ts.configs.recommended,
	...svelte.configs["flat/recommended"],
	prettier,
	...svelte.configs["flat/prettier"],
	{
		languageOptions: {
			globals: {
				...globals.browser,
				...globals.node,
			},
		},
	},
	{
		plugins: {
			"simple-import-sort": simpleImportSort,
		},
		rules: {
			"simple-import-sort/exports": "error",
			"simple-import-sort/imports": [
				"error",
				{
					// The default grouping, but with no blank lines.
					groups: [["^\\u0000", "^node:", "^@?\\w", "^", "^\\."]],
				},
			],
			"@typescript-eslint/no-unused-vars": [
				"error",
				{
					varsIgnorePattern: "^\\$\\$Props$",
				},
			],
		},
	},
	{
		files: ["**/*.svelte"],
		languageOptions: {
			parserOptions: {
				parser: ts.parser,
			},
		},
	},
	{
		ignores: ["build/", ".svelte-kit/", "dist/"],
	},
];
