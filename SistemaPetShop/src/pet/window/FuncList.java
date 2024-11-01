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

public class FuncList extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FuncList frame = new FuncList();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FuncList() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

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
        tableModel.addColumn("Cargo");
        tableModel.addColumn("Data de Emissão");
        
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(74, 603, 103, 35);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
        });
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(689, 603, 108, 35);
        contentPane.add(btnExcluir);
        btnExcluir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); // Obtém a linha selecionada
            if (selectedRow >= 0) {
                String funcionarioId = tableModel.getValueAt(selectedRow, 0).toString(); // Obtém o ID do funcionário

                // Excluir do banco de dados
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este funcionário?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    excluirFuncionario(funcionarioId);
                    tableModel.removeRow(selectedRow); // Remove a linha da tabela
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um funcionário para excluir.");
            }
        });
        
        loadFuncData(); // Carrega os dados na tabela ao inicializar
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

            String sql = "SELECT * FROM funcionarios"; // Certifique-se de que a tabela se chama 'funcionarios'
            PreparedStatement cmd = conexao.prepareStatement(sql);
            ResultSet resultado = cmd.executeQuery();
            tableModel.setRowCount(0); // Limpa a tabela antes de carregar os dados
            
            while (resultado.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultado.getString("funcionario_id"));
                row.add(resultado.getString("nome")); // Corrigido para getString
                row.add(resultado.getString("cargo"));
                row.add(resultado.getString("data_admissao")); // Corrigido para getString
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
