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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class EditarCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldCpf;
    private JTextField textFieldEndereco;
    private JTextField textFieldTelefone;
    private JTextField textFieldEmail;
    private JTextField textFieldObservacao;
    private String clienteId;
    private ListarClientes listarClientes; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                
                ListarClientes listarClientes = new ListarClientes();
                EditarCliente frame = new EditarCliente(listarClientes, "1", "Nome Exemplo", "12345678900", "EndereÃ§o Exemplo", "123456789", "email@example.com", "ObservaÃ§Ã£o Exemplo");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EditarCliente(ListarClientes listarClientes, String id, String nome, String cpf, String endereco, String telefone, String email, String observacao) {
        this.listarClientes = listarClientes; 
        this.clienteId = id; 
        initialize();
        textFieldNome.setText(nome);
        textFieldCpf.setText(cpf);
        textFieldEndereco.setText(endereco);
        textFieldTelefone.setText(telefone);
        textFieldEmail.setText(email);
        textFieldObservacao.setText(observacao);
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNome.setBounds(257, 139, 150, 30);
        contentPane.add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldNome.setBounds(417, 139, 300, 30);
        contentPane.add(textFieldNome);

        JLabel lblCpf = new JLabel("NÃºmero de CPF:");
        lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCpf.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCpf.setBounds(257, 180, 150, 30);
        contentPane.add(lblCpf);

        textFieldCpf = new JTextField();
        textFieldCpf.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldCpf.setBounds(417, 180, 300, 30);
        contentPane.add(textFieldCpf);

        JLabel lblEndereco = new JLabel("EndereÃ§o:");
        lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEndereco.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEndereco.setBounds(257, 221, 150, 30);
        contentPane.add(lblEndereco);

        textFieldEndereco = new JTextField();
        textFieldEndereco.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldEndereco.setBounds(417, 221, 300, 30);
        contentPane.add(textFieldEndereco);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTelefone.setBounds(257, 262, 150, 30);
        contentPane.add(lblTelefone);

        textFieldTelefone = new JTextField();
        textFieldTelefone.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldTelefone.setBounds(417, 262, 300, 30);
        contentPane.add(textFieldTelefone);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEmail.setBounds(257, 303, 150, 30);
        contentPane.add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldEmail.setBounds(417, 303, 300, 30);
        contentPane.add(textFieldEmail);

        JLabel lblObservacao = new JLabel("ObservaÃ§Ã£o:");
        lblObservacao.setHorizontalAlignment(SwingConstants.RIGHT);
        lblObservacao.setFont(new Font("Arial", Font.PLAIN, 16));
        lblObservacao.setBounds(257, 344, 150, 30);
        contentPane.add(lblObservacao);

        textFieldObservacao = new JTextField();
        textFieldObservacao.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldObservacao.setBounds(417, 344, 300, 30);
        contentPane.add(textFieldObservacao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSalvar.setBounds(842, 612, 150, 30);
        btnSalvar.addActionListener(e -> atualizarCliente());
        contentPane.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVoltar.setBounds(28, 612, 150, 30);
        btnVoltar.addActionListener(e -> {
            listarClientes.setVisible(true); 
            dispose(); 
        });
        contentPane.add(btnVoltar);
    }

    private void atualizarCliente() {
        try {
            Connection conexao = ConnectionFactory.createConnection();
            String sql = "UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, telefone = ?, email = ?, observacao = ? WHERE cliente_id = ?";
            PreparedStatement cmd = conexao.prepareStatement(sql);

            cmd.setString(1, textFieldNome.getText());
            cmd.setString(2, textFieldCpf.getText());
            cmd.setString(3, textFieldEndereco.getText());
            cmd.setString(4, textFieldTelefone.getText());
            cmd.setString(5, textFieldEmail.getText());
            cmd.setString(6, textFieldObservacao.getText());
            cmd.setString(7, clienteId);

            cmd.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            dispose(); 
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }
}
