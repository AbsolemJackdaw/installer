package installer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Gui_2 extends JPanel
implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2278553001071268710L;
	JButton go;
	JButton stop;

	JTextField text;

	JFileChooser chooser;
	String choosertitle;

	String absolPathMod;
	String absolPathBUp;

	Font font = new Font("Arial", Font.BOLD, 14);
	public Gui_2(String path1, String path2) {
		absolPathMod = path1;
		absolPathBUp = path2;

		text = new JTextField("Please Choose an Installation Path");
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
				chooser.setCurrentDirectory(new File(System.getProperty("user.home") +"/AppData/Roaming/.minecraft"));
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
							new MainClass(absolPathMod, absolPathBUp, 
									chooser.getSelectedFile().getPath()+"/");
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
}