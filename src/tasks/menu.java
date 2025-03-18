package tasks;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Scanner;

public class menu extends contacts
{
    private static final String FILE_NAME = "products.dat";

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

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(_table_model);
        _table.setRowSorter(sorter);

        _frame.add(new JScrollPane(_table), BorderLayout.CENTER);
    }

    private void create_frame_buttons()
    {
        JButton button_add = new JButton("Добавить продукт");
        JButton button_print = new JButton("Печать");

        JButton button_load_from_file = new JButton("Загрузить");
        JButton button_read_from_file = new JButton("Сохранить");

        button_add.addActionListener(e -> open_add_product_dialog());
        button_print.addActionListener(e -> dump_products());

        button_load_from_file.addActionListener(e -> load_from_file());
        button_read_from_file.addActionListener(e -> read_from_file());

        JPanel panel = new JPanel();
        panel.add(button_add);
        panel.add(button_print);
        panel.add(button_load_from_file);
        panel.add(button_read_from_file);

        _frame.add(panel, BorderLayout.SOUTH);
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

    public void read_from_file()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME)))
        {
            oos.writeObject(_super_market.get_products());
            System.out.println("Products saved successfully.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void load_from_file()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME)))
        {
            List<super_market.item> prd = (List<super_market.item>)ois.readObject();
            _super_market.add_all(prd);

            _table_model.setRowCount(0);

            for (super_market.item product : prd)
            {
                _table_model.addRow(new Object[]{product.get_code(), product.get_name(), product.get_vendor()});
            }

            System.out.println("Products loaded successfully.");
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
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
