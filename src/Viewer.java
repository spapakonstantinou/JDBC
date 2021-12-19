import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Viewer{

    JFrame frame2;
    DefaultTableModel defaultTableModel;
    JTable table;
    int flag=0;


    Viewer() {

        //setting the properties of second JFrame
        frame2 = new JFrame("PLAYERS DETAILS");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(1200, 800);

        //Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("player_id");
        defaultTableModel.addColumn("name");
        defaultTableModel.addColumn("surName");
        defaultTableModel.addColumn("FatherName");
        defaultTableModel.addColumn("age");
        defaultTableModel.addColumn("phone");
        defaultTableModel.addColumn("email");
        defaultTableModel.addColumn("password");
        defaultTableModel.addColumn("confirmPassword");
        defaultTableModel.addColumn("country");
        defaultTableModel.addColumn("budget");
        defaultTableModel.addColumn("gender");


        try {

            //String myDriver = "org.gjt.mm.mysql.Driver";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spiros", "root", "6979863701sp");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from spiros.player");


            while (resultSet.next()) {

                //Retrieving details from the database and storing it in the String variables
                String player_id = resultSet.getString("PLAYER_ID");
                String name = resultSet.getString("NAME");
                String surName = resultSet.getString("SURNAME");
                String fatherName = resultSet.getString("FATHERNAME");
                String age = resultSet.getString("AGE");
                String phone = resultSet.getString("PHONE");
                String email = resultSet.getString("EMAIL");
                String password = resultSet.getString("PASSWORD");
                String confirmPassword = resultSet.getString("CONFIRMPASSWORD");
                String country = resultSet.getString("COUNTRY");
                String budget = resultSet.getString("BUDGET");
                String gender = resultSet.getString("GENDER");


                flag = 1;
                defaultTableModel.addRow(new Object[]{player_id, name, surName, fatherName, age, phone, email, password, confirmPassword, country, budget, gender});
                frame2.setVisible(true);
                frame2.validate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}