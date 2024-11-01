package pet.window;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainPage frame = new MainPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainPage() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 29, 1284, 617);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblFundoPrincipal = new JLabel("");
        lblFundoPrincipal.setIcon(new ImageIcon(MainPage.class.getResource("/img/Cream Brown And Orange Illustration Event Birthday Presentation 3.png")));
        lblFundoPrincipal.setBounds(0, 0, 1284, 616);
        panel.add(lblFundoPrincipal);

        JLabel lblLogomarca = new JLabel("      © 2024 PetShop");
        lblLogomarca.setBounds(0, 649, 415, 32);
        contentPane.add(lblLogomarca);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1064, 32);
        contentPane.add(menuBar);

        JMenu mnUsuario = new JMenu("Usuários");
        mnUsuario.setIcon(new ImageIcon(MainPage.class.getResource("/img/concluir.png")));
        menuBar.add(mnUsuario);

        JMenu mnCadastrar = new JMenu("Cadastrar");
        mnCadastrar.setIcon(new ImageIcon(MainPage.class.getResource("/img/adiciona.png")));
        mnUsuario.add(mnCadastrar);

        JMenuItem mntmFuncionario = new JMenuItem("Funcionário");
        mntmFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Funcionario().setVisible(true);
                dispose();
            }
        });
        mnCadastrar.add(mntmFuncionario);

        JMenuItem mntmCliente = new JMenuItem("Cliente");
        mntmCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Cliente().setVisible(true);
                dispose();
            }
        });
        mnCadastrar.add(mntmCliente);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Usuário");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Usuarios().setVisible(true);
                dispose();
            }
        });
        mnCadastrar.add(mntmNewMenuItem_1);

        JMenuItem mntmExit = new JMenuItem("Sair");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        mnUsuario.add(mntmExit);

        JMenu mnEstoque = new JMenu("Loja");
        mnEstoque.setIcon(new ImageIcon(MainPage.class.getResource("/img/shopping-cart.png")));
        menuBar.add(mnEstoque);

        JMenuItem mntmCadastrarEstoque = new JMenuItem("Cadastrar");
        mntmCadastrarEstoque.setIcon(new ImageIcon(MainPage.class.getResource("/img/adiciona.png")));
        mntmCadastrarEstoque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CadastrarEstoque().setVisible(true);
                dispose();
            }
        });
        mnEstoque.add(mntmCadastrarEstoque);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Comprar");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Loja().setVisible(true);
            }
        });
        mntmNewMenuItem_2.setIcon(new ImageIcon(MainPage.class.getResource("/img/shopping-cart.png")));
        mnEstoque.add(mntmNewMenuItem_2);

        // New "Animais" Menu Item
        JMenuItem mntmAnimais = new JMenuItem("Animais");
        mntmAnimais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Animais().setVisible(true);
                dispose(); // Optionally close the current frame
            }
        });
        mnEstoque.add(mntmAnimais);

        JMenu mnServicos = new JMenu("Serviços");
        mnServicos.setIcon(new ImageIcon("F:\\Codes\\Java\\SistemaPetShop\\src\\img\\Serviços.png"));
        menuBar.add(mnServicos);

        JMenuItem mntmNewMenuItem = new JMenuItem("Cadastrar");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Servicos().setVisible(true);
                dispose();
            }
        });
        mnServicos.add(mntmNewMenuItem);

        // New "Listar" Menu
        JMenu mnListar = new JMenu("Listar");
        menuBar.add(mnListar);

        JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
        mntmListarClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListarClientes().setVisible(true);
                dispose();
            }
        });
        mnListar.add(mntmListarClientes);
        
        // New menu item for listing Funcionarios
        JMenuItem mntmListarFuncionarios = new JMenuItem("Listar Funcionários");
        mntmListarFuncionarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListFuncionarios().setVisible(true);
                dispose();
            }
        });
        mnListar.add(mntmListarFuncionarios);
        
                JMenuItem mntmNewMenuItem_3 = new JMenuItem("Estoque");
                mnListar.add(mntmNewMenuItem_3);
                mntmNewMenuItem_3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Estoque().setVisible(true);
                        dispose(); // Fecha a janela atual
                    }
                });
                mntmNewMenuItem_3.setIcon(new ImageIcon(MainPage.class.getResource("/img/shopping-cart.png")));
    }
}
