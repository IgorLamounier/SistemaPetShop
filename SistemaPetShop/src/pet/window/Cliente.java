package pet.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Pane;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtEmail;
	private JTextField txtObs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		Pane = new JPanel();
		Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(Pane);
		Pane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cadastro de Cliente");
		lblTitulo.setBounds(64, 60, 286, 68);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		Pane.add(lblTitulo);
		
		JLabel lblNomeCli = new JLabel("Nome:");
		lblNomeCli.setBounds(53, 157, 62, 25);
		lblNomeCli.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeCli.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblNomeCli);
		
		txtNome = new JTextField();
		txtNome.setBounds(125, 157, 372, 25);
		txtNome.setColumns(10);
		Pane.add(txtNome);
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setBounds(64, 204, 51, 25);
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblTel);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(126, 204, 271, 25);
		txtTelefone.setColumns(10);
		Pane.add(txtTelefone);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(553, 153, 93, 32);
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(656, 157, 381, 25);
		txtEndereco.setColumns(10);
		Pane.add(txtEndereco);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(584, 200, 62, 32);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(656, 204, 381, 25);
		txtEmail.setColumns(10);
		Pane.add(txtEmail);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(64, 245, 51, 25);
		lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPF.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblCPF);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(125, 245, 272, 25);
		Pane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblObsCli = new JLabel("Obs:");
		lblObsCli.setBounds(584, 245, 62, 25);
		lblObsCli.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObsCli.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblObsCli);
		
		txtObs = new JTextField();
		txtObs.setBounds(656, 245, 381, 25);
		txtObs.setColumns(10);
		Pane.add(txtObs);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnConcluir.setBounds(867, 603, 99, 32);
		btnConcluir.setFont(new Font("Arial", Font.PLAIN, 15));
		Pane.add(btnConcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(755, 603, 102, 32);
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Limpa os campos
		        txtNome.setText("");
		        txtTelefone.setText("");
		        txtCPF.setText("");
		        txtEndereco.setText("");
		        txtEmail.setText("");
		        txtObs.setText("");
		    }
		});
		Pane.add(btnLimpar);

		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainPage().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(643, 603, 102, 32);
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 15));
		Pane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Cliente.class.getResource("/img/Fundo2.png")));
		lblNewLabel.setBounds(0, 0, 1064, 681);
		Pane.add(lblNewLabel);
	}
	
	private void registrar() {
		String nome = txtNome.getText();
		String telefone = txtTelefone.getText();
		String cpf = txtCPF.getText();
		String endereco = txtEndereco.getText();
		String email = txtEmail.getText();
		String obs = txtObs.getText();

		
		if(nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || email.isEmpty() || obs.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
		}
		
		if(addClienteDatabase(nome, telefone, cpf, endereco, email, obs)) {
			JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
		}
		
	}
	
	private boolean addClienteDatabase(String nome, String telefone, String cpf, String endereco, String email, String obs) {
	boolean sucess = false;
	
	try {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO clientes(nome, endereco, telefone, cpf, email, observacao) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement cmd = conexao.prepareStatement(sql);
		
		cmd.setString(1, nome);
		cmd.setString(2, endereco);
		cmd.setString(3, telefone);
		cmd.setString(4, cpf);
		cmd.setString(5, email);
		cmd.setString(6, obs);
		
		int rowAffected = cmd.executeUpdate();
		sucess = rowAffected > 0;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sucess;

	}
}
