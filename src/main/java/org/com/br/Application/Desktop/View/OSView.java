package org.com.br.Application.Desktop.View;

import org.com.br.Application.Desktop.Controller.OrdemServicoDetalheController;
import org.com.br.Core.Domain.Models.OrdemServico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OSView extends JFrame {

    private List<OrdemServico> listaOS;

    public OSView(List<OrdemServico> listaOS) {
        this.listaOS = listaOS;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/icon.jpg")));

        // Configurações básicas da janela
        setTitle("Visualização de Ordens de Serviço");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Configuração do layout
        setLayout(new BorderLayout());

        // Carregar a imagem de fundo
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/JELF DYNAMICS.jpg"));
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(resizedImage));
        backgroundLabel.setLayout(new BorderLayout());

        // Painel de título
        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BorderLayout());
        tituloPanel.setBackground(new Color(0, 0, 0, 150)); // Fundo preto semi-transparente
        JLabel titulo = new JLabel("Ordens de serviço em aberto", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        tituloPanel.add(titulo, BorderLayout.CENTER);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        super.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));


        // Adicionar o painel de título ao topo
        backgroundLabel.add(tituloPanel, BorderLayout.NORTH);

        // Painel para os cartões
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Permitir que o fundo seja visível
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Layout que organiza os cartões lado a lado

        // Botão para cadastrar nova OS
        JButton btnCadastrarOS = new JButton("Cadastrar Nova OS");
        btnCadastrarOS.setBackground(Color.DARK_GRAY);
        btnCadastrarOS.setForeground(Color.WHITE);
        btnCadastrarOS.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCadastrarOS.setFocusPainted(false);

// Ação para abrir a tela de cadastro
        btnCadastrarOS.addActionListener(e -> {
            try {
                CadastroOSView cadastroOSView = new CadastroOSView();
                cadastroOSView.showScreen();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(OSView.this, "Erro ao abrir tela de cadastro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

// Adiciona o botão ao topo, abaixo do título
        tituloPanel.add(btnCadastrarOS, BorderLayout.SOUTH);


        // Criar cartões para exibir as informações das ordens de serviço
        for (OrdemServico os : listaOS) {
            JPanel osCard = new JPanel();
            osCard.setLayout(new BorderLayout());
            osCard.setPreferredSize(new Dimension(200, 150)); // Tamanho fixo do cartão
            osCard.setBackground(Color.BLACK); // Fundo preto
            osCard.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Borda branca

            // Adicionar informações da OS
            JLabel osInfo = new JLabel("<html><div style='text-align: center;'>"
                    + "Placa: " + os.getPlaca() + "<br>"
                    + "ID OS: " + os.getIdOrdemServico() + "<br>"
                    + "Status: " + os.getStatusOS()
                    + "</div></html>");
            osInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
            osInfo.setForeground(Color.WHITE); // Texto branco
            osInfo.setHorizontalAlignment(SwingConstants.CENTER);
            osCard.add(osInfo, BorderLayout.CENTER);

            // Botão de detalhes
            JButton btnDetalhes = new JButton("Detalhes");
            btnDetalhes.setBackground(Color.GRAY);
            btnDetalhes.setForeground(Color.WHITE); // Texto branco
            btnDetalhes.setFocusPainted(false);
            btnDetalhes.setFont(new Font("SansSerif", Font.BOLD, 15));
            DetalhesOSView detalhesOSView = new DetalhesOSView();
            OrdemServicoDetalheController ordemServicoDetalheController = new OrdemServicoDetalheController(OSView.this);
            btnDetalhes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ação para abrir detalhes da OS

                    try {
                        ordemServicoDetalheController.ordemServiceContrucut(os.getIdOrdemServico());
                        detalhesOSView.show(ordemServicoDetalheController.getOrdemServico(), ordemServicoDetalheController.getVeiculo(),
                                ordemServicoDetalheController.getItemPecaList(), ordemServicoDetalheController.getItemServicoList(),
                                ordemServicoDetalheController.getPecaList(), ordemServicoDetalheController.getServicoList(), ordemServicoDetalheController.getFuncionarioList());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            osCard.add(btnDetalhes, BorderLayout.SOUTH);

            // Adicionar o cartão ao painel de conteúdo
            contentPanel.add(osCard);
        }

        // Adicionar o painel de conteúdo ao centro
        backgroundLabel.add(contentPanel, BorderLayout.CENTER);

        // Adicionar o background à janela
        add(backgroundLabel, BorderLayout.CENTER);
    }

    // Método show() para exibir a tela
    public void showScreen() {
        setVisible(true);
    }
}
