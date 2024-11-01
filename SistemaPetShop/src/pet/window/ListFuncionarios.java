package pet.window;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
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

public class ListFuncionarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ListFuncionarios frame = new ListFuncionarios();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ListFuncionarios() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        JLabel lblFuncionarios = new JLabel("Funcionarios");
        lblFuncionarios.setHorizontalAlignment(SwingConstants.LEFT);
        lblFuncionarios.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
        lblFuncionarios.setBounds(30, 33, 184, 55);
        contentPane.add(lblFuncionarios);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 99, 989, 472);
        contentPane.add(scrollPane);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Endereco");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Cargo");
        tableModel.addColumn("Data de Admissão");
        
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(74, 603, 103, 35);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose(); // Fecha a janela atual
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(689, 603, 108, 35);
        contentPane.add(btnExcluir);
        btnExcluir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String funcionarioId = tableModel.getValueAt(selectedRow, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o funcionário " + funcionarioId + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    excluirFuncionario(funcionarioId);
                    loadFuncData(); // Atualiza a tabela
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um funcionário para excluir.");
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            	String funcionario_id = tableModel.getValueAt(selectedRow, 0).toString();
                String nome = tableModel.getValueAt(selectedRow, 1).toString();
                String cpf =  tableModel.getValueAt(selectedRow, 2).toString();
                String endereco =  tableModel.getValueAt(selectedRow, 3).toString();
                String telefone =  tableModel.getValueAt(selectedRow, 4).toString();
                String email =  tableModel.getValueAt(selectedRow, 5).toString();
                String funcao =  tableModel.getValueAt(selectedRow, 6).toString();
                String dataAdmissao =  tableModel.getValueAt(selectedRow, 7).toString();

                // Chama o EditFuncionario passando os dados
                EditFuncionario editFuncionarioPage = new EditFuncionario(new ListFuncionarios(), funcionario_id, nome, cpf, endereco, telefone, email, funcao, dataAdmissao);
                editFuncionarioPage.setVisible(true);
                dispose(); // Fecha a janela atual
            } else {
                JOptionPane.showMessageDialog(ListFuncionarios.this, "Selecione um funcionário para editar.");
            }
        });
        btnEditar.setBounds(807, 603, 103, 35);
        contentPane.add(btnEditar);
        
        loadFuncData();
    }
    
    private void excluirFuncionario(String funcionarioId) {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection();
            String sql = "DELETE FROM funcionarios WHERE funcionario_id = ?";
            PreparedStatement cmd = conexao.prepareStatement(sql);
            cmd.setString(1, funcionarioId);
            cmd.executeUpdate();
            JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir funcionário: " + e.getMessage());
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

            String sql = "SELECT * FROM funcionarios";
            PreparedStatement cmd = conexao.prepareStatement(sql);
            ResultSet resultado = cmd.executeQuery();
            tableModel.setRowCount(0); 
            
            while (resultado.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultado.getString("funcionario_id"));
                row.add(resultado.getString("nome"));
                row.add(resultado.getString("cpf"));
                row.add(resultado.getString("endereco"));
                row.add(resultado.getString("telefone"));
                row.add(resultado.getString("email"));
                row.add(resultado.getString("cargo"));
                row.add(resultado.getString("data_admissao"));
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
