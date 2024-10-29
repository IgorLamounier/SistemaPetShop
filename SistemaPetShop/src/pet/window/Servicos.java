package pet.window;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Servicos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JComboBox<String> comboBoxServicos;
    private DefaultListModel<String> listModel;
    private JList<String> listServicos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Servicos frame = new Servicos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public Servicos() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblServicos = new JLabel("Serviços:");
        lblServicos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblServicos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblServicos.setBounds(0, 385, 121, 18);
        contentPane.add(lblServicos);
        
        JLabel lblOutros = new JLabel("Observação:");
        lblOutros.setHorizontalAlignment(SwingConstants.RIGHT);
        lblOutros.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblOutros.setBounds(24, 341, 126, 20);
        contentPane.add(lblOutros);
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setBounds(160, 341, 341, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblpreco = new JLabel("Preço:");
        lblpreco.setHorizontalAlignment(SwingConstants.RIGHT);
        lblpreco.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblpreco.setBounds(635, 586, 79, 28);
        contentPane.add(lblpreco);
        
        JLabel lblDescrio = new JLabel("Descrição:");
        lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio.setBounds(0, 106, 121, 35);
        contentPane.add(lblDescrio);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(131, 116, 341, 20);
        contentPane.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(724, 593, 341, 20);
        contentPane.add(textField_2);
        
        JButton btnConcluir = new JButton("Concluir Serviço");
        btnConcluir.setFont(new Font("Arial", Font.PLAIN, 12));
        btnConcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            }
        });
        btnConcluir.setBounds(918, 637, 136, 23);
        contentPane.add(btnConcluir);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnVoltar.setBounds(10, 637, 113, 23);
        contentPane.add(btnVoltar);
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLimpar.setBounds(754, 637, 113, 23);
        contentPane.add(btnLimpar);
        
        JLabel lblPet = new JLabel("Pet:");
        lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPet.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblPet.setBounds(0, 26, 121, 18);
        contentPane.add(lblPet);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Arial", Font.PLAIN, 12));
        textField_3.setColumns(10);
        textField_3.setBounds(131, 26, 341, 20);
        contentPane.add(textField_3);
        
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCliente.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblCliente.setBounds(0, 65, 121, 18);
        contentPane.add(lblCliente);
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Arial", Font.PLAIN, 12));
        textField_4.setColumns(10);
        textField_4.setBounds(131, 68, 341, 20);
        contentPane.add(textField_4);
        
        JLabel lblDataAgendamento = new JLabel("Data Agendamento:");
        lblDataAgendamento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDataAgendamento.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDataAgendamento.setBounds(513, 11, 190, 22);
        contentPane.add(lblDataAgendamento);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(713, 17, 341, 20);
        contentPane.add(textField_5);

       
        comboBoxServicos = new JComboBox<>();
        comboBoxServicos.addItem("Tosa");
        comboBoxServicos.addItem("Banho");
        comboBoxServicos.addItem("Exames");
        comboBoxServicos.addItem("Vacinação");
        
        comboBoxServicos.setBounds(160, 410, 200, 25);
        contentPane.add(comboBoxServicos);

        JButton btnAddServico = new JButton("Adicionar Serviço");
        btnAddServico.addActionListener(e -> {
            String servicoSelecionado = (String) comboBoxServicos.getSelectedItem();
            if (servicoSelecionado != null) {
                listModel.addElement(servicoSelecionado);
            }
        });
        btnAddServico.setBounds(370, 410, 150, 25);
        contentPane.add(btnAddServico);

        listModel = new DefaultListModel<>();
        listServicos = new JList<>(listModel);
        listServicos.setBounds(160, 446, 200, 100);
        contentPane.add(listServicos);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIdade.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblIdade.setBounds(0, 152, 121, 35);
        contentPane.add(lblIdade);
        
        JLabel lblDescrio_1_1 = new JLabel("Porte:");
        lblDescrio_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio_1_1.setBounds(316, 198, 121, 35);
        contentPane.add(lblDescrio_1_1);
        
        JLabel lblDescrio_1_2 = new JLabel("Raça:");
        lblDescrio_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio_1_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio_1_2.setBounds(0, 198, 121, 35);
        contentPane.add(lblDescrio_1_2);
        
        JLabel lblDescrio_1_2_1 = new JLabel("Espécie:");
        lblDescrio_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio_1_2_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio_1_2_1.setBounds(316, 152, 121, 35);
        contentPane.add(lblDescrio_1_2_1);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(131, 162, 175, 20);
        contentPane.add(textField_6);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(131, 208, 175, 20);
        contentPane.add(textField_7);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(445, 208, 175, 20);
        contentPane.add(textField_8);
        
        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(445, 162, 175, 20);
        contentPane.add(textField_9);
        
        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(713, 48, 341, 20);
        contentPane.add(textField_10);
        
        JLabel lblHorrio = new JLabel("Horário:");
        lblHorrio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHorrio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblHorrio.setBounds(513, 44, 190, 22);
        contentPane.add(lblHorrio);
        
        JCheckBox chckbxNewCheckBox_2_1_2_1 = new JCheckBox("Castração");
        chckbxNewCheckBox_2_1_2_1.setBounds(260, 553, 100, 30);
        contentPane.add(chckbxNewCheckBox_2_1_2_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Servicos.class.getResource("/img/Fundo2.png")));
        lblNewLabel.setBounds(0, 0, 1065, 681);
        contentPane.add(lblNewLabel);
    }
}
