package pet.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pet.window.BCrypt;
import pet.window.ConnectionFactory;
import pet.window.MainPage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFundo;
	private JTextField txtLogin;
	private JPasswordField pswSenha;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLogin = new JLabel("Username");
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setFont(new Font("Arial", Font.BOLD, 12));
		lblLogin.setBounds(196, 170, 65, 14);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(196, 195, 190, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		lblSenha = new JLabel("Password");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Arial", Font.BOLD, 12));
		lblSenha.setBounds(196, 224, 65, 14);
		contentPane.add(lblSenha);
		
		pswSenha = new JPasswordField();
		pswSenha.setBounds(196, 249, 190, 20);
		contentPane.add(pswSenha);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/img/logar.png")));
		btnNewButton.setBounds(249, 280, 89, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Pet Shop");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(213, 11, 150, 69);
		contentPane.add(lblNewLabel);
		
		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(249, 314, 89, 23);
		contentPane.add(btnExit);
		
		lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(Login.class.getResource("/img/Cream Brown And Orange Illustration Event Birthday Presentation 2.png")));
		lblFundo.setBounds(0, 0, 584, 436);
		contentPane.add(lblFundo);
		

		
		
	}
	
	//ao clicar no botão vai ser chamado esta ação
	private void login() {
		String username = txtLogin.getText();
		String senha = new String(pswSenha.getPassword());
		
		if(authenticateUser(username, senha)) {
			JOptionPane.showMessageDialog(this, "Bem-vindo " + username + "!");
			//Redirecionar para outra tela
			new MainPage().setVisible(true); //Abre a janela MainPage
			this.dispose(); //Fecha a janela de login
		}else {
			JOptionPane.showMessageDialog(this, "Usuário inválido e/ou senha incorreta!");
		}
		
	}
	
	//Método para autenticar o usuário
	private boolean authenticateUser(String username, String senha) {
		boolean isValid = false;
			
		try {
			Connection conexao = ConnectionFactory.createConnection();
			
			String sql = "select senha from usuarios where username = ?";
			PreparedStatement cmd = conexao.prepareStatement(sql);					
			cmd.setString(1, username);
			
			ResultSet resultado = cmd.executeQuery();
			
			if(resultado.next()) {
				String hashedPassword = resultado.getString("senha");
				if(BCrypt.checkpw(senha, hashedPassword)) {
					isValid = true;
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
		
	}
}
