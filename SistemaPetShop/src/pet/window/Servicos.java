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
    private JTextField txtObs;
    private JTextField txtDescricao;
    private JTextField textField_2;
    private JTextField txtPet;
    private JTextField txtCliente;
    private JTextField txtAgenda;
    private JTextField txtIdade;
    private JTextField txtRaca;
    private JTextField txtPorte;
    private JTextField txtEspecie;
    private JTextField txtHora;
    private JComboBox<String> cmbServicos;
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
        
        txtObs = new JTextField();
        txtObs.setFont(new Font("Arial", Font.PLAIN, 12));
        txtObs.setBounds(160, 341, 341, 20);
        contentPane.add(txtObs);
        txtObs.setColumns(10);
        
        JLabel lblpreco = new JLabel("Preço:");
        lblpreco.setHorizontalAlignment(SwingConstants.RIGHT);
        lblpreco.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblpreco.setBounds(635, 586, 79, 28);
        contentPane.add(lblpreco);
        
        JLabel lblDescrio = new JLabel("Descrição:");
        lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio.setBounds(0, 94, 121, 35);
        contentPane.add(lblDescrio);
        
        txtDescricao = new JTextField();
        txtDescricao.setColumns(10);
        txtDescricao.setBounds(131, 104, 341, 20);
        contentPane.add(txtDescricao);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(724, 593, 341, 20);
        contentPane.add(textField_2);
        
        JButton btnConcluir = new JButton("Concluir Serviço");
        btnConcluir.setFont(new Font("Arial", Font.PLAIN, 12));
        btnConcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	agendarPet();
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
        
        txtPet = new JTextField();
        txtPet.setFont(new Font("Arial", Font.PLAIN, 12));
        txtPet.setColumns(10);
        txtPet.setBounds(131, 26, 341, 20);
        contentPane.add(txtPet);
        
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCliente.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblCliente.setBounds(0, 65, 121, 18);
        contentPane.add(lblCliente);
        
        txtCliente = new JTextField();
        txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
        txtCliente.setColumns(10);
        txtCliente.setBounds(131, 63, 341, 20);
        contentPane.add(txtCliente);
        
        JLabel lblDataAgendamento = new JLabel("Data Agendamento:");
        lblDataAgendamento.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDataAgendamento.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDataAgendamento.setBounds(513, 11, 190, 22);
        contentPane.add(lblDataAgendamento);
        
        txtAgenda = new JTextField();
        txtAgenda.setColumns(10);
        txtAgenda.setBounds(713, 17, 341, 20);
        contentPane.add(txtAgenda);

       
        cmbServicos = new JComboBox<>();
        cmbServicos.addItem("Tosa");
        cmbServicos.addItem("Banho");
        cmbServicos.addItem("Exames");
        cmbServicos.addItem("Vacinação");
        
        cmbServicos.setBounds(160, 378, 200, 25);
        contentPane.add(cmbServicos);

        JButton btnAddServico = new JButton("Adicionar Serviço");
        btnAddServico.addActionListener(e -> {
            String servicoSelecionado = (String) cmbServicos.getSelectedItem();
            if (servicoSelecionado != null) {
                listModel.addElement(servicoSelecionado);
            }
        });
        btnAddServico.setBounds(370, 378, 150, 25);
        contentPane.add(btnAddServico);

        listModel = new DefaultListModel<>();
        listServicos = new JList<>(listModel);
        listServicos.setBounds(160, 414, 200, 100);
        contentPane.add(listServicos);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIdade.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblIdade.setBounds(0, 140, 121, 35);
        contentPane.add(lblIdade);
        
        JLabel lblDescrio_1_1 = new JLabel("Porte:");
        lblDescrio_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio_1_1.setBounds(316, 186, 121, 35);
        contentPane.add(lblDescrio_1_1);
        
        JLabel lblDescrio_1_2 = new JLabel("Raça:");
        lblDescrio_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio_1_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio_1_2.setBounds(0, 186, 121, 35);
        contentPane.add(lblDescrio_1_2);
        
        JLabel lblDescrio_1_2_1 = new JLabel("Espécie:");
        lblDescrio_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescrio_1_2_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblDescrio_1_2_1.setBounds(316, 140, 121, 35);
        contentPane.add(lblDescrio_1_2_1);
        
        txtIdade = new JTextField();
        txtIdade.setColumns(10);
        txtIdade.setBounds(131, 150, 175, 20);
        contentPane.add(txtIdade);
        
        txtRaca = new JTextField();
        txtRaca.setColumns(10);
        txtRaca.setBounds(131, 196, 175, 20);
        contentPane.add(txtRaca);
        
        txtPorte = new JTextField();
        txtPorte.setColumns(10);
        txtPorte.setBounds(445, 196, 175, 20);
        contentPane.add(txtPorte);
        
        txtEspecie = new JTextField();
        txtEspecie.setColumns(10);
        txtEspecie.setBounds(445, 150, 175, 20);
        contentPane.add(txtEspecie);
        
        txtHora = new JTextField();
        txtHora.setColumns(10);
        txtHora.setBounds(713, 48, 341, 20);
        contentPane.add(txtHora);
        
        JLabel lblHorrio = new JLabel("Horário:");
        lblHorrio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHorrio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 19));
        lblHorrio.setBounds(513, 44, 190, 22);
        contentPane.add(lblHorrio);
        
        JCheckBox chckbxNewCheckBox_2_1_2_1 = new JCheckBox("Castração");
        chckbxNewCheckBox_2_1_2_1.setBounds(260, 521, 100, 30);
        contentPane.add(chckbxNewCheckBox_2_1_2_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Servicos.class.getResource("/img/Fundo2.png")));
        lblNewLabel.setBounds(0, 0, 1065, 681);
        contentPane.add(lblNewLabel);
        

    }
    
    private void agendarPet() {
    	String cliente = txtCliente.getText();
    	String pet = txtPet.getText();
    	String servicos = cmbServicos.getSelectedItem().toString();
    	String descricao = txtDescricao.getText();
    	String idade = txtIdade.getText();
    	String raca = txtRaca.getText();
    	String especie = txtEspecie.getText();
    	String porte = txtPorte.getText();
    	String data = txtAgenda.getText();
    	String hora = txtHora.getText();
    	String obs = txtObs.getText();
    	
    	if(cliente.isEmpty() || pet.isEmpty() || servicos.isEmpty() || porte.isEmpty()) {
    		
    	}
    	
    }
    
}
