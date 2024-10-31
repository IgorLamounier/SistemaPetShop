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

public class CadastrarEstoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JComboBox<String> cmbTipo;
	private JTextField txtQuant;
	private JTextField txtMarca;
	private JTextField txtFornecedor;
	private JTextField txtFab;
	private JTextField txtVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEstoque frame = new CadastrarEstoque();
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
	public CadastrarEstoque() {
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
		lblNome.setBounds(370, 87, 56, 23);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(436, 87, 153, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 19));
		lblQuantidade.setBounds(324, 121, 102, 23);
		contentPane.add(lblQuantidade);
		
		txtQuant = new JTextField();
		txtQuant.setColumns(10);
		txtQuant.setBounds(436, 121, 153, 23);
		contentPane.add(txtQuant);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Arial", Font.PLAIN, 19));
		lblMarca.setBounds(370, 155, 56, 23);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(436, 155, 153, 23);
		contentPane.add(txtMarca);
		
		JLabel lblQuantidade_1 = new JLabel("Fornecedor:");
		lblQuantidade_1.setFont(new Font("Arial", Font.PLAIN, 19));
		lblQuantidade_1.setBounds(322, 189, 104, 23);
		contentPane.add(lblQuantidade_1);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setColumns(10);
		txtFornecedor.setBounds(436, 189, 153, 23);
		contentPane.add(txtFornecedor);
		
		JLabel lblTipo = new JLabel("Categoria:");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTipo.setBounds(332, 227, 94, 23);
		contentPane.add(lblTipo);
		
		JLabel lblQuantidade_1_1 = new JLabel("Fab:");
		lblQuantidade_1_1.setFont(new Font("Arial", Font.PLAIN, 19));
		lblQuantidade_1_1.setBounds(388, 261, 38, 23);
		contentPane.add(lblQuantidade_1_1);
		
		txtFab = new JTextField();
		txtFab.setColumns(10);
		txtFab.setBounds(436, 261, 153, 23);
		contentPane.add(txtFab);
		
		JLabel lblQuantidade_1_2 = new JLabel("Val:");
		lblQuantidade_1_2.setFont(new Font("Arial", Font.PLAIN, 19));
		lblQuantidade_1_2.setBounds(388, 295, 38, 23);
		contentPane.add(lblQuantidade_1_2);
		
		txtVal = new JTextField();
		txtVal.setColumns(10);
		txtVal.setBounds(436, 295, 153, 23);
		contentPane.add(txtVal);
		
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 19));
		lblPreco.setBounds(370, 329, 56, 23);
		contentPane.add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(436, 329, 153, 23);
		contentPane.add(txtPreco);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarEstoque();
			}
		});
		btnAdicionar.setBounds(469, 363, 89, 23);
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
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVoltar.setBounds(10, 647, 89, 23);
		contentPane.add(btnVoltar);
		
		
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setBounds(0, 0, 1064, 681);
		lblFundo.setIcon(new ImageIcon(CadastrarEstoque.class.getResource("/img/Fundo2.png")));
		contentPane.add(lblFundo);

	}
	
	private void registrarEstoque() {
		String nome = txtNome.getText();
		String quantidade = txtQuant.getText();
		String marca = txtMarca.getText();
		String fornecedor = txtFornecedor.getText();
		String tipo = cmbTipo.getSelectedItem().toString();
		String fab = txtFab.getText();
		String val = txtVal.getText();
		String preco = txtPreco.getText();
		
		if(nome.isEmpty() || quantidade.isEmpty() || marca.isEmpty() || fornecedor.isEmpty() || 
				tipo.isEmpty() || preco.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
		}
		
		if(addEstoqueDatabase(nome, quantidade, marca, fornecedor, tipo, fab, val, preco)) {
			JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
		}
		
	}
	
	private boolean addEstoqueDatabase(String nome, String quantidade, String marca, String fornecedor, String tipo, String fab, String val, String preco) {
		boolean sucess = false;
		
		try {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "INSERT INTO estoque(nome_produto, quantidade, marca, fornecedor, tipo, data_fabricacao, data_vencimento, preco) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement cmd = conexao.prepareStatement(sql);
			
			cmd.setString(1, nome);
			cmd.setString(2, quantidade);
			cmd.setString(3, marca);
			cmd.setString(4, fornecedor);
			cmd.setString(5, tipo);
			cmd.setString(6, fab);
			cmd.setString(7, val);
			cmd.setString(8, preco);
			
			int rowAffected = cmd.executeUpdate();
			sucess = rowAffected > 0; 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucess;
	}
}
