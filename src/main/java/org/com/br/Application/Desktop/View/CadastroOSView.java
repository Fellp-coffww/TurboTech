package org.com.br.Application.Desktop.View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Core.Domain.Models.OrdemServico;
import org.com.br.Application.Desktop.Controller.OrdemServicoController;

public class CadastroOSView extends JFrame {

    public CadastroOSView() {
        setTitle("Cadastro de Nova Ordem de Serviço");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Painel para seleção de veículo
        JPanel veiculoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblVeiculo = new JLabel("Selecione o Veículo:");
        JComboBox<Veiculo> comboBoxVeiculos = new JComboBox<>();

        // Exemplo: carregar veículos do controlador (substituir por lógica real)
        OrdemServicoController controller = new OrdemServicoController();
        List<Veiculo> veiculos = controller.getVeiculos();
        for (Veiculo veiculo : veiculos) {
            comboBoxVeiculos.addItem(veiculo);
        }

        veiculoPanel.add(lblVeiculo);
        veiculoPanel.add(comboBoxVeiculos);

        // Botão de salvar
        JButton btnSalvar = new JButton("Cadastrar OS");
        btnSalvar.setBackground(Color.DARK_GRAY);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnSalvar.addActionListener(e -> {
            Veiculo veiculoSelecionado = (Veiculo) comboBoxVeiculos.getSelectedItem();
            if (veiculoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo para cadastrar a OS.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Criar e salvar a nova OS
            OrdemServico novaOS = new OrdemServico();
            novaOS.setPlaca(veiculoSelecionado.getPlaca());
            novaOS.setStatusOS("Orçamento"); // Status inicial
            LocalDate localDate = LocalDate.now();
            // Converter LocalDate para Date
            Date date = Date.valueOf(localDate);
            novaOS.setData(date);
            novaOS.setPlaca(comboBoxVeiculos.getSelectedItem().toString());

            try {
                controller.createOrdemServico(novaOS);
                JOptionPane.showMessageDialog(this, "Ordem de Serviço cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar OS: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Adicionar componentes ao frame
        add(veiculoPanel);
        add(btnSalvar);
    }

    // Método para exibir a tela
    public void showScreen() {
        setVisible(true);
    }
}
