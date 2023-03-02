package src;

 // This is the dialog for Employee search by ID

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchByIdDialog extends JDialog implements ActionListener, SearchDialog {
    private EmployeeDetails parent;
    private JButton search, cancel;
    private JTextField searchField;

    // constructor for SearchByIdDialog 
    public SearchByIdDialog(EmployeeDetails parent) {
        setTitle("Search by ID");
        setModal(true);
        this.parent = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(searchPane());
        setContentPane(scrollPane);

        getRootPane().setDefaultButton(search);
        
        setSize(500, 190);
        setLocation(350, 250);
        setVisible(true);
    }

    // initialize search container
    public Container searchPane() {
        JPanel searchPanel = new JPanel(new GridLayout(3, 1));
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLabel searchLabel;

        searchPanel.add(new JLabel("Search by ID"));

        textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        textPanel.add(searchLabel = new JLabel("Enter ID:"));
        searchLabel.setFont(this.parent.font1);
        textPanel.add(searchField = new JTextField(20));
        searchField.setFont(this.parent.font1);
        searchField.setDocument(new JTextFieldLimit(20));
        
        buttonPanel.add(search = new JButton("Search"));
        search.addActionListener(this);
        search.requestFocus();
        
        buttonPanel.add(cancel = new JButton("Cancel"));
        cancel.addActionListener(this);

        searchPanel.add(textPanel);
        searchPanel.add(buttonPanel);

        return searchPanel;
    }

    // implementation of SearchDialog interface method
    @Override
    public List<Employee> search() {
        List<Employee> employees = new ArrayList<>();
        try {
            double id = Double.parseDouble(searchField.getText());
            this.parent.searchByIdField.setText(String.valueOf(id));
            employees = this.parent.searchEmployeeById();
            dispose();
        } catch (NumberFormatException num) {
            searchField.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(null, "Wrong ID format!");
        }
        return employees;
    }
    // implementation of SearchDialog interface method
    @Override
    public void cancel() {
        dispose();
    }
    // implementation of ActionListener interface method
    public void actionPerformed(ActionEvent e) {
        // if option search, search for Employee
        if (e.getSource() == search) {
            search();
        } else if (e.getSource() == cancel) {
            cancel();
        }
    }
    // implementation of SearchDialog interface method
    @Override
    public String getSearchText() {
        return searchField.getText();
    }
}