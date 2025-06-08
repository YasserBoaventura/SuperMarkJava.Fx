
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Container;

public class Dados1 {

    JFrame fran1=new JFrame();
    JPanel pan1=new JPanel();
    JLabel AlbCodigo=new JLabel("Codigo");
    JLabel AlbNome=new JLabel("Nome");
    JLabel AlbIdade=new JLabel("Idade");
    JLabel AlbMorada=new JLabel("Morada");
    JTextField txtC=new JTextField("");
    JTextField txtN=new JTextField("");
    JTextField txtM=new JTextField("");
    JTextField txtI=new JTextField("");
    String coluna[]={"Codigo", "Nome", "Morada","Idade"};
    Object dados[][];
    DefaultTableModel modelo=new DefaultTableModel(dados,coluna);
    JTable tabela=new JTable(modelo);
    JScrollPane scrol=new JScrollPane(tabela);
    JButton butPrencher=new JButton("Prencher");
    JButton butApagar=new JButton("Apagar");
  Container conteiner=fran1.getContentPane();

  public Dados1(){
   pan1.setLayout(null);
   conteiner.add(pan1);
   pan1.add(AlbNome); pan1.add(txtN);
   pan1.add(AlbCodigo); pan1.add(txtC);
   pan1.add(AlbIdade); pan1.add(txtI);
   pan1.add(AlbMorada); pan1.add(txtM);
   pan1.add(butPrencher);
   pan1.add(butApagar);
   pan1.add(scrol);
   fran1.setTitle("Dados");
   fran1.setVisible(true);
   fran1.setResizable(true);
   fran1.setSize(600,700);

   AlbCodigo.setBounds(20,30, 100,25);  
   txtC.setBounds(140,30,300,25);
   AlbNome.setBounds(20,70,100,25);
   txtN.setBounds(140,70,300,25);
   AlbIdade.setBounds(20,110,100,25);
   txtI.setBounds(140,110,300,25);
   AlbMorada.setBounds(20,150,100,25);
   txtM.setBounds(140,150,300,25); 
   scrol.setBounds(20,190,420,200);
   butPrencher.setBounds(20,410,420,25);
   butApagar.setBounds(20,450,420,25);
  ActivarEventos();
  }
  public void ActivarEventos(){
    butPrencher.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Registar();
            }
        }
    );
butApagar.addActionListener(
    new ActionListener() {
       public void actionPerformed(ActionEvent e){
        Apagar();

       } 
    }
);
  }
  public void Apagar(){
    String cod=txtC.getText();
    String CodigoTabela="";
    boolean y=false;
    for(int i=0;i<modelo.getRowCount(); i++){
        CodigoTabela=(String) modelo.getValueAt(i, 0);

        if(CodigoTabela.equals(cod)){
            modelo.removeRow(i);
            y=true;
            JOptionPane.showMessageDialog(null,"Removido com sucesso");
        }
        else{
            JOptionPane.showMessageDialog(null,"Nao foi possivel");
        }
    }

  }

  public void Registar(){
    if(txtC==null && txtI==null && txtN==null && txtM==null){
JOptionPane.showMessageDialog(null,"Obrigado preencher todos os dados");
    }else{
    String cod=txtC.getText();
    String codigoTabela="";
    boolean y=false;

    for(int i=0; i<modelo.getRowCount();i++){
        codigoTabela=(String) modelo.getValueAt(i, 0);
        if(codigoTabela.equals(cod)){
           y=true;
           break; 
        }
    }
    if(y==false){
        String k []=new String[4];
        k[0]=txtC.getText();
        k[1]=txtN.getText();
        k[2]=txtI.getText();
        k[3]=txtM.getText();
        modelo.addRow(k);
        JOptionPane.showMessageDialog(null, "Adiconado com sucesso");
    }
    else{
        JOptionPane.showMessageDialog(null,"Nao fo possivel");
    }}
  }
public static void main(String[]args){
    new Dados1();
}



}
