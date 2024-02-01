export const randomQuotes= () => {
    const quotes = [
        "Fall in love with the process, not just the result.",
        "Be the reason someone smiles today.",
        "There's magic in a good night's sleep.",
        "The only way to do great work is to love what you do.",
        "The mind is everything. What you think you become.",
        "The only source of knowledge is experience.",
        "A journey of a thousand miles begins with a single step.",
        "Never regret anything that made you smile.",
        "Life is too short for bad coffee and uninspired dreams.",
        "Fear makes you small, hope makes you fly."
    ]

    const idx = Math.floor(Math.random() * quotes.length);
    return quotes[idx];
}