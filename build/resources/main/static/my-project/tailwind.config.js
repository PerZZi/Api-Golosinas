/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors:{
        morado: "#2a1250"
      },
      fontFamily: {
        'catseye': ['catseye', 'sans-serif'],
      },
    },
  },
  plugins: [],
}

