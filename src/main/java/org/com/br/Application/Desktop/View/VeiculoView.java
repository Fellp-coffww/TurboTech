package org.com.br.Application.Desktop.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VeiculoView {

    public static void show() {
        JFrame novaTela = new JFrame("Cadastro de Veículo");
        novaTela.setSize(700, 700); // Tamanho da tela
        novaTela.setLocationRelativeTo(null); // Centralizar a tela
        novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaTela.setLayout(new BorderLayout());

        // Carregar a imagem de fundo
        ImageIcon imageIcon = new ImageIcon(VeiculoView.class.getResource("/background_combobox.jpg"));
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(novaTela.getWidth(), novaTela.getHeight(), Image.SCALE_SMOOTH);

        // Criar o JLabel para exibir a imagem de fundo
        JLabel backgroundLabel = new JLabel(new ImageIcon(resizedImage));
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setOpaque(true); // Tornar o fundo opaco para a visibilidade dos campos

        // Criar painel para o título com fundo preto transparente
        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BorderLayout());
        tituloPanel.setBackground(new Color(0, 0, 0, 150)); // Cor preta com transparência (alpha 150)

        JLabel titulo = new JLabel("Cadastro de Veículo", JLabel.CENTER);
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

        // Campo de Marca do Veículo
        JPanel campoMarcaPanel = new JPanel();
        campoMarcaPanel.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente
        campoMarcaPanel.setLayout(new BorderLayout());

        JLabel lblMarca = new JLabel("Marca do Veículo:");
        lblMarca.setForeground(Color.white);
        lblMarca.setOpaque(true);
        lblMarca.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblMarca.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente para o rótulo

        // ComboBox para Marca do Veículo
        List<String> listaMarcas = new ArrayList<>();
        listaMarcas.add("Toyota");
        listaMarcas.add("Honda");
        listaMarcas.add("Ford");
        listaMarcas.add("Chevrolet");
        listaMarcas.add("Nissan");

        JComboBox<String> cmbMarca = new JComboBox<>(listaMarcas.toArray(new String[0]));
        cmbMarca.setForeground(Color.BLACK);
        cmbMarca.setToolTipText("Selecione a marca do veículo");

        // Adicionar o JComboBox ao painel
        campoMarcaPanel.add(cmbMarca, BorderLayout.CENTER);

        // Campo de Modelo do Veículo
        JLabel lblModelo = new JLabel("Modelo do Veículo:");
        lblModelo.setForeground(Color.white);
        lblModelo.setOpaque(true);
        lblModelo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblModelo.setBackground(new Color(0, 0, 0, 150));

        JComboBox<String> cmbModelo = new JComboBox<>(new String[]{"Corolla", "Civic", "F-150", "Onix", "Altima"});
        cmbModelo.setForeground(Color.BLACK);
        cmbModelo.setToolTipText("Selecione o modelo do veículo");

        // Campo de Placa do Veículo
        JLabel lblPlaca = new JLabel("Placa do Veículo:");
        lblPlaca.setForeground(Color.white);
        lblPlaca.setOpaque(true);
        lblPlaca.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblPlaca.setBackground(new Color(0, 0, 0, 150));

        JTextField txtPlaca = new JTextField(10);
        txtPlaca.setForeground(Color.BLACK);
        txtPlaca.setToolTipText("Digite a placa do veículo");

        // Campo de Chassi do Veículo
        JLabel lblChassi = new JLabel("Número do Chassi:");
        lblChassi.setForeground(Color.white);
        lblChassi.setOpaque(true);
        lblChassi.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblChassi.setBackground(new Color(0, 0, 0, 150));

        JTextField txtChassi = new JTextField(17);
        txtChassi.setForeground(Color.BLACK);
        txtChassi.setToolTipText("Digite o número do chassi do veículo");

        // Campo de Quilometragem do Veículo
        JLabel lblQuilometragem = new JLabel("Quilometragem:");
        lblQuilometragem.setForeground(Color.white);
        lblQuilometragem.setOpaque(true);
        lblQuilometragem.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblQuilometragem.setBackground(new Color(0, 0, 0, 150));

        JTextField txtQuilometragem = new JTextField(10);
        txtQuilometragem.setForeground(Color.BLACK);
        txtQuilometragem.setToolTipText("Digite a quilometragem do veículo");

        // Campo de Número do Patrimônio
        JLabel lblPatrimonio = new JLabel("Número do Patrimônio:");
        lblPatrimonio.setForeground(Color.white);
        lblPatrimonio.setOpaque(true);
        lblPatrimonio.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblPatrimonio.setBackground(new Color(0, 0, 0, 150));

        JTextField txtPatrimonio = new JTextField(10);
        txtPatrimonio.setForeground(Color.BLACK);
        txtPatrimonio.setToolTipText("Digite o número de patrimônio");

        // Campo de Ano do Veículo (usando JComboBox para ano)
        JLabel lblAnoVeiculo = new JLabel("Ano do Veículo:");
        lblAnoVeiculo.setForeground(Color.white);
        lblAnoVeiculo.setOpaque(true);
        lblAnoVeiculo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblAnoVeiculo.setBackground(new Color(0, 0, 0, 150));

        // Criar uma lista de anos para selecionar
        List<Integer> listaAnos = new ArrayList<>();
        for (int i = 1980; i <= 2024; i++) {
            listaAnos.add(i);
        }

        JComboBox<Integer> cmbAnoVeiculo = new JComboBox<>(listaAnos.toArray(new Integer[0]));
        cmbAnoVeiculo.setForeground(Color.BLACK);
        cmbAnoVeiculo.setToolTipText("Selecione o ano do veículo");

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34)); // Cor verde
        btnSalvar.setForeground(Color.white);
        btnSalvar.setFocusPainted(false);
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(novaTela, "Veículo salvo com sucesso!");
            cmbMarca.setSelectedIndex(0);  // Resetar a seleção da combo box
            txtPlaca.setText("");
            txtChassi.setText("");
            txtQuilometragem.setText("");
            txtPatrimonio.setText("");
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60)); // Cor vermelha
        btnCancelar.setForeground(Color.white);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> novaTela.dispose()); // Fechar a tela

        // Adicionar os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(lblMarca, gbc);
        gbc.gridx = 1;
        panelEntrada.add(campoMarcaPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(lblModelo, gbc);
        gbc.gridx = 1;
        panelEntrada.add(cmbModelo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEntrada.add(lblPlaca, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtPlaca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelEntrada.add(lblChassi, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtChassi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelEntrada.add(lblQuilometragem, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtQuilometragem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelEntrada.add(lblPatrimonio, gbc);
        gbc.gridx = 1;
        panelEntrada.add(txtPatrimonio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelEntrada.add(lblAnoVeiculo, gbc);
        gbc.gridx = 1;
        panelEntrada.add(cmbAnoVeiculo, gbc);

        // Botões "Salvar" e "Cancelar"
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false);
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 1;
        gbc.gridy = 7;
        panelEntrada.add(panelButtons, gbc);

        // Adicionar o painel de entrada ao fundo
        backgroundLabel.add(panelEntrada, BorderLayout.CENTER);

        // Adicionar o painel do título no topo
        backgroundLabel.add(tituloPanel, BorderLayout.NORTH);

        // Definir o conteúdo da janela
        novaTela.setContentPane(backgroundLabel);
        novaTela.setVisible(true);
    }
}
