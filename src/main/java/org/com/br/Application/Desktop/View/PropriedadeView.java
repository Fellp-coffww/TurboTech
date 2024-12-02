package org.com.br.Application.Desktop.View;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import org.com.br.Application.Desktop.Controller.ClienteController;

public class PropriedadeView {

    private static ClienteController clienteController;

    public static void show() {
        JFrame frame = new JFrame("Cadastro de clientes");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PessoaView.class.getResource("/icon.jpg")));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);

        // Painel de fundo
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        // Painel do título
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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos do formulário
        JLabel lblNome = criarLabel("Nome Completo:");
        JTextField txtNome = new JTextField(20);

        JLabel lblCpfCnpj = criarLabel("Escolha CPF ou CNPJ:");
        JComboBox<String> cbCpfCnpj = new JComboBox<>(new String[]{"CNPJ", "CPF"});

        JLabel lblCpfCnpjInput = criarLabel("Digite o CNPJ:");
        JTextField txtCpfCnpj = new JTextField(20);

        JLabel lblDataInicio = criarLabel("Data de Início:");
        JSpinner spinnerDataInicio = criarDateSpinner();

        JLabel lblDataFim = criarLabel("Data de Fim:");
        JSpinner spinnerDataFim = criarDateSpinner();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnCancelar = new JButton("Cancelar");

        // Adicionar os componentes ao painel com GridBagLayout
        int row = 0;
        panelForm.add(lblNome, ajustarGbc(gbc, row, 0));
        panelForm.add(txtNome, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblCpfCnpj, ajustarGbc(gbc, row, 0));
        panelForm.add(cbCpfCnpj, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblCpfCnpjInput, ajustarGbc(gbc, row, 0));
        panelForm.add(txtCpfCnpj, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblDataInicio, ajustarGbc(gbc, row, 0));
        panelForm.add(spinnerDataInicio, ajustarGbc(gbc, row++, 1));

        panelForm.add(lblDataFim, ajustarGbc(gbc, row, 0));
        panelForm.add(spinnerDataFim, ajustarGbc(gbc, row++, 1));

        // Painel para botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotoes.setOpaque(false);
        btnSalvar.setBackground(new Color(34, 139, 34)); // Verde
        btnSalvar.setForeground(Color.white);
        btnSalvar.setFocusPainted(false);
        btnCancelar.setBackground(new Color(220, 20, 60)); // Vermelho
        btnCancelar.setForeground(Color.white);
        btnCancelar.setFocusPainted(false);

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnCancelar);

        // Ação do botão Salvar
        btnSalvar.addActionListener(e -> {
            try {
                Date dataInicio = (Date) spinnerDataInicio.getValue();
                Date dataFim = (Date) spinnerDataFim.getValue();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataInicioStr = sdf.format(dataInicio);
                String dataFimStr = sdf.format(dataFim);

                clienteController = new ClienteController(frame);
                // clienteController.salvar(..., dataInicioStr, dataFimStr);
                JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão Limpar
        btnLimpar.addActionListener(e -> {
            txtNome.setText("");
            txtCpfCnpj.setText("");
            spinnerDataInicio.setValue(new Date());
            spinnerDataFim.setValue(new Date());
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(e -> frame.dispose());

        // Atualizar campo CPF/CNPJ ao mudar seleção
        cbCpfCnpj.addActionListener(e -> {
            lblCpfCnpjInput.setText(cbCpfCnpj.getSelectedItem().toString().equals("CNPJ") ? "Digite o CNPJ:" : "Digite o CPF:");
        });

        // Adicionar painéis ao frame
        backgroundLabel.add(panelForm, BorderLayout.CENTER);
        backgroundLabel.add(panelBotoes, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JLabel criarLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setBackground(new Color(0, 0, 0, 150));
        return label;
    }

    private static JSpinner criarDateSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);
        spinner.setValue(new Date()); // Define a data inicial como a data atual
        return spinner;
    }

    private static GridBagConstraints ajustarGbc(GridBagConstraints gbc, int row, int col) {
        gbc.gridx = col;
        gbc.gridy = row;
        return gbc;
    }
}
