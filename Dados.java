




import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.Container;




public class Dados {
    JFrame fran1=new JFrame();
    JPanel pan1=new JPanel();
    JLabel AlbCodigo=new JLabel("Codigo");
    JLabel AlbNome=new JLabel("Nome");
    JLabel AlbIdade=new JLabel("Idade");
    JLabel AlbMorada=new JLabel("Morada");
    JTextField txtC=new JTextField("");
    JTextField txtN=new JTextField("");
    JTextField txtI=new JTextField("");
    JTextField txtM=new JTextField("");
    JButton butPrencher=new JButton("Preencher");
    JButton butApagar=new JButton("Apagar");
  JButton butLimpar=new JButton("Limpar");
  JButton butProcura=new JButton("Procurar");
 JButton butNova=new JButton("Nova Tela"); 
    //Como criar Listas
    String colunas[]={"Codigo","Nome","Idade","Morada"};
    Object Dados[][];
DefaultTableModel modelo=new DefaultTableModel(Dados,colunas);
    JTable tabela=new JTable(modelo);
    JScrollPane scrol=new JScrollPane(tabela);// Cria um scrol na area da lista
   Container Container=fran1.getContentPane();
   public Dados(){
    pan1.setLayout(null);
    Container.add(pan1);
    pan1.add(AlbCodigo);
    pan1.add(AlbNome);
    pan1.add(AlbMorada);
    pan1.add(AlbIdade);
    pan1.add(txtC);
    pan1.add(txtN);
    pan1.add(txtI);
    pan1.add(txtM);
    pan1.add(scrol);
    pan1.add(butPrencher);
    pan1.add(butApagar);
    pan1.add(butLimpar);
    pan1.add(butProcura);
    pan1.add(butNova);
    
    fran1.setVisible(true);
    fran1.setSize(400,400);
    fran1.setTitle("Janela");


    AlbCodigo.setBounds(20,30,100,25); txtC.setBounds(140,30,320,25);
    AlbNome.setBounds(20,60,100,25);  txtN.setBounds(140,60,320,25);
    AlbMorada.setBounds(20,90,100,25); txtM.setBounds(140,90,320,25); 
    AlbIdade.setBounds(20,120,100,25); txtI.setBounds(140,120, 320,25);
    scrol.setBounds(60,200,270,320);
   butPrencher.setBounds( 20,560,320,25);
   butApagar.setBounds(20,590,320,25);
   butLimpar.setBounds(20,620,320,25);
   butProcura.setBounds(20,650,320,25);
  butNova.setBounds(20, 680,320,25);
  ActivarEventos();


   }
   //Funcao que activa eventos
   public void ActivarEventos(){
    butPrencher.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Preencher();
            }
        }
    );
    butApagar.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Apagar();
            }
        }
    );
    butLimpar.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Limpar(); 
            }
        }
    );
    butProcura.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Procurar();
            }
        }
    );
    butNova.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Dados();
            }
        }
    );

   }
   public void Limpar(){
    txtC.setText("");
    txtN.setText("");
    txtM.setText("");
    txtI.setText("");
   }
   public void Procurar(){
    String cod=txtC.getText();
    String CodigoTabela="";
   boolean y=false;
   for(int i=0; i<modelo.getRowCount();i++){
    CodigoTabela=(String) modelo.getValueAt(i, 0);
    if(cod.equals(CodigoTabela)){
        
        JOptionPane.showMessageDialog(null,"fmfk");
    }
   }
   }
   //Funcao que Apaga a linha na Tabela
   public void Apagar(){
    String cod=txtC.getText();
    String CodigoTabela="";
    boolean y=false;
    for(int i=0;i<modelo.getRowCount();i++){ 
        CodigoTabela=(String)modelo.getValueAt(i, 0);
        if(cod.equals(CodigoTabela)){
            modelo.removeRow(i);
            JOptionPane.showMessageDialog(null,"Removido com sucesso");
            
            y=true;
            break;
        }
    
    
        
    }
    if(y==false){
        JOptionPane.showMessageDialog(null,"Nao exise alguem com esse codigo digite outro");
    }

    }

   
   public void Preencher(){
    String cod=txtC.getText();
    String CodigoTabela="";
    boolean y=false;
    for(int i=0; i<modelo.getRowCount(); i++){
        CodigoTabela=(String) modelo.getValueAt(i, 0);
        if(CodigoTabela.equals(cod)){
            y=true;
            break;

        }

    }
    if(y==false){
        String k[]=new String[4];
        k[0]=txtC.getText();
        k[1]=txtN.getText();
        k[2]=txtI.getText();
        k[3]=txtM.getText();
        modelo.addRow(k);
        JOptionPane.showMessageDialog(null,"Individuo adicionado com sucesso");
    }
    else{
        JOptionPane.showMessageDialog(null,"Nao foi possivel ,Porfavor digite o outro codigo");
    }


}
   public static void main(String[]args){
    new Dados();
   }


}




