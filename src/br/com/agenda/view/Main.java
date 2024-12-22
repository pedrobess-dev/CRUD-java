package br.com.agenda.view;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Main extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField;
    private JTextField idadeField;
    private JButton addButton, updateButton, deleteButton;
    private ContatoDAO contatoDAO;

    public Main() {
        contatoDAO = new ContatoDAO();

        setTitle("Agenda de Contatos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Idade:"));
        idadeField = new JTextField();
        formPanel.add(idadeField);

        addButton = new JButton("Adicionar");
        updateButton = new JButton("Atualizar");
        deleteButton = new JButton("Excluir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Idade", "Data Cadastro"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadTable();

        // Ações dos botões
        addButton.addActionListener(e -> addContato());
        updateButton.addActionListener(e -> updateContato());
        deleteButton.addActionListener(e -> deleteContato());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    nomeField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    idadeField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        for (Contato c : contatoDAO.getContatos()) {
            tableModel.addRow(new Object[]{c.getId(), c.getNome(), c.getIdade(), c.getDataCadastro()});
        }
    }

    private void addContato() {
        String nome = nomeField.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setIdade(idade);
        contato.setDataCadastro(new Date());
        contatoDAO.insert(contato);

        loadTable();
        clearFields();
    }

    private void updateContato() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contato para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nome = nomeField.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contato contato = new Contato();
        contato.setId(id);
        contato.setNome(nome);
        contato.setIdade(idade);
        contato.setDataCadastro(new Date());
        contatoDAO.update(contato);

        loadTable();
        clearFields();
    }

    private void deleteContato() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contato para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        contatoDAO.delete(id);

        loadTable();
        clearFields();
    }

    private void clearFields() {
        nomeField.setText("");
        idadeField.setText("");
    }

    public static void main(String[] args) {
        new Main();
    }
}
