import {conectaAPI} from "./conectaAPI.js";


const formulario = document.querySelector("[data-formulario]");
 
async function criarposts(evento) {
    evento.preventDefault() // cancela o comportamento padrao de um elemento HTML quando ocorre um evento
    // por exemplo o envio do formulãrio, para que os elementos sejam manipulados. é como dizer ao navegador:
    // "Não faça o que você normalmente faria neste evento. Eu vou cuidar disso manualmente.”
 
    const titulo = document.querySelector("[data-titulo]").value;
    const autor = document.querySelector("[data-autor]").value;
    const mtexto = $('#summernote').summernote('code');
   
    try{
        await conectaAPI.criaPosts(autor,titulo,mtexto);
        window.location.href = "../index.html";
        alert("Post publicado com sucesso!")
    }catch(error){
        alert(error);
    };
   
}
formulario.addEventListener("submit", evento => criarposts(evento));