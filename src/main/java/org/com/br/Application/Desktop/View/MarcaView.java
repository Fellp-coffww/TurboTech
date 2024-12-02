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
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.com.br.Application.Desktop.Controller.MarcaController;

public class MarcaView {

    public static void show() throws Exception {

        JFrame novaTela = new JFrame("Cadastro de Marca");
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
        backgroundLabelCombobox.setOpaque(true);

        JPanel tituloPanel = new JPanel(new BorderLayout());
        tituloPanel.setBackground(new Color(0, 0, 0, 150));

        JLabel titulo = new JLabel("Cadastro de Marca", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        tituloPanel.add(titulo, BorderLayout.CENTER);

        JPanel panelEntrada = new JPanel(new GridBagLayout());
        panelEntrada.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblMarca = new JLabel("Marca do Carro:");
        lblMarca.setForeground(Color.WHITE);
        lblMarca.setOpaque(true);
        lblMarca.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblMarca.setBackground(new Color(0, 0, 0, 150));

        JTextField txtMarca = new JTextField(20);
        txtMarca.setToolTipText("Digite a marca do carro");

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);

        MarcaController marcaController = new MarcaController(novaTela);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    marcaController.criarMarca(txtMarca.getText());
                    txtMarca.setText(""); // Limpar campo após salvar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(novaTela, "Erro ao salvar a marca: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                novaTela.dispose();
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(lblMarca, gbc);

        gbc.gridx = 1;
        panelEntrada.add(txtMarca, gbc);

        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false);
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelEntrada.add(panelButtons, gbc);

        backgroundLabelCombobox.add(tituloPanel, BorderLayout.NORTH);
        backgroundLabelCombobox.add(panelEntrada, BorderLayout.CENTER);

        novaTela.setContentPane(backgroundLabelCombobox);
        novaTela.setVisible(true);
    }
}


