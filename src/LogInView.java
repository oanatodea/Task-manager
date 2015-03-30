import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

	import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.LayoutQueue;


public class LogInView extends JFrame{

		JLabel emaillbl = new JLabel("Email");
		JTextField emailtxt = new JTextField();
		JLabel cnplbl = new JLabel("CNP");
		JPasswordField cnptxtField = new JPasswordField();
		JButton login = new JButton("Log in");
		
		public LogInView(){
			
			this.setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocation(300, 200);
			this.setTitle("Log in");
			
			cnptxtField.setEchoChar('*');
			
			
			GridBagLayout cc = new GridBagLayout();
			cc.columnWidths=new int[]{150,250};
			cc.rowHeights=new int[]{40,40,40};
			
			this.setLayout(cc);
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.insets= new Insets(5, 5, 5, 5);
			c.gridwidth=1;
			
			c.gridx =0;
			c.gridy=0;
			this.add(emaillbl,c);
			c.gridx =1;
			c.gridy=0;
			this.add(emailtxt,c);
			c.gridx =0;
			c.gridy=1;
			this.add(cnplbl,c);
			c.gridx =1;
			c.gridy=1;
			this.add(cnptxtField,c);
			c.gridx =1;
			c.gridy=2;
			this.add(login,c);
			
			this.pack();
			
		}
		
		public void addLogInListener(MouseListener l){
			login.addMouseListener(l);
		}

		public String getEmail() {
			return emailtxt.getText();
		}

		public String getCnp() {
			return cnptxtField.getText();
		}

		public JButton getLogin() {
			return login;
		}
		
}
