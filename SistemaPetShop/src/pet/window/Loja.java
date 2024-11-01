package pet.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public class Loja extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;
    private JButton btnComprar;
    private DefaultListModel<String> listModel;
    private JList<String> listSelecionados;
    private HashMap<Integer, Integer> quantidades;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Loja frame = new Loja();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Loja() {
        setTitle("Sistema de Venda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setResizable(false);

        JLabel lblProdutos = new JLabel("Produtos");
        lblProdutos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
        lblProdutos.setBounds(30, 33, 184, 55);
        contentPane.add(lblProdutos);

        modeloTabela = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false; // Impede edição das células
            }
        };
        modeloTabela.addColumn("Produto ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Preço");

        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(40, 99, 989, 472);
        contentPane.add(scrollPane);

        lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setBounds(729, 583, 200, 30);
        contentPane.add(lblTotal);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(729, 613, 100, 30);
        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double total = calcularTotal();
                if (total > 0) {
                    mostrarOpcaoPagamento(total);
                } else {
                    JOptionPane.showMessageDialog(Loja.this, "Nenhum produto selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnComprar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(40, 613, 100, 30);
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
        });
        contentPane.add(btnVoltar);

        listModel = new DefaultListModel<>();
        listSelecionados = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(listSelecionados);
        listScrollPane.setBounds(388, 585, 331, 93);
        contentPane.add(listScrollPane);

        JButton btnRemoverItem = new JButton("Remover Item");
        btnRemoverItem.setBounds(850, 613, 130, 30);
        btnRemoverItem.addActionListener(e -> removerItemSelecionado());
        contentPane.add(btnRemoverItem);

        quantidades = new HashMap<>();
        loadProdutos(); // Carrega produtos do banco de dados

        tabelaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tabelaProdutos.getSelectedRow();
                if (row != -1) {
                    int id = Integer.parseInt((String) modeloTabela.getValueAt(row, 0)); // Converte para Integer
                    String nome = (String) modeloTabela.getValueAt(row, 1);
                    double preco = Double.parseDouble((String) modeloTabela.getValueAt(row, 3)); // Converte para double

                    quantidades.put(id, quantidades.getOrDefault(id, 0) + 1);
                    atualizarLista();
                    calcularTotal();
                }
            }
        });
    }

    private void loadProdutos() {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.createConnection(); // Método para obter conexão ao banco
            String sql = "SELECT * FROM produtos"; // Sua tabela de produtos
            PreparedStatement cmd = conexao.prepareStatement(sql);
            ResultSet resultado = cmd.executeQuery();
            modeloTabela.setRowCount(0); // Limpa a tabela antes de carregar

            while (resultado.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultado.getString("produto_id")); // Alterado para produto_id
                row.add(resultado.getString("nome"));
                row.add(resultado.getString("tipo"));
                row.add(resultado.getString("preco"));
                modeloTabela.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage());
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

    private double calcularTotal() {
        double total = 0.0;
        listModel.clear();
        for (int id : quantidades.keySet()) {
            String nome = (String) modeloTabela.getValueAt(id - 1, 1);
            double preco = Double.parseDouble((String) modeloTabela.getValueAt(id - 1, 3));
            int quantidade = quantidades.get(id);
            total += preco * quantidade;
            listModel.addElement(nome + " - R$ " + preco + " (Qtd: " + quantidade + ")");
        }
        lblTotal.setText(String.format("Total: R$ %.2f", total));
        return total;
    }

    private void atualizarLista() {
        listModel.clear();
        for (int id : quantidades.keySet()) {
            String nome = (String) modeloTabela.getValueAt(id - 1, 1);
            double preco = Double.parseDouble((String) modeloTabela.getValueAt(id - 1, 3));
            int quantidade = quantidades.get(id);
            listModel.addElement(nome + " - R$ " + preco + " (Qtd: " + quantidade + ")");
        }
    }

    private void mostrarOpcaoPagamento(double total) {
        Object[] options = {"Crédito", "Débito", "PIX"};
        String message = String.format("Valor da compra: R$ %.2f\nEscolha a forma de pagamento:", total);

        int escolha = JOptionPane.showOptionDialog(
            this,
            message,
            "Forma de Pagamento",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (escolha != -1) {
            String metodoPagamento = options[escolha].toString();
            JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso via " + metodoPagamento);
            listModel.clear();
            lblTotal.setText("Total: R$ 0.00");
            quantidades.clear();
        }
    }

    private void removerItemSelecionado() {
        int[] indicesSelecionados = listSelecionados.getSelectedIndices();
        for (int i = indicesSelecionados.length - 1; i >= 0; i--) {
            String item = listModel.get(indicesSelecionados[i]);
            String[] partes = item.split(" - R\\$ ");
            String nome = partes[0];

            // Busca o ID do produto pelo nome
            int id = -1;
            for (int j = 0; j < modeloTabela.getRowCount(); j++) {
                if (modeloTabela.getValueAt(j, 1).equals(nome)) {
                    id = Integer.parseInt((String) modeloTabela.getValueAt(j, 0)); // Converte para Integer
                    break;
                }
            }

            // Remove a quantidade do item selecionado
            if (id != -1) {
                int quantidadeAtual = quantidades.get(id);
                if (quantidadeAtual > 1) {
                    quantidades.put(id, quantidadeAtual - 1);
                } else {
                    quantidades.remove(id);
                }
            }
        }
        atualizarLista();
        calcularTotal();
    }
}
