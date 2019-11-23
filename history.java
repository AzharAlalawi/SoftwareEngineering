import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
  
public class history extends JFrame
{
   public history() {
      super("Previously Searched Keywords");
  
      Object[][] tabledata = {
            { "Happy","Positive", new Integer(150),"AA-Ron", "View" },
            { "Sad", "Negative", new Integer(107), "Aditya", "View" },
      };
  
      String columnheaders[] = { "Keyword", "Result", "Count", "User", "View" };
  
      JTable table = new JTable(tabledata, columnheaders);
      Font g = new Font("Arial", Font.PLAIN, 16);
      table.setFont(g);
      JScrollPane scrollPane = new JScrollPane(table);
      
      //SET CUSTOM RENDERER TO TEAMS COLUMN
      table.getColumnModel().getColumn(4).setCellRenderer((TableCellRenderer) new ButtonRenderer());;

      //SET CUSTOM EDITOR TO TEAMS COLUMN
      table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
      // set font for headers
      Font f = new Font("Arial", Font.BOLD, 20);
      JTableHeader header = table.getTableHeader();
      
      header.setFont(f);
 
      getContentPane().add(scrollPane);
  
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent we) {
            System.exit(0);
         }
      });
  
      pack();
   }
  
   public static void main(String []args) {
      history main = new history();
      main.show();
   }
}
//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer
{

//CONSTRUCTOR
public ButtonRenderer() {
  //SET BUTTON PROPERTIES
  setOpaque(true);
}
@Override
public Component getTableCellRendererComponent(JTable table, Object obj,
    boolean selected, boolean focused, int row, int col) {

  //SET PASSED OBJECT AS BUTTON TEXT
    setText((obj==null) ? "":obj.toString());

  return this;
}

}
//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor
{
protected JButton btn;
 private String lbl;
 private Boolean clicked;

 public ButtonEditor(JTextField txt) {
  super(txt);

  btn=new JButton();
  btn.setOpaque(true);

  //WHEN BUTTON IS CLICKED
  btn.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {

      fireEditingStopped();
    }
  });
}

 //OVERRIDE A COUPLE OF METHODS
 @Override
public Component getTableCellEditorComponent(JTable table, Object obj,
    boolean selected, int row, int col) {

    //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
   lbl=(obj==null) ? "":obj.toString();
   btn.setText(lbl);
   clicked=true;
  return btn;
}

//IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
 @Override
public Object getCellEditorValue() {

   if(clicked)
    {
    //SHOW US SOME MESSAGE
      JOptionPane.showMessageDialog(btn, lbl+" has been clicked");
    }
  //SET IT TO FALSE NOW THAT ITS CLICKED
  clicked=false;
  return new String(lbl);
}

 @Override
public boolean stopCellEditing() {

       //SET CLICKED TO FALSE FIRST
    clicked=false;
  return super.stopCellEditing();
}

 @Override
protected void fireEditingStopped() {
  super.fireEditingStopped();
}
}
