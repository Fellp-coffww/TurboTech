package org.com.br.Application.Desktop.View;

import org.com.br.Application.Desktop.Controller.MarcaController;

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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MarcaView {

    public static void show() throws Exception {

        JFrame novaTela = new JFrame("Cadastro de Marca");
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

        JLabel titulo = new JLabel("Cadastro de Marca", JLabel.CENTER);
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

        // Criar o fundo preto transparente para o título "Marca" e o campo de texto "Marca"
        JPanel campoMarcaPanel = new JPanel();
        campoMarcaPanel.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente
        campoMarcaPanel.setLayout(new BorderLayout());

        // Campos de entrada
        JLabel lblMarca = new JLabel("Marca do Carro:");
        lblMarca.setForeground(Color.white);
        lblMarca.setOpaque(true);
        lblMarca.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblMarca.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente para o rótulo "Marca"

        JTextField txtMarca = new JTextField(20);
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setToolTipText("Digite a marca do carro");

        // Adicionar os campos aos seus respectivos painéis com fundo preto transparente
        campoMarcaPanel.add(txtMarca, BorderLayout.CENTER);
        MarcaController controller = new MarcaController(novaTela);

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34)); // Cor verde
        btnSalvar.setForeground(Color.white);
        btnSalvar.setFocusPainted(false);
        MarcaController marcaController = new MarcaController(novaTela);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    marcaController.criarMarca(txtMarca.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                txtMarca.setText("");  // Limpar campos após salvar
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

        // Adicionar os componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(lblMarca, gbc);

        gbc.gridx = 1;
        panelEntrada.add(campoMarcaPanel, gbc); // Adicionar painel com fundo preto transparente para o campo "Marca"


        // Botões "Salvar" e "Cancelar"
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false); // Tornar o painel de botões transparente
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 1;
        gbc.gridy = 2;
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
