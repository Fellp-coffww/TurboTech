package org.com.br.Application.Desktop.View;
import org.com.br.Application.Desktop.Controller.ClienteController;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class NaoApagarView {

    ClienteController clienteController = new ClienteController();


    public NaoApagarView() throws Exception {
    }

    public void init(){
        JFrame frame = new JFrame("Sistema de Gerenciamento de Oficina");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Painel de fundo com cor sólida
        frame.getContentPane().setBackground(new Color(0xA3C4D3));

        // Painel de botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setOpaque(false);
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Botões
        JButton btnCadastroCliente = criarBotao("Cadastro de Cliente");
        JButton btnCadastroPeca = criarBotao("Cadastro de Peça");

        // Adicionar ações aos botões
        btnCadastroCliente.addActionListener(e -> {
            try {
                abrirTelaCadastroCliente();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnCadastroPeca.addActionListener(e -> abrirTelaCadastroPeca());

        // Adicionar botões ao painel
        panelBotoes.add(btnCadastroCliente);
        panelBotoes.add(btnCadastroPeca);

        frame.add(panelBotoes, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private  JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(0x4A9AE9));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setFocusPainted(false);
        return botao;
    }

    private  void abrirTelaCadastroCliente() throws ParseException {
        JFrame cadastroFrame = new JFrame("Cadastro de Cliente");
        cadastroFrame.setSize(900, 700);
        cadastroFrame.setLocationRelativeTo(null);
        cadastroFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos do cadastro
        JLabel lblNome = new JLabel("Nome Completo:");
        JTextField txtNome = new JTextField(20);

        JLabel lblCpfCnpj = new JLabel("Escolha CPF ou CNPJ:");
        String[] opcoesCpfCnpj = {"CNPJ" ,"CPF" };
        JComboBox<String> cbCpfCnpj = new JComboBox<>(opcoesCpfCnpj);

        final String CPF_MASK = "###.###.###-##";
        final String CNPJ_MASK = "##.###.###/####-##";
        MaskFormatter cpfCnpjFormatter;

        cpfCnpjFormatter = new MaskFormatter(CNPJ_MASK);
        cpfCnpjFormatter.setPlaceholderCharacter('_');
        cpfCnpjFormatter.setAllowsInvalid(false);
        cpfCnpjFormatter.setOverwriteMode(true);

        JLabel lblCpfCnpjInput = new JLabel("Digite o CNPJ:");
        JFormattedTextField txtCpfCnpj = new JFormattedTextField(cpfCnpjFormatter);
        txtCpfCnpj.setColumns(14);


        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField(20);

        JLabel lblLogradouro = new JLabel("Logradouro:");
        JTextField txtLogradouro = new JTextField(20);

        JLabel lblNumero = new JLabel("Número:");
        JTextField txtNumero = new JTextField(20);


        JLabel lblComplemento = new JLabel("Complemento:");
        JTextField txtComplemento = new JTextField(20);

        MaskFormatter telefoneFormatter = new MaskFormatter("+## (##) #####-####");
        telefoneFormatter.setPlaceholderCharacter('*');
        telefoneFormatter.setAllowsInvalid(false);
        telefoneFormatter.setOverwriteMode(true);

        JLabel lblTelefone = new JLabel("Telefone (DDI-DDD):");
        JFormattedTextField txtTelefone = new JFormattedTextField(telefoneFormatter);
        txtTelefone.setColumns(13);

        JLabel lblEmail1 = new JLabel("Email 1:");
        JTextField txtEmail1 = new JTextField(20);

        JLabel lblEmail2 = new JLabel("Email 2:");
        JTextField txtEmail2 = new JTextField(20);

        JLabel lblRazaoSocial = new JLabel("Razão Social:");
        JTextField txtRazaoSocial = new JTextField(20);

        JLabel lblInscricaoEstadual = new JLabel("Inscrição Estadual:");
        JTextField txtInscricaoEstadual = new JTextField(20);

        JLabel lblContato = new JLabel("Contato:");
        JTextField txtContato = new JTextField(20);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");

        // Adicionar os campos ao layout
        int row = 0;

        cadastroFrame.add(lblNome, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtNome, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblCpfCnpj, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(cbCpfCnpj, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblCpfCnpjInput, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtCpfCnpj, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblEndereco, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtEndereco, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblLogradouro, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtLogradouro, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblNumero, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtNumero, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblComplemento, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtComplemento, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblTelefone, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtTelefone, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblEmail1, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtEmail1, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblEmail2, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtEmail2, ajustarGbc(gbc, row++, 1));

        // Campos extras para CNPJ
        cbCpfCnpj.addActionListener(e -> {
            boolean isCnpj = cbCpfCnpj.getSelectedItem().toString().equals("CNPJ");
            lblCpfCnpjInput.setText(isCnpj ? "Digite o CNPJ:" : "Digite o CPF:");
            try {
                cpfCnpjFormatter.setMask(isCnpj ? CNPJ_MASK : CPF_MASK);
                cpfCnpjFormatter.install(txtCpfCnpj);
            } catch (ParseException ex) {
                throw new RuntimeException("Erro ao atualizar a máscara.", ex);
            }
            lblRazaoSocial.setVisible(isCnpj);
            txtRazaoSocial.setVisible(isCnpj);
            lblInscricaoEstadual.setVisible(isCnpj);
            txtInscricaoEstadual.setVisible(isCnpj);
            lblContato.setVisible(isCnpj);
            txtContato.setVisible(isCnpj);
        });

        cadastroFrame.add(lblRazaoSocial, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtRazaoSocial, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblInscricaoEstadual, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtInscricaoEstadual, ajustarGbc(gbc, row++, 1));

        cadastroFrame.add(lblContato, ajustarGbc(gbc, row, 0));
        cadastroFrame.add(txtContato, ajustarGbc(gbc, row++, 1));

        // Painel para botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnLimpar);

        gbc.gridwidth = 2;
        cadastroFrame.add(panelBotoes, ajustarGbc(gbc, row++, 0));

        // Ação do botão Salvar
        btnSalvar.addActionListener(e -> {

            try {
                clienteController.criarPessoa(cbCpfCnpj.getSelectedItem().toString(),txtNome.getText().trim(), txtEmail1.getText(),txtTelefone.getText(),
                        txtEndereco.getText(),txtComplemento.getText(),txtNumero.getText(), txtCpfCnpj.getText(), txtInscricaoEstadual.getText(),
                        txtContato.getText(), txtRazaoSocial.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


            JOptionPane.showMessageDialog(cadastroFrame, "Dados salvos com sucesso!");
        });

        // Ação do botão Limpar
        btnLimpar.addActionListener(e -> {
            int confirmacao = JOptionPane.showConfirmDialog(
                    cadastroFrame,
                    "Você tem certeza que deseja limpar todos os dados?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacao == JOptionPane.YES_OPTION) {
                txtNome.setText("");
                txtCpfCnpj.setText("");
                txtEndereco.setText("");
                txtLogradouro.setText("");
                txtNumero.setText("");
                txtComplemento.setText("");
                txtTelefone.setText("");
                txtEmail1.setText("");
                txtEmail2.setText("");
                txtRazaoSocial.setText("");
                txtInscricaoEstadual.setText("");
                txtContato.setText("");
                cbCpfCnpj.setSelectedIndex(0);
            }
        });

        cadastroFrame.setVisible(true);
    }

    private static void abrirTelaCadastroPeca() {
        JFrame pecaFrame = new JFrame("Cadastro de Peça");
        pecaFrame.setSize(500, 400);
        pecaFrame.setLocationRelativeTo(null);
        pecaFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos do cadastro de peça
        JLabel lblNomePeca = new JLabel("Nome da Peça:");
        JTextField txtNomePeca = new JTextField(20);

        JLabel lblCodigoPeca = new JLabel("Código da Peça:");
        JTextField txtCodigoPeca = new JTextField(20);

        JLabel lblPrecoPeca = new JLabel("Preço:");
        JTextField txtPrecoPeca = new JTextField(10);

        JButton btnSalvarPeca = new JButton("Salvar");
        JButton btnLimparPeca = new JButton("Limpar");

        // Adicionar os campos ao layout
        int row = 0;
        pecaFrame.add(lblNomePeca, ajustarGbc(gbc, row, 0));
        pecaFrame.add(txtNomePeca, ajustarGbc(gbc, row++, 1));

        pecaFrame.add(lblCodigoPeca, ajustarGbc(gbc, row, 0));
        pecaFrame.add(txtCodigoPeca, ajustarGbc(gbc, row++, 1));

        pecaFrame.add(lblPrecoPeca, ajustarGbc(gbc, row, 0));
        pecaFrame.add(txtPrecoPeca, ajustarGbc(gbc, row++, 1));

        // Painel para botões
        JPanel panelBotoesPeca = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotoesPeca.add(btnSalvarPeca);
        panelBotoesPeca.add(btnLimparPeca);

        gbc.gridwidth = 2;
        pecaFrame.add(panelBotoesPeca, ajustarGbc(gbc, row++, 0));

        // Ação do botão Salvar
        btnSalvarPeca.addActionListener(e -> {
            JOptionPane.showMessageDialog(pecaFrame, "Peça salva com sucesso!");
        });

        // Ação do botão Limpar
        btnLimparPeca.addActionListener(e -> {
            int confirmacao = JOptionPane.showConfirmDialog(
                    pecaFrame,
                    "Você tem certeza que deseja limpar todos os dados?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacao == JOptionPane.YES_OPTION) {
                txtNomePeca.setText("");
                txtCodigoPeca.setText("");
                txtPrecoPeca.setText("");
            }
        });

        pecaFrame.setVisible(true);
    }
    private static GridBagConstraints ajustarGbc(GridBagConstraints gbc, int row, int col) {
        gbc.gridx = col;
        gbc.gridy = row;
        return gbc;
    }

}


