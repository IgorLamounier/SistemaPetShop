package pet.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Funcionario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome;
    private JTextField txtCPF;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JButton btnCadastrar;
    private JComboBox<String> cmbCargo;
    private JTextField txtData;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	Funcionario frame = new Funcionario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public Funcionario() {
        setSize(1080, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font font = new Font("Arial", Font.PLAIN, 16);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setBounds(50, 30, 150, 30);
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setFont(font);
        JLabel lblCPF = new JLabel("Número de CPF:");
        lblCPF.setBounds(50, 71, 150, 30);
        lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCPF.setFont(font);
        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(10, 112, 190, 30);
        lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEndereco.setFont(font);
        JLabel lblTelefone = new JLabel("Telefone de Contato:");
        lblTelefone.setBounds(50, 153, 150, 30);
        lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefone.setFont(font);
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(50, 194, 150, 30);
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setFont(font);
        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(50, 235, 150, 30);
        lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCargo.setFont(font);

        txtNome = new JTextField();
        txtNome.setBounds(210, 30, 300, 30);
        txtNome.setFont(font);
        txtCPF = new JTextField();
        txtCPF.setBounds(210, 71, 300, 30);
        txtCPF.setFont(font);
        txtEndereco = new JTextField();
        txtEndereco.setBounds(210, 112, 300, 30);
        txtEndereco.setFont(font);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(210, 153, 300, 30);
        txtTelefone.setFont(font);
        txtEmail = new JTextField();
        txtEmail.setBounds(210, 194, 300, 30);
        txtEmail.setFont(font);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(210, 317, 150, 30);
        btnCadastrar.setFont(font);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });
        getContentPane().setLayout(null);

        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lblCPF);
        getContentPane().add(txtCPF);
        getContentPane().add(lblEndereco);
        getContentPane().add(txtEndereco);
        getContentPane().add(lblTelefone);
        getContentPane().add(txtTelefone);
        getContentPane().add(lblEmail);
        getContentPane().add(txtEmail);
        getContentPane().add(lblCargo);
        getContentPane().add(btnCadastrar);
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(372, 317, 150, 30);
        btnLimpar.setFont(new Font("Arial", Font.PLAIN, 16));
        getContentPane().add(btnLimpar);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 640, 150, 30);
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        getContentPane().add(btnVoltar);
        
        cmbCargo = new JComboBox<>();
        cmbCargo.addItem("Veterinário");
        cmbCargo.addItem("Atendente");
        cmbCargo.addItem("Gerente");
        cmbCargo.addItem("Recepcionista");
        cmbCargo.addItem("Assistente");
        cmbCargo.setBounds(210, 235, 300, 30);
        getContentPane().add(cmbCargo);
        
        JLabel lblData = new JLabel("Data de Admissão:");
        lblData.setHorizontalAlignment(SwingConstants.RIGHT);
        lblData.setFont(new Font("Arial", Font.PLAIN, 16));
        lblData.setBounds(50, 276, 150, 30);
        getContentPane().add(lblData);
        
        txtData = new JTextField();
        txtData.setFont(new Font("Arial", Font.PLAIN, 16));
        txtData.setBounds(210, 276, 300, 30);
        getContentPane().add(txtData);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Funcionario.class.getResource("/img/Fundo2.png")));
        lblNewLabel.setBounds(0, 0, 1064, 681);
        getContentPane().add(lblNewLabel);

        setVisible(true);
    }

    private void cadastrarFuncionario() {
        String nome = txtNome.getText().trim();
        String cpf = txtCPF.getText().trim();
        String endereco = txtEndereco.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        String cargo = cmbCargo.getSelectedItem().toString();
        String data = txtData.getText();

        if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || telefone.isEmpty() || email.isEmpty() || cargo.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        if (addFuncionarioDatabase(nome, cpf, endereco, telefone, email, cargo, data)) {
			JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
		}

       
    }
    
    private boolean addFuncionarioDatabase(String nome, String cpf, String endereco, String telefone, String email, String cargo, String data) {
		boolean sucess = false;
		
		try {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "insert into funcionarios (nome, cpf, endereco, telefone, email, cargo, data_admissao) values (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement cmd = conexao.prepareStatement(sql);
			
			cmd.setString(1, nome);
			cmd.setString(2, cpf);
			cmd.setString(3, endereco);
			cmd.setString(4, telefone);
			cmd.setString(5, email);
			cmd.setString(6, cargo);
			cmd.setString(7, data);
			
			int rowAffected = cmd.executeUpdate();
			sucess = rowAffected > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucess;
    
    }
}
