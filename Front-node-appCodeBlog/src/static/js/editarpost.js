import { conectaAPI } from "./conectaAPI.js";

const formulario = document.querySelector("[data-formulario]");


// Recupera os dados salvos no localStorage
const dadosPost = JSON.parse(
    localStorage.getItem("dadoseditarpost")
);


// Verifica se existe um post selecionado
if (!dadosPost) {

    alert("Nenhum post selecionado para edição.");

    window.location.replace("/index.html");

} else {

    // Preenche título
    document.querySelector("[data-titulo]").value = dadosPost.titulo;

    // Preenche autor
    document.querySelector("[data-autor]").value = dadosPost.autor;

    // Preenche o Summernote
    $("#summernote").summernote(
        "code",
        dadosPost.texto
    );
}


// Envia o formulário
async function editarPosts(evento) {

    evento.preventDefault();

    const titulo = document.querySelector("[data-titulo]").value;
    const autor = document.querySelector("[data-autor]").value;

    // Pega o conteúdo HTML do Summernote
    const mtexto = $("#summernote").summernote("code");


    try {

        await conectaAPI.editaPost(
            dadosPost.id,
            autor,
            titulo,
            mtexto,
            dadosPost.data
        );

        // Remove os dados temporários
        localStorage.removeItem("dadoseditarpost");
        window.location.replace("/index.html");
        alert("Post editado com sucesso!");

    } catch (error) {

        alert(error);

    }
}


formulario.addEventListener(
    "submit",
    evento => editarPosts(evento)
);