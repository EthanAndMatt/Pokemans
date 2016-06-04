import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphicPanel extends JPanel {
	private String ip = "";
	private String port = "";
	private JTextField txt_ip = new javax.swing.JTextField(8);
	private JTextField txt_port = new javax.swing.JTextField(8);
	private JTextArea txa_info = new JTextArea(10,10);
	private JButton but_conn = new javax.swing.JButton();
	private Client client;

	public GraphicPanel(Client c) {
		this.client = c;
		this.setPreferredSize(new Dimension(600,450));
	}

	public void initializeIPP(){
		setName("Pokemon Testbench");
		txt_ip.setLocation(100, 100);
		add(txt_ip);
		txt_port.setLocation(200, 100);
		add(txt_port);
		
		but_conn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				port = txt_port.getText();
				ip = txt_ip.getText();
				txa_info.append("Connecting to "+ip+":"+port+"...");
				client.connect(ip,port);
			}
		});
		
		add(but_conn);
		
		add(txa_info);
	}

	public void Error_usage(){

	}

	public String getIP(){
		return ip;
	}

	public String getPort(){
		return port;
	}

	public void print(String string) {
		txa_info.append(string);
	}
}
