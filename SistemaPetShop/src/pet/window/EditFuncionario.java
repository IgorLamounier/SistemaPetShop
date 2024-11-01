package pet.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class EditFuncionario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String funcionario_id;
    private JTextField txtAdmcao;
    private JTextField txtEmail;
    private JTextField txtTel;
    private JTextField txtEnd;
    private JTextField txtCPF;
    private JTextField txtNome;
    private ListFuncionarios listFuncionarios;
    private JComboBox<String> cmbFuncao; // Declare cmbFuncao como variável de classe
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                
            	ListFuncionarios listFuncionarios = new ListFuncionarios();
                EditFuncionario frame = new EditFuncionario(listFuncionarios, "1", "Nome Exemplo", "12345678900", "EndereÃ§o Exemplo", "123456789", "email@example.com", "Veterinário", "2022-01-15");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public EditFuncionario(ListFuncionarios listFuncionarios, String funcionario_id, String nome, String cpf, String endereco, String telefone, String email, String cargo, String dataAdmissao) {
    	this.listFuncionarios = listFuncionarios; 
        this.funcionario_id = funcionario_id; 
        initialize();

        txtNome.setText(nome);
        txtCPF.setText(cpf);
        txtEnd.setText(endereco);
        txtTel.setText(telefone);
        txtEmail.setText(email);
        cmbFuncao.setSelectedItem(cargo);
        txtAdmcao.setText(dataAdmissao);
    }

    
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVoltar.setBounds(28, 612, 150, 30);
        btnVoltar.addActionListener(e -> {
            listFuncionarios.setVisible(true); 
            dispose(); 
        });
        contentPane.add(btnVoltar);

        // Campos de texto
        txtAdmcao = new JTextField();
        txtAdmcao.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAdmcao.setBounds(417, 385, 300, 30);
        contentPane.add(txtAdmcao);

        cmbFuncao = new JComboBox<>(new String[]{"Veterinário", "Atendente", "Gerente", "Recepcionista", "Assistente"});
        cmbFuncao.setBounds(417, 344, 300, 30);
        contentPane.add(cmbFuncao);

        // Labels
        JLabel lblData = new JLabel("Data de Admissão:");
        lblData.setHorizontalAlignment(SwingConstants.RIGHT);
        lblData.setFont(new Font("Arial", Font.PLAIN, 16));
        lblData.setBounds(257, 385, 150, 30);
        contentPane.add(lblData);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCargo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCargo.setBounds(257, 344, 150, 30);
        contentPane.add(lblCargo);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEmail.setBounds(417, 303, 300, 30);
        contentPane.add(txtEmail);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEmail.setBounds(257, 303, 150, 30);
        contentPane.add(lblEmail);

        JLabel lblTelefone = new JLabel("Telefone de Contato:");
        lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTelefone.setBounds(257, 262, 150, 30);
        contentPane.add(lblTelefone);

        txtTel = new JTextField();
        txtTel.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTel.setBounds(417, 262, 300, 30);
        contentPane.add(txtTel);

        txtEnd = new JTextField();
        txtEnd.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEnd.setBounds(417, 221, 300, 30);
        contentPane.add(txtEnd);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEndereco.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEndereco.setBounds(217, 221, 190, 30);
        contentPane.add(lblEndereco);

        JLabel lblCPF = new JLabel("Número de CPF:");
        lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCPF.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCPF.setBounds(257, 180, 150, 30);
        contentPane.add(lblCPF);

        txtCPF = new JTextField();
        txtCPF.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCPF.setBounds(417, 180, 300, 30);
        contentPane.add(txtCPF);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNome.setBounds(417, 139, 300, 30);
        contentPane.add(txtNome);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNome.setBounds(257, 139, 150, 30);
        contentPane.add(lblNome);

        JLabel lblEditarFuncionario = new JLabel("Editar Funcionario");
        lblEditarFuncionario.setHorizontalAlignment(SwingConstants.LEFT);
        lblEditarFuncionario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
        lblEditarFuncionario.setBounds(28, 50, 273, 55);
        contentPane.add(lblEditarFuncionario);
        
        JButton btnConcluir = new JButton("Concluir");
        btnConcluir.setFont(new Font("Arial", Font.PLAIN, 16));
        btnConcluir.setBounds(854, 612, 150, 30);
        btnConcluir.addActionListener(e -> atualizarfuncionario());
        contentPane.add(btnConcluir);
    }

    
    private void atualizarfuncionario(){
    	
    	try {
            Connection conexao = ConnectionFactory.createConnection();
            String sql = "UPDATE funcionarios SET nome = ?, cpf = ?, endereco = ?, telefone = ?, email = ?, cargo = ?, data_admissao = ? WHERE funcionario_id = ?";
            PreparedStatement cmd = conexao.prepareStatement(sql);

            cmd.setString(1, txtNome.getText());
            cmd.setString(2, txtCPF.getText());
            cmd.setString(3, txtEnd.getText());
            cmd.setString(4, txtTel.getText());
            cmd.setString(5, txtEmail.getText());
            cmd.setString(6, cmbFuncao.getSelectedItem().toString());
            cmd.setString(7, txtAdmcao.getText());
            cmd.setString(8, funcionario_id);

            cmd.executeUpdate();
            JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário: " + e.getMessage());
        }
    	
    }
}
