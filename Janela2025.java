import javax.swing.*;
import java.awt.event.*;
import java.awt.Container;
import java.util.Vector;
import java.io.*;
import java.io.Serializable;


public class Janela2025{
    Vector<Funcionar> x=new Vector<>();
    JFrame fra1=new JFrame();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JLabel AlbCodigo=new JLabel("Codigo");
    JLabel AblNome=new JLabel("Nome");
    JLabel AblSexo=new JLabel("Sexo");
    JLabel AblOs=new JLabel("OBservacao");//Caixa de Obscervacao
    JTextField txtC=new JTextField("");
    JTextField tctN=new JTextField("");
    String lista[]={"Femenino", "Masculino","Gay"};
    JComboBox cobSexo=new JComboBox<>(lista);
    JTextArea txtArea =new JTextArea("");
    JScrollPane scrol=new JScrollPane(txtArea);
    JRadioButton radCasado=new JRadioButton("Casado");
    JRadioButton radSolteiro=new JRadioButton("Solteiro");
    ButtonGroup g=new ButtonGroup();

    JButton butResgistar=new JButton("Resgistar");
    JButton butActualizar=new JButton("Actualizar");
    JButton butProcurar=new JButton("Procurar");
    JButton butApagar=new JButton("Apagar");
    JButton butFicheiro=new JButton("Exportar FicheiTexto");
    JButton butClose=new JButton("Close");
    JButton butLimapr=new JButton("Limpar Formulario");

    Container contador=fra1.getContentPane();

    public Janela2025(){
       
        pan1.setLayout(null);
        pan2.setLayout(null);
        contador.add(pan1);
        pan1.add(AlbCodigo);
        pan1.add(AblNome);
        pan1.add(AblSexo);
        pan1.add(AblOs);
        pan1.add(txtC);
        pan1.add(tctN);
        pan1.add(cobSexo);
        pan1.add(scrol);
        pan1.add(pan2);
        pan2.add(radCasado);
        pan2.add(radSolteiro);       
        g.add(radCasado);  g.add(radSolteiro);  // Agrupamento dos butoes de selecao 
        pan2.setBorder(BorderFactory.createTitledBorder("Estado Civil"));

        pan1.add(butResgistar);
        pan1.add(butApagar);
        pan1.add(butActualizar);
        pan1.add(butProcurar);
        pan1.add(butFicheiro);
         pan1.add(butClose);
           pan1.add(butLimapr);

           AlbCodigo.setBounds(20,30,100,25);
           txtC.setBounds(140,30,200,25);
           tctN.setBounds(140,70,200,25);
           AblNome.setBounds(20,70,100,25);
           pan2.setBounds(20,110,320,100);
           radCasado.setBounds(20,20,120,25);
           radSolteiro.setBounds(20,60,120,25);
            AblSexo.setBounds(20,230,120,25); cobSexo.setBounds(140,230,200,25);
           AblOs.setBounds(20,255,320,100);
           scrol.setBounds(20,310,320,100); 
           butResgistar.setBounds(20,430,320,25);
           butActualizar.setBounds(20,470,320,25);
           butProcurar.setBounds(20,510,320,25);
           butApagar.setBounds(20,550,320,25);
           butFicheiro.setBounds(20,590,320,25);
           butClose.setBounds(20,630,320,25);
           butLimapr.setBounds(20,670,320,25);
           fra1.setSize(500,750);
           fra1.setVisible(true);
           fra1.setTitle("Funcionario");
           activarEventos();
           x=(Vector<Funcionar>) OF.leObject("DAT.F1");
           if(x==null){
            x=new Vector<>();
           }
       


    }
public void activarEventos(){
    butResgistar.addActionListener(
        new ActionListener() {
         public void actionPerformed(ActionEvent e){
            Resgistar();
         }   
        }
    );
    butActualizar.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Actualizar();
            }
        }
    );
    butProcurar.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                procurar();
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
    butLimapr.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e){
         Limpar();
            }
        }
    );
   butFicheiro.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
      
      
            }
        }
    );
    butClose.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fra1.dispose();
            }
        }
    );


}
public void Resgistar(){
Funcionar f=new Funcionar();
f.codigo=Long.parseLong(txtC.getText());
long cod=Long.parseLong(txtC.getText());
f.nome=tctN.getText();
if(radCasado.isSelected()){
    f.EstadoCivil=" Casado";
}
else{
    f.EstadoCivil="Solteiro";
}
    f.obs=txtArea.getText();
    f.sexo=(String) cobSexo.getSelectedItem();


boolean y=false;
for(int i=0; i<x.size(); i++){
    if(x.get(i).codigo==cod){
        y=true;
        break;
    }
}
if(y==false){
  
    x.addElement(f);
    OF.guardarObjecto(x, "DAT.F1");
    JOptionPane.showMessageDialog(null, "Funcionario adcionado com sucesso");
   
}
else{
    JOptionPane.showMessageDialog(null,"Nao foi possivel adicionar O codigo ja e existente");
}

}

public void Actualizar(){
    Funcionar a=new Funcionar();
 a.codigo= Long.parseLong(txtC.getText());
 a.nome=tctN.getText();
 boolean y=false;int index=-1;
 for(int i=0; i<x.size(); i++){   
    if(x.get(i).codigo==a.codigo){

    index=i;
      
        y=true;
        break;
    }
  }
    if(y==true){
      
        x.setElementAt(a, index);
        OF.guardarObjecto(x, "DAT.F1");
      
        JOptionPane.showMessageDialog(null,"Funcionario Actualizado com sucesoo");
    }
    else{
        JOptionPane.showMessageDialog( null, "Nao existe um funcionario com esse codigo porfavor digite outro");
    }

    }
  
    public void procurar(){
        Funcionar f=new Funcionar();
        long cod=Long.parseLong(txtC.getText());
          tctN.setText("");
              cobSexo.setSelectedIndex(-1);
              radCasado.setSelected(false);
              radSolteiro.setSelected(false);;  
              for(int i=0; i<x.size(); i++){
                if(x.get(i).codigo==cod){

              
       tctN.setText(x.get(i).nome);
     
   
        cobSexo.setSelectedItem(x.get(i).sexo);
        txtArea.setText(x.get(i).obs);
 
      if(x.get(i).EstadoCivil.equals("Casado")) {
        radCasado.setSelected(true);
        OF.guardarObjecto(x, "DAT.F1");
        
      }else{
        radSolteiro.setSelected(true); break;
      
      }
      
    }
              
}
 
 }
    
    
   public void Apagar(){
    Estudante f=new Estudante();
 long cod=Long.parseLong(txtC.getText());
 
    boolean y=false; int index=-1;
 for(int i=0; i<x.size();i++){
    if(x.get(i).codigo==cod){
        y=true;
        index=i;
      
      
        break;

    }
 }
 if(y==true){
    txtC.setText("");
    tctN.setText("");
    x.removeElementAt(index);
    OF.guardarObjecto(x, "DAT.F1");
    JOptionPane.showMessageDialog(null," Funcionario removido com sucesso ");
 }
 else{
    JOptionPane.showMessageDialog(null," Nao existe um funcionario com este codigo ");
   

 }
}





public void Limpar(){
    txtC.setText("");
    tctN.setText("");
    txtArea.setText("");
}


 public static void main(String[]args){
  new Janela2025();
 }
}
class Funcionar implements  Serializable{

    public long codigo;
    public String nome;
    public String EstadoCivil;
    public String sexo;
    public String obs;
}




    
