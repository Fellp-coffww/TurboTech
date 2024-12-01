    package org.com.br.Application.Desktop.View;

    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.Font;
    import java.awt.GridBagConstraints;
    import java.awt.GridBagLayout;
    import java.awt.Image;
    import java.awt.Insets;
    import java.util.ArrayList;
    import java.util.List;

    import javax.swing.BorderFactory;
    import javax.swing.DefaultListCellRenderer;
    import javax.swing.JButton;
    import javax.swing.JComboBox;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.JTextField;
    import javax.swing.border.EmptyBorder;
    import javax.swing.border.TitledBorder;
    import javax.swing.ImageIcon;
    import javax.swing.JList;

    import org.com.br.Application.Desktop.Controller.ModeloController;
    import org.com.br.Application.Desktop.Services.MarcaService;
    import org.com.br.Core.Domain.Models.Marca;
    import org.com.br.Infra.Repository.MarcaRepository;

    public class ModeloView {

        public static void show() throws Exception {
            JFrame novaTela = new JFrame("Cadastro de Modelo");
            novaTela.setSize(700, 700); // Tamanho da tela
            novaTela.setLocationRelativeTo(null); // Centralizar a tela
            novaTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            novaTela.setLayout(new BorderLayout());

            // Carregar a imagem de fundo
            ImageIcon imageIconCombobox = new ImageIcon(HomeView.class.getResource("/JELF DYNAMICS.jpg"));
            Image imageCombobox = imageIconCombobox.getImage();
            Image resizedImageCombobox = imageCombobox.getScaledInstance(novaTela.getWidth(), novaTela.getHeight(), Image.SCALE_SMOOTH);

            // Criar o JLabel para exibir a imagem de fundo
            JLabel backgroundLabelCombobox = new JLabel(new ImageIcon(resizedImageCombobox));
            backgroundLabelCombobox.setLayout(new BorderLayout());
            backgroundLabelCombobox.setOpaque(true); // Tornar o fundo opaco para a visibilidade dos campos

            // Criar painel para o título com fundo preto transparente
            JPanel tituloPanel = new JPanel();
            tituloPanel.setLayout(new BorderLayout());
            tituloPanel.setBackground(new Color(0, 0, 0, 150)); // Cor preta com transparência (alpha 150)

            JLabel titulo = new JLabel("Cadastro de Modelo", JLabel.CENTER);
            titulo.setFont(new Font("SansSerif", Font.BOLD, 28)); // Fonte do título
            titulo.setForeground(Color.WHITE); // Cor do título

            tituloPanel.add(titulo, BorderLayout.CENTER);
            tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno

            // Painel de entrada de dados
            JPanel panelEntrada = new JPanel();
            panelEntrada.setLayout(new GridBagLayout());
            panelEntrada.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            // Criar o fundo preto transparente para o título "Marca" e o campo de combo box "Marca"
            JPanel campoMarcaPanel = new JPanel();
            campoMarcaPanel.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente
            campoMarcaPanel.setLayout(new BorderLayout());

            // Campos de entrada
            JLabel lblMarca = new JLabel("Marca do Carro:");
            lblMarca.setForeground(Color.white);
            lblMarca.setOpaque(true);
            lblMarca.setFont(new Font("SansSerif", Font.BOLD, 28));
            lblMarca.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente para o rótulo "Marca"

            MarcaService marcaService = new MarcaService(new MarcaRepository());

            // Alteração para JComboBox com lista
            List<Marca> listaMarcas = marcaService.getMarcas();

            List<String> listaMarcaString = new ArrayList<>();

            for (Marca marca : listaMarcas) {
                listaMarcaString.add(marca.getDescricao());
            }

            JComboBox<String> cmbMarca = new JComboBox<>(listaMarcaString.toArray(new String[0]));

            // Estilização do JComboBox para o cmbMarca
            cmbMarca.setPreferredSize(new Dimension(180, 50));
            cmbMarca.setFont(new Font("SansSerif", Font.BOLD, 14));
            cmbMarca.setBackground(new Color(230, 240, 255));
            cmbMarca.setForeground(new Color(50, 50, 50));
            cmbMarca.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(0, 0, 0), 2),
                    "Selecione a Marca",
                    TitledBorder.CENTER, // Centraliza o título
                    TitledBorder.TOP, // Posição do título
                    new Font("SansSerif", Font.BOLD, 12),
                    new Color(50, 50, 50)));

            // Renderizador personalizado para os itens do cmbMarca
            cmbMarca.setRenderer(new DefaultListCellRenderer() {
                @Override
                public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    label.setFont(new Font("SansSerif", Font.BOLD, 14));
                    label.setBorder(new EmptyBorder(5, 10, 5, 10));
                    if (isSelected) {
                        label.setBackground(new Color(100, 150, 255));
                        label.setForeground(Color.WHITE);
                    } else {
                        label.setBackground(Color.WHITE);
                        label.setForeground(Color.BLACK);
                    }
                    return label;
                }
            });

            cmbMarca.setToolTipText("Selecione a marca do carro");

            // Criar o fundo preto transparente para o campo de "Observação"
            JPanel campoObservacaoPanel = new JPanel();
            campoObservacaoPanel.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente
            campoObservacaoPanel.setLayout(new BorderLayout());

            JLabel lblModelo = new JLabel("Modelo do Carro:");
            lblModelo.setForeground(Color.white);
            lblModelo.setOpaque(true);
            lblModelo.setFont(new Font("SansSerif", Font.BOLD, 28));
            lblModelo.setBackground(new Color(0, 0, 0, 150)); // Fundo preto transparente para o rótulo "Marca"

            JTextField txtObservacao = new JTextField(20);
            txtObservacao.setForeground(Color.BLACK);
            txtObservacao.setToolTipText("Digite uma observação (se necessário)");

            // Adicionar os componentes aos seus respectivos painéis com fundo preto transparente
            campoMarcaPanel.add(cmbMarca, BorderLayout.CENTER);
            campoObservacaoPanel.add(txtObservacao, BorderLayout.CENTER);

            // Botões
            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.setBackground(new Color(34, 139, 34)); // Cor verde
            btnSalvar.setForeground(Color.white);
            btnSalvar.setFocusPainted(false);
            btnSalvar.addActionListener(e -> {
                ModeloController modeloController;

                try {
                    modeloController = new ModeloController(novaTela);
                    long idMarca = 0;
                    for (Marca marca : listaMarcas) {
                        if (marca.getDescricao().equals(cmbMarca.getSelectedItem().toString())) {
                            idMarca = marca.getIdMarca();
                        }
                    }
                    modeloController.createModelo(txtObservacao.getText(), idMarca);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                cmbMarca.setSelectedIndex(0);  // Resetar a seleção da combo box
                txtObservacao.setText("");
            });

            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.setBackground(new Color(220, 20, 60)); // Cor vermelha
            btnCancelar.setForeground(Color.white);
            btnCancelar.setFocusPainted(false);
            btnCancelar.addActionListener(e -> novaTela.dispose());

            // Adicionar os componentes ao painel
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelEntrada.add(lblMarca, gbc);

            gbc.gridx = 1;
            panelEntrada.add(campoMarcaPanel, gbc); // Adicionar painel com fundo preto transparente para o campo "Marca"

            gbc.gridx = 0;
            gbc.gridy = 1;
            panelEntrada.add(lblModelo, gbc);

            gbc.gridx = 1;
            panelEntrada.add(campoObservacaoPanel, gbc); // Adicionar painel com fundo preto transparente para o campo "Observação"

            // Botões "Salvar" e "Cancelar"
            JPanel panelButtons = new JPanel();
            panelButtons.setOpaque(false); // Tornar o painel de botões transparente
            panelButtons.add(btnSalvar);
            panelButtons.add(btnCancelar);

            gbc.gridx = 1;
            gbc.gridy = 2;
            panelEntrada.add(panelButtons, gbc);

            // Adicionar o painel de entrada ao fundo
            backgroundLabelCombobox.add(panelEntrada, BorderLayout.CENTER);

            // Adicionar o painel do título no topo
            backgroundLabelCombobox.add(tituloPanel, BorderLayout.NORTH);

            // Definir o conteúdo da janela
            novaTela.setContentPane(backgroundLabelCombobox);
            novaTela.setVisible(true);
        }
    }
