import java.awt.event.*;
import java.awt.Container;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.*;
import java.awt.Container;
import javax.swing.*;
import javax.swing.border.Border;     

/**
 *
 * @author user
 */
public class YasserInter {
    JFrame fran1=new JFrame();
    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JLabel AblA=new JLabel("Valor de A");
    JLabel AblB=new JLabel("Valor de B");
    JLabel AblR=new JLabel("Resultado");
    JTextField txtA=new JTextField("");
    JTextField txtB=new JTextField("");
    JTextField txtR=new JTextField("");
    JRadioButton radS=new JRadioButton("Soma");
    JRadioButton radM=new JRadioButton("Multiplicacao");
    JRadioButton radD=new JRadioButton("Diferenca");
    JRadioButton radDi=new JRadioButton("Divisao");
    JCheckBox ja=new JCheckBox("Calculo Extra");
    JButton butCalcular=new JButton("Calcular");
    JButton butLimpar=new JButton("Limapar");
    JButton butClose=new JButton("Close");
    JButton butNova=new JButton("Nova Janela");
    ButtonGroup g=new ButtonGroup();
    Container container=fran1.getContentPane();

    public YasserInter(){
      pan1.setLayout(null);
      pan2.setLayout(null);
      container.add(pan1);  
      pan1.add(AblA);
      pan1.add(AblB);
      pan1.add(AblR);
      pan1.add(txtA);
      pan1.add(txtB);
      pan1.add(txtR);
      pan1.add(pan2);
      pan2.add(radS);
      pan2.add(radM);
      pan2.add(radD);
      pan2.add(radDi);
      pan2.add(ja);
      pan1.add(butCalcular);
      pan1.add(butLimpar);
      pan1.add(butNova);
      pan1.add(butClose);
      pan2.setBorder(BorderFactory.createTitledBorder("Operacao"));
  AblA.setBounds(20,30,100,25); txtA.setBounds(140,30, 100,25); 
  AblB.setBounds(20,70,100,25); txtB.setBounds(140,70,100,25);
  pan2.setBounds(20,170,220,140);
   radS.setBounds(20,20,120,25);
   radM.setBounds(20,40,120,25);
   radD.setBounds(20,60,120,25);
   radDi.setBounds(20,80,120,25);
   ja.setBounds(20,100,120,25);
   g.add(radS); g.add(radM); g.add(radD); g.add(ja);
   AblR.setBounds(20,110,220,25);
   txtR.setBounds(20,150, 220,25);
   butCalcular.setBounds(20,330,220,25);
   butLimpar.setBounds(20,370,220,25);
   butNova.setBounds(20,410,220,25);
   butClose.setBounds(20,450,220,25);
   fran1.setTitle("Janela"); fran1.setVisible(true);
   fran1.setSize(600,600);
   fran1.setResizable(true);
   ActivarEventos();


    }
    public void ActivarEventos(){
        butCalcular.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e){
            Calcular();
               } 
            }
        );
        butLimpar.addActionListener(
            new ActionListener() {
            public void actionPerformed(ActionEvent e){
            Limpar();
            }    
            }
        );
        butNova.addActionListener(
            new ActionListener() {
            public void actionPerformed(ActionEvent e){
             new YasserInter();
            }    
            }
        );
        butClose.addActionListener(
          new ActionListener() {
            public void actionPerformed(ActionEvent e){
              System.exit(0);
            }
          }
        );
   
    }
    public void Limpar(){
        txtA.setText("");
        txtB.setText("");
        txtR.setText("");

    }
    
    public void Calcular(){
        txtR.setText("");
      double a,b,r;
      a=Double.parseDouble(txtA.getText());
      b=Double.parseDouble(txtB.getText());
      
      if(radS.isSelected()){
        r=a+b;
        txtR.setText(r+"");
      }
      if(radM.isSelected()){
        r=a*b;
        txtR.setText(r+"");
      }
      if(radD.isSelected()){
        r=a-b;
        txtR.setText(r+"");

      }
      if(radDi.isSelected()){
        r=a/b;
        txtR.setText(r+"");
      }
      if(ja.isSelected()){
        r=a*b+a+3;
        txtR.setText(r+"");
      }
    }
    public static void main(String[]args){
        new YasserInter();
    }


}