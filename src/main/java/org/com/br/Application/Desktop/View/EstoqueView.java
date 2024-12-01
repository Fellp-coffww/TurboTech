package org.com.br.Application.Desktop.View;

import java.awt.Component;
import java.awt.Toolkit;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.com.br.Application.Desktop.Controller.MarcaController;
import org.com.br.Application.Desktop.Controller.ModeloController;
import org.com.br.Application.Desktop.Controller.PecaController;
import org.com.br.Application.Desktop.Controller.ServicoController;
import org.com.br.Core.Domain.Models.Marca;
import org.com.br.Core.Domain.Models.Modelo;
import org.com.br.Core.Domain.Models.Peca;
import org.com.br.Core.Domain.Models.Servico;

public class EstoqueView extends JFrame {

    public EstoqueView(List<Modelo> modelos, List<Marca> marcas, List<Peca> pecas, List<Servico> servicos) {
        setTitle("Gerenciamento de Estoque");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/icon.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Painel principal com layout em abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adicionar tabelas às abas
        tabbedPane.addTab("Marca", criarTabelaMarcas(marcas));
        tabbedPane.addTab("Modelo", criarTabelaModelos(modelos));
        tabbedPane.addTab("Peça", criarTabelaPecas(pecas));
        tabbedPane.addTab("Serviço", criarTabelaServicos(servicos));

        add(tabbedPane);
        setVisible(true);
    }

    private JScrollPane criarTabelaMarcas(List<Marca> marcas) {
        String[] colunas = {"ID", "Descrição", "Deletar", "Atualizar"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3;
            }
        };

        for (Marca marca : marcas) {
            model.addRow(new Object[]{
                    marca.getIdMarca(),
                    marca.getDescricao(),
                    "Deletar",
                    "Atualizar"
            });
        }

        JTable table = new JTable(model);

        table.getColumn("Deletar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
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

        table.getColumn("Atualizar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Atualizar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                Marca marca = marcas.get(row);
                String novaDescricao = JOptionPane.showInputDialog(this, "Atualize a descrição:", marca.getDescricao());
                long idMarca = marca.getIdMarca();
                if (novaDescricao != null && !novaDescricao.trim().isEmpty()) {
                    marca.setDescricao(novaDescricao);
                    MarcaController controller = new MarcaController(this);
                    controller.editarMarca(novaDescricao, idMarca);

                    model.setValueAt(novaDescricao, row, 1);
                    model.setValueAt(idMarca, row, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar marca: " + e.getMessage());
            }
        }));

