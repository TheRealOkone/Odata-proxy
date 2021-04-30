package org.odata4j.examples.visual;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Viewform extends JFrame
{
    // Текстовые поля
    JTextField queryField, userField, passwordField, databaseField;


    private String query = null;
    private String user = null;
    private String password = null;
    private String database = null;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Viewform()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание текстовых полей
        queryField = new PTextField("Скрипт",25);
        userField = new PTextField("Пользователь",25);
        queryField.setToolTipText("Команда");
        userField.setToolTipText("Пользователь");
        passwordField = new PTextField("Пароль",25);
        databaseField = new PTextField("БД",25);
        passwordField.setToolTipText("Пароль");
        databaseField.setToolTipText("Адрес БД");
        JButton button = new JButton("Пуск");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query = queryField.getText();
                user = userField.getText();
                password = passwordField.getText();
                database = databaseField.getText();
                setVisible(false);
            }

        });

        // Слушатель окончания ввода
        queryField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query = queryField.getText();
                user = userField.getText();
                password = passwordField.getText();
                database = databaseField.getText();
                setVisible(false);
            }

        });
        userField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query = queryField.getText();
                user = userField.getText();
                password = passwordField.getText();
                database = databaseField.getText();
                setVisible(false);
            }

        });
        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query = queryField.getText();
                user = userField.getText();
                password = passwordField.getText();
                database = databaseField.getText();
                setVisible(false);
            }

        });
        databaseField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query = queryField.getText();
                user = userField.getText();
                password = passwordField.getText();
                database = databaseField.getText();
                setVisible(false);
            }

        });

        JPanel contents = new JPanel(new GridLayout(5,1));
        button.setHorizontalAlignment   (SwingConstants.CENTER );
        button.setSize(300,20);
        contents.add(queryField);
        contents.add(userField);
        contents.add(passwordField);
        contents.add(databaseField);
        contents.add(button);
        setContentPane(contents);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}
