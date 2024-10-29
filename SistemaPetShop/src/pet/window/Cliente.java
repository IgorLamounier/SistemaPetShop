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
	private JTextField txtNomeAni;
	private JTextField txtEspecie;
	private JTextField txtRaca;
	private JTextField txtIdade;
	private JTextField txtPelo;
	private JTextField txtPeso;
	private JTextField txtObsAni;
	private JTextField txtObs;
	private JComboBox<String> cmbSexo;
	private JComboBox<String> cmbPorte;

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
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setBounds(64, 204, 51, 25);
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblTel);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(553, 153, 93, 32);
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblEndereco);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(584, 200, 62, 32);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblEmail);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(64, 245, 51, 25);
		lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPF.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblCPF);
		
		JLabel lblTituloPet = new JLabel("Cadastro do Animal");
		lblTituloPet.setBounds(64, 313, 460, 68);
		lblTituloPet.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		Pane.add(lblTituloPet);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(125, 245, 272, 25);
		Pane.add(txtCPF);
		txtCPF.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(126, 204, 271, 25);
		txtTelefone.setColumns(10);
		Pane.add(txtTelefone);
		
		txtNome = new JTextField();
		txtNome.setBounds(125, 157, 372, 25);
		txtNome.setColumns(10);
		Pane.add(txtNome);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(656, 157, 381, 25);
		txtEndereco.setColumns(10);
		Pane.add(txtEndereco);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(656, 204, 381, 25);
		txtEmail.setColumns(10);
		Pane.add(txtEmail);
		
		JLabel lblNomePet = new JLabel("Nome:");
		lblNomePet.setBounds(64, 392, 62, 25);
		lblNomePet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomePet.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblNomePet);
		
		JLabel lblEspecia = new JLabel("Espécie:");
		lblEspecia.setBounds(41, 441, 85, 25);
		lblEspecia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspecia.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblEspecia);
		
		JLabel lblRaca = new JLabel("Raça:");
		lblRaca.setBounds(64, 487, 62, 25);
		lblRaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRaca.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblRaca);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(254, 530, 62, 25);
		lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdade.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblIdade);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(64, 530, 62, 25);
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPeso.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblPeso);
		
		JLabel lblPelo = new JLabel("Cor do pelo:");
		lblPelo.setBounds(556, 392, 114, 25);
		lblPelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPelo.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblPelo);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(608, 441, 62, 25);
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblSexo);
		
		JLabel lblPorte = new JLabel("Porte:");
		lblPorte.setBounds(608, 487, 62, 25);
		lblPorte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorte.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblPorte);
		
		JLabel lblObsCli = new JLabel("Obs:");
		lblObsCli.setBounds(584, 245, 62, 25);
		lblObsCli.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObsCli.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblObsCli);
		
		JLabel lblObsAni = new JLabel("Obs:");
		lblObsAni.setBounds(608, 530, 62, 25);
		lblObsAni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObsAni.setFont(new Font("Arial", Font.PLAIN, 19));
		Pane.add(lblObsAni);
		
		cmbSexo = new JComboBox<String>();
		cmbSexo.addItem("Maculino");
		cmbSexo.addItem("Feminino");
		cmbSexo.setBounds(680, 445, 184, 22);
		Pane.add(cmbSexo);
		
		cmbPorte = new JComboBox<String>();
		cmbPorte.addItem("Pequeno");
		cmbPorte.addItem("Médio");
		cmbPorte.addItem("Grande");
		cmbPorte.setBounds(680, 491, 184, 22);
		Pane.add(cmbPorte);
		
		txtNomeAni = new JTextField();
		txtNomeAni.setBounds(136, 392, 372, 25);
		txtNomeAni.setColumns(10);
		Pane.add(txtNomeAni);
		
		txtEspecie = new JTextField();
		txtEspecie.setBounds(136, 441, 271, 25);
		txtEspecie.setColumns(10);
		Pane.add(txtEspecie);
		
		txtRaca = new JTextField();
		txtRaca.setBounds(136, 487, 271, 25);
		txtRaca.setColumns(10);
		Pane.add(txtRaca);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(322, 530, 114, 25);
		txtIdade.setColumns(10);
		Pane.add(txtIdade);
		
		txtPelo = new JTextField();
		txtPelo.setBounds(680, 392, 272, 25);
		txtPelo.setColumns(10);
		Pane.add(txtPelo);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(136, 530, 108, 25);
		Pane.add(txtPeso);
		txtPeso.setColumns(10);
		
		txtObsAni = new JTextField();
		txtObsAni.setBounds(680, 530, 286, 25);
		txtObsAni.setColumns(10);
		Pane.add(txtObsAni);
		
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
		Pane.add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		String aniNome = txtNomeAni.getText();
		String especie = txtEspecie.getText();
		String raca = txtRaca.getText();
		String peso = txtPeso.getText();
		String idade = txtIdade.getText();
		String pelo = txtPelo.getText();
		String sexo = cmbSexo.getSelectedItem().toString();
		String porte = cmbPorte.getSelectedItem().toString();
		String aniObs = txtObsAni.getText();
		
		if(nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || email.isEmpty() || obs.isEmpty() || aniNome.isEmpty() || especie.isEmpty() ||
				raca.isEmpty() || peso.isEmpty() || idade.isEmpty() || pelo.isEmpty() || sexo.isEmpty() || porte.isEmpty() || aniObs.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
		}
		
	}
	
}
