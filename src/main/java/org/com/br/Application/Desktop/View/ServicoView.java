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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServicoView {
    public static void show() {
        JFrame novaTela = new JFrame("Cadastro do Serviço");
        novaTela.setSize(700, 700); // Tamanho da tela
        novaTela.setLocationRelativeTo(null); // Centralizar a tela
        novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaTela.setLayout(new BorderLayout());

        // Carregar a imagem de fundo
        ImageIcon imageIconCombobox = new ImageIcon(HomeView.class.getResource("/background_combobox.jpg"));
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

        JLabel titulo = new JLabel("Cadastro do Serviço", JLabel.CENTER);
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

        // Campo: Nome da Peça
        JLabel lblNome = new JLabel("Nome do Serviço:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setOpaque(true);
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblNome.setBackground(new Color(0, 0, 0, 150));

        JTextField txtNome = new JTextField(20);
        txtNome.setForeground(Color.BLACK);
        txtNome.setToolTipText("Insira o nome do serviço");

        // Campo: Valor por Unidade
        JLabel lblValor = new JLabel("Valor por Unidade:");
        lblValor.setForeground(Color.WHITE);
        lblValor.setOpaque(true);
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblValor.setBackground(new Color(0, 0, 0, 150));

        JTextField txtValor = new JTextField(20);
        txtValor.setForeground(Color.BLACK);
        txtValor.setToolTipText("Insira o valor por unidade");

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34)); // Cor verde
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String valor = txtValor.getText();

                // Validar os campos antes de salvar
                if (nome.isEmpty() || valor.isEmpty()) {
                    JOptionPane.showMessageDialog(novaTela, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(novaTela, "Peça salva com sucesso!");
                    
                    txtNome.setText("");
                    txtValor.setText("");
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60)); // Cor vermelha
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                novaTela.dispose(); // Fechar a tela
            }
        });

        // Adicionar os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(lblNome, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelEntrada.add(lblValor, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtValor, gbc);

        // Adicionar os botões
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false); // Tornar o painel de botões transparente
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelEntrada.add(panelButtons, gbc);

        // Adicionar o painel de entrada ao fundo
        backgroundLabelCombobox.add(panelEntrada, BorderLayout.CENTER);

        // Adicionar o painel do título no topo
        backgroundLabelCombobox.add(tituloPanel, BorderLayout.NORTH);

        // Definir o conteúdo da janela
        novaTela.setContentPane(backgroundLabelCombobox);
        novaTela.setVisible(true);
    }
}
