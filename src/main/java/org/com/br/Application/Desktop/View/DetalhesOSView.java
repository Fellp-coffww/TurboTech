package org.com.br.Application.Desktop.View;

import org.com.br.Core.Domain.Models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetalhesOSView {

    public void show(OrdemServico ordemServico, Veiculo veiculo, List<ItemPeca> pecas, List<ItemServico> servicos, List<Peca> listaPecas, List<Servico> listaServicos) {
        JFrame frame = new JFrame("Detalhes da Ordem de Serviço");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/icon.jpg")));
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Fundo com imagem
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/JELF DYNAMICS.jpg"));
        Image backgroundImg = backgroundIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImg));
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        // Painel principal
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.setOpaque(false);

        // Informações OS e veículo
        JPanel panelInfo = new JPanel(new GridLayout(1, 2, 20, 20));
        panelInfo.setOpaque(false);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelInfo.add(createInfoPanel("Informações OS", ordemServico));
        panelInfo.add(createInfoPanel("Informações Veículo", veiculo));

        // Tabelas
        JTable tableServicos = criarTabelaServicos(servicos);
        JTable tablePecas = criarTabelaPecas(pecas);

        JScrollPane scrollServicos = new JScrollPane(tableServicos);
        JScrollPane scrollPecas = new JScrollPane(tablePecas);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Serviços", scrollServicos);
        tabbedPane.addTab("Peças", scrollPecas);

        // Botões
        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false);
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnAddServico = new JButton("Adicionar Serviço");
        btnAddServico.addActionListener(e -> showAddServicoFrame(listaServicos));

        JButton btnAddPeca = new JButton("Adicionar Peça");
        btnAddPeca.addActionListener(e -> showAddPecaFrame(listaPecas));

        panelButtons.add(btnAddServico);
        panelButtons.add(btnAddPeca);

        // Adicionar tudo ao painel principal
        panelMain.add(panelInfo, BorderLayout.NORTH);
        panelMain.add(tabbedPane, BorderLayout.CENTER);
        panelMain.add(panelButtons, BorderLayout.SOUTH);

        backgroundLabel.add(panelMain, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createInfoPanel(String title, Object data) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setOpaque(false);

        if (data instanceof OrdemServico) {
            OrdemServico os = (OrdemServico) data;
            panel.add(new JLabel("ID OS:"));
            panel.add(new JLabel(String.valueOf(os.getIdOrdemServico())));
            panel.add(new JLabel("Data:"));
            panel.add(new JLabel(String.valueOf(os.getData())));
            panel.add(new JLabel("Status:"));
            panel.add(new JLabel(os.getStatusOS()));
            panel.add(new JLabel("Preço Total:"));
            panel.add(new JLabel(String.format("R$ %.2f", os.getPrecoTotal())));
            panel.add(new JLabel("Preço Pago:"));
            panel.add(new JLabel(String.format("R$ %.2f", os.getPrecoPago())));
        } else if (data instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) data;
            panel.add(new JLabel("Placa:"));
            panel.add(new JLabel(veiculo.getPlaca()));
            panel.add(new JLabel("Chassi:"));
            panel.add(new JLabel(veiculo.getChassi()));
            panel.add(new JLabel("Kilometragem:"));
            panel.add(new JLabel(veiculo.getKilometragem()));
            panel.add(new JLabel("Modelo (ID):"));
            panel.add(new JLabel(String.valueOf(veiculo.getIdModelo())));
            panel.add(new JLabel("Ano:"));
            panel.add(new JLabel(String.valueOf(veiculo.getAno())));
        }

        return panel;
    }

    private JTable criarTabelaServicos(List<ItemServico> servicos) {
        String[] colunas = {"ID", "ID OS", "Serviço (ID)", "CPF", "Quantidade", "Valor Unitário", "Valor Total"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (ItemServico servico : servicos) {
            model.addRow(new Object[]{
                    servico.getIdItemServico(),
                    servico.getIdOrdemServico(),
                    servico.getIdServico(),
                    servico.getCpf(),
                    servico.getQuantidade(),
                    String.format("R$ %.2f", servico.getValorUnitario()),
                    String.format("R$ %.2f", servico.getValorTotal())
            });
        }

        JTable table = new JTable(model);
        ajustarEstiloTabela(table);
        return table;
    }

    private JTable criarTabelaPecas(List<ItemPeca> pecas) {
        String[] colunas = {"ID", "ID OS", "Peça (ID)", "Quantidade", "Valor Unitário", "Valor Total"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (ItemPeca peca : pecas) {
            model.addRow(new Object[]{
                    peca.getIdItemPeca(),
                    peca.getIdOrdemServico(),
                    peca.getIdPeca(),
                    peca.getQuantidade(),
                    String.format("R$ %.2f", peca.getValorUnitario()),
                    String.format("R$ %.2f", peca.getValorTotal())
            });
        }

        JTable table = new JTable(model);
        ajustarEstiloTabela(table);
        return table;
    }

    private void ajustarEstiloTabela(JTable table) {
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
    }

    private void showAddServicoFrame(List<Servico> servicos) {
        JFrame frame = new JFrame("Adicionar Serviço");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JComboBox<String> comboBox = new JComboBox<>();
        for (Servico servico : servicos) {
            comboBox.addItem(servico.getIdServico() + " - " + servico.getDescricao() + " (R$ " + String.format("%.2f", servico.getValorUnitario()) + ")");
        }

        JTextField txtQuantidade = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Serviço adicionado com sucesso!"));

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> frame.dispose());

        frame.add(new JLabel("Selecione Serviço:"));
        frame.add(comboBox);
        frame.add(new JLabel("Quantidade:"));
        frame.add(txtQuantidade);
        frame.add(btnSalvar);
        frame.add(btnCancelar);

        frame.setVisible(true);
    }

    private void showAddPecaFrame(List<Peca> pecas) {
        JFrame frame = new JFrame("Adicionar Peça");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JComboBox<String> comboBox = new JComboBox<>();
        for (Peca peca : pecas) {
            comboBox.addItem(peca.getIdPeca() + " - " + peca.getDescricao() + " (R$ " + String.format("%.2f", peca.getValorUnitario()) + ")");
        }

        JTextField txtQuantidade = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Peça adicionada com sucesso!"));

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> frame.dispose());

        frame.add(new JLabel("Selecione Peça:"));
        frame.add(comboBox);
        frame.add(new JLabel("Quantidade:"));
        frame.add(txtQuantidade);
        frame.add(btnSalvar);
        frame.add(btnCancelar);

        frame.setVisible(true);
    }
}
