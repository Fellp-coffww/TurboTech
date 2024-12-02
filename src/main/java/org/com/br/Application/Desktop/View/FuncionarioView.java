package org.com.br.Application.Desktop.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.com.br.Application.Desktop.Controller.FuncionarioController;

public class FuncionarioView {

    public static void show() {
        JFrame novaTela = new JFrame("Cadastro de Funcionário");
        novaTela.setSize(700, 700); // Tamanho da tela
        novaTela.setLocationRelativeTo(null); // Centralizar a tela
        novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaTela.setLayout(new BorderLayout());

        // Carregar a imagem de fundo
        ImageIcon imageIconCombobox = new ImageIcon(HomeView.class.getResource("/JELF DYNAMICS.jpg"));
        Image imageCombobox = imageIconCombobox.getImage();
        Image resizedImageCombobox = imageCombobox.getScaledInstance(novaTela.getWidth(), novaTela.getHeight(), Image.SCALE_SMOOTH);

        // Criar o JLabel para exibir a imagem de fundo
        JLabel backgroundLabelCombobox = new JLabel(new ImageIcon(resizedImageCombobox));
        backgroundLabelCombobox.setLayout(new BorderLayout());
        backgroundLabelCombobox.setOpaque(true); // Tornar o fundo opaco para a visibilidade dos campos

        // Criar painel para o título com fundo preto transparente
        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BorderLayout());
        tituloPanel.setBackground(new Color(0, 0, 0, 150)); // Cor preta com transparência (alpha 150)

        JLabel titulo = new JLabel("Cadastro de Funcionário", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28)); // Fonte do título
        titulo.setForeground(Color.WHITE); // Cor do título

        tituloPanel.add(titulo, BorderLayout.CENTER);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno

        // Painel de entrada de dados
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridBagLayout());
        panelEntrada.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo Nome
        JLabel lblNome = new JLabel("Nome do Funcionário:");
        lblNome.setForeground(Color.white);
        lblNome.setOpaque(true);
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblNome.setBackground(new Color(0, 0, 0, 150));

        JTextField txtNome = new JTextField(20);
        txtNome.setToolTipText("Digite o nome do funcionário");

        // Campo CPF com máscara
        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setForeground(Color.white);
        lblCPF.setOpaque(true);
        lblCPF.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblCPF.setBackground(new Color(0, 0, 0, 150));

        JFormattedTextField txtCPF = new JFormattedTextField(); // Inicialização padrão
        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.setPlaceholderCharacter('_'); // Define o caractere de preenchimento
            maskCPF.install(txtCPF); // Aplica a máscara no campo
        } catch (Exception e) {
            JOptionPane.showMessageDialog(novaTela, "Erro ao configurar a máscara do CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        txtCPF.setColumns(20);
        txtCPF.setToolTipText("Digite o CPF no formato ***.***.***-**");

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34)); // Cor verde
        btnSalvar.setForeground(Color.white);
        btnSalvar.setFocusPainted(false);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText().trim();
                    String CPF = txtCPF.getText().trim();

                    FuncionarioController funcionarioController = new FuncionarioController(novaTela);
                    funcionarioController.criarFuncionario(nome, CPF.replaceAll("\\D", ""));
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(novaTela, "Erro ao salvar o funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txtNome.setText("");
                    txtCPF.setText("");
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60)); // Cor vermelha
        btnCancelar.setForeground(Color.white);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                novaTela.dispose(); // Fechar a tela
            }
        });

        // Adicionar os componentes ao painel de entrada
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(lblNome, gbc);

        gbc.gridx = 1;
        panelEntrada.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(lblCPF, gbc);

        gbc.gridx = 1;
        panelEntrada.add(txtCPF, gbc);

        // Painel de botões
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false); // Tornar o painel de botões transparente
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelEntrada.add(panelButtons, gbc);

        // Adicionar os painéis ao fundo
        backgroundLabelCombobox.add(panelEntrada, BorderLayout.CENTER);

        // Adicionar o painel do título no topo
        backgroundLabelCombobox.add(tituloPanel, BorderLayout.NORTH);

        // Definir o conteúdo da janela
        novaTela.setContentPane(backgroundLabelCombobox);
        novaTela.setVisible(true);
    }
}
