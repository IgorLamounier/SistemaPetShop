package pet.window;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Estoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JComboBox<String> cmbTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque();
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
	public Estoque() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Produtos");
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		lblNewLabel.setBounds(10, 11, 289, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 19));
		lblNome.setBounds(370, 193, 56, 23);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(436, 193, 153, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTipo.setBounds(370, 227, 56, 23);
		contentPane.add(lblTipo);
		
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 19));
		lblPreco.setBounds(370, 261, 56, 23);
		contentPane.add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(436, 261, 153, 23);
		contentPane.add(txtPreco);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarEstoque();
			}
		});
		btnAdicionar.setBounds(469, 295, 89, 23);
		contentPane.add(btnAdicionar);
		
		cmbTipo = new JComboBox<>();
		cmbTipo.addItem("Ração");
		cmbTipo.addItem("Higiene");
		cmbTipo.addItem("Brinquedo");
		cmbTipo.addItem("Medicamento");
		cmbTipo.addItem("Acessório");
		cmbTipo.setBounds(436, 227, 153, 23);
		contentPane.add(cmbTipo);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainPage().setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVoltar.setBounds(10, 647, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setBounds(0, 0, 1064, 681);
		lblFundo.setIcon(new ImageIcon(Estoque.class.getResource("/img/Fundo2.png")));
		contentPane.add(lblFundo);
		

		
	}
	
	private void registrarEstoque() {
		String nome = txtNome.getText();
		String tipo = cmbTipo.getSelectedItem().toString();
		String preco = txtPreco.getText();
		
		if(nome.isEmpty() || tipo.isEmpty() || preco.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
		}
		
		if(addEstoqueDatabase(nome, tipo, preco)) {
			JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
		}
		
	}
	
	private boolean addEstoqueDatabase(String nome, String tipo, String preco) {
		boolean sucess = false;
		
		try {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "insert into produtos (nome, tipo, preco) values (?, ?, ?);";
			PreparedStatement cmd = conexao.prepareStatement(sql);
			
			cmd.setString(1, nome);
			cmd.setString(2, tipo);
			cmd.setString(3, preco);
			
			int rowAffected = cmd.executeUpdate();
			sucess = rowAffected > 0; 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucess;
	}
}
