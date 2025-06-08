import java.io.Serializable;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Container;
import java.util.Vector;
import java.io.*;
import java.io.*;

public class OF {
 public static void guardarObjecto(Serializable s, String fich){
try{
    File f=new File(fich);
    FileOutputStream fos=new FileOutputStream(f);
    ObjectOutputStream oos=new ObjectOutputStream(fos);
    oos.writeObject(s);
    oos.close();

} catch(Exception e){
    JOptionPane.showMessageDialog(null, "Erro-Out"+e.getMessage());
}
 }
 public static Object leObject (String fich){
    Object y=null;    
    try{
        File f=new File(fich);
        FileInputStream fis=new FileInputStream(f);
        ObjectInputStream ois=new ObjectInputStream(fis);
        y=(Object) ois.readObject();
        ois.close();
    } catch(Exception e){
        JOptionPane.showMessageDialog(null,  "Erro IN" +e.getMessage());
    }
    return y;
 }
}
