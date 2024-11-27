package org.com.br.Application.Desktop.View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.com.br.Application.Desktop.Controller.OrdemServicoController;
import org.com.br.Core.Domain.Models.OrdemServico;

public class HomeView {

    static OrdemServicoController ordemServicoController = new OrdemServicoController();

    public static void init() {
        JFrame frame = new JFrame("Sistema de Gerenciamento de Oficina");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/icon.jpg")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Carregar a imagem de fundo da tela principal
        ImageIcon imageIconPrincipal = new ImageIcon(HomeView.class.getResource("/background_principal.jpg"));
        Image imagePrincipal = imageIconPrincipal.getImage();
        Image resizedImagePrincipal = imagePrincipal.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);

        JLabel backgroundLabelPrincipal = new JLabel(new ImageIcon(resizedImagePrincipal));
        backgroundLabelPrincipal.setLayout(new BorderLayout());

        // Adicionar título estilizado com fundo arredondado no topo
        JPanel tituloPanel = new RoundedPanel(20, new Color(0, 0, 0, 150));
        tituloPanel.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Sistema de Gerenciamento de Oficina", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        tituloPanel.add(titulo, BorderLayout.CENTER);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backgroundLabelPrincipal.add(tituloPanel, BorderLayout.NORTH);

        // Painel de opções transparente
        JPanel panelOpcoes = new JPanel();
        panelOpcoes.setOpaque(false);
        panelOpcoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Combobox Cadastro Geral

        // Combobox Cadastro Geral
        String[] opcoesCadastroGeral = {"Cadastro de Marca", "Cadastro de Modelo", "Cadastro de Peça", "Cadastro de Serviço"};
        JComboBox<String> comboBoxCadastroGeral = new JComboBox<>(opcoesCadastroGeral);
        comboBoxCadastroGeral.setPreferredSize(new Dimension(200, 40));
        comboBoxCadastroGeral.setBorder(BorderFactory.createTitledBorder("Cadastro Geral"));
        comboBoxCadastroGeral.addActionListener(e -> {
            String opcaoSelecionada = (String) comboBoxCadastroGeral.getSelectedItem();
            switch (opcaoSelecionada) {
                case "Cadastro de Marca":
                    try {
                        abrirTelaCadastroMarca();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Cadastro de Modelo":
                    abrirTelaCadastroModelo();
                    break;
                case "Cadastro de Peça":
                    abrirTelaCadastroPeca();
                    break;
                case "Cadastro de Serviço":
                    abrirTelaCadastroServico();
                    break;
            }
        });

        // Combobox Cadastro de Pessoas
        String[] opcoesCadastroPessoas = {"Cadastrar Cliente", "Cadastrar Funcionário"};
        JComboBox<String> comboBoxCadastroPessoas = new JComboBox<>(opcoesCadastroPessoas);
        comboBoxCadastroPessoas.setPreferredSize(new Dimension(200, 40));
        comboBoxCadastroPessoas.setBorder(BorderFactory.createTitledBorder("Cadastro de Pessoas"));
        comboBoxCadastroPessoas.addActionListener(e -> {
            String opcaoSelecionada = (String) comboBoxCadastroPessoas.getSelectedItem();
            switch (opcaoSelecionada) {
                case "Cadastrar Cliente":
                    abrirTelaCadastroCliente();
                    break;
                case "Cadastrar Funcionário":
                    abrirTela("Cadastrar Funcionário");
                    break;
            }
        });

        // Combobox Cadastro de Veículo e Oficina
        String[] opcoesCadastroVeiculoOficina = {"Cadastrar Veículo", "Cadastrar Oficina"};
        JComboBox<String> comboBoxCadastroVeiculoOficina = new JComboBox<>(opcoesCadastroVeiculoOficina);
        comboBoxCadastroVeiculoOficina.setPreferredSize(new Dimension(200, 40));
        comboBoxCadastroVeiculoOficina.setBorder(BorderFactory.createTitledBorder("Cadastro de Veículo e Oficina"));
        comboBoxCadastroVeiculoOficina.addActionListener(e -> {
            String opcaoSelecionada = (String) comboBoxCadastroVeiculoOficina.getSelectedItem();
            switch (opcaoSelecionada) {
                case "Cadastrar Veículo":
                    abrirTela("Cadastrar Veículo");
                    break;
                case "Cadastrar Oficina":
                    abrirTela("Cadastrar Oficina");
                    break;
            }
        });

        // Combobox Ordem de Serviço
        String[] opcoesOrdemServico = {"Cadastro de O.S", "Visualização de O.S"};
        JComboBox<String> comboBoxOrdemServico = new JComboBox<>(opcoesOrdemServico);
        comboBoxOrdemServico.setPreferredSize(new Dimension(200, 40));
        comboBoxOrdemServico.setBorder(BorderFactory.createTitledBorder("Ordem de Serviço"));
        // Modifique o trecho onde você chama a OSView
        comboBoxOrdemServico.addActionListener(e -> {
            String opcaoSelecionada = (String) comboBoxOrdemServico.getSelectedItem();
            switch (opcaoSelecionada) {
                case "Cadastro de O.S":
                    abrirTela("Cadastro de Ordem de Serviço");
                    break;
                case "Visualização de O.S":
                    try {
                        // Supondo que você tenha um método em OrdemServicoController que retorna a lista de OS
                        List<OrdemServico> listaOrdensServico = ordemServicoController.getListOrdemServico();
                        OSView osView = new OSView(listaOrdensServico); // Passa a lista de OS para a tela
                        osView.showScreen(); // Exibe a tela da OS
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Para depuração
                        JOptionPane.showMessageDialog(null, "Erro ao carregar a visualização de Ordens de Serviço: " + ex.getMessage());
                    }
                    break;
            }
        });


        // Adicionar comboboxes ao painel
        panelOpcoes.add(comboBoxCadastroGeral);
        panelOpcoes.add(comboBoxCadastroPessoas);
        panelOpcoes.add(comboBoxCadastroVeiculoOficina);
        panelOpcoes.add(comboBoxOrdemServico);

        // Adicionar ComboBoxes ao painel de opções
        panelOpcoes.add(comboBoxCadastroGeral);
        panelOpcoes.add(comboBoxCadastroPessoas);
        panelOpcoes.add(comboBoxCadastroVeiculoOficina);
        panelOpcoes.add(comboBoxOrdemServico);

        // Adicionar painel de opções ao centro
        backgroundLabelPrincipal.add(panelOpcoes, BorderLayout.CENTER);

        // Configurar o frame com a imagem de fundo
        frame.setContentPane(backgroundLabelPrincipal);
        frame.setVisible(true);
    }

