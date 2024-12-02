package org.com.br.Application.Desktop.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


import org.com.br.Application.Desktop.Controller.PecaController;

public class PecaView {
    public static void show() {
        JFrame novaTela = new JFrame("Cadastro de Peças");
        novaTela.setIconImage(Toolkit.getDefaultToolkit().getImage(PessoaView.class.getResource("/icon.jpg")));
        novaTela.setSize(700, 700); // Tamanho da tela
        novaTela.setLocationRelativeTo(null); // Centralizar a tela
        novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaTela.setLayout(new BorderLayout());

        // Carregar a imagem de fundo
        ImageIcon imageIconCombobox = new ImageIcon(HomeView.class.getResource("/JELF DYNAMICS.jpg"));
        Image imageCombobox = imageIconCombobox.getImage();
        Image resizedImageCombobox = imageCombobox.getScaledInstance(novaTela.getWidth(), novaTela.getHeight(), Image.SCALE_SMOOTH);

        JLabel backgroundLabelCombobox = new JLabel(new ImageIcon(resizedImageCombobox));
        backgroundLabelCombobox.setLayout(new BorderLayout());

        // Criar painel para o título
        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BorderLayout());
        tituloPanel.setBackground(new Color(0, 0, 0, 150)); // Preto transparente

        JLabel titulo = new JLabel("Cadastro de Peças", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        tituloPanel.add(titulo, BorderLayout.CENTER);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de entrada de dados
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridBagLayout());
        panelEntrada.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo: Código da Peça
        JLabel lblCodigo = criarLabel("Código da Peça:");
        JTextField txtCodigo = criarTextField(20);

        // Campo: Nome da Peça
        JLabel lblNome = criarLabel("Nome da Peça:");
        JTextField txtNome = criarTextField(20);

        // Campo: Quantidade (apenas números inteiros)
        JLabel lblQuantidade = criarLabel("Quantidade:");
        JTextField txtQuantidade = criarTextField(20);
        ((AbstractDocument) txtQuantidade.getDocument()).setDocumentFilter(new NumericDocumentFilter(false));

        // Campo: Valor por Unidade (apenas números decimais)
        JLabel lblValor = criarLabel("Valor por Unidade:");
        JTextField txtValor = criarTextField(20);
        ((AbstractDocument) txtValor.getDocument()).setDocumentFilter(new NumericDocumentFilter(true));


        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34)); // Verde
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText().trim();
                    Integer quantidade = Integer.valueOf(txtQuantidade.getText().trim());
                    double valor = Double.valueOf(txtValor.getText().trim());
                    String codigo = txtCodigo.getText().trim();

                    PecaController pecaController = new PecaController(novaTela);
                    pecaController.criarPeca(nome, quantidade, valor, codigo);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(novaTela, "Erro ao salvar peça: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txtCodigo.setText("");
                    txtNome.setText("");
                    txtQuantidade.setText("");
                    txtValor.setText("");
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60)); // Vermelho
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> novaTela.dispose());

        // Adicionar os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(lblCodigo, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(lblNome, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEntrada.add(lblQuantidade, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtQuantidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelEntrada.add(lblValor, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtValor, gbc);

        // Adicionar os botões
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false);
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelEntrada.add(panelButtons, gbc);

        backgroundLabelCombobox.add(panelEntrada, BorderLayout.CENTER);
        backgroundLabelCombobox.add(tituloPanel, BorderLayout.NORTH);

        novaTela.setContentPane(backgroundLabelCombobox);
        novaTela.setVisible(true);
    }

    /**
     * Cria um JLabel com estilo padrão.
     */
    private static JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 28));
        label.setBackground(new Color(0, 0, 0, 150));
        label.setOpaque(true);
        return label;
    }

    /**
     * Cria um JTextField com estilo padrão.
     */
    private static JTextField criarTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setForeground(Color.BLACK);
        return textField;
    }

    /**
     * Classe para limitar entrada de texto a valores numéricos.
     */
    /**
     * Classe para limitar entrada de texto a valores numéricos, aceitando ponto ou vírgula como separador decimal.
     */
    private static class NumericDocumentFilter extends DocumentFilter {
        private final boolean allowDecimal;

        public NumericDocumentFilter(boolean allowDecimal) {
            this.allowDecimal = allowDecimal;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            if (isValidInput(string, fb.getDocument().getText(0, fb.getDocument().getLength()))) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) return;
            if (isValidInput(text, fb.getDocument().getText(0, fb.getDocument().getLength()))) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isValidInput(String text, String currentText) {
            try {
                String combinedText = currentText + text;

                if (allowDecimal) {
                    // Substituir vírgula por ponto para validação
                    combinedText = combinedText.replace(",", ".");
                    Double.parseDouble(combinedText);
                } else {
                    Integer.parseInt(combinedText);
                }
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }




}
