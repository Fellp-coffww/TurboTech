package org.com.br.Application.Desktop.View;

import javax.swing.*;
import java.awt.*;

public class MarcaView {

    public static void show(){

        JFrame novaTela = new JFrame("Cadastro de Marca");
        novaTela.setSize(400, 300); // Tamanho menor para essa tela
        novaTela.setLocationRelativeTo(null);
        novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaTela.setLayout(new BorderLayout());

        // Carregar a imagem de fundo das telas das comboboxes
        ImageIcon imageIconCombobox = new ImageIcon(HomeView.class.getResource("/background_combobox.jpg")); // Caminho da imagem das comboboxes
        Image imageCombobox = imageIconCombobox.getImage();
        Image resizedImageCombobox = imageCombobox.getScaledInstance(novaTela.getWidth(), novaTela.getHeight(), Image.SCALE_SMOOTH);

        // Criar o JLabel para exibir a imagem de fundo das telas das comboboxes
        JLabel backgroundLabelCombobox = new JLabel(new ImageIcon(resizedImageCombobox));
        backgroundLabelCombobox.setLayout(new BorderLayout()); // Definir o layout para o fundo

        // Painel de entrada de dados
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridBagLayout());
        panelEntrada.setOpaque(false); // Tornar o painel transparente

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos de entrada
        JLabel lblMarca = new JLabel("Marca do Carro:");
        lblMarca.setForeground(Color.black); // Alterar a cor do texto para branco

        JTextField txtMarca = new JTextField(20);
        txtMarca.setForeground(Color.BLACK); // Alterar a cor do texto para preto

        JLabel lblObservacao = new JLabel("Observação (Opcional):");
        lblObservacao.setForeground(Color.black); // Alterar a cor do texto para branco

        JTextField txtObservacao = new JTextField(20);
        txtObservacao.setForeground(Color.BLACK); // Alterar a cor do texto para preto

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> JOptionPane.showMessageDialog(novaTela, "Salvo com sucesso!")); // Exibir mensagem ao salvar

        // Adicionar componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(lblMarca, gbc);

        gbc.gridx = 1;
        panelEntrada.add(txtMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(lblObservacao, gbc);

        gbc.gridx = 1;
        panelEntrada.add(txtObservacao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelEntrada.add(btnSalvar, gbc);

        // Adicionar o painel de entrada ao fundo
        backgroundLabelCombobox.add(panelEntrada, BorderLayout.CENTER);

        novaTela.setContentPane(backgroundLabelCombobox); // Definir o painel de fundo como o conteúdo
        novaTela.setVisible(true);

    }

}
