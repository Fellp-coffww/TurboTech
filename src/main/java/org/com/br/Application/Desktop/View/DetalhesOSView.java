package org.com.br.Application.Desktop.View;

import org.com.br.Core.Domain.Models.ItemPeca;
import org.com.br.Core.Domain.Models.ItemServico;
import org.com.br.Core.Domain.Models.OrdemServico;
import org.com.br.Core.Domain.Models.Veiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetalhesOSView {

    public void show(OrdemServico ordemServico, Veiculo veiculo, List<ItemPeca> pecas, List<ItemServico> servicos) {
        JFrame frame = new JFrame("Detalhes da Ordem de Serviço");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg")));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);

        // Plano de fundo
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/background_principal.jpg"));
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(resizedImage));
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        // Painel principal
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.setOpaque(false);
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Informações da OS
        JPanel panelOSInfo = new JPanel(new GridLayout(6, 2, 10, 10));
        panelOSInfo.setOpaque(false);

        panelOSInfo.add(new JLabel("ID OS:"));
        panelOSInfo.add(new JLabel(String.valueOf(ordemServico.getIdOrdemServico())));

        panelOSInfo.add(new JLabel("Data:"));
        panelOSInfo.add(new JLabel(String.valueOf(ordemServico.getData())));

        panelOSInfo.add(new JLabel("Status:"));
        panelOSInfo.add(new JLabel(ordemServico.getStatusOS()));

        panelOSInfo.add(new JLabel("Preço Total:"));
        panelOSInfo.add(new JLabel(String.format("R$ %.2f", ordemServico.getPrecoTotal())));

        panelOSInfo.add(new JLabel("Preço Pago:"));
        panelOSInfo.add(new JLabel(String.format("R$ %.2f", ordemServico.getPrecoPago())));

        panelOSInfo.add(new JLabel("Placa do Veículo:"));
        panelOSInfo.add(new JLabel(ordemServico.getPlaca()));

        // Informações do veículo
        JPanel panelVeiculoInfo = new JPanel(new GridLayout(5, 2, 10, 10));
        panelVeiculoInfo.setOpaque(false);

        panelVeiculoInfo.add(new JLabel("Placa:"));
        panelVeiculoInfo.add(new JLabel(veiculo.getPlaca()));

        panelVeiculoInfo.add(new JLabel("Chassi:"));
        panelVeiculoInfo.add(new JLabel(veiculo.getChassi()));

        panelVeiculoInfo.add(new JLabel("Kilometragem:"));
        panelVeiculoInfo.add(new JLabel(veiculo.getKilometragem()));

        panelVeiculoInfo.add(new JLabel("Modelo (ID):"));
        panelVeiculoInfo.add(new JLabel(String.valueOf(veiculo.getIdModelo())));

        panelVeiculoInfo.add(new JLabel("Ano:"));
        panelVeiculoInfo.add(new JLabel(String.valueOf(veiculo.getAno())));

        // Tabelas
        JTable tableServicos = criarTabelaServicos(servicos);
        JTable tablePecas = criarTabelaPecas(pecas);

        // Scroll para as tabelas
        JScrollPane scrollServicos = new JScrollPane(tableServicos);
        JScrollPane scrollPecas = new JScrollPane(tablePecas);

        // Abas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Serviços", scrollServicos);
        tabbedPane.addTab("Peças", scrollPecas);

        // Layout geral
        panelMain.add(panelOSInfo, BorderLayout.NORTH);
        panelMain.add(panelVeiculoInfo, BorderLayout.CENTER);
        panelMain.add(tabbedPane, BorderLayout.SOUTH);

        backgroundLabel.add(panelMain, BorderLayout.CENTER);

        frame.setVisible(true);
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
}

