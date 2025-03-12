package tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.Scanner;

public class menu extends contacts
{
    static
    {
        new menu();
    }

    private JFrame _frame;
    private JTable _table;
    private DefaultTableModel _table_model;
    private super_market _super_market;

    private void create_frame()
    {
        _frame = new JFrame("Супер маркет");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(400, 300);
        _frame.setLayout(new BorderLayout());
    }

    private void create_table_content()
    {
        _table_model = new DefaultTableModel(new Object[]{"ID", "Имя", "Поставщик"}, 0);
        _table = new JTable(_table_model);
        _frame.add(new JScrollPane(_table), BorderLayout.CENTER);
    }

    private void create_frame_buttons()
    {
        JButton button_add = new JButton("Добавить продукт");
        JButton button_print = new JButton("Печать");

        button_add.addActionListener(e -> open_add_product_dialog());
        button_print.addActionListener(e -> dump_products());

        _frame.add(button_add, BorderLayout.SOUTH);
        _frame.add(button_print, BorderLayout.SOUTH);
    }

    private void dump_products()
    {
        System.out.println("Список продуктов:");
        for (int i = 0; i < _table_model.getRowCount(); i++)
        {
            int id = (int) _table_model.getValueAt(i, 0);
            String name = (String) _table_model.getValueAt(i, 1);
            String supplier = (String) _table_model.getValueAt(i, 2);
            System.out.printf("ID: %d, Имя: %s, Поставщик: %s%n", id, name, supplier);
        }
    }

    private void open_add_product_dialog()
    {
        JDialog dialog = new JDialog(_frame, "Добавить продукт", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new FlowLayout());

        JTextField idField = new JTextField(5);
        JTextField nameField = new JTextField(10);
        JTextField supplierField = new JTextField(10);
        JButton confirmButton = new JButton("Добавить");

        dialog.add(new JLabel("ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Имя:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Поставщик:"));
        dialog.add(supplierField);
        dialog.add(confirmButton);

        confirmButton.addActionListener(e ->
        {
            try
            {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String supplier = supplierField.getText();

                contacts.super_market.item product = _super_market.add_product(nameField.getText(), supplierField.getText(), "Россия", 55666, 520);
                _table_model.addRow(new Object[]{product.get_code(), product.get_name(), product.get_vendor()});

                dialog.dispose();
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(dialog, "Введите корректные данные!");
            }
        });

        dialog.setVisible(true);
    }

    @Override
    public void execute(Scanner scanner)
    {
        _super_market = new super_market("Пятёрочка");
        create_frame();
        create_table_content();
        create_frame_buttons();
        _frame.setVisible(true);
    }

    @Override
    public int get_task_id()
    {
        return 6;
    }
}
