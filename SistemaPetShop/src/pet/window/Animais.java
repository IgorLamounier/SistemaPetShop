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
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Animais extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Pane;
	private JTextField txtNomeAni;
	private JTextField txtEspecie;
	private JTextField txtRaca;
	private JTextField txtIdade;
	private JTextField txtPelo;
	private JTextField txtPeso;
	private JTextField txtObsAni;
	private JTextField txtNomeCli;
	private JTextField txtSexo;
	private JTextField txtPorte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animais frame = new Animais();
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
	public Animais() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		Pane = new JPanel();
		Pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(Pane);
		Pane.setLayout(null);
		
		JLabel lblTituloPet = new JLabel("Cadastro do Animal");
		lblTituloPet.setBounds(64, 11, 460, 68);
		lblTituloPet.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		Pane.add(lblTituloPet);
		
		JLabel lblIDCliente = new JLabel("ID do Cliente:");
		lblIDCliente.setBounds(43, 92, 147, 25);
		lblIDCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIDCliente.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblIDCliente);
		
		txtNomeCli = new JTextField();
		txtNomeCli.setBounds(200, 92, 53, 25);
		txtNomeCli.setColumns(10);
		Pane.add(txtNomeCli);
		
		JLabel lblNomePet = new JLabel("Nome:");
		lblNomePet.setBounds(128, 128, 62, 25);
		lblNomePet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomePet.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblNomePet);
		
		txtNomeAni = new JTextField();
		txtNomeAni.setBounds(200, 128, 372, 25);
		txtNomeAni.setColumns(10);
		Pane.add(txtNomeAni);
		
		JLabel lblEspecia = new JLabel("Espécie:");
		lblEspecia.setBounds(105, 164, 85, 25);
		lblEspecia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspecia.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblEspecia);
		
		txtEspecie = new JTextField();
		txtEspecie.setBounds(200, 164, 271, 25);
		txtEspecie.setColumns(10);
		Pane.add(txtEspecie);
		
		JLabel lblRaca = new JLabel("Raça:");
		lblRaca.setBounds(128, 210, 62, 25);
		lblRaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRaca.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblRaca);
		
		txtRaca = new JTextField();
		txtRaca.setBounds(200, 210, 271, 25);
		txtRaca.setColumns(10);
		Pane.add(txtRaca);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(318, 253, 62, 25);
		lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdade.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(386, 253, 114, 25);
		txtIdade.setColumns(10);
		Pane.add(txtIdade);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(128, 253, 62, 25);
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPeso.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(200, 253, 108, 25);
		Pane.add(txtPeso);
		txtPeso.setColumns(10);
		
		JLabel lblPelo = new JLabel("Cor do pelo:");
		lblPelo.setBounds(644, 90, 114, 25);
		lblPelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPelo.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblPelo);
		
		txtPelo = new JTextField();
		txtPelo.setBounds(768, 90, 272, 25);
		txtPelo.setColumns(10);
		Pane.add(txtPelo);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(696, 128, 62, 25);
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblSexo);
		
		JLabel lblPorte = new JLabel("Porte:");
		lblPorte.setBounds(694, 164, 62, 25);
		lblPorte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorte.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblPorte);
		
		JLabel lblObsAni = new JLabel("Obs:");
		lblObsAni.setBounds(696, 200, 62, 25);
		lblObsAni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObsAni.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblObsAni);
		
		txtObsAni = new JTextField();
		txtObsAni.setBounds(768, 200, 286, 25);
		txtObsAni.setColumns(10);
		Pane.add(txtObsAni);

		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(867, 603, 99, 32);
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnConcluir.setFont(new Font("Arial", Font.PLAIN, 15));
		Pane.add(btnConcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(755, 603, 102, 32);
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Limpa os campos
		        txtNomeCli.setText("");
		        txtNomeAni.setText("");
		        txtEspecie.setText("");
		        txtRaca.setText("");
		        txtPeso.setText("");
		        txtIdade.setText("");
		        txtPelo.setText("");
		        txtSexo.setText("");
		        txtPorte.setText("");
		        txtObsAni.setText("");
		    }
		});
		Pane.add(btnLimpar);

		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(643, 603, 102, 32);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainPage().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 15));
		Pane.add(btnVoltar);
		
		txtSexo = new JTextField();
		txtSexo.setBounds(768, 128, 272, 25);
		txtSexo.setColumns(10);
		Pane.add(txtSexo);
		
		txtPorte = new JTextField();
		txtPorte.setColumns(10);
		txtPorte.setBounds(768, 164, 272, 25);
		Pane.add(txtPorte);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1064, 681);
		lblNewLabel.setIcon(new ImageIcon(Animais.class.getResource("/img/Fundo2.png")));
		Pane.add(lblNewLabel);
		

		

	}
	
	private void registrar() {
		String cliente = txtNomeCli.getText();
		String nome = txtNomeAni.getText();
		String especie = txtEspecie.getText();
		String raca = txtRaca.getText();
		String peso = txtPeso.getText();
		String idade = txtIdade.getText();
		String pelo = txtPelo.getText();
		String sexo = txtSexo.getText();
		String porte = txtPorte.getText();
		String obs = txtObsAni.getText();

		
		if(cliente.isEmpty() || nome.isEmpty() || especie.isEmpty() || raca.isEmpty() || peso.isEmpty() || idade.isEmpty() || pelo.isEmpty() || sexo.isEmpty() || porte.isEmpty() || obs.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
		}
		
		if(addAnimalDatabase(cliente, nome, especie, raca, peso, idade, pelo, sexo, porte, obs)) {
			JOptionPane.showMessageDialog(this, "Animal cadastrado com sucesso!");
		}
		
	}
	
	private boolean addAnimalDatabase(String cliente, String nome, String especie, String raca, String peso, String idade, String pelo, String sexo, String porte, String obs) {
	boolean sucess = false;
	
	try {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO animais(cliente_id, nome, especie, raca, peso, idade, cor_pelo, sexo, porte, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement cmd = conexao.prepareStatement(sql);
		
		cmd.setString(1, cliente);
		cmd.setString(2, nome);
		cmd.setString(3, especie);
		cmd.setString(4, raca);
		cmd.setString(5, peso);
		cmd.setString(6, idade);
		cmd.setString(7, pelo);
		cmd.setString(8, sexo);
		cmd.setString(9, porte);
		cmd.setString(10, obs);
		
		int rowAffected = cmd.executeUpdate();
		sucess = rowAffected > 0;
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sucess;

	}
}
