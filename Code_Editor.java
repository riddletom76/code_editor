import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Code_Editor extends JFrame{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//private JPanel jpnl1,jpnl2;
private int count =1;
private MenuBar jmb;
private Menu file,edit,about;
private MenuItem cut,copy,paste,selectall,open,new_tab,save,close_tab,abt;
private RSyntaxTextArea jtx= new RSyntaxTextArea(20, 60);
private RTextScrollPane jsc = new RTextScrollPane(jtx);
private JTabbedPane jtb = new JTabbedPane();
RSyntaxTextArea jtx1;


public Code_Editor(){
	super("Code Editor");
	setmenu();
	setMenuBar(jmb);
//jpnl1.setPreferredSize(new Dimension(5,20));
//jtx.setBackground(Color.BLACK);
//jtx.setForeground(Color.CYAN);
//jsc.add(jtx);
	jtx.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
	jtx.setCodeFoldingEnabled(true);
	jtb.add("tab1",jsc);

	getContentPane().add(jtb);
}
void setmenu(){
	
	file = new Menu("File");
	edit = new Menu("Edit");
	about = new Menu("About");
	cut = new MenuItem("Cut");
	copy = new MenuItem("Copy");
	paste = new MenuItem("Paste");
	selectall = new MenuItem("Selectall");
	//n = new MenuItem("New");
	open = new MenuItem("Open");
	new_tab = new MenuItem("New Tab");
	save =  new MenuItem("Save");
	close_tab = new MenuItem("Close");
	abt = new MenuItem("sofware");

	cut.addActionListener(new B());
	copy.addActionListener(new B());
	paste.addActionListener(new B());
	selectall.addActionListener(new B());
	open.addActionListener(new C());
	new_tab.addActionListener(new D());
	save.addActionListener(new E());
	close_tab.addActionListener(new F());
	abt.addActionListener(new G());
	
	
	jmb = new MenuBar();
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	edit.add(selectall);
	//file.add(n);
	file.add(open);
	file.add(new_tab);
	file.add(save);
	about.add(abt);
	//file.add(close_tab); closing the current tab
	
	jmb.add(file);
	jmb.add(edit);
	jmb.add(about);
}

public class B implements ActionListener{
public void actionPerformed(ActionEvent e){
if(e.getSource()==cut){
jtx.cut();
}
if(e.getSource()==copy){
jtx.copy();
}
if(e.getSource()==paste){
jtx.paste();
}
if(e.getSource()==selectall){
jtx.selectAll();
}
}
}
private class C implements ActionListener{
public void actionPerformed(ActionEvent e){
if(e.getSource()==open){
openFile();
}
}
}
private class D implements ActionListener{
public void actionPerformed(ActionEvent e){
if(e.getSource()==new_tab){
     jtx1= new RSyntaxTextArea(20,60);
    RTextScrollPane jsc1 = new RTextScrollPane(jtx1);
    jtx1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
	jtx1.setCodeFoldingEnabled(true);
//jtx1.setBackground(Color.BLACK);
//jtx1.setForeground(Color.CYAN);

String ntab = "tab"+(count+1);

jtb.addTab(ntab,jsc1);
count++;
}
}
}
private class E implements ActionListener{
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==save){
        	SaveAs();    
        }
    }
    
}

private class F implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==close_tab){
			//jtb.removeTabAt(count); method to be used?
		}
	}
}

private class G implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==abt){
			JOptionPane.showMessageDialog(jsc, "Guided by Kaushik Sir"+"\n"+"Done by Rishabh and Ravi",
				    "Java Swing Project",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

void openFile(){  
JFileChooser fc=new JFileChooser();  
int i=fc.showOpenDialog(this);  
         
if(i==JFileChooser.APPROVE_OPTION){  
File f=fc.getSelectedFile(); 
String fn = f.getName();
String filepath=f.getPath(); 
int index=jtb.getSelectedIndex();
jtb.setTitleAt(index, fn);
//public void setTitleAt(int jtb.getSelectedIndex(), String filepath);          
displayContent(filepath);  
             
}  
         
}  
 
void displayContent(String fpath){  
try{  
BufferedReader br=new BufferedReader(new FileReader(fpath));  
String s1="",s2="";  
             
while((s1=br.readLine())!=null){  
s2+=s1+"\n";  
}  
//System.out.println(s2);
int index=jtb.getSelectedIndex();
if(index==0)
jtx.setText(s2);  
else{ jtx1.setText(s2);System.out.println("**** "+s2);}
br.close();  
}catch (Exception e) {e.printStackTrace();  }  
} 

public void SaveAs() {
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
    final JFileChooser saveAsFileChooser = new JFileChooser();
    saveAsFileChooser.setApproveButtonText("Save");
    saveAsFileChooser.setFileFilter(extensionFilter);
    int actionDialog = saveAsFileChooser.showOpenDialog(this);
    if (actionDialog != JFileChooser.APPROVE_OPTION) {
       return;
    }
    File file = saveAsFileChooser.getSelectedFile();
    if (!file.getName().endsWith(".txt")) {
       file = new File(file.getAbsolutePath());
    }

    BufferedWriter outFile = null;
    try {
       outFile = new BufferedWriter(new FileWriter(file));

       jtx.write(outFile);

    } catch (IOException ex) {
       ex.printStackTrace();
    } finally {
       if (outFile != null) {
          try {
             outFile.close();
          } catch (IOException e) {}
       }
    }
 }


public static void main(String args[]){
Code_Editor p = new Code_Editor();
p.setVisible(true);
p.setSize(400,400);
//p.iterate();
p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
