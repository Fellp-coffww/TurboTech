package org.com.br.Application.Desktop.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.com.br.Application.Desktop.Controller.MarcaController;
import org.com.br.Core.Domain.Models.Marca;

public class MarcaEditView {

    public static void show() throws Exception {

        JFrame editTela = new JFrame("Editar Marca");
        editTela.setSize(500, 300); // Tamanho da tela
        editTela.setLocationRelativeTo(null); // Centralizar a tela
        editTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editTela.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos de entrada para edição
        JLabel lblNomeMarca = new JLabel("Nome da Marca:");
        lblNomeMarca.setFont(new Font("SansSerif", Font.BOLD, 16));

        JTextField txtNomeMarca = new JTextField(20);
        txtNomeMarca.setToolTipText("Digite o nome da marca que deseja editar");

        JLabel lblNovoNome = new JLabel("Novo Nome da Marca:");
        lblNovoNome.setFont(new Font("SansSerif", Font.BOLD, 16));

        JTextField txtNovoNome = new JTextField(20);
        txtNovoNome.setToolTipText("Digite o novo nome da marca");

        // Botão "Salvar"
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34)); // Cor verde
        btnSalvar.setForeground(Color.white);
        btnSalvar.setFocusPainted(false);

        MarcaController marcaController = new MarcaController(editTela);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Pega o nome da marca que o usuário quer editar
                    String nomeMarca = txtNomeMarca.getText().trim();

                    // Verifica se o nome da marca está vazio
                    if (nomeMarca.isEmpty()) {
                        JOptionPane.showMessageDialog(editTela, "Por favor, informe o nome da marca!");
                        return;
                    }

                    // Busca a marca pelo nome para obter o ID
                    Marca marca = new Marca(nomeMarca);
                    int id = marcaController.getMarcaId(marca); // Obtém o ID da marca a partir do nome

                    if (id == -1) {  // Se não encontrar a marca
                        JOptionPane.showMessageDialog(editTela, "Marca não encontrada!");
                        return;
                    }

                    // Pega o novo nome para atualizar
                    String novoNome = txtNovoNome.getText().trim();

                    if (novoNome.isEmpty()) {
                        JOptionPane.showMessageDialog(editTela, "Por favor, informe o novo nome da marca!");
                        return;
                    }

                    // Atualiza a marca com o novo nome
                    marcaController.editarMarca(id, novoNome);

                    // Limpa os campos após salvar
                    txtNomeMarca.setText("");
                    txtNovoNome.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(editTela, "Erro ao salvar a marca: " + ex.getMessage());
                }
            }
        });

        // Botão "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 20, 60)); // Cor vermelha
        btnCancelar.setForeground(Color.white);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTela.dispose(); // Fecha a tela
            }
        });

        // Adicionar componentes à tela
        gbc.gridx = 0;
        gbc.gridy = 0;
        editTela.add(lblNomeMarca, gbc);

        gbc.gridx = 1;
        editTela.add(txtNomeMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        editTela.add(lblNovoNome, gbc);

        gbc.gridx = 1;
        editTela.add(txtNovoNome, gbc);

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnSalvar);
        panelButtons.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        editTela.add(panelButtons, gbc);

        // Tornar a janela visível
        editTela.setVisible(true);
    }
}
