
import java.awt.event.*;
import java.io.Serializable;
import java.awt.Container;
import javax.swing.*;
import java.util.Vector;
import java.io.*;


public class Yasser2{
    Vector <Estudante> x=new Vector<>();
   JFrame fra1=new JFrame();
   JPanel pan1=new JPanel();
   JLabel AlbC=new JLabel("Codigo");
   JLabel AlbN=new JLabel("Nome");
   JLabel AlbM=new JLabel("Morada");
   JLabel AblI=new JLabel("Idade");
   JTextField txtC=new JTextField("");
   JTextField txtN=new JTextField("");
   JTextField txtM=new JTextField("");
    JTextField txtI=new JTextField("");
    JButton butResgistrar=new JButton("Resgistar");
    JButton butProcurar=new JButton("Procurar");
    JButton butActualizar=new JButton("Actualizar");
    JButton butLimMenu=new JButton("Apagar");
    JButton butLimpaFormulario=new JButton("Limpar Formulario");
    JButton butClose=new JButton("Close");
    JButton butExite=new JButton("Exit");
    Container container=fra1.getContentPane();

  public Yasser2(){
    pan1.setLayout(null);
    container.add(pan1);
    pan1.add(AlbC);
    pan1.add(AlbN);
    pan1.add(AlbM);
    pan1.add(AblI);
    pan1.add(txtC);
    pan1.add(txtN);
    pan1.add(txtM);
    pan1.add(txtI);
    pan1.add(butResgistrar);
    pan1.add(butProcurar);
    pan1.add(butActualizar);
    pan1.add(butLimMenu);
    pan1.add(butLimpaFormulario);
    pan1.add(butClose);
    pan1.add(butExite);
    AlbC.setBounds(20,30,100,25); txtC.setBounds(140,30,100,25);
    AlbN.setBounds(20,70,100,25);  txtN.setBounds(140,70,100,25);
    AlbM.setBounds(20,110,100,25); txtM.setBounds(140,110,100,25);
    AblI.setBounds(20,150,100,25); txtI.setBounds(140,150,100,25);
    butResgistrar.setBounds(20,270,220,25);
    butProcurar.setBounds(20,310,220,25); 
    butActualizar.setBounds(20,350,220,25);
    butLimMenu.setBounds(20,390,220, 25);
    butLimpaFormulario.setBounds(20,430,220,25);
    butClose.setBounds(20,470,220,25);
    butExite.setBounds(20,510,220,25);
fra1.setTitle("Cadastro"); fra1.setVisible(true);
fra1.setSize(600,600);
fra1.setResizable(true);
ActivarEventos();
x=(Vector<Estudante>) FI.leObject("TT.DAT");
if(x==null){
    x=new Vector<>();
}

}
public void ActivarEventos(){
    butResgistrar.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Registar();
            }
        }
    );
    butProcurar.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Procurar();
            }
        }
    );
    butLimMenu.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Apagar();
            }
            
        }
    );
    butLimpaFormulario.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LimparFormulario();
            }
        }
    );
    butClose.addActionListener(
        new ActionListener() {
           public void actionPerformed(ActionEvent e){
            fra1.dispose();
           } 
        }
    );butActualizar.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e){
            Actualizar();
          } 
        }
    );
    butExite.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);

            }
        }
    );
}
public void Actualizar(){
    Estudante a=new Estudante();

    a.codigo=Long.parseLong(txtC.getText());
    a.nome=txtN.getText();
    a.Morada=txtM.getText();
    a.Idade=Integer.parseInt(txtI.getText());
    
    boolean y=false; int index=-1;
    for(int i=0; i<x.size();i++){
        if(x.get(i).codigo==a.codigo){
            
            y=true; 
        index=i;
            break;
        }
    }
    if(y==true){
        FI.guardarObjecto(x, "TT.DAT");
        x.setElementAt(a, index);
       
        JOptionPane.showMessageDialog( null," Actualizacao Feita com Sucesso");
    }
    else{
        JOptionPane.showMessageDialog( null,"Nao foi possivel adicionar");
    }

}

 



public void LimparFormulario(){
    
    txtC.setText("");
   txtM.setText("");
    txtN.setText("");
    txtI.setText("");

}
public void Apagar(){
long cod=Long.parseLong(txtC.getText());
for(int i=0; i<x.size();i++){
    
    boolean y=false;
    if(x.get(i).codigo==cod){
     
       
        txtC.setText("");
        txtN.setText("");
        txtM.setText("");
        txtI.setText("");
        x.removeElementAt(i);
     JOptionPane.showMessageDialog(null,"Estudante removido com sucesso"+x.size());
        y=true;
        break;
    }
    else{
    JOptionPane.showMessageDialog( null, "Nao existe o estudante com esse codigo porfavor digite outro");
    }
    

}




}
public double media(){
    double a=3, b=4;
    return a+b;
}
// Funcao que faz a procura do estudante
public void Procurar(){
long cod=Long.parseLong(txtC.getText());
txtN.setText("");
txtM.setText("");
txtI.setText("");
boolean y=false;

for(int i=0; i<x.size(); i++){
    if(x.get(i).codigo==cod){
        txtN.setText(x.get(i).nome);
        txtM.setText(x.get(i).Morada);
        txtI.setText(x.get(i).Idade+"");
        FI.guardarObjecto(x, "TT.DAT");
        txtM.setText(x.get(i).Morada);
        y=true;
        JOptionPane.showMessageDialog(null, "Procura feita com sucesso. ");
if(y==false){
    JOptionPane.showConfirmDialog(null,"Codigo nao existente digite outro");
    }}}
}
public static void main(String[]args){
    new Yasser2();
}
// Funcao que resgista O estudante
public void Registar(){
    Estudante a=new Estudante();

    a.codigo=Long.parseLong(txtC.getText());
    a.nome=txtN.getText();
    a.Morada=txtM.getText();
    a.Idade=Integer.parseInt(txtI.getText());
    
    boolean y=false;
    for(int i=0; i<x.size();i++){
        if(x.get(i).codigo==a.codigo){
            y=true;
        
            break;
        }
    }
    if(y==false){
        x.addElement(a);
        FI.guardarObjecto(x, "TT.DAT");
        txtC.setText("");
        txtN.setText("");
        txtM.setText("");
        txtI.setText("");
        JOptionPane.showMessageDialog( null,"Feito com Sucesso");
    }
    else{
        JOptionPane.showMessageDialog( null,"Nao foi possivel adicionar o estudante  porque eles tem o mesmo codigo");
    }

}

}


class Estudante implements Serializable{
    public long codigo;
    public String nome;
    public String Morada;
    public int Idade;

    public Estudante(){
       this.codigo=0;
       this.nome="--";
       this.Morada="0--";
       this.Idade=0;

        
    }

}
