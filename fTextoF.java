import java.awt.Container;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;

import javax.swing.*;

public class fTextoF {




 Vector<Cidadao> x=new Vector<>();
    JFrame fran1=new JFrame();
    JPanel pan1=new JPanel();
    JLabel AlbN=new JLabel("Nome");
    JLabel AlbC=new JLabel("Codigo");
    JLabel AlbM=new JLabel("Morada");
    JLabel AlbNC=new JLabel("Numero da casa");
    JTextField txtNC=new JTextField("");
    JTextField txtM=new JTextField("");
    JTextField txtCodigo=new JTextField("");
    JTextField txtNome=new JTextField("");
    JButton butRegistar=new JButton("Regitar no vector");
    JButton butFT=new JButton("FTxT");
    JButton butClose=new JButton("Close");

    Container container=fran1.getContentPane();


    public fTextoF(){
        pan1.setLayout(null);
        container.add(pan1);
        pan1.add(AlbN);
        pan1.add(AlbC);
        pan1.add(txtCodigo);
        pan1.add(txtNome);
        pan1.add(AlbNC);
        pan1.add(txtNC);
        pan1.add(txtM);
        pan1.add(AlbM);
        pan1.add(butFT);
        pan1.add(butRegistar);
        fran1.setVisible(true);
        fran1.setResizable(true);
        fran1.setSize(450,550);
      AlbN.setBounds(20, 30, 120, 25);txtNome.setBounds(140,30,220,25);
      AlbC.setBounds(20,70,120, 25);  txtCodigo.setBounds(140, 70,220,25);
      AlbM.setBounds(20,110, 120,25); txtM.setBounds(140,110,220,25);
      butRegistar.setBounds(120, 330, 220, 25);  
      txtNC.setBounds(140, 150,220,25);
      AlbNC.setBounds(20,150,120,25);
      butFT.setBounds(120,370,220,25);
      ActivarEventos();
    }

    public void ActivarEventos(){
        butRegistar.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
               Regsitar();
                
                }
            }
        );
        butFT.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    salvarEmArquivo();
                }
            }
        );
    }
    public static void main(String[]args){
        new fTextoF();   
    }

    public void Regsitar(){    
    Cidadao C=new Cidadao();
  long cod=Long.parseLong(txtCodigo.getText());
            C.nome=txtNome.getText();
           C.codigo=Long.parseLong(txtCodigo.getText());
           C.NuCasa=Integer.parseInt(txtNC.getText());
           C.Morada=txtM.getText();
           boolean y=false;
           for(int i=0; i<x.size(); i++){
            if(x.get(i).codigo==cod){
                y=true;
                break;
            }
           }
           if(y==false){
            x.addElement(C);
            JOptionPane.showMessageDialog(null, "Sucesso");
           }
        }
        public void salvarEmArquivo() {
            String caminho = System.getProperty("user.home") + "/Desktop/date.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho,true))) {
        
                {
                    writer.write("Nomes: " + txtNome.getText() +
                                 " Codigo: " + txtCodigo.getText() +
                                 " Morada: " + txtM.getText() +
                                 " Numero da Casa: " + txtNC.getText());
                    writer.newLine();
                }

         writer.close();
                JOptionPane.showMessageDialog(null, "Gravados em date.txt");
        
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar: " + e.getMessage());
            }
        }
    }
    



        
    

    class Cidadao{
    public String nome;
    public long codigo;
    public String Morada;
    public int NuCasa;

}

    

