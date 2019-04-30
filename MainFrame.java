import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{

Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;


 MainFrame()
{
 
 c=getContentPane();
 c.setLayout(new FlowLayout());

 btnAdd = new JButton("Add");
 btnView = new JButton("View");
 btnUpdate = new JButton("Update");
 btnDelete = new JButton("Delete");

 c.add(btnAdd);
 c.add(btnView);
 c.add(btnUpdate);
 c.add(btnDelete);


 btnAdd.addActionListener(new ActionListener()
 {
  public void actionPerformed(ActionEvent ae)
  {
   AddFrame a = new AddFrame();
   dispose();
  }
 });

 btnView.addActionListener(new ActionListener()
 {
  public void actionPerformed(ActionEvent ae)
  {
   ViewFrame a = new ViewFrame();
   dispose();
  }
 });


btnUpdate.addActionListener(new ActionListener()
 {
  public void actionPerformed(ActionEvent ae)
  {
   UpdateFrame a = new UpdateFrame();
   dispose();
  }
 });

btnDelete.addActionListener(new ActionListener()
 {
  public void actionPerformed(ActionEvent ae)
  {
   DeleteFrame a = new DeleteFrame();
   dispose();
  }
 });

 
 setTitle("Employee Management System");
 setSize(500, 150);
 setLocationRelativeTo(null);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setVisible(true);

} //end of constructor

  public static void main(String args[])
  {
   MainFrame m = new MainFrame();
  } //end of main

}//end of class MainFrame


 class DatabaseHandler
{
 static Connection con;
 
 static void getCon()
 {
  
  try
  {
   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

   con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","p123");
  }
  catch(SQLException e)
  {
   JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
  }
 
 }


  public void addEmployee(int id, String name)
 {
  
  getCon();
 
   try
   {
  
    String sql="insert into employee values(?,?)";
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setInt(1,id);
    pst.setString(2,name);
    int r = pst.executeUpdate();
    JOptionPane.showMessageDialog(new JDialog(),r+"records inserted");
   }
   catch(SQLException e)
  {
   JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
  }

 }



 public void updateEmployee(int id, String name)
 {
  
  getCon();
 
   try
   {
  
    String sql="update employee set ename=? where eid=?";
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setInt(2,id);
    pst.setString(1,name);
    int r = pst.executeUpdate();
     
    if(r==0)
     JOptionPane.showMessageDialog(new JDialog(),"no such records found");
     else
      JOptionPane.showMessageDialog(new JDialog(),r+" "+"records updated");
   }
   catch(SQLException e)
  {
   JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
  }

 }




 public void deleteEmployee(int id)
 {
  
  getCon();
 
   try
   {
  
    String sql="delete from employee where eid=?";
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setInt(1,id);
   
    int r = pst.executeUpdate();
     
    if(r==0)
     JOptionPane.showMessageDialog(new JDialog(),"no such records found");
     else
      JOptionPane.showMessageDialog(new JDialog(),r+" "+"records deleted");
   }
   catch(SQLException e)
  {
   JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
  }

 }



   public String viewEmployee()
 {
  
  getCon();
  StringBuffer sb = new StringBuffer();
 
   try
   {
  
    String sql="select * from employee order by eid";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);    

      while(rs.next())
     { 
      int i = rs.getInt(1);
      String n = rs.getString(2);

      sb.append("id:"+i+" Name:"+n+"\n");
     }

   }
   catch(SQLException e)
  {
   JOptionPane.showMessageDialog(new JDialog(),"issue"+e);
  }
    return sb.toString();
 }

}// end of class DataBaseHandler
  
  


















