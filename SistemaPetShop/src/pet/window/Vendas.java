package pet.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class Vendas extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;
    private JButton btnComprar;
    private JPanel panelresult;
    private JButton btnRemoverItem;
    private DefaultListModel<String> listModel;
    private JList<String> listSelecionados; 
    private HashMap<Integer, Integer> quantidades; 
    private JLabel lblNewLabel;

    public Vendas() {
        setTitle("Sistema de Venda");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        String[] colunas = {"ID", "Nome", "Tipo", "Preço"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProdutos = new JTable(modeloTabela);

        quantidades = new HashMap<>();

        adicionarProdutos();

        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(30, 30, 1020, 500);
        getContentPane().add(scrollPane);

        lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setBounds(729, 541, 200, 30);
        getContentPane().add(lblTotal);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(729, 582, 100, 30);
        btnComprar.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                double total = calcularTotal();
                if (total > 0) { 
                    mostrarOpcaoPagamento(total);
                } else {
                    JOptionPane.showMessageDialog(Vendas.this, "Nenhum produto selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(btnComprar);
        
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(0, 650, 100, 30);
        getContentPane().add(btnVoltar);
        
        
        panelresult = new JPanel();
        panelresult.setBounds(388, 549, 331, 93);
        panelresult.setLayout(new BorderLayout()); 
        getContentPane().add(panelresult);
        
        
        listModel = new DefaultListModel<>();
        listSelecionados = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(listSelecionados);
        panelresult.add(listScrollPane, BorderLayout.CENTER);
        
        
        btnRemoverItem = new JButton("Remover Item");
        btnRemoverItem.setBounds(388, 650, 148, 30);
        btnRemoverItem.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                removerItemSelecionado();
            }
        });
        getContentPane().add(btnRemoverItem);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Vendas.class.getResource("/img/Cream Brown And Orange Illustration Event Birthday Presentation 3.png")));
        lblNewLabel.setBounds(0, 0, 1064, 680);
        getContentPane().add(lblNewLabel);

        
        tabelaProdutos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabelaProdutos.getSelectedRow();
                if (row != -1) { 
                    int id = (Integer) modeloTabela.getValueAt(row, 0);
                    String nome = (String) modeloTabela.getValueAt(row, 1);
                    double preco = (Double) modeloTabela.getValueAt(row, 3);

                    
                    quantidades.put(id, quantidades.getOrDefault(id, 0) + 1);

                    
                    atualizarLista();

                    
                    calcularTotal();
                }
            }
        });
    }

    private void adicionarProdutos() {
        modeloTabela.addRow(new Object[]{1, "Produto 1", "Tipo A", 10.00});
        modeloTabela.addRow(new Object[]{2, "Produto 2", "Tipo B", 20.00});
        modeloTabela.addRow(new Object[]{3, "Produto 3", "Tipo A", 30.00});
        modeloTabela.addRow(new Object[]{4, "Produto 4", "Tipo C", 40.00});
    }

    private double calcularTotal() {
        double total = 0.0;
        
        listModel.clear();
        for (int id : quantidades.keySet()) {
            String nome = (String) modeloTabela.getValueAt(id - 1, 1); 
            double preco = (Double) modeloTabela.getValueAt(id - 1, 3); 
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
            double preco = (Double) modeloTabela.getValueAt(id - 1, 3);
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
            String[] partes = item.split(" ");
            String nome = partes[0];
            int id = 0;

            
            for (int j = 0; j < modeloTabela.getRowCount(); j++) {
                if (modeloTabela.getValueAt(j, 1).equals(nome)) {
                    id = (Integer) modeloTabela.getValueAt(j, 0);
                    break;
                }
            }

            
            int quantidadeAtual = quantidades.get(id);
            if (quantidadeAtual > 1) {
                quantidades.put(id, quantidadeAtual - 1);
            } else {
                quantidades.remove(id);
            }
        }
        atualizarLista(); 
        calcularTotal(); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vendas sistemaVenda = new Vendas();
            sistemaVenda.setVisible(true);
        });
    }
}
