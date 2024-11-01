package pet.window;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Estoque extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textBarraPesquisa;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Estoque frame = new Estoque();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Estoque() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        textBarraPesquisa = new JTextField();
        textBarraPesquisa.setBounds(476, 632, 211, 20);
        contentPane.add(textBarraPesquisa);
        textBarraPesquisa.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(697, 631, 89, 23);
        contentPane.add(btnBuscar);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textBarraPesquisa.getText();
                searchUsers(keyword);
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new MainPage().setVisible(true);
				dispose();
        	}
        });
        btnVoltar.setBounds(52, 631, 89, 23);
        contentPane.add(btnVoltar);

        JLabel lblEstoque = new JLabel("Estoque");
        lblEstoque.setHorizontalAlignment(SwingConstants.LEFT);
        lblEstoque.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
        lblEstoque.setBounds(28, 24, 136, 55);
        contentPane.add(lblEstoque);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 1044, 499);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane.setViewportView(table); 

        JButton btnCadsProdut = new JButton("Cadastrar Produto");
        btnCadsProdut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new CadastrarEstoque().setVisible(true);
        	}
        });
        btnCadsProdut.setBounds(895, 631, 141, 23);
        contentPane.add(btnCadsProdut);
        
        JButton btnNewButton = new JButton("Excluir");
        btnNewButton.setBounds(796, 631, 89, 23);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Captura a linha selecionada

                if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                    String nomeProduto = (String) tableModel.getValueAt(selectedRow, 0); // Captura o nome do produto
                    int resposta = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja excluir o produto: " + nomeProduto + "?", 
                        "Confirmação de Exclusão", 
                        JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        deleteProduct(nomeProduto); // Chama o método de exclusão
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um produto para excluir.");
                }
            }
        });



        // Define as colunas da tabela
        tableModel.addColumn("Nome produto");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Fornecedor");
        tableModel.addColumn("Validade");

        loadUserData();
    }

    private void loadUserData() {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection();
            if (conexao == null) {
                return;
            }

            String sql = "SELECT * FROM estoque";
            PreparedStatement cmd = conexao.prepareStatement(sql);
            ResultSet resultado = cmd.executeQuery();
            tableModel.setRowCount(0); 
            
            while (resultado.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultado.getString("nome_produto"));
                row.add(String.valueOf(resultado.getInt("quantidade")));
                row.add(resultado.getString("marca"));
                row.add(resultado.getString("fornecedor")); 
                row.add(String.valueOf(resultado.getInt("data_vencimento"))); 
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

    private void searchUsers(String keyword) {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection();
            String sql = "SELECT estoque_id, produto_id, nome_produto, quantidade, marca, fornecedor FROM estoque WHERE nome_produto LIKE ? OR produto_id LIKE ?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();

            tableModel.setRowCount(0); // Limpa os dados da tabela antes de carregar novamente
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("estoque_id")); 
                row.add(rs.getString("produto_id")); 
                row.add(rs.getString("nome_produto")); 
                row.add(rs.getString("quantidade")); 
                row.add(rs.getString("marca")); 
                row.add(rs.getString("fornecedor")); 
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar os dados: " + e.getMessage());
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
    
    private void deleteProduct(String nomeProduto) {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection();
            String sql = "DELETE FROM estoque WHERE nome_produto = ?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeProduto);
            int rowsAffected = pst.executeUpdate(); // Executa a exclusão

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                loadUserData(); // Recarrega os dados da tabela
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir o produto: " + e.getMessage());
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
