package fr.unice.polytech.devint.dinstallor.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import fr.unice.polytech.devint.dinstallor.controllers.InstallationController;
import fr.unice.polytech.devint.dinstallor.models.Picture;

public class LicenceView extends InstallationView {

	public LicenceView(InstallationController ic) {
		super(ic);
		
		this.setLayout(new BorderLayout());
		
		JPanel headPanel = new JPanel();
		headPanel.setLayout(new BorderLayout());
		
		headPanel.add((new Picture("." + File.separator + "resources" + File.separator + "logo_devint.png")).getJLabel(), BorderLayout.WEST);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		
		titlePanel.add(Box.createHorizontalGlue());
		
		JLabel title = new JLabel("Licence");
		title.setFont(title.getFont().deriveFont(Font.BOLD));
		title.setFont(title.getFont().deriveFont(Float.parseFloat("25")));
		titlePanel.add(title);
		
		titlePanel.add(Box.createHorizontalGlue());
		
		
		headPanel.add(titlePanel, BorderLayout.CENTER);
		
		this.add(headPanel, BorderLayout.NORTH);
		
		
		JPanel licencePanel = new JPanel();
		licencePanel.setBackground(Color.WHITE);
		
		String licenceContent = "<html>";
		String licenceFile = "." + File.separator + "resources" + File.separator + "licence.txt";
			
		try {
			InputStream ips=new FileInputStream(licenceFile); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine()) != null){
				licenceContent += ligne + "<br/>";
			}
			br.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		licenceContent += "</html>";
		
		JLabel label = new JLabel(licenceContent);
		/*JTextField label = new JTextField(licenceContent);
		label.setEditable(false);*/
//		JPanel panel = new JPanel();
//		panel.add(label);

		licencePanel.add(label);
		
//		JScrollPane s = new JScrollPane(panel);
//		s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane s = new JScrollPane(licencePanel);
		s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
//		licencePanel.add(s);
		this.add(s, BorderLayout.CENTER);
		
		JPanel actions = new JPanel();
		actions.setLayout(new BorderLayout());
		this.add(actions, BorderLayout.SOUTH);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		actions.add(buttons, BorderLayout.EAST);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cancel();
			}
			
		});
		buttons.add(cancelButton);
		
		JButton nextButton = new JButton("Suivant");
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				nextStep();
			}
			
		});
		buttons.add(nextButton);
	}
	
	public void cancel() {
		int n = JOptionPane.showConfirmDialog(
		    this.getController(),
		    "Voulez-vous vraiment quitter l'installation?",
		    "",
		    JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION) {
			this.getController().cancel();
		}
	}
	
	public void nextStep() {
		this.getController().nextStep();
	}
}
