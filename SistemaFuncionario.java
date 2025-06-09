import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.util.Vector;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.io.Serializable;


public class SistemaFuncionario {
    Vector<Function> x=new Vector<>();
 JFrame fran1=new JFrame();
 JPanel pan1=new JPanel();
 JPanel pan2=new JPanel();
 JLabel albN=new JLabel("Nome");
 JLabel albC=new JLabel("Codigo");
 JLabel albCargo=new JLabel("Cargo");
 JLabel albSector=new JLabel("Sector");
 JLabel albSalario=new JLabel("Salario");
 JTextField txtN=new JTextField("");
 JTextField txtC=new JTextField("");
 JTextField txtCargo=new JTextField("");
 JTextField txtSector=new JTextField("");
 JTextField txtSalario=new JTextField("");
 JRadioButton radSe=new JRadioButton(" V D");
 ButtonGroup g=new ButtonGroup();
JButton butActualizar=new JButton("Actualizar");
JButton butProcu=new JButton("Procurar");
JButton butApagar=new JButton("Apagar");
JButton butResgitar=new JButton("Registar");
Container container=fran1.getContentPane();

public SistemaFuncionario(){
    Vector <Function> x=new Vector<>();
    pan1.setLayout(null);
    pan2.setLayout(null);
    container.add(pan1);  
  
    pan1.add(albN);
    pan1.add(albC);
    pan1.add(albCargo);
    pan1.add(albSector); 
    pan1.add(albSalario);
    pan1.add(txtN);
    pan1.add(txtC);
    pan1.add(txtCargo);
    pan1.add(txtSector);
    pan1.add(txtSalario);
    pan1.add(pan2);
    pan1.add(butActualizar);
    pan1.add(butProcu);
    pan1.add(butApagar);
    pan1.add(butResgitar);
    pan2.add(radSe);
    pan2.setBorder(BorderFactory.createTitledBorder("Visualizar Dados"));
    albN.setBounds(20,30,100,25);  txtN.setBounds(140,30,100,25);
    albC.setBounds(20, 60,100,25);   txtC.setBounds(140,60,100,25);
   albSector.setBounds(20,90,100,25);   txtSector.setBounds(140,90,100,25);
   albCargo.setBounds(20,120,100,25);  txtCargo.setBounds(140,120,100,25);
   albSalario.setBounds(20,150,100,25);  txtSalario.setBounds(140,150,100,25);
   butActualizar.setBounds(20,330,220,25);
   butApagar.setBounds(20,370,220,25);
   butResgitar.setBounds(20,410,220,25);
   butProcu.setBounds(20,450,220,25);
   pan2.setBounds(20,170,130,110);
   radSe.setBounds(20,15,60,90);
     fran1.setVisible(true);
    fran1.setTitle("Yasser");
    fran1.setSize(700,600);          
    fran1.setResizable(true);
    ActivarEventos();  
    x=(Vector<Function>) OF.leObject("F1.DAT");
           if(x==null){
            x=new Vector<>();
        }
}
public void ActivarEventos(){
    butResgitar.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Registar();
            }
            
        }
    );
    butProcu.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                procura(); 
            }
        }
    );
    butActualizar.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Actualizar();
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

}// Funcao que faz registos
public void Registar(){
    Function y=new Function();
    y.codigo=Long.parseLong(txtC.getText());
    y.nome=txtN.getText();
    y.cargo=txtCargo.getText();
    y.sector=txtSector.getText();
    y.salario=Integer.parseInt(txtSalario.getText());
  
    boolean k=false;
  for(int i=0; i<x.size(); i++){
    if(x.get(i).codigo==y.codigo){
  
        k=true;
        JOptionPane.showMessageDialog(null, "Nao foi possivel registar o funcionario");
        break;
    }
    
}// Ele  Adiciona o elemento se k==false
        if(k==false){

       x.addElement(y);
       txtC.setText("");
       txtN.setText("");
       txtCargo.setText("");
       txtSalario.setText("");
       txtSector.setText("");
       OF.guardarObjecto(x, "F1.DAT");
       JOptionPane.showMessageDialog(null," Funcionario adicionado com sucesso ");
       
        }
 }
 // Funcao que actualiza os dados
 public void Actualizar(){
    Function  a=new Function();
    a.codigo=Long.parseLong(txtC.getText());
    a.nome=txtN.getText();
    a.cargo=txtCargo.getText();
    a.sector=txtSector.getText();
    a.salario=Integer.parseInt(txtSalario.getText());
    boolean y=false; int index=-1;
    for(int i=0; i<x.size(); i++){
        if(x.get(i).codigo==a.codigo){
          y=true;
          index=i;
        }
    }
    if(y==true){
        x.setElementAt(a, index);
        JOptionPane.showMessageDialog(null, "Funcionario actualizado com sucesso");
    }
    if(y==false){
        JOptionPane.showMessageDialog(null," Funcionario nao econtrado");
    }
 }
 //Funcao que faz a procura
  public void procura(){
    
    long cod=Long.parseLong(txtC.getText());
    txtCargo.setText("");
    txtN.setText("");
    txtSector.setText("");
    txtSalario.setText("");
    boolean k=false;
    for(int i=0; i<x.size(); i++){
        if(x.get(i).codigo==cod){
        txtCargo.setText(x.get(i).cargo);
        txtN.setText(x.get(i).nome);
        txtSalario.setText(x.get(i).salario+"");
        txtSector.setText(x.get(i).sector);
        OF.guardarObjecto(x, "F1.DAT");
         k=true;
        

            JOptionPane.showMessageDialog(null, "Sucesso");

        }
    }
    if(k==false){
  JOptionPane.showMessageDialog(null,"Funcionario nao encontrado");
    }
  } 

  //Funcao que Apaga o funcionario
  public void Apagar(){
    Function a=new Function();

    long cod=Long.parseLong(txtC.getText());
    boolean y=false;
    for(int i=0; i<x.size(); i++){
        if(x.get(i).codigo==cod){
    x.removeElementAt(i);
    txtC.setText("");
    txtN.setText("");
    txtCargo.setText("");
    txtSector.setText("");
    txtSalario.setText("");
    JOptionPane.showMessageDialog(null, "Funcionario removido com sucesso");
    
    y=true;
    break;
        }

    }if(y==false){
        JOptionPane.showMessageDialog( null, "Nao existe um funcionario com esse codigo ");
    }
  }

       
        
 


//Funcao Main
 public static void main(String[]args){
    new SistemaFuncionario();
}


}

// Class do vector

    
class Function implements Serializable{
    public long codigo;
    public String nome;
    public String sector;
    public String cargo;
    public double salario;

   

    }
   