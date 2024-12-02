package org.com.br.Application.Desktop.View;

import org.com.br.Application.Desktop.Controller.OrdemServicoController;
import org.com.br.Application.Desktop.Controller.OrdemServicoDetalheController;
import org.com.br.Application.Desktop.Services.OrdemServicoService;
import org.com.br.Core.Domain.Models.*;
import org.com.br.Infra.Interfaces.IFuncionario;
import org.com.br.Infra.Interfaces.IPeca;
import org.com.br.Infra.Interfaces.IServico;
import org.com.br.Infra.Repository.FuncionarioRepository;
import org.com.br.Infra.Repository.PecaRepository;
import org.com.br.Infra.Repository.ServicoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetalhesOSView {

    private OrdemServico ordemServico;

    private JTable tablePecas;

    private JTable tableServicos;


    public void show(OrdemServico ordemServico, Veiculo veiculo, List<ItemPeca> pecas, List<ItemServico> servicos, List<Peca> listaPecas, List<Servico> listaServicos, List<Funcionario> funcionarios) {
        JFrame frame = new JFrame("Detalhes da Ordem de Serviço");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/icon.jpg")));
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        this.ordemServico = ordemServico;

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

        updatePriceOS(pecas, servicos);

        panelInfo.add(createInfoPanel("Informações OS", ordemServico));
        panelInfo.add(createInfoPanel("Informações Veículo", veiculo));

        // Tabelas

        this.tableServicos = criarTabelaServicos(servicos);
        this.tablePecas = criarTabelaPecas(pecas);


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
        btnAddServico.addActionListener(e -> showAddServicoFrame(listaServicos, funcionarios));

        JButton btnAddPeca = new JButton("Adicionar Peça");
        btnAddPeca.addActionListener(e -> showAddPecaFrame(listaPecas));

        JButton btnSelEstadoOS = new JButton("Alterar estado");
        btnSelEstadoOS.addActionListener(e -> showEstadoFrame(this.ordemServico));


        panelButtons.add(btnAddServico);
        panelButtons.add(btnAddPeca);
        panelButtons.add(btnSelEstadoOS);

        // Adicionar tudo ao painel principal
        panelMain.add(panelInfo, BorderLayout.NORTH);
        panelMain.add(tabbedPane, BorderLayout.CENTER);
        panelMain.add(panelButtons, BorderLayout.SOUTH);

        backgroundLabel.add(panelMain, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showEstadoFrame(OrdemServico ordemServico) {
        JFrame frame = new JFrame("Alterar Estado da Ordem de Serviço");
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        // Opções para a combo box
        String[] estados = {"Orçamento", "Aprovado", "Executando", "Finalizada", "Paga"};
        JComboBox<String> comboBoxEstados = new JComboBox<>(estados);

        // Seleciona o estado atual da ordem na combo box
        comboBoxEstados.setSelectedItem(ordemServico.getStatusOS());

        // Campo para inserir o valor pago com máscara de R$
        JLabel labelValorPago = new JLabel("Digite o valor pago:");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        NumberFormatter currencyFormatter = new NumberFormatter(currencyFormat);
        currencyFormatter.setAllowsInvalid(false); // Impede caracteres inválidos
        currencyFormatter.setMinimum(0.0); // Não permite valores negativos

        // Definir o campo de texto formatado
        JFormattedTextField campoValorPago = new JFormattedTextField(currencyFormatter);
        campoValorPago.setColumns(10);
        campoValorPago.setValue(0.0); // Define valor inicial como zero
        campoValorPago.setHorizontalAlignment(JTextField.RIGHT);

        // Inicialmente, o campo de valor pago está invisível
        labelValorPago.setVisible(false);
        campoValorPago.setVisible(false);

        // Atualiza visibilidade do campo de valor pago com base na seleção
        comboBoxEstados.addActionListener(e -> {
            String estadoSelecionado = (String) comboBoxEstados.getSelectedItem();
            boolean isPaga = "Paga".equals(estadoSelecionado);
            labelValorPago.setVisible(isPaga);
            campoValorPago.setVisible(isPaga);
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String novoEstado = comboBoxEstados.getSelectedItem().toString();
            this.ordemServico.setStatusOS(novoEstado);

            if ("Paga".equals(novoEstado)) {
                try {
                    // Obtém o valor do campo formatado
                    Number valorPago = (Number) campoValorPago.getValue();
                    if (valorPago == null) {
                        throw new NumberFormatException();
                    }
                    this.ordemServico.setPrecoPago(valorPago.doubleValue()); // Supondo que OrdemServico tenha esse atributo
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um valor válido para o pagamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Atualiza no banco de dados ou sistema
            OrdemServicoController ordemServicoController = new OrdemServicoController();
            ordemServicoController.updateOrdemServico(this.ordemServico);

            JOptionPane.showMessageDialog(frame, "Estado atualizado para: " + novoEstado);
            frame.dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> frame.dispose());

        frame.add(new JLabel("Selecione o novo estado:"));
        frame.add(comboBoxEstados);
        frame.add(labelValorPago);
        frame.add(campoValorPago);
        frame.add(btnSalvar);
        frame.add(btnCancelar);

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
        try {
            String[] colunas = {"ID", "Serviço (ID)", "Nome", "Reponsável", "Quantidade", "Valor Unitário", "Valor Total", "Ações"};
            DefaultTableModel model = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 7; // Apenas a coluna de ações é editável
                }
            };

            IServico servicoRepository = new ServicoRepository();
            IFuncionario funcionarioRepository = new FuncionarioRepository();
            int i = 1;
            for (ItemServico servico : servicos) {
                model.addRow(new Object[]{
                        i++,
                        servico.getIdServico(),
                        servicoRepository.getServicoById(servico.getIdServico()).getDescricao(),
                        funcionarioRepository.getFuncionarioByCpf(servico.getCpf()).getNome(),
                        servico.getQuantidade(),
                        String.format("R$ %.2f", servico.getValorUnitario()),
                        String.format("R$ %.2f", servico.getValorTotal()),
                        "Deletar"
                });
            }

            JTable table = new JTable(model);
            ajustarEstiloTabela(table);

            table.getColumn("Ações").setCellRenderer(new ButtonRenderer());
            table.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
                OrdemServicoDetalheController ordemServicoDetalheController = new OrdemServicoDetalheController();
                    ordemServicoDetalheController.deleteItemServico(servicos.get(row));
                servicos.remove(row);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Serviço removido com sucesso! Atualizar página para renovar tabela. ");
                try {
                    atualizarTabelaServicos(servicos);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }));

            return table;
        } catch (Exception e) {
            return null;
        }
    }


    private void atualizarTabelaServicos(List<ItemServico> servicos) throws Exception {
        DefaultTableModel model = (DefaultTableModel) tableServicos.getModel();
        model.setRowCount(0); // Limpa todas as linhas

        IServico servicoRepository = new ServicoRepository();
        IFuncionario funcionarioRepository = new FuncionarioRepository();

        int i = 1;
        for (ItemServico servico : servicos) {
            model.addRow(new Object[]{
                    i++,
                    servico.getIdServico(),
                    servicoRepository.getServicoById(servico.getIdServico()).getDescricao(),
                    funcionarioRepository.getFuncionarioByCpf(servico.getCpf()).getNome(),
                    servico.getQuantidade(),
                    String.format("R$ %.2f", servico.getValorUnitario()),
                    String.format("R$ %.2f", servico.getValorTotal()),
                    "Deletar"
            });
        }

        model.fireTableDataChanged(); // Notifica a tabela sobre a atualização
    }

    private void atualizarTabelaPecas(List<ItemPeca> pecas) throws Exception {
        DefaultTableModel model = (DefaultTableModel) tablePecas.getModel();
        model.setRowCount(0); // Limpa todas as linhas

        IPeca pecaRepository = new PecaRepository();

        int i = 1;
        for (ItemPeca peca : pecas) {
            model.addRow(new Object[]{
                    i++,
                    pecaRepository.getPecaById(peca.getIdPeca()).getDescricao(),
                    peca.getQuantidade(),
                    String.format("R$ %.2f", peca.getValorUnitario()),
                    String.format("R$ %.2f", peca.getValorTotal()),
                    "Deletar"
            });
        }

        model.fireTableDataChanged(); // Notifica a tabela sobre a atualização
    }


    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int row;
        private boolean clicked;
        private final java.util.function.Consumer<Integer> onClick;

        public ButtonEditor(JCheckBox checkBox, java.util.function.Consumer<Integer> onClick) {
            super(checkBox);
            this.onClick = onClick;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped();
                if (clicked) onClick.accept(row);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            clicked = true;
            button.setText(value == null ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }

    private JTable criarTabelaPecas(List<ItemPeca> pecas) {
        try {
            IPeca pecaRepository = new PecaRepository();
            String[] colunas = {"ID", "Nome Peça", "Quantidade", "Valor Unitário", "Valor Total", "Ações"};
            DefaultTableModel model = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 5; // Apenas a coluna de ações é editável
                }
            };

            int i = 1;
            for (ItemPeca peca : pecas) {
                model.addRow(new Object[]{
                        i++,
                        pecaRepository.getPecaById(peca.getIdPeca()).getDescricao(),
                        peca.getQuantidade(),
                        String.format("R$ %.2f", peca.getValorUnitario()),
                        String.format("R$ %.2f", peca.getValorTotal()),
                        "Deletar"
                });
            }

            JTable table = new JTable(model);
            ajustarEstiloTabela(table);

            table.getColumn("Ações").setCellRenderer(new ButtonRenderer());
            table.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
                try {
                    atualizarTabelaPecas(pecas);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                OrdemServicoDetalheController ordemServicoDetalheController = new OrdemServicoDetalheController();
                ordemServicoDetalheController.deleteItemPeca(pecas.get(row));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Peça removida com sucesso! Atualizar página para renovar tabela. ");
                pecas.remove(row);
                //updatePriceOS(pecas, null);
            }));

            return table;
        } catch (Exception e) {
            return null;
        }
    }

    private void updatePriceOS(List<ItemPeca> pecas, List<ItemServico> servicos){

        try {
            double n = 0;

            for (ItemServico servico : servicos) {
                n = n + servico.getValorTotal();
            }


            for (ItemPeca peca : pecas) {
                n = n + peca.getValorTotal();
            }

            ordemServico.setPrecoTotal(n);

        }catch (Exception e){

        }
    }

    private void ajustarEstiloTabela(JTable table) {
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
    }



    private void showAddServicoFrame(List<Servico> servicos, List<Funcionario> funcionarios) {
        JFrame frame = new JFrame("Adicionar Serviço");
        frame.setSize(400, 400); // Ajustado para acomodar o novo campo
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 1, 10, 10)); // Ajustado para incluir o novo campo

        // ComboBox para selecionar serviços
        JComboBox<String> comboBoxServico = new JComboBox<>();
        for (Servico servico : servicos) {
            comboBoxServico.addItem(servico.getDescricao());
        }

        // ComboBox para selecionar funcionários
        JComboBox<String> comboBoxFuncionario = new JComboBox<>();
        for (Funcionario funcionario : funcionarios) {
            comboBoxFuncionario.addItem(funcionario.getNome());
        }

        // Campo para quantidade
        JTextField txtQuantidade = new JTextField();

        // Controlador
        OrdemServicoDetalheController ordemServicoDetalheController = new OrdemServicoDetalheController(frame);
        ordemServicoDetalheController.setOrdemServico(this.ordemServico);

        // Botão Salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String servicoSelecionado = comboBoxServico.getSelectedItem().toString();
                String funcionarioSelecionado = comboBoxFuncionario.getSelectedItem().toString();
                int quantidade = Integer.valueOf(txtQuantidade.getText());

                ordemServicoDetalheController.addItemServico(servicoSelecionado, quantidade, funcionarioSelecionado);

                double n = 0;

                for (Servico servico : servicos) {
                    if(servico.getDescricao().equals(servicoSelecionado)){
                        n = servico.getValorUnitario() * quantidade;
                        break;
                    }
                }
                try {
                    atualizarTabelaServicos(ordemServicoDetalheController.getItemServicoList());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ordemServico.setPrecoTotal(ordemServico.getPrecoTotal() + n);
                ordemServicoDetalheController.updateOS(ordemServico);


            }
        });

        // Botão Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> frame.dispose());

        // Adicionando os componentes ao frame
        frame.add(new JLabel("Selecione Serviço:"));
        frame.add(comboBoxServico);
        frame.add(new JLabel("Selecione Funcionário:"));
        frame.add(comboBoxFuncionario);
        frame.add(new JLabel("Quantidade:"));
        frame.add(txtQuantidade);
        frame.add(btnSalvar);
        frame.add(btnCancelar);

        frame.setVisible(true);
    }


    private void showAddPecaFrame(List<Peca> pecas ) {
        JFrame frame = new JFrame("Adicionar Peça");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JComboBox<String> comboBox = new JComboBox<>();
        for (Peca peca : pecas) {
            comboBox.addItem(peca.getDescricao());
        }

        JTextField txtQuantidade = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        OrdemServicoDetalheController ordemServicoDetalheController = new OrdemServicoDetalheController(frame);
        ordemServicoDetalheController.setOrdemServico(this.ordemServico);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordemServicoDetalheController.addItemPeca(comboBox.getSelectedItem().toString(), Integer.valueOf(txtQuantidade.getText()));
                tablePecas = criarTabelaPecas(ordemServicoDetalheController.getItemPecaList());

                double n = 0;

                for (Peca peca : pecas) {
                    if(peca.getDescricao().equals(comboBox.getSelectedItem().toString())){
                        n = peca.getValorUnitario() * Integer.valueOf(txtQuantidade.getText());
                        break;
                    }
                }

                try {
                    atualizarTabelaPecas(ordemServicoDetalheController.getItemPecaList());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ordemServico.setPrecoTotal(ordemServico.getPrecoTotal() + n);
                ordemServicoDetalheController.updateOS(ordemServico);

            }});

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
