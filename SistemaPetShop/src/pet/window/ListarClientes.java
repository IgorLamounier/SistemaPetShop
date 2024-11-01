package pet.window;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListarClientes frame = new ListarClientes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ListarClientes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        
        JLabel lblClientes = new JLabel("Clientes");
        lblClientes.setHorizontalAlignment(SwingConstants.LEFT);
        lblClientes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
        lblClientes.setBounds(26, 39, 184, 55);
        contentPane.add(lblClientes);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 119, 989, 472);
        contentPane.add(scrollPane);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Endereco");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Email");
        tableModel.addColumn("Observacao");
        
        table = new JTable(tableModel); 
        scrollPane.setViewportView(table);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(74, 603, 103, 35);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose(); 
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(645, 603, 108, 35);
        contentPane.add(btnExcluir);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { 
                    String clienteId = tableModel.getValueAt(selectedRow, 0).toString();
                    String nome = tableModel.getValueAt(selectedRow, 1).toString();
                    String endereco = tableModel.getValueAt(selectedRow, 2).toString();
                    String telefone = tableModel.getValueAt(selectedRow, 3).toString();
                    String cpf = tableModel.getValueAt(selectedRow, 4).toString();
                    String email = tableModel.getValueAt(selectedRow, 5).toString();
                    String observacao = tableModel.getValueAt(selectedRow, 6).toString();

                    EditarCliente editarClienteFrame = new EditarCliente(new ListarClientes(), clienteId, nome, cpf, endereco, telefone, email, observacao);
                    editarClienteFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ListarClientes.this, "Selecione um cliente para editar.");
                }
            }
        });
        btnEditar.setBounds(762, 602, 108, 35);
        contentPane.add(btnEditar);
        
        btnExcluir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { 
                String clienteId = tableModel.getValueAt(selectedRow, 0).toString(); 
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o cliente " + clienteId + "?", "ConfirmaÃ§Ã£o", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    excluirCliente(clienteId); 
                    loadFuncData(); 
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            }
        });

        loadFuncData(); 
    }

    private void excluirCliente(String clienteId) {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection();
            String sql = "DELETE FROM clientes WHERE cliente_id = ?";
            PreparedStatement cmd = conexao.prepareStatement(sql);
            cmd.setString(1, clienteId);
            cmd.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cliente excluÃ­do com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir cliente: " + e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadFuncData() {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection();
            if (conexao == null) {
                return;
            }

            String sql = "SELECT * FROM clientes"; 
            PreparedStatement cmd = conexao.prepareStatement(sql);
            ResultSet resultado = cmd.executeQuery();
            tableModel.setRowCount(0); 
            
            while (resultado.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultado.getString("cliente_id"));
                row.add(resultado.getString("nome"));
                row.add(resultado.getString("endereco"));
                row.add(resultado.getString("telefone"));
                row.add(resultado.getString("cpf"));
                row.add(resultado.getString("email"));
                row.add(resultado.getString("observacao"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}