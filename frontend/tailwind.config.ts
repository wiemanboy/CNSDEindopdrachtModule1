import type { Config } from "tailwindcss";

export default {
	content: ["./src/**/*.{html,js,svelte,ts}"],
	theme: {
		extend: {
			colors: {
				link: {
					DEFAULT: "#0070f3",
					hover: "#0366d6",
				},
				primary: {
					light: {
						DEFAULT: "#fff",
					},
					dark: {
						DEFAULT: "#222",
					},
				},
				secondary: {
					light: {
						DEFAULT: "#eee",
					},
					dark: {
						DEFAULT: "#333",
					},
				},
				tertiary: {
					light: {
						DEFAULT: "#eee",
					},
					dark: {
						DEFAULT: "#111",
					},
				},
				subtle: {
					light: {
						DEFAULT: "#697979",
					},
					dark: {
						DEFAULT: "#9c9c9c",
					},
				},
			},
			keyframes: {
				fadeIn: {
					"0%": {
						opacity: "0",
					},
					"100%": {
						opacity: "1",
					},
				},
				fromRight: {
					"0%": {
						transform: "translateX(5%)",
					},
					"100%": {
						transform: "translateX(0)",
					},
				},
				fromTop: {
					"0%": {
						transform: "translateY(-5%)",
					},
					"100%": {
						transform: "translateY(0)",
					},
				},
				wiggle: {
					"0%, 100%": {
						transform: "rotate(-5deg)",
					},
					"50%": {
						transform: "rotate(5deg)",
					},
				},
				popoutWiggle: {
					"0%, 100%": {
						transform: "scale(1) rotate(0deg)",
					},
					"25%, 75%": {
						transform: "scale(1.2) rotate(-5deg)",
					},
					"50%": {
						transform: "scale(1.2) rotate(5deg)",
					},
				},
				popIn: {
					"0%": {
						opacity: "0",
						transform: "translateY(50%)",
					},
					"100%": {
						opacity: "1",
						transform: "translateY(0)",
					},
				},
			},
			animation: {
				fadein: "fadeIn 0.7s ease-in-out",
			},
		},
	},
	plugins: [],
} satisfies Config;
