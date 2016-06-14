import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class SortTable {
  static JDialog dialog;
  static JTable table;
  public static void ShowTable(String name,String[] files){
    dialog = new JDialog();
    dialog.setTitle(name);
    
    DefaultTableModel model = new DefaultTableModel(new Object[] {"Sorted games"},0);
    table = new JTable(model);
    dialog.setLayout(new BorderLayout());
    dialog.add(new JScrollPane(table),BorderLayout.CENTER);
    for (int i = 9999 ; i!=0; i --)
      model.addRow(new Object[]{files[i]});
    dialog.pack();
    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    dialog.setResizable(false);
    dialog.setVisible(true); 
  }
  
  public static void ShowTableStatistic(String name,String[] files){
    dialog = new JDialog();
    dialog.setTitle(name);
    DefaultTableModel model = new DefaultTableModel(new Object[] {"bombs","blocks","enemys"},0);
    table = new JTable(model);
    dialog.setLayout(new BorderLayout());
    dialog.add(new JScrollPane(table),BorderLayout.CENTER);
      model.addRow(new Object[]{files[0],files[1],files[2]});
    dialog.pack();
    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    dialog.setResizable(false);
    dialog.setVisible(true);
  }
}
