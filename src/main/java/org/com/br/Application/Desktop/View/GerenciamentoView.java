package org.com.br.Application.Desktop.View;

import java.awt.*;
import java.util.Hashtable;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.com.br.Application.Desktop.Controller.ClienteController;
import org.com.br.Application.Desktop.Controller.FuncionarioController;
import org.com.br.Application.Desktop.Controller.VeiculoController;
import org.com.br.Core.Domain.Models.Funcionario;
import org.com.br.Core.Domain.Models.PessoaFisica;
import org.com.br.Core.Domain.Models.PessoaJuridica;
import org.com.br.Core.Domain.Models.Veiculo;
import org.com.br.Infra.Repository.FuncionarioRepository;
import org.com.br.Infra.Repository.PessoaFisicaRepository;
import org.com.br.Infra.Repository.PessoaJuridicaRepository;
import org.com.br.Infra.Repository.VeiculoRepository;

public class GerenciamentoView extends JFrame {

    public GerenciamentoView(
            Hashtable<String, Veiculo> veiculos,
            Hashtable<String, Funcionario> funcionarios,
            Hashtable<String, PessoaFisica> pessoasFisicas,
            Hashtable<String, PessoaJuridica> pessoasJuridicas) {

        setTitle("Gerenciamento de Dados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/icon.jpg")));

        // Painel principal com abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adicionar tabelas às abas
        tabbedPane.addTab("Veículos", criarTabela("Veiculo", veiculos));
        tabbedPane.addTab("Funcionários", criarTabela("Funcionario", funcionarios));
        tabbedPane.addTab("Pessoas Físicas", criarTabela("PessoaFisica", pessoasFisicas));
        tabbedPane.addTab("Pessoas Jurídicas", criarTabela("PessoaJuridica", pessoasJuridicas));

        add(tabbedPane);
        setVisible(true);
    }

    /**
     * Cria uma tabela baseada no tipo de dado.
     *
     * @param tipoDado   O tipo de dado a ser exibido na tabela (e.g., "Veiculo").
     * @param hashtable  Os dados a serem exibidos na tabela.
     * @return JScrollPane contendo a tabela criada.
     */
    private <T> JScrollPane criarTabela(String tipoDado, Hashtable<String, T> hashtable) {
        String[] colunas;
        DefaultTableModel model;

        switch (tipoDado) {
            case "Veiculo":
                colunas = new String[]{"Placa", "Chassi", "Quilometragem", "Ano", "Nº Patrimônio", "Deletar", "Atualizar"};
                model = criarModeloTabela(colunas, hashtable, tipoDado);
                break;
            case "Funcionario":
                colunas = new String[]{"CPF", "Nome", "Deletar", "Atualizar"};
                model = criarModeloTabela(colunas, hashtable, tipoDado);
                break;
            case "PessoaFisica":
                colunas = new String[]{"CPF", "Nome", "Email", "Telefone 1", "Endereço", "Deletar", "Atualizar"};
                model = criarModeloTabela(colunas, hashtable, tipoDado);
                break;
            case "PessoaJuridica":
                colunas = new String[]{"CNPJ", "Razão Social", "Email", "Contato", "Endereço", "Deletar", "Atualizar"};
                model = criarModeloTabela(colunas, hashtable, tipoDado);
                break;
            default:
                throw new IllegalArgumentException("Tipo de dado desconhecido: " + tipoDado);
        }

        JTable table = new JTable(model);
        configurarColunasComBotoes(table, hashtable, model, tipoDado);

        return new JScrollPane(table);
    }

    /**
     * Cria o modelo da tabela com base nos dados fornecidos.
     *
     * @param colunas    As colunas da tabela.
     * @param hashtable  Os dados a serem exibidos.
     * @param tipoDado   O tipo de dado (e.g., "Veiculo", "Funcionario").
     * @return DefaultTableModel configurado.
     */
    private <T> DefaultTableModel criarModeloTabela(String[] colunas, Hashtable<String, T> hashtable, String tipoDado) {
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= colunas.length - 2; // Apenas colunas de ação são editáveis
            }
        };

        // Preenchendo a tabela com dados do Hashtable
        hashtable.forEach((key, value) -> {
            switch (tipoDado) {
                case "Veiculo":
                    Veiculo veiculo = (Veiculo) value;
                    model.addRow(new Object[]{veiculo.getPlaca(), veiculo.getChassi(), veiculo.getKilometragem(),
                            veiculo.getAno(), veiculo.getIdModelo(), "Deletar", "Atualizar"});
                    break;
                case "Funcionario":
                    Funcionario funcionario = (Funcionario) value;
                    model.addRow(new Object[]{funcionario.getCpf(), funcionario.getNome(), "Deletar", "Atualizar"});
                    break;
                case "PessoaFisica":
                    PessoaFisica pessoaFisica = (PessoaFisica) value;
                    model.addRow(new Object[]{pessoaFisica.getCpf(), pessoaFisica.getNome(), pessoaFisica.getEmail(),
                            pessoaFisica.getDdd1() + " " + pessoaFisica.getTelefone1(),
                            pessoaFisica.getLogradouro() + ", " + pessoaFisica.getNumeroEnd(),
                            "Deletar", "Atualizar"});
                    break;
                case "PessoaJuridica":
                    PessoaJuridica pessoaJuridica = (PessoaJuridica) value;
                    model.addRow(new Object[]{pessoaJuridica.getCnpj(), pessoaJuridica.getRazaoSocial(), pessoaJuridica.getEmail(),
                            pessoaJuridica.getContato(),
                            pessoaJuridica.getLogradouro() + ", " + pessoaJuridica.getNumeroEnd(),
                            "Deletar", "Atualizar"});
                    break;
            }
        });

        return model;
    }

    /**
     * Configura as colunas de ação com botões "Deletar" e "Atualizar".
     */
    private <T> void configurarColunasComBotoes(JTable table, Hashtable<String, T> hashtable, DefaultTableModel model, String tipoDado) {
        table.getColumn("Deletar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                String key = (String) model.getValueAt(row, 0); // Identificador (CPF, CNPJ, ou Placa)
                switch (tipoDado) {
                    case "Veiculo":
                        try {
                            VeiculoRepository veiculoRepository = new VeiculoRepository();
                            veiculoRepository.deleteVeiculo(key);
                            JOptionPane.showMessageDialog(this, "Veiculo: " + key + " foi removido.");
                        }catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Erro ao deletar veiculo, verificar se veiculo não pertence a uma OS");
                        }
                        break;
                    case "Funcionario":
                        try {
                            FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
                            funcionarioRepository.deleteFuncionario(key);
                            JOptionPane.showMessageDialog(this, "Funcionário " + key + " foi removido.");
                        }catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Erro ao deletar funcionário, verificar se funcionário não pertence a uma OS");
                        }
                        break;
                    case "PessoaFisica":
                        try {
                            PessoaFisicaRepository pessoaFisicaRepository = new PessoaFisicaRepository();
                            pessoaFisicaRepository.deletePessoaFisica(key);
                            hashtable.remove(key);
                            JOptionPane.showMessageDialog(this, "Cliente Pessoa Física com CPF " + key + " foi removido.");
                        }catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Erro ao deletar cliente, verifique se ele não pertence a um Veiculo");
                        }
                        break;
                    case "PessoaJuridica":
                        try {
                            PessoaJuridicaRepository pessoaJuridicaRepository = new PessoaJuridicaRepository();
                            pessoaJuridicaRepository.deletePessoaJuridica(key);
                            hashtable.remove(key);
                            JOptionPane.showMessageDialog(this, "Cliente Pessoa Jurídica com CNPJ " + key + " foi removido.");
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Erro ao deletar cliente, verifique se ele não pertence a um Veiculo");
                        }
                }
                hashtable.remove(key);
                model.removeRow(row);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao deletar item: " + e.getMessage());
            }
        }));

        table.getColumn("Atualizar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Atualizar").setCellEditor(new ButtonEditor(new JCheckBox(), row -> {
            try {
                String key = (String) model.getValueAt(row, 0); // Identificador (CPF, CNPJ, ou Placa)
                switch (tipoDado) {
                    case "Veiculo":

                        try {
                            Veiculo veiculo = (Veiculo) hashtable.get(key);
                            String quilometragem = JOptionPane.showInputDialog(this, "Atualize a quilometragem:", veiculo.getKilometragem());
                            veiculo.setKilometragem(quilometragem);
                            String chassi = JOptionPane.showInputDialog(this, "Atualize o chassi:", veiculo.getChassi());
                            int ano = Integer.parseInt(JOptionPane.showInputDialog(this, "Atualize o ano:", veiculo.getAno()));
                            veiculo.setChassi(chassi);
                            veiculo.setAno(ano);
                            VeiculoRepository vr = new VeiculoRepository();
                            vr.updateVeiculo(veiculo);
                            JOptionPane.showMessageDialog(this, "Veiculo atualizado com sucesso.");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, "Erro ao atualizar veiculo, checar informações.");
                        }
                        break;
                    case "Funcionario":
                        try {
                            Funcionario funcionario = (Funcionario) hashtable.get(key);
                            String novoNome = JOptionPane.showInputDialog(this, "Atualize o nome:", funcionario.getNome());
                            funcionario.setNome(novoNome);
                            FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
                            funcionarioRepository.updateFuncionario(funcionario);
                            JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso.");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário, checar informações");
                        }
                        break;
                    case "PessoaFisica":
                        try {
                            PessoaFisica pessoaFisica = (PessoaFisica) hashtable.get(key);
                            String novoNomeFisico = JOptionPane.showInputDialog(this, "Atualize o nome:", pessoaFisica.getNome());
                            String novoEmail = JOptionPane.showInputDialog(this, "Atualize o Email:", pessoaFisica.getEmail());
                            String novoTelefone = JOptionPane.showInputDialog(this, "Atualize o telefone:", pessoaFisica.getTelefone1());
                            String novoEndereço = JOptionPane.showInputDialog(this, "Atualize o Endereço:", pessoaFisica.getLogradouro());
                            pessoaFisica.setNome(novoNomeFisico);
                            pessoaFisica.setEmail(novoEmail);
                            pessoaFisica.setTelefone1(novoTelefone);
                            pessoaFisica.setLogradouro(novoEndereço);
                            PessoaFisicaRepository pfRepository = new PessoaFisicaRepository();
                            pfRepository.updatePessoaFisica(pessoaFisica);
                            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso.");
                        } catch (Exception e){
                            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente, checar informações");
                        }
                        break;
                    case "PessoaJuridica":
                        try {
                        PessoaJuridica pessoaJuridica = (PessoaJuridica) hashtable.get(key);
                        String novaRazaoSocial = JOptionPane.showInputDialog(this, "Atualize a razão social:", pessoaJuridica.getRazaoSocial());
                        String novoEmail = JOptionPane.showInputDialog(this, "Atualize o email:", pessoaJuridica.getEmail());
                        String novoContato = JOptionPane.showInputDialog(this, "Atualize o contato: ", pessoaJuridica.getContato());
                        String novoEndereco = JOptionPane.showInputDialog(this, "Atualize o endereço: ", pessoaJuridica.getLogradouro());
                        pessoaJuridica.setRazaoSocial(novaRazaoSocial);
                        pessoaJuridica.setEmail(novoEmail);
                        pessoaJuridica.setContato(novoContato);
                        pessoaJuridica.setLogradouro(novoEndereco);
                        PessoaJuridicaRepository pfRepository = new PessoaJuridicaRepository();
                        pfRepository.updatePessoaJuridica(pessoaJuridica);
                        JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso.");
                        break;
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente, checar informações");

                        }
                }
                atualizarTabela(table, hashtable, model, tipoDado);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao atualizar item: " + e.getMessage());
            }
        }));
    }


    private <T> void atualizarTabela(JTable table, Hashtable<String, T> hashtable, DefaultTableModel model, String tipoDado) {
        model.setRowCount(0); // Limpa a tabela
        hashtable.forEach((key, value) -> {
            switch (tipoDado) {
                case "Veiculo":
                    Veiculo veiculo = (Veiculo) value;
                    model.addRow(new Object[]{
                            veiculo.getPlaca(), veiculo.getChassi(), veiculo.getKilometragem(),
                            veiculo.getAno(), veiculo.getIdModelo(), "Deletar", "Atualizar"
                    });
                    break;
                case "Funcionario":
                    Funcionario funcionario = (Funcionario) value;
                    model.addRow(new Object[]{
                            funcionario.getCpf(), funcionario.getNome(), "Deletar", "Atualizar"
                    });
                    break;
                case "PessoaFisica":
                    PessoaFisica pessoaFisica = (PessoaFisica) value;
                    model.addRow(new Object[]{
                            pessoaFisica.getCpf(), pessoaFisica.getNome(), pessoaFisica.getEmail(),
                            pessoaFisica.getDdd1() + " " + pessoaFisica.getTelefone1(),
                            pessoaFisica.getLogradouro() + ", " + pessoaFisica.getNumeroEnd(),
                            "Deletar", "Atualizar"
                    });
                    break;
                case "PessoaJuridica":
                    PessoaJuridica pessoaJuridica = (PessoaJuridica) value;
                    model.addRow(new Object[]{
                            pessoaJuridica.getCnpj(), pessoaJuridica.getRazaoSocial(), pessoaJuridica.getEmail(),
                            pessoaJuridica.getContato(),
                            pessoaJuridica.getLogradouro() + ", " + pessoaJuridica.getNumeroEnd(),
                            "Deletar", "Atualizar"
                    });
                    break;
            }
        });
        table.revalidate();
    }


    // Classes ButtonRenderer e ButtonEditor para botões nas células
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
    }
}
