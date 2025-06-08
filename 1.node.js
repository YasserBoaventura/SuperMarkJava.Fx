const { copyFileSync } = require('fs');
const { console } = require('inspector');
const readline= require('readline');


// A class dos metodos de modelagem os requisito de um Sistema de Gestao de estoque
// 
class Produto{
    constructor(id, nome,quantidade, preco){
        this.id=id;
        this.nome=nome;
        this.quantidade=quantidade;
        this.preco=preco;
    }
    //paramentro pra Actualizar Quantidade
    atualizarQuantidade(novaQuantidade){
        this.quantidade=novaQuantidade;
    }
    toString(){
    return `ID: ${this.id} | Nome: ${this.nome} | Quantidade: ${this.quantidade} | Preço: R$${this.preco.toFixed(2)}`;
}
}
//Class Estoque

class Estoque{
    constructor(){
        this.produtos=[];
        this.proximold=1;
    }
    //Funcao que Adiciona os produtos
    adicionarProduto(nome, quantidade,preco){
        const produto= new Produto(this.proximold++, nome,quantidade,preco);
        this.produtos.push(produto);
        console.log(`\nProduto "${nome}" adicionado com sucesso\n`);
    }
    //Funcao que remove os produtos atrazes do ID
    removerProduto(id){
        const index=this.produtos.findIndex(p=> p.id ===id);
        if(index!==-1){
            const removido=this.produtos.splice(index,1)[0];
            console.log(`\nProduto "${removido.nome}" removido com sucesso\n`)
            
        }else{
            console.log(`\nProduto com ID ${id} nao encontrado.\n`);  console.log(`\nProduto "${nome}" adicionado com sucesso\n`);
        }
    }

    atualizarQuantidade(id, novaQuantidade){
        const produto = this.produtos.find(p=> p.id ===id);
        if(produto){
            produto.atualizarQuantidade(novaQuantidade);
            console.log(`/nQuantidade do produto"${produto.nome}" atualizada./n`);
        }
        else{
            console.log(`\nProduto com ID ${id} nao encontrado.\n`);
        }
    }

   listarProduto(){
  if(this.produtos.length===0){
    console.log("/nEstoque vazio./n");
  } else{
    console.log("/nProdutos em estoque.");
   this.produtos.forEach(p => console.log(p.toString()));
   console.log("");
  }
  
 }
 buscarProduto(nome){
    const encontrados= this.produtos.filter(p => p.nome.toLowerCase().includes(nome.toLowerCase()));
    if(encontrados.length===0){
        console.log(`\nNenhum produto encontrado com nome"${nome}".\n`);
    }else{
        console.log(`/nProdutos encontrados:/n`);
        encontrados.forEach(p => console.log(p.toString()));

        console.log("");
    }
 }


}
// Interface de linha de comando------
const rl =readline.createInterface({ 
    input: process.stdin,
    output: process.sdout
});
const estoque =new Estoque();

function menu() {
    console.log(`
===== GERENCIAMENTO DE ESTOQUE =====
1. Adicionar produto
2. Remover produto
3. Atualizar quantidade
4. Listar produtos
5. Buscar produto por nome
6. Sair
`);
rl.question("Escolha uma opcao: ",(opcao)=>{
    switch(opcao.trim()){
        case'1':
        rl.question("Nome do produto: ",nome=>{
            rl.question("Quantidade: ",q=>{
                rl.question("Preco: ", p=>{
                    estoque.adicionarProduto(nome,parselInt(q),parseFloat(p));
                    menu();
                });

            });
        });
        break;
        case '2':
            rl.question("ID do podtudo a remover.:",id=>{
                estoque.removerProduto(parseInt(id));
                menu();
            });
            break;
            case '3':
                rl.question("ID do produto: ",id=>{
                    rl.question("Nova Quatidade: ",q =>{
                  estoque.atualizarQuantidade(parseInt(id), parseInt(q));
                  menu();
                    });
                });
                break;
                case '4':
                    estoque.listarProduto();
                    menu();
                    break;
                    case '5':
                        rl.question("Nome do produto a buscar: ",nome =>{
                            estoque.buscarProduto(nome);
                            menu();
                        });
                    break;
                    case '6':
                        console.log("/nSaindo..... Ate mais!");
                        rl.close();
                        break;
                        default:
                            console.log("Digite uma opcaco correcta.");
                            menu();
    }

});

}
menu();