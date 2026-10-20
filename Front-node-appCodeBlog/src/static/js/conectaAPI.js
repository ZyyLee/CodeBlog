async function listaPosts() {
    const response = await fetch("http://localhost:8080/api/posts");

    const conexaoconvertida = await response.json();

    return conexaoconvertida;
}


async function criaPosts(autor, titulo, mtexto) {

    const response = await fetch("http://localhost:8080/api/newpost", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            autor: autor,
            titulo: titulo,
            texto: mtexto,
        })
    });

    return response.json();
}


async function editaPost(id, autor, titulo, mtexto, data) {

    const response = await fetch(`http://localhost:8080/api/posts/${id}`, {
        method: "PATCH",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            autor: autor,
            titulo: titulo,
            texto: mtexto,
        })
    });

    if (!response.ok) {
        throw new Error("Erro ao editar o post.");
    }

    return response.json();
}

async function deletaPost(id) {
    const response = await fetch(`http://localhost:8080/api/posts/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("Não foi possivel excluir o post");
    }

}


export const conectaAPI = {
    listaPosts,
    criaPosts,
    editaPost,
    deletaPost
};