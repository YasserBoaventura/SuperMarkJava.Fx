const readline = require('readline');

class Produto {
    constructor(id, nome, quantidade, preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    toString() {
        return `ID: ${this.id} | Nome: ${this.nome} | Quantidade: ${this.quantidade} | Preço: R$${this.preco.toFixed(2)}`;
    }
}
// Classe estoque
class Estoque {
    constructor() {
        this.produtos = [];
        this.proximoId = 1;
       
    }
    //Funcao que que adiciona produtos no estoque

    adicionar(nome, quantidade, preco) {
        const produto = new Produto(this.proximoId++, nome, quantidade, preco);
        this.produtos.push(produto);
        console.log(` Produto "${nome}" adicionado com sucesso.`);
    }
//Funcao que imprime a lista dos produtos no estoque
    listar() {
        if (this.produtos.length ===0) {
            console.log(" Estoque vazio.");
        } else {
            console.log("\n Produtos no estoque:\n");
            this.produtos.forEach(p => console.log(p.toString()));
        }
    }
// Funcao que actualiza os produtos
    atualizar(id, novaQtd) {
        const produto = this.produtos.find(p => p.id === id);
        if (produto) {
            produto.quantidade = novaQtd;
            console.log(` Quantidade de "${produto.nome}" atualizada para ${novaQtd}.`);
        } else {
            console.log(` Produto com ID ${id} não encontrado porfavor digite um id existente.`);
        }
    }
// Funcao que remove os produtos atraves do ID
    remover(id) {
        const index = this.produtos.findIndex(p => p.id === id);
        if (index !== -1) {
            const removido = this.produtos.splice(index, 1)[0];
            console.log(` Produto "${removido.nome}" removido.`);
            
        } else {
            console.log(` Produto com ID ${id} não encontrado.`);
        }
    }
    //Funcao que faz a venda e atualiza a quantidade anterior
vender(id, quantidade){
  const produto= this.produtos.find(p=> p.id=== id);
  if(!produto){
    console.log("Nao existe um produto esse ID");
  }if(produto.quantidade< quantidade){
    console.log(` Estoque insuficiente. Disponível: ${produto.quantidade}`);
  }
  //Quando a venda e feita
  else{
  produto.quantidade-=quantidade;
  const total = produto.preco*quantidade;
  console.log(` Venda realizada: ${quantidade} x "${produto.nome}" = R$${total.toFixed(2)}`);
  }
}

    buscar(nome) {
        const resultados = this.produtos.filter(p => p.nome.toLowerCase().includes(nome.toLowerCase()));
       
        if (resultados.length > 0) {
            console.log("\n  Produtos encontrados:\n");
            resultados.forEach(p => console.log(p.toString()));
        } else {
            console.log(` Nenhum produto com nome "${nome}" encontrado.`);
        }
    }
}

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const estoque = new Estoque();

function menu() {
    console.log(`
==== GERENCIAMENTO DE ESTOQUE ====
1. Adicionar produto
2. Listar produtos
3. Atualizar quantidade
4. Remover produto
5. Buscar produto
6. Vender
7. sair
`);
    rl.question("Escolha uma opção: ", (opcao) => {
        switch (opcao.trim()) {
            case '1':
                rl.question("Nome: ", nome => {
                    rl.question("Quantidade: ", qtd => {
                        rl.question("Preço: ", preco => {
                            estoque.adicionar(nome, parseInt(qtd), parseFloat(preco));
                            menu();
                        });
                    });
                });
                break;
            case '2':
                estoque.listar();
                menu();
                break;
            case '3':
                rl.question("ID do produto: ", id => {
                    rl.question("Nova quantidade: ", novaQtd => {
                        estoque.atualizar(parseInt(id), parseInt(novaQtd));
                        menu();
                    });
                });
                break;
            case '4':
                rl.question("ID do produto a remover: ", id => {
                    estoque.remover(parseInt(id));
                    menu();
                });
                break;
            case '5':
                rl.question("Nome para buscar: ", nome => {
                    estoque.buscar(nome);
                    menu();
                });
                break;
            case '6':
                   rl.question("Digite o ID do produto: ", id => {
                    rl.question("Digite a quandidade que deseja vender ", quantidadeV => {
                        estoque.vender(parseInt(id),parseInt(quantidadeV));
                        menu();
                    });
                   });
                 break;
                    
            case '7':
                console.log(" Encerrando...");
                rl.close();
                break;
            default:
                console.log(" Opção inválida.");
                menu();
        }
    });
}
menu();