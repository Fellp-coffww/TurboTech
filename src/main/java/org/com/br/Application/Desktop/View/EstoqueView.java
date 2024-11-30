package org.com.br.Application.Desktop.View;

import org.com.br.Application.Desktop.Controller.MarcaController;
import org.com.br.Application.Desktop.Controller.ModeloController;
import org.com.br.Application.Desktop.Controller.PecaController;
import org.com.br.Application.Desktop.Controller.ServicoController;
import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Core.Domain.Models.Modelo;
import org.com.br.Core.Domain.Models.Peca;
import org.com.br.Core.Domain.Models.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class EstoqueView extends JFrame {

    public EstoqueView(List<Modelo> modelos, List<Marca> marcas, List<Peca> pecas, List<Servico> servicos) {
        setTitle("Gerenciamento de Estoque");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Painel principal com layout em abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adicionar tabelas às abas
        tabbedPane.addTab("Modelo", criarTabelaModelos(modelos));
        tabbedPane.addTab("Marca", criarTabelaMarcas(marcas));
        tabbedPane.addTab("Peça", criarTabelaPecas(pecas));
        tabbedPane.addTab("Serviço", criarTabelaServicos(servicos));

        add(tabbedPane);
        setVisible(true);
    }

    private JScrollPane criarTabelaModelos(List<Modelo> modelos) {
        String[] colunas = {"ID", "Descrição", "ID Marca", "Ações"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Apenas a coluna "Ações" é editável
            }
        };

        for (Modelo modelo : modelos) {
            model.addRow(new Object[]{
                    modelo.getIdModelo(),
                    modelo.getDescricao(),
                    modelo.getIdMarca(),
                    "Deletar"
            });
        }

        JTable table = new JTable(model);
        table.getColumn("Ações").setCellRenderer(new ButtonRenderer());
        table.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                ModeloController controller = new ModeloController(this);
                controller.deleteModelo(modelos.get(row).getIdModelo());
                modelos.remove(row);
                model.removeRow(row);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar modelo, não é possível deletar modelo relacionado a um veiculo");
            }
        }));

        return new JScrollPane(table);
    }

    private JScrollPane criarTabelaMarcas(List<Marca> marcas) {
        String[] colunas = {"ID", "Descrição", "Ações"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Apenas a coluna "Ações" é editável
            }
        };

        for (Marca marca : marcas) {
            model.addRow(new Object[]{
                    marca.getIdMarca(),
                    marca.getDescricao(),
                    "Deletar"
            });
        }

        JTable table = new JTable(model);
        table.getColumn("Ações").setCellRenderer(new ButtonRenderer());
        table.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                MarcaController controller = new MarcaController(this);
                controller.deletarMarca(marcas.get(row).getIdMarca());
                marcas.remove(row);
                model.removeRow(row);


            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar marca: " + e.getMessage());
            }
        }));

        return new JScrollPane(table);
    }

    private JScrollPane criarTabelaPecas(List<Peca> pecas) {
        String[] colunas = {"ID", "Descrição", "Quantidade", "Valor Unitário", "Código", "Ações"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Apenas a coluna "Ações" é editável
            }
        };

        for (Peca peca : pecas) {
            model.addRow(new Object[]{
                    peca.getIdPeca(),
                    peca.getDescricao(),
                    peca.getQuantidade(),
                    peca.getValorUnitario(),
                    peca.getCodigo(),
                    "Deletar"
            });
        }

        JTable table = new JTable(model);
        table.getColumn("Ações").setCellRenderer(new ButtonRenderer());
        table.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                PecaController controller = new PecaController(this);
                controller.deletePeca(pecas.get(row).getIdPeca());
                pecas.remove(row);
                model.removeRow(row-1);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar peça: " + e.getMessage());
            }
        }));

        return new JScrollPane(table);
    }

    private JScrollPane criarTabelaServicos(List<Servico> servicos) {
        String[] colunas = {"ID", "Descrição", "Valor Unitário", "Ações"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Apenas a coluna "Ações" é editável
            }
        };

        for (Servico servico : servicos) {
            model.addRow(new Object[]{
                    servico.getIdServico(),
                    servico.getDescricao(),
                    servico.getValorUnitario(),
                    "Deletar"
            });
        }

        JTable table = new JTable(model);
        table.getColumn("Ações").setCellRenderer(new ButtonRenderer());
        table.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                ServicoController controller = new ServicoController(this);
                controller.deletarServico(servicos.get(row).getIdServico());
                servicos.remove(row);
                model.removeRow(row-1);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar serviço: " + e.getMessage());
            }
        }));

        return new JScrollPane(table);
    }

}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : "Deletar");
        return this;
    }
}

class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private final JButton button;
    private String label;
    private boolean isClicked;
    private final Consumer<Integer> onClick;
    private int row; // Armazena a linha atual

    public ButtonEditor(JCheckBox checkBox, Consumer<Integer> onClick) {
        this.button = new JButton();
        this.button.setOpaque(true);
        this.onClick = onClick;

        // Configurar ação do botão
        button.addActionListener(e -> {
            isClicked = true;
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row; // Salva a linha atual
        label = value != null ? value.toString() : "Deletar";
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isClicked) {
            onClick.accept(row); // Usa a linha armazenada
        }
        isClicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;
        return super.stopCellEditing();
    }
}