    private static JComboBox<String> criarComboBox(String[] opcoes, java.awt.event.ActionListener listener) {
        JComboBox<String> comboBox = new JComboBox<>(opcoes);
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBox.setPreferredSize(new Dimension(220, 40));
        comboBox.addActionListener(e -> {
            // Garantir que e.getSource() seja tratado corretamente
            JComboBox<String> sourceComboBox = (JComboBox<String>) e.getSource();
            String opcaoSelecionada = (String) sourceComboBox.getSelectedItem();
            listener.actionPerformed(new java.awt.event.ActionEvent(opcaoSelecionada, e.getID(), opcaoSelecionada));
        });
        return comboBox;
    }

    private static void abrirTelaCadastroMarca() throws Exception {
        MarcaView.show();
    }

    private static void abrirTelaCadastroModelo() {
        ModeloView.show();
    }

    private static void abrirTelaCadastroPeca() {
        PecaView.show();
    }

    private static void abrirTelaCadastroServico() {
        ServicoView.show();
    }

    private static void abrirTelaCadastroCliente() {PessoaView.show();
    }

    private static void abrirTela(String titulo) {
        JFrame novaTela = new JFrame(titulo);
        novaTela.setSize(900, 700);
        novaTela.setLocationRelativeTo(null);
        novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        novaTela.setLayout(new BorderLayout());

        ImageIcon imageIconCombobox = new ImageIcon(HomeView.class.getResource("/background_combobox.jpg"));
        Image imageCombobox = imageIconCombobox.getImage();
        Image resizedImageCombobox = imageCombobox.getScaledInstance(novaTela.getWidth(), novaTela.getHeight(), Image.SCALE_SMOOTH);

        JLabel backgroundLabelCombobox = new JLabel(new ImageIcon(resizedImageCombobox));
        backgroundLabelCombobox.setLayout(new BorderLayout());

        JLabel label = new JLabel("Tela de " + titulo, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        backgroundLabelCombobox.add(label, BorderLayout.NORTH);

        novaTela.setContentPane(backgroundLabelCombobox);
        novaTela.setVisible(true);
    }

    // Classe para criar painéis com bordas arredondadas
    static class RoundedPanel extends JPanel {
        private final int cornerRadius;
        private final Color backgroundColor;

        public RoundedPanel(int cornerRadius, Color backgroundColor) {
            this.cornerRadius = cornerRadius;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeView::init);
    }
}
