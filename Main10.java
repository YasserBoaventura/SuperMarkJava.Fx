
 


import java.util.Scanner;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.InputMismatchException;
class Main10 {

    private int codigo;
    private String nome;
    private double test1;
    private double test2;
    
// Costrutor 
    public Main10(int codigo, String nome, double test1, double test2 ){
        this.codigo=codigo;
        this.nome=nome;
        this.test1=test1;
        this.test2=test2;
    }
    //Funcao que calcula a media
    public double media(){
        return (this.test1+this.test2)/2;
    }
    //Funcao que endica a situacao do estudante
    public String Classifica(){
        String y= "Admitido";
        if(media()>=14){
            y= "Dispensado";
        }
        if(media()<10){
            y="Excluido";
        }
        return y;
    }
    //Funcoes getters e setters
    public int getCodigo(){
        return codigo;
    }
    public void setCodigo(int codigo){
        this.codigo=codigo;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public double getTest1(){
        return test1;
    }
    public void setTest1(double test1){
        this.test1=test1;
    }
    public double getTest2(){
        return test2;
    }
    public void setTest2(double test2){
        this.test2=test2;
    }
  

  

}
   
     // Class Estudante
class programax{
    Main10 lista[]=new Main10[5000];
    int size=0;
    
    // Funcao que preenche  
    