        return new JScrollPane(table);
    }

    private JScrollPane criarTabelaModelos(List<Modelo> modelos) {
        String[] colunas = {"ID", "Descrição", "ID Marca", "Deletar", "Atualizar"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4;
            }
        };

        for (Modelo modelo : modelos) {
            model.addRow(new Object[]{
                    modelo.getIdModelo(),
                    modelo.getDescricao(),
                    modelo.getIdMarca(),
                    "Deletar",
                    "Atualizar"
            });
        }

        JTable table = new JTable(model);

        table.getColumn("Deletar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                ModeloController controller = new ModeloController(this);
                controller.deleteModelo(modelos.get(row).getIdModelo());
                modelos.remove(row);
                model.removeRow(row);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar modelo: " + e.getMessage());
            }
        }));

        table.getColumn("Atualizar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Atualizar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                Modelo modelo = modelos.get(row);
                String novaDescricao = JOptionPane.showInputDialog(this, "Atualize a descrição:", modelo.getDescricao());
                long idMarca = modelo.getIdMarca();
                long idModelo = modelo.getIdModelo();
                if (novaDescricao != null && !novaDescricao.trim().isEmpty()) {
                    modelo.setDescricao(novaDescricao);
                    ModeloController controller = new ModeloController(this);
                    controller.editarModelo(novaDescricao, idMarca, idModelo);
                    
                    model.setValueAt(novaDescricao, row, 1);
                    model.setValueAt(idMarca, row, 2);
                    model.setValueAt(idModelo, row, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar modelo: " + e.getMessage());
            }
        }));

        return new JScrollPane(table);
    }


    private JScrollPane criarTabelaPecas(List<Peca> pecas) {
        String[] colunas = {"ID", "Descrição", "Quantidade", "Valor Unitário", "Código", "Deletar", "Atualizar"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6;
            }
        };

        for (Peca peca : pecas) {
            model.addRow(new Object[]{
                    peca.getIdPeca(),
                    peca.getDescricao(),
                    peca.getQuantidade(),
                    peca.getValorUnitario(),
                    peca.getCodigo(),
                    "Deletar",
                    "Atualizar"
            });
        }

        JTable table = new JTable(model);

        table.getColumn("Deletar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                PecaController controller = new PecaController(this);
                controller.deletePeca(pecas.get(row).getIdPeca());
                pecas.remove(row);
                model.removeRow(row);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar peça: " + e.getMessage());
            }
        }));

        table.getColumn("Atualizar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Atualizar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                Peca peca = pecas.get(row);
                String novaDescricao = JOptionPane.showInputDialog(this, "Atualize a descrição:", peca.getDescricao());
                String novaQuantidadeString = JOptionPane.showInputDialog(this,"Atualize a quantidade",peca.getQuantidade());
                String novoValoString = JOptionPane.showInputDialog(this,"Atualize o Valor Unitario",peca.getValorUnitario());
                String novoCodigo = JOptionPane.showInputDialog(this,"Atualize o Codigo",peca.getCodigo());
                int novaQuantidade = Integer.valueOf(novaQuantidadeString);
                double novoValor = Double.valueOf(novoValoString);
                long idPeca = peca.getIdPeca();
                if (novaDescricao != null && !novaDescricao.trim().isEmpty()) {
                    peca.setDescricao(novaDescricao);
                    peca.setQuantidade(novaQuantidade);
                    peca.setValorUnitario(novoValor);
                    peca.setCodigo(novoCodigo);
                    PecaController controller = new PecaController(this);
                    controller.editarPeca(novaDescricao,novaQuantidade,novoValor,novoCodigo,idPeca);

                    model.setValueAt(novaDescricao, row, 1);
                    model.setValueAt(novaQuantidade, row, 2);
                    model.setValueAt(novoValor, row, 3);
                    model.setValueAt(novoCodigo, row, 4);
                    model.setValueAt(idPeca, row, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar peça: " + e.getMessage());
            }
        }));

        return new JScrollPane(table);
    }

    private JScrollPane criarTabelaServicos(List<Servico> servicos) {
        String[] colunas = {"ID", "Descrição", "Valor Unitário", "Deletar", "Atualizar"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4;
            }
        };

        for (Servico servico : servicos) {
            model.addRow(new Object[]{
                    servico.getIdServico(),
                    servico.getDescricao(),
                    servico.getValorUnitario(),
                    "Deletar",
                    "Atualizar"
            });
        }

        JTable table = new JTable(model);

        table.getColumn("Deletar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                ServicoController controller = new ServicoController(this);
                controller.deletarServico(servicos.get(row).getIdServico());
                servicos.remove(row);
                model.removeRow(row);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar serviço: " + e.getMessage());
            }
        }));

        table.getColumn("Atualizar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Atualizar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                Servico servico = servicos.get(row);
                String novaDescricao = JOptionPane.showInputDialog(this, "Atualize a descrição:", servico.getDescricao());
                String novoValorUnitarioString = JOptionPane.showInputDialog(this,"Atualize o valor",servico.getValorUnitario());
                double novaValor = Double.valueOf(novoValorUnitarioString);
                
                long idServico = servico.getIdServico();
                if (novaDescricao != null && !novaDescricao.trim().isEmpty()) {
                    servico.setDescricao(novaDescricao);
                    servico.setValorUnitario(novaValor);
                    ServicoController controller = new ServicoController(this);
                    controller.editarServiço(novaDescricao, novaValor, idServico);

                    model.setValueAt(novaDescricao, row, 1); // Coluna 1: Descrição
                    model.setValueAt(novaValor, row, 2);     // Coluna 2: Valor Unitário
                    model.setValueAt(idServico, row, 0);     // Coluna 0: ID
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar serviço: " + e.getMessage());
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
        setText(value != null ? value.toString() : "");
        return this;
    }
}

class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private final JButton button;
    private String label;
    private boolean isClicked;
    private final Consumer<Integer> onClick;
    private int row;

    public ButtonEditor(JCheckBox checkBox, Consumer<Integer> onClick) {
        this.button = new JButton();
        this.button.setOpaque(true);
        this.onClick = onClick;

        button.addActionListener(e -> {
            isClicked = true;
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        label = value != null ? value.toString() : "";
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isClicked) {
            onClick.accept(row);
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
