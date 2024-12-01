package org.com.br.Application.Desktop.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.com.br.Application.Desktop.Controller.ClienteController;

public class PessoaView {

    private static ClienteController clienteController;

    public static void show() {
        JFrame frame = new JFrame("Cadastro de clientes");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PessoaView.class.getResource("/icon.jpg")));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);

        // Carregar a imagem de fundo
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        JPanel tituloPanel = new HomeView.RoundedPanel(20, new Color(0, 0, 0, 150));
        tituloPanel.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Cadastro de clientes", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        tituloPanel.add(titulo, BorderLayout.CENTER);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backgroundLabel.add(tituloPanel, BorderLayout.NORTH);

        // Painel principal para os campos
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setOpaque(false); // Torna o painel transparente
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding interno
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preencher horizontalmente

        // Campos do formulário
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setForeground(Color.white);
        lblNome.setOpaque(true);
        lblNome.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblNome.setBackground(new Color(0, 0, 0, 150));
        JTextField txtNome = new JTextField(20);

        JLabel lblCpfCnpj = new JLabel("Escolha CPF ou CNPJ:");
        lblCpfCnpj.setForeground(Color.white);
        lblCpfCnpj.setOpaque(true);
        lblCpfCnpj.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblCpfCnpj.setBackground(new Color(0, 0, 0, 150));

        String[] opcoesCpfCnpj = {"CNPJ", "CPF"};
        JComboBox<String> cbCpfCnpj = new JComboBox<>(opcoesCpfCnpj);

        final String CPF_MASK = "###.###.###-##";
        final String CNPJ_MASK = "##.###.###/####-##";
        MaskFormatter cpfCnpjFormatter = criarMaskFormatter(CNPJ_MASK);

        JLabel lblCpfCnpjInput = new JLabel("Digite o CNPJ:");
        lblCpfCnpjInput.setForeground(Color.white);
        lblCpfCnpjInput.setOpaque(true);
        lblCpfCnpjInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblCpfCnpjInput.setBackground(new Color(0, 0, 0, 150));
        JFormattedTextField txtCpfCnpj = new JFormattedTextField(cpfCnpjFormatter);
        txtCpfCnpj.setColumns(14);

        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField(20);
        lblEndereco.setForeground(Color.white);
        lblEndereco.setOpaque(true);
        lblEndereco.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblEndereco.setBackground(new Color(0, 0, 0, 150));

        JLabel lblComplemento = new JLabel("Complemento:");
        JTextField txtComplemento = new JTextField(20);
        lblComplemento.setForeground(Color.white);
        lblComplemento.setOpaque(true);
        lblComplemento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblComplemento.setBackground(new Color(0, 0, 0, 150));

        MaskFormatter telefoneFormatter = criarMaskFormatter("+## (##) #####-####");
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setForeground(Color.white);
        lblTelefone.setOpaque(true);
        lblTelefone.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblTelefone.setBackground(new Color(0, 0, 0, 150));

        JFormattedTextField txtTelefone = new JFormattedTextField(telefoneFormatter);
        txtTelefone.setColumns(15);

        JLabel lblEmail1 = new JLabel("Email 1:");
        lblEmail1.setForeground(Color.white);
        lblEmail1.setOpaque(true);
        lblEmail1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblEmail1.setBackground(new Color(0, 0, 0, 150));
        JTextField txtEmail1 = new JTextField(20);

        JLabel lblRazaoSocial = new JLabel("Razão Social:");
        lblRazaoSocial.setForeground(Color.white);
        lblRazaoSocial.setOpaque(true);
        lblRazaoSocial.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblRazaoSocial.setBackground(new Color(0, 0, 0, 150));
        JTextField txtRazaoSocial = new JTextField(20);

        JLabel lblInscricaoEstadual = new JLabel("Inscrição Estadual:");
        lblInscricaoEstadual.setForeground(Color.white);
        lblInscricaoEstadual.setOpaque(true);
        lblInscricaoEstadual.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblInscricaoEstadual.setBackground(new Color(0, 0, 0, 150));
        JTextField txtInscricaoEstadual = new JTextField(20);

        JLabel lblContato = new JLabel("Contato:");
        lblContato.setForeground(Color.white);
        lblContato.setOpaque(true);
        lblContato.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblContato.setBackground(new Color(0, 0, 0, 150));
        JTextField txtContato = new JTextField(20);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        JButton btnLimpar = new JButton("Limpar");
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> frame.dispose());

        // Adicionando os campos ao painel com GridBagLayout
        int row = 0;

        panelForm.add(lblNome, ajustarGbc(gbc, row, 0));
        panelForm.add(txtNome, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblCpfCnpj, ajustarGbc(gbc, row, 0));
        panelForm.add(cbCpfCnpj, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblCpfCnpjInput, ajustarGbc(gbc, row, 0));
        panelForm.add(txtCpfCnpj, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblEndereco, ajustarGbc(gbc, row, 0));
        panelForm.add(txtEndereco, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblComplemento, ajustarGbc(gbc, row, 0));
        panelForm.add(txtComplemento, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblTelefone, ajustarGbc(gbc, row, 0));
        panelForm.add(txtTelefone, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblEmail1, ajustarGbc(gbc, row, 0));
        panelForm.add(txtEmail1, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblRazaoSocial, ajustarGbc(gbc, row, 0));
        panelForm.add(txtRazaoSocial, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblInscricaoEstadual, ajustarGbc(gbc, row, 0));
        panelForm.add(txtInscricaoEstadual, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblContato, ajustarGbc(gbc, row, 0));
        panelForm.add(txtContato, ajustarGbc(gbc, row++, 1));

        // Painel para os botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotoes.setOpaque(false);
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnCancelar); // Botão Cancelar adicionado

        // Adicionar os painéis ao fundo
        backgroundLabel.add(panelForm, BorderLayout.CENTER);
        backgroundLabel.add(panelBotoes, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static MaskFormatter criarMaskFormatter(String mascara) {
        try {
            return new MaskFormatter(mascara);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao criar máscara de formato: " + e.getMessage());
        }
    }

    private static GridBagConstraints ajustarGbc(GridBagConstraints gbc, int row, int col) {
        gbc.gridx = col;
        gbc.gridy = row;
        return gbc;
    }
}
