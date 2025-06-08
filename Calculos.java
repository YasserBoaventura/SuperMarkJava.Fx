



import java.awt.Container;
import javax.swing.*;
import java.awt.event.*;
public class Calculos {




   JFrame fran1 = new JFrame();
   JPanel pan1 = new JPanel();
   JPanel pan2 = new JPanel();
   JLabel AblA = new JLabel("Valor de A");
   JLabel AblB = new JLabel("Valor de B");
   JLabel AblR = new JLabel("Resultado");
   JTextField txtA = new JTextField("");
   JTextField txtB = new JTextField("");
   JTextField txtR = new JTextField("");
   JRadioButton radS = new JRadioButton("Soma");
   JRadioButton radM = new JRadioButton("Multiplicacao");
   JRadioButton radD = new JRadioButton("Diferenca");
   JRadioButton radDi = new JRadioButton("Divisao");
   JCheckBox ja = new JCheckBox("Calculo Extra");
   JButton butCalcular = new JButton("Calcular");
   JButton butLimpar = new JButton("Limapar");
   JButton butClose = new JButton("Close");
   JButton butNova = new JButton("Nova Janela");
   ButtonGroup g = new ButtonGroup();
   Container container;

   public Calculos (){
      this.container = this.fran1.getContentPane();
      this.pan1.setLayout(null);
      this.pan2.setLayout(null);
      this.container.add(this.pan1);
      this.pan1.add(this.AblA);
      this.pan1.add(this.AblB);
      this.pan1.add(this.AblR);
      this.pan1.add(this.txtA);
      this.pan1.add(this.txtB);
      this.pan1.add(this.txtR);
      this.pan1.add(this.pan2);
      this.pan2.add(this.radS);
      this.pan2.add(this.radM);
      this.pan2.add(this.radD);
      this.pan2.add(this.radDi);
      this.pan2.add(this.ja);
      this.pan1.add(this.butCalcular);
      this.pan1.add(this.butLimpar);
      this.pan1.add(this.butNova);
      this.pan1.add(this.butClose);
      this.pan2.setBorder(BorderFactory.createTitledBorder("Operacao"));
      this.AblA.setBounds(20, 30, 100, 25);
      this.txtA.setBounds(140, 30, 100, 25);
      this.AblB.setBounds(20, 70, 100, 25);
      this.txtB.setBounds(140, 70, 100, 25);
      this.pan2.setBounds(20, 170, 220, 140);
      this.radS.setBounds(20, 20, 120, 25);
      this.radM.setBounds(20, 40, 120, 25);
      this.radD.setBounds(20, 60, 120, 25);
      this.radDi.setBounds(20, 80, 120, 25);
      this.ja.setBounds(20, 100, 120, 25);
      this.g.add(this.radS);
      this.g.add(this.radM);
      this.g.add(this.radD);
      this.g.add(this.ja);
      this.AblR.setBounds(20, 110, 220, 25);
      this.txtR.setBounds(20, 150, 220, 25);
      this.butCalcular.setBounds(20, 330, 220, 25);
      this.butLimpar.setBounds(20, 370, 220, 25);
      this.butNova.setBounds(20, 410, 220, 25);
      this.butClose.setBounds(20, 450, 220, 25);
      this.fran1.setTitle("Janela");
      this.fran1.setVisible(true);
      this.fran1.setSize(600, 600);
      this.fran1.setResizable(true);
      this.ActivarEventos();
   }

   public void ActivarEventos() {
     butCalcular.addActionListener(
        new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Calcular();   
            }
        }
     );
   }

   public void Limpar() {
      this.txtA.setText("");
      this.txtB.setText("");
      this.txtR.setText("");
   }

   public void Calcular() {
      this.txtR.setText("");
      double var1 = Double.parseDouble(this.txtA.getText());
      double var3 = Double.parseDouble(this.txtB.getText());
      double var5;
      if (this.radS.isSelected()) {
         var5 = var1 + var3;
         this.txtR.setText("" + var5);
      }

      if (this.radM.isSelected()) {
         var5 = var1 * var3;
         this.txtR.setText("" + var5);
      }

      if (this.radD.isSelected()) {
         var5 = var1 - var3;
         this.txtR.setText("" + var5);
      }

      if (this.radDi.isSelected()) {
         var5 = var1 / var3;
         this.txtR.setText("" + var5);
      }

      if (this.ja.isSelected()) {
         var5 = var1 * var3 + var1 + 3.0;
         this.txtR.setText("" + var5);
      }

   }

   public static void main(String[] var0) {
      new YasserInter();
   }
}


