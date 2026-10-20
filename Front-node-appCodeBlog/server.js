const express = require("express");
const path = require("path");

const app = express();
const PORT = 3000;

// Arquivos estáticos
app.use(express.static(path.join(__dirname, "src/static")));
app.use(express.static(path.join(__dirname, "src")));

// Página inicial
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "src", "index.html"));
});

app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});