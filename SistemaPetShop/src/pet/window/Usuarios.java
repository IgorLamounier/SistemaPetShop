package pet.window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pswSenha;
	private JPasswordField pswConfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios frame = new Usuarios();
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
	public Usuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/show.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false); // Desativa o redimensionamento da janela
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		lblUsername.setBounds(136, 167, 115, 14);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(262, 164, 265, 20);
		contentPane.add(txtUsername);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSenha.setBounds(136, 206, 115, 14);
		contentPane.add(lblSenha);
		
		pswSenha = new JPasswordField();
		pswSenha.setBounds(261, 203, 265, 20);
		contentPane.add(pswSenha);
		
		JLabel lblConfSenha = new JLabel("Conf. Senha:");
		lblConfSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		lblConfSenha.setBounds(136, 247, 115, 14);
		contentPane.add(lblConfSenha);
		
		pswConfSenha = new JPasswordField();
		pswConfSenha.setBounds(262, 244, 265, 20);
		contentPane.add(pswConfSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Chamar um evento				
				registerUser();
			}
		});
		btnCadastrar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/salvar.png")));
		btnCadastrar.setBounds(249, 347, 134, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/remove.png")));
		btnLimpar.setBounds(393, 347, 134, 23);
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Limpa os campos
		        txtUsername.setText("");
		        pswSenha.setText("");
		        pswConfSenha.setText("");
		    }
		});
		contentPane.add(btnLimpar);

		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBounds(57, 490, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose(); // Fecha a janela atual
        });
	}
	
	private void registerUser() {
		String username = txtUsername.getText();
		String senha = new String(pswSenha.getPassword());
		String confsenha = new String(pswConfSenha.getPassword());
		
		
		if(username.isEmpty() || senha.isEmpty() || confsenha.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");	
		}else {
			if(!senha.equals(confsenha)) {
				JOptionPane.showMessageDialog(this, "As senhas estão incorretas.");
			}
		}
		
		if(addUserDatabase(username, senha, confsenha)) {
			JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
			//Vou trocar
		}
		
	}
	
	private boolean addUserDatabase(String username, String senha, String confsenha) {
		boolean sucess = false;
		
		try {
			if(senha.equals(confsenha)) {//inserir
				Connection conexao = ConnectionFactory.createConnection();
				String sql = "INSERT INTO usuarios(username, senha) VALUES (?, ?);;";
				PreparedStatement cmd = conexao.prepareStatement(sql);
				
				String hashedPassString = BCrypt.hashpw(senha, BCrypt.gensalt());
				
				cmd.setString(1, username);
				cmd.setString(2, hashedPassString);
				
				int rowAffected = cmd.executeUpdate();
				sucess = rowAffected > 0;
			}//inserir
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucess;
	}
}
//ALTER TABLE `users` ADD UNIQUE(`username`);



