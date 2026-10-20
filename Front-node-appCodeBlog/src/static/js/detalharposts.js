async function carregarDadosPost() {
  // pega os dados do localStorage
  const dadosSalvos = localStorage.getItem('dadosdetalhespost');
 
  if (dadosSalvos) {
    // Converte a string de volta para um objeto JavaScript
    const dados = JSON.parse(dadosSalvos);
 
    exibirdadoshtml(dados);
  }
}

async function exibirdadoshtml(dados){
 
    const detalhepost = document.getElementById("iddetalhespost");
        const h1 = document.createElement("h1");
        const p =  document.createElement("p");
        const spanautor =  document.createElement("span");  
        const spandata = document.createElement("span");
        const ptextopost = document.createElement("p");
        const spantexto = document.createElement("span");
        const spantitulo = document.createElement("span");
        const tagiperson = document.createElement("i");
        const tagidate = document.createElement("i");
        const tagbr = document.createElement("br");
        const tagsection = document.createElement("section");
       
        h1.setAttribute("class", 'card-title');
        h1.setAttribute("style", 'font-weight: bold; margin-bottom: 20px;');
        spantitulo.textContent = dados.titulo;
        h1.appendChild(spantitulo);
       
        // AUTOR E DATA
        p.setAttribute("class", 'card-subtitle mb-2 text-muted');
 
        tagiperson.setAttribute("class", 'material-icons icone-person');
        tagiperson.textContent = "person_outline";
        spanautor.innerText = dados.autor;
       
 
        tagidate.setAttribute("class", 'material-icons');
        tagidate.textContent = "date_range";
        spandata.textContent = dados.data;
 
        p.appendChild(tagiperson);
        p.appendChild(spanautor);
        p.appendChild(tagbr);
        p.appendChild(tagidate);
        p.appendChild(spandata);
       
        // TEXTO DO POST
        ptextopost.setAttribute("class", 'card-text');
        ptextopost.setAttribute("style", 'margin-top: 40px;');
        spantexto.innerHTML = dados.texto;
        ptextopost.appendChild(spantexto);
        tagsection.appendChild(ptextopost);
        detalhepost.appendChild(h1);
        detalhepost.appendChild(p);
        detalhepost.appendChild(tagsection);
}
 
carregarDadosPost();