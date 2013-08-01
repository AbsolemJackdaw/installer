package installer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Gui_1 extends JPanel
implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6449099186717250394L;
	JButton go;
	JButton stop;

	JTextField text;

	JFileChooser chooser;
	String choosertitle;

	Font font = new Font("Arial", Font.BOLD, 14);
	
	static JFrame frame = new JFrame("");
	
	public Gui_1() {

		text = new JTextField("Please Choose Folder to Load mods from");
		text.setFont(font);
		text.setEditable(false);
		this.add(text);

		addButtons();
	}

	private void addButtons(){
		go = new JButton("Continue");
		go.addActionListener(this);
		add(go);

		stop = new JButton("Cancel");
		stop.addActionListener(this);
		add(stop);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){

			JButton jB = (JButton) e.getSource();

			if(jB.getText().equals("Cancel")){
				System.out.println("Program stopped manually");
				System.exit(0);

			}else{

				chooser = new JFileChooser(); 
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setCurrentDirectory(new File(System.getProperty("user.home") +"/Desktop"));
				chooser.setDialogTitle("Choose a directory to Load mods");
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//    
				if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
					System.out.println("getCurrentDirectory(): " 
							+  chooser.getCurrentDirectory());
					System.out.println("getSelectedFile().getPath : " 
							+  chooser.getSelectedFile().getPath());

					SwingUtilities.invokeLater(new Runnable() {
						public void run() { 
							JFrame fr = new JFrame("");
							Gui_2 panel = new Gui_2(chooser.getSelectedFile().getPath()+"/" ,
									chooser.getCurrentDirectory().getPath()+"/");
							fr.addWindowListener(
									new WindowAdapter() {
										public void windowClosing(WindowEvent e) {
											System.exit(0);
										}
									}
									);
							fr.getContentPane().add(panel,"Center");
							fr.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4, Toolkit.getDefaultToolkit().getScreenSize().height / 4);
							fr.setSize(panel.getPreferredSize());
							fr.setVisible(true);	
							frame.setVisible(false);
						}
					}
							);
				}
				else {
					System.out.println("No Selection ");
				}
			}
		}
	}

	public Dimension getPreferredSize(){
		return new Dimension(350, 100);
	}

	public static void main(String s[]) {
		
		Gui_1 panel = new Gui_1();
		frame.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				}
				);
		frame.getContentPane().add(panel,"Center");
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4, Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
	}
}