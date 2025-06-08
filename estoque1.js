class Produto{
    constructor(id, nome,quantidade,preco){
        this.id=id;
        this.nome=nome;
        this.preco=preco;
        this.quantidade= quantidade;
    }
    toString(){
return `ID: ${this.id} ! Nome: ${this.nome} ! Quantidade: ${this.quantidade} Preco: ${this.preco.toFixed(2)}`
    }
}
class Estoque{
    constructor(){
   this.produtos=[];
   this.proximoId=1;     

    }
    adicionar(nome,quantidade, preco){
        const produto= Produto(this.proximoId++,nome,quantidade,preco);
        this.produtos.push(produto);
  console.log(`Produto "${nome}"adicionado com sucesso `);
    }
    //Imprime a lista
listar(){
    if(this.produtos.length===0){
        console.log(`sem produtos no estoque`);
    }
 else{
console.log(`Pordutos no estque `)  
this.produtos.forEach(p=> console.log(p.toString()));
 }
}
// Atualizar
Actualizar(id,novaQtd){
    const produto= this.produtos.find(p=> p.id===id);
    if(produto){
        produto.quantidade=novaQtd;
        console.log(`produto actualizado com sucesso "${produto.nome}" agora o estoque esta com ${novaQtd}.`);

    }
    else{
        console.log(`Nao existe o produto com esse codigo ${id}.`)
    }

    
}
 //Remover
remover(id){
    const index=this.produtos.findIndex(p=> p.id===id);
    if(index!==-1){
        const removido=this.produtos.splice(index,1)[0];
        console.log(`Produto removido com sucesso "${removido.nome}`);
    }
    else{
        console.log(`Nao existe um produto com esse ${id}`);
    }
}
// Vendas
vender(id, quantidade){
    const produto=this.produtos.find(p=> p.id===id);
    if(!produto){
        console.log(`Sem produtos com esse ID`);
    } 
    if(produto.quantidade>quantidade){
        console.log(`Quantidade insuficiente ${produto.quantidade}`);
    }
    else{
        produto.quantidade -=quantidade;
        const total= produto.preco*quantidade;
        console.log(` Venda realizada: ${quantidade} x "${produto.nome}" = R$${total.toFixed(2)}`);
    }
    
}







}