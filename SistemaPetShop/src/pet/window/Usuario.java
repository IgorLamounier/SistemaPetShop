package pet.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Usuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtUsername;
	private JPasswordField pswSenha;
	private JPasswordField pswConfSenha;
	private JComboBox<String> cmbPerfil; // Corrigido para o tipo adequado

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
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
	public Usuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuario.class.getResource("/img/show.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false); // Desativa o redimensionamento da janela
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNome.setBounds(136, 125, 115, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(262, 122, 371, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
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
		
		JLabel lblPerfil = new JLabel("Perfil:");
		lblPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerfil.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPerfil.setBounds(136, 289, 115, 14);
		contentPane.add(lblPerfil);
		
		// Use a variável já declarada em vez de criar um novo JComboBox local
		cmbPerfil = new JComboBox<>();
		cmbPerfil.addItem("Usuario");
		cmbPerfil.addItem("Gerente");
		cmbPerfil.addItem("Caixa");
		cmbPerfil.addItem("Diretor");
		cmbPerfil.addItem("Admin");
		cmbPerfil.addItem("SuperAdmin");
		cmbPerfil.setBounds(261, 285, 266, 22);
		contentPane.add(cmbPerfil);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Chamar um evento				
				registerUser();
			}
		});
		btnCadastrar.setIcon(new ImageIcon(Usuario.class.getResource("/img/salvar.png")));
		btnCadastrar.setBounds(249, 347, 134, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(Usuario.class.getResource("/img/remove.png")));
		btnLimpar.setBounds(393, 347, 134, 23);
		contentPane.add(btnLimpar);
	}
	
	private void registerUser() {
		String nome = txtNome.getText();
		String username = txtUsername.getText();
		String senha = new String(pswSenha.getPassword());
		String confsenha = new String(pswConfSenha.getPassword());
		
		// Use o JComboBox da classe e não uma instância local
		String perfil = cmbPerfil.getSelectedItem().toString();
		
		if(nome.isEmpty() || username.isEmpty() || senha.isEmpty() || confsenha.isEmpty() || perfil.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");	
		}else {
			if(!senha.equals(confsenha)) {
				JOptionPane.showMessageDialog(this, "As senhas estão incorretas.");
			}
		}
		
		if(addUserDatabase(nome, username, senha, confsenha, perfil)) {
			JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
			//Vou trocar
		}
		
	}
	
	private boolean addUserDatabase(String nome, String username, String senha, String confsenha, String perfil) {
		boolean sucess = false;
		
		try {
			if(senha.equals(confsenha)) {//inserir
				Connection conexao = ConnectionFactory.createConnection();
				String sql = "insert into users (username, password, nome values (?, ?, ?);";
				PreparedStatement cmd = conexao.prepareStatement(sql);
				
				String hashedPassString = BCrypt.hashpw(senha, BCrypt.gensalt());
				
				cmd.setString(1, username);
				cmd.setString(2, hashedPassString);
				cmd.setString(3, nome);
				
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