    public void preencherLista(){
      Scanner teclado=new Scanner(System.in);
      System.out.println("Deseja inserir quantos estudantes: ");
              size=teclado.nextInt();
              for(int i=0; i<size; i++){
             System.out.println("Digite os dados dos estudantes"+(i+1));
             System.out.println("Codigo: ");
             int codigo=teclado.nextInt();
             System.out.println("Nome: ");
             String nome=teclado.next();
             System.out.println("Teste1: ");
             double test1=teclado.nextInt();
             System.out.println("Teste2: ");
             double test2=teclado.nextInt();
             
             teclado.nextLine();
             lista[i]=new Main10(codigo,nome,test1,test2);
              
        }
    }
    // Funcao que imprime a lista dos dados dos estudantes
    public void imprimirLista(){
        for(int i=0; i<size; i++ ){
        Main10 estudante=lista[i];
        System.out.println("Codigo: "+estudante.getCodigo());
        System.out.println("Nome: "+ estudante.getNome());
        System.out.println("Test1: "+ estudante.getTest1());
        System.out.println("Tes2: "+ estudante.getTest2());
        System.out.println("Media: "+estudante.media());
        System.out.println("Classificacao: "+estudante.Classifica() );
        }
    }
    // Funcao que cria um fichero de texto dos nomes 
    public void NomesTxT(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("TXT.Nomes"))){
            for(int i=0; i<size; i++){
                writer.write(lista[i].getNome());
                writer.newLine();
                 }
            System.out.println("Os nomes estao guardados no ficheiro TXT.Nomes");
        }catch(IOException e){
            System.out.println("Erro ao gravar os nomes no arquivo"+e.getMessage());
            
        }        
    }
    // Funcao que que escreve as medias no Ficheiro
   
    public void MediaFile(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("TXT.Medias"))){

            for(int i=0; i<size; i++){
                Main10 estudante=lista[i];
                writer.write("Nomes: "+estudante.getNome()+"Medias"+ estudante.media()+estudante.Classifica());
                writer.newLine();
            }
            System.out.println("OS nomes e as media estao guardados no Ficheiro TXT.Medias");
            
        }catch(IOException e){
            System.out.println("Erro ao guardar os nomes e as medias no ficheiro"+e.getMessage());
        }
    }
    // Funcao que orcganiza as medias de uma forma decrescente
    public void odenarPorOrdemCrescente(){
        Arrays.sort(lista,0, size,Comparator.comparingDouble(Main10::media));
        
        
    }
    //Funcao que organiza as medias de uma forma decrescente
    public void ordenarPorMediaDecrente(){
        Arrays.sort(lista, 0,size,(e1, e2)->Long.compare(e2.getCodigo(), e1.getCodigo()));
        
            
        }
    //Funcao que apaga o estudante atraz do codigo
    public void ApagarEstudante(){
        Scanner teclado=new Scanner(System.in);
        System.out.println("Por favor digite o codigo que pretende Apagar: ");
        int codigo=teclado.nextInt();
        int index=-1;
        for(int i=0; i<size; i++){
            if(lista[i].getCodigo()==codigo){
                index=i;
         
                break;
               
            }
        }
            if(index !=-1){
            for(int i=index; i<size-1;i++){
                lista[i]=lista[i+1];
                
            }
            lista[size-1]=null;
            size--;
            System.out.println("Estudante Removido com sucesso.");
        }
        else{
            System.out.println("Estudante nao encontrado.");
        }
        
    }
    // Funcao que faz o calculo da percentagem dos excluido
    public double PercentagemExcluido(){
      
        int ExcluidoCount=0;
       for(int i=0; i<size; i++){
        String classificacao =lista[i].Classifica();
        System.out.println("Clasiificacao: "+classificacao);// debug
        if (classificacao != null && classificacao.equals("Excluido")) {
            ExcluidoCount++;

       }
       if(size==0){
     // Ela vai retornar zero se o total for zero
         System.out.println("Nao tem o numero de esturdantes ");
        return 0;
       }
       System.out.println("");

       }
       double percentagem=(ExcluidoCount*100.0)/size;
       System.out.println("A percentagem: "+percentagem);
       
       System.out.println("Excluido: "+ExcluidoCount);
          return percentagem;
     

        
    
}
    //Fumcao que faz o calculo da percentagem dos Adimitidos
    public double PercentagemAdimitido(){
      int AdimitidoCount=0;
      
      for(int i=0; i<size; i++){
        String classificao=lista[i].Classifica();
        
        if(classificao!=null && classificao.equals("Admitido")){
            AdimitidoCount++;
        }
        if(size==0){
            return 0; // nao e possivel calcular a percentagem se for igual a zero;
        }
      }
      System.out.println("Adimitidos: "+AdimitidoCount);
      double percentagem=(AdimitidoCount*100.0)/size;
      System.out.println("Percentagem: "+percentagem);
      return percentagem;
    }
    //Fumcao que faz o calculo da percentagem dos Dispensados
    public double PercentagemDispensado(){
    int DispensadosCount=0;
    for(int i=0; i<size; i++){
        String classifica=lista[i].Classifica();
        System.out.println("Classifica"+classifica);
        if(classifica!= null && classifica.equals("Dispensado")){
            DispensadosCount++;
        }
        if(size==0){
            return 0; // percentagem de 0 nao existe;;
        }
    }
    double percentagem= (DispensadosCount*100.0)/size;
    System.out.println("Percentagem: "+percentagem);
    return percentagem;
    }
    
    // Funcao que retorna o nome do estudante atravez do codigo do parametro 
    public String nomeEstudante(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Digite o codigo: ");
        int codigo=scanner.nextInt();
        boolean y=true;
        for(int i=0; i<size; i++){ 

            String nome ="";
            
            if(lista[i].getCodigo()==codigo){ 
                nome=lista[i].getNome();
               System.out.println("mome: "+nome);
               y=true;//#endregion
            
               

            }
        }
      
            if(y==false){
                System.out.println("Nao existe estudante com esse codigo");
            }   
         
            
           
            
         return null;
    }
    // Funcao que retorna o estudante identificado pelo codigo do parametro
    public Main10 getEstudante(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Digite o codigo de Estudante");
        int codigo=scanner.nextInt();
        for(int i=0; i<size; i++){
            if(lista[i].getCodigo()==codigo){
             String e=lista[i].getNome();
             System.out.println("A Lista dos estudante identificado pelo codigo"+e);
                 
            }
        }
        return null;
        
    }
    // Funcao que mostra os nomes dos disopensados
    
   public String NomesDispensados(){
    for(int i=0; i<size; i++){
        
        String Dispensado=lista[i].Classifica();
        
        if(Dispensado!=null && Dispensado.equals("Dispensado")){
          String nome=lista[i].getNome();
          System.out.println("Dispensados: "+nome);
        }
        
    }
   return null;
   }
   // funcao que imprime os nomes dos adimitido para o exame
   public String nomesExcluidos(){
    for(int i=0; i<size;i++){
        String Excluidos=lista[i].Classifica();
        
        if(Excluidos!=null && Excluidos.equals("Excluido")){
            String nome=lista[i].getNome();
            System.out.println("A lista dos Adimitidos para o exame sao: "+nome);
        }

    }
    return null;
   }
   // funcao que imprime os nomes dos adimitidos
   public String NomesAdimitidos(){
    for(int i=0; i<size; i++){
        String Adimitidos=lista[i].Classifica();
       
        if(Adimitidos!=null && Adimitidos.equals("Admitido")){
            String nome=lista[i].getNome();
            System.out.println("A lista dos nomes dos adimitidos para o exame:  "+nome);
        }
        else{
            System.out.println("Sem estudante Adimitido para o exame");
        }
    }
    return null;

   }
    
  //Funcao que imprime os dados do Estudante atraz do codigo
     public void imprimirNomes(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Digite o codigo do estudante: ");
        int codigo=scanner.nextInt();
        boolean y=false;
         for(int i=0; i<size; i++){  
            if(lista[i].getCodigo()==codigo){
                System.out.println("Nome: "+lista[i].getNome());
                System.out.println("Teste 1: "+lista[i].getTest1());
                System.out.println("Teste2: "+lista[i].getTest2());
                System.out.println("Media: "+lista[i].media());
                System.out.println("Classifacao "+lista[i].Classifica());
              y=true;
              break;
            }
        
            }
            if(y==false){
                System.out.println("Nenhum estudante com este codigo");
                
            }
        }
         
        
    
    // imprime as percentagem dos estudantes adimitido excluido e Dispensado
    public void imprimirPecentagem(){
        
      System.out.println(" A percentagem dos Dispensados e: "+PercentagemDispensado());
      System.out.println("A percentagem dos Excluidos e: "+PercentagemExcluido()); 
      System.out.println("A percentagem dos Adimitidos e: "+PercentagemAdimitido());
    }
    // Funcao do menu do programa
    public void menuPrograma(){
       Scanner teclado=new Scanner(System.in);
       
       while(true){
            System.out.println("\nMenu de Funções:");
            System.out.println("1. Preencher Lista");
            System.out.println("2. Imprimir Lista");
            System.out.println("3. Escrever Nomes em TXT");
            System.out.println("4. Escrever Nomes e Médias em TXT");
            System.out.println("5. Ordenar por Média");
            System.out.println("6. Ordenar por Código");
            System.out.println("7. Apagar Estudante");
            System.out.println("8. Calcular Percentagem de Excluídos");
            System.out.println("9. Calcular Percentagem de Dispensas");
            System.out.println("10. Calcular Percentagem de Admitidos");
            System.out.println("11. Buscar Estudante por Código");
            System.out.println("12. Imprimir Nomes de Estudantes Dispensados");
            System.out.println("13. Imprimir Dados de um Estudante");
            System.out.println("14. Imprimir Percentagens");
            System.out.println("15.Imprimir os nomes dos Excluidos");
            System.out.println("16. Imprimir os nomes dos adimitidos para o exame");
            System.out.println("0. Sair");
            
        
            int opcao=teclado.nextInt();
            switch(opcao){
                case 1:
                     preencherLista();
                    break;
                case 2:
                    imprimirLista();
                    break;
                case 3:
                    NomesTxT();
                    break;
                case 4:
                
                    MediaFile();
                    break;
                case 5:
                    odenarPorOrdemCrescente();
                    break;
                case 6:
                    ordenarPorMediaDecrente();
                    break;
                case 7:
                     ApagarEstudante();
                             break;
                case 8:
                    PercentagemExcluido();
                    break;
                case 9:
                  PercentagemDispensado();
                  break;
                case 10:
                PercentagemAdimitido();break;
                    
                case 11:
                    nomeEstudante();
                    
                    break;
                case 12:
                NomesDispensados();
                    break;
                case 13:
                    imprimirNomes();
                    break;
                case 14:
                    imprimirPecentagem();
                    break;
                    case 15:
                    nomesExcluidos();
                    break;
                    case 16:
                    NomesAdimitidos();
                    break;



                case 0:
                    System.out.println("Saindo.....");
                    System.exit(0);
                    break;
                    default:
                    System.out.println("Por favor Digite uma opco valida.");
                    
                    
                        
                    
          }
            
        }
       
            
       }
    
     public static void main(String []args){
     programax p=new programax();
         p.menuPrograma();
    
     }}
    
     
 


 
 
    



