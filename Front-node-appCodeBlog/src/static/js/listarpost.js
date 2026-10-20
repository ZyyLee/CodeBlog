import { conectaAPI } from "./conectaAPI.js";

async function carregarPosts() {
    const data = await conectaAPI.listaPosts();
    exibirDadoshtml(data);
}

function exibirDadoshtml(data) {

    const listaposts = document.getElementById("postsid");
    data.forEach(function (item) {
        const div = document.createElement("div");
        const p = document.createElement("p");
        const ptextopost = document.createElement("p");
        const spanautor = document.createElement("span");
        const spandata = document.createElement("span");
        const spantitulo = document.createElement("span");
        const spantexto = document.createElement("span");
        const divdeledit = document.createElement("div")
        const spaneditar = document.createElement("span");
        const spandeletar = document.createElement("span");
        const labeleditar = document.createElement("abbr");
        const labeldeletar = document.createElement("abbr");
        const tagieditar = document.createElement("i");
        const tagideletar = document.createElement("i");
        const linktitulo = document.createElement("a");
        const linkeditar = document.createElement("a");
        const linkdeletar = document.createElement("a");
        const h4 = document.createElement("h4");

        //Card post
        div.setAttribute("class", 'card shadow-sm bg-white');
        div.setAttribute("style", 'margin-top: 20px;');

        //Autor e data
        p.setAttribute("class", 'card-subtitle mb-2 text-muted');
        p.setAttribute("style", 'font-size: 0.8rem; padding-top: 10px; padding-left: 20px; padding-right: 20px;')
        spanautor.textContent = item.autor;
        spandata.textContent = " " + item.data;

        //Titulo
        linktitulo.setAttribute("href", '#');
        linktitulo.setAttribute("style", 'text-decoration: none;');
        h4.setAttribute("class", 'card-title');
        h4.setAttribute("style", 'font-weight: bold; color: black; padding-top: 3px; padding-left: 20px;');
        spantitulo.textContent = item.titulo;
        linktitulo.addEventListener("click", () => detalhesPosts(item.id, item.titulo, item.autor, item.data, item.texto));

        //Texto do post
        ptextopost.setAttribute("class", 'card-text');
        ptextopost.setAttribute("style", 'padding-left: 20px; padding-bottom: 10px; padding-right: 20px;');
        spantexto.innerHTML = item.texto;

        // Limita o texto em 100 caracteres e adiciona reticências, se necessário
        if (item.texto.length > 100) {
            spantexto.innerHTML = item.texto.substring(0, 100) + '...';
        }
        else {
            spantexto.innerHTML = item.texto;
        }

        //Icone editar
        labeleditar.setAttribute("title", 'Editando');
        linkeditar.setAttribute("href", '#');
        tagieditar.setAttribute("class", 'material-icons left icone-editar');
        tagieditar.setAttribute("style", 'padding-left: 20px; padding-bottom: 10px;');
        tagieditar.textContent = "edit";

        // Evento do botão editar
        linkeditar.addEventListener("click", (event) => {
            event.preventDefault();
            editarPost(item);
        });

        //Agrupar editar
        labeleditar.appendChild(linkeditar);
        linkeditar.appendChild(tagieditar);
        spaneditar.appendChild(labeleditar);

        //Icone deletar
        labeldeletar.setAttribute("title", 'Deletar');
        linkdeletar.setAttribute("href", '#');
        tagideletar.setAttribute("class", 'material-icons left icone-deletar');
        tagideletar.setAttribute("style", 'padding-left: 20px;');
        tagideletar.textContent = "delete";
        
        //Evento de deletar
        linkdeletar.addEventListener("click", async (evento) => {
            evento.preventDefault();
            const confirmou = window.confirm(`Deseja realmente excluir o post "${item.titulo}"?`);
            if (!confirmou) {
                return;
            }
            try {
                await conectaAPI.deletaPost(item.id);
                div.remove();
            } catch (erro) {
                alert(erro.message);
            }
        });

        //Agrupar deletar
        labeldeletar.appendChild(linkdeletar);
        linkdeletar.appendChild(tagideletar);
        spandeletar.appendChild(labeldeletar);

        //Agrupar delete e editar
        divdeledit.setAttribute("style", "align-items: center;");
        divdeledit.appendChild(spaneditar);
        divdeledit.appendChild(spandeletar);

        //Agrupar publicação
        p.appendChild(spanautor);
        p.appendChild(spandata);
        ptextopost.appendChild(spantexto);
        linktitulo.appendChild(h4);
        h4.appendChild(spantitulo);
        div.appendChild(p);
        div.appendChild(linktitulo);
        div.appendChild(ptextopost);
        div.appendChild(divdeledit);
        listaposts.appendChild(div);
    })

}

async function detalhesPosts(id, titulo, autor, data, texto) {
    //monta os dados para passar para outra pagina
    const dados = {
        id: id,
        titulo: titulo,
        autor: autor,
        data: data,
        texto: texto
    };
    // Salve os dados no localStorage
    localStorage.setItem('dadosdetalhespost', JSON.stringify(dados));

    const detalhepostUrl = "./template/detalhes-post.html";
    // Redireciona para a nova URL
    window.location.href = detalhepostUrl;

}

function editarPost(post) {

    localStorage.setItem(
        "dadoseditarpost",
        JSON.stringify(post)
    );

    window.location.href = "./template/editar-post.html";
}

carregarPosts();