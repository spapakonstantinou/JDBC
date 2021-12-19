import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class RegistrationForm implements ActionListener {

    //create labels
    JFrame frame;
    String[] gender={"None", "Male", "Female", "Other"};

    JLabel nameLabel = new JLabel("NAME");
    JLabel surNameLabel = new JLabel("SURNAME");
    JLabel fatherNameLabel = new JLabel("FATHER NAME");
    JLabel ageLabel = new JLabel("AGE");
    JLabel phoneLabel = new JLabel("PHONE");
    JLabel emailLabel = new JLabel("EMAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel confirmPasswordLabel = new JLabel("CONFIRM PASSWORD");
    JLabel countryLabel = new JLabel("COUNTRY");
    JLabel budgetLabel = new JLabel("BUDGET");
    JLabel sexLabel = new JLabel("GENDER");

    //create fields
    JTextField nameTextField = new JTextField();
    JTextField surNameTextField = new JTextField();
    JTextField fatherNameTextField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField phoneTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField countryTextField = new JTextField();
    JTextField budgetField = new JTextField();
    JComboBox sexComboBox = new JComboBox(gender);

    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");


    //create window
    RegistrationForm()
    {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow()
    {
        frame=new JFrame();
        frame.setTitle("Registration Form");
        frame.setBounds(240,40,380,800);
        frame.getContentPane().setBackground(Color.white);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public void setLocationAndSize()
    {
        // specific location of labels
        nameLabel.setBounds(20,20,100,70);
        surNameLabel.setBounds(20,70,100,70);
        fatherNameLabel.setBounds(20,120,100,70);
        ageLabel.setBounds(20,170,100,70);
        phoneLabel.setBounds(20,220,100,70);
        emailLabel.setBounds(20,270,100,70);
        passwordLabel.setBounds(20,320,100,70);
        confirmPasswordLabel.setBounds(20,370,140,70);
        countryLabel.setBounds(20,420,100,70);
        budgetLabel.setBounds(20,470,100,70);
        sexLabel.setBounds(20,520,100,70);

        //specific location of fields
        nameTextField.setBounds(180,43,165,23);
        surNameTextField.setBounds(180,93,165,23);
        fatherNameTextField.setBounds(180,143,165,23);
        ageField.setBounds(180,193,165,23);
        phoneTextField.setBounds(180,243,165,23);
        emailTextField.setBounds(180,293,165,23);
        passwordField.setBounds(180,343,165,23);
        confirmPasswordField.setBounds(180,393,165,23);
        countryTextField.setBounds(180,443,165,23);
        budgetField.setBounds(180,493,165,23);
        sexComboBox.setBounds(180,543,165,23);

        registerButton.setBounds(70,650,100,35);
        resetButton.setBounds(220,650,100,35);


    }
    public void addComponentsToFrame()
    {
        frame.add(nameLabel);
        frame.add(surNameLabel);
        frame.add(fatherNameLabel);
        frame.add(ageLabel);
        frame.add(phoneLabel);
        frame.add(emailLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(countryLabel);
        frame.add(budgetLabel);
        frame.add(sexLabel);

        frame.add(nameTextField);
        frame.add(surNameTextField);
        frame.add(fatherNameTextField);
        frame.add(ageField);
        frame.add(phoneTextField);
        frame.add(emailTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(countryTextField);
        frame.add(budgetField);
        frame.add(sexComboBox);

        frame.add(registerButton);
        frame.add(resetButton);


    }
    public void actionEvent()
    {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==registerButton)
        {
            try {

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spiros", "root", "6979863701sp");
                String query = " INSERT INTO player (name, surName, fatherName, age, phone, email, password, confirmPassword, country, budget, gender)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


                PreparedStatement Pstatement=connection.prepareStatement(query);

                Pstatement.setString(1,nameTextField.getText());
                Pstatement.setString(2,surNameTextField.getText());
                Pstatement.setString(3,fatherNameTextField.getText());
                Pstatement.setString(4, String.valueOf(Integer.parseInt(ageField.getText())));
                Pstatement.setString(5,phoneTextField.getText());
                Pstatement.setString(6,emailTextField.getText());
                Pstatement.setString(7,passwordField.getText());
                Pstatement.setString(8, confirmPasswordField.getText());;
                Pstatement.setString(9, countryTextField.getText());;
                Pstatement.setString(10,budgetField.getText());
                Pstatement.setString(11,sexComboBox.getSelectedItem().toString());

                //Checking for the Password match
                if (passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()) && Integer.parseInt(ageField.getText()) >= 18)
                {
                    Pstatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Registered Successfully");
                }
                else
                {
                    if (Integer.parseInt(ageField.getText()) < 18)
                    {
                        JOptionPane.showMessageDialog(null,"You are so young to play lottery games");
                    }
                    if (!(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())))
                    {
                        JOptionPane.showMessageDialog(null,"Please check your password");
                    }
                }

                connection.close();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        }
        if(e.getSource() == resetButton)
        {
            //Clearing Fields
            nameTextField.setText("");
            surNameTextField.setText("");
            fatherNameTextField.setText("");
            ageField.setText("");
            nameTextField.setText("");
            phoneTextField.setText("");
            emailTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            countryTextField.setText("");
            budgetField.setText("");
            sexComboBox.setSelectedItem("None");
        }

        if(e.getSource() == "" ){
            new Choices();
        }
    }
}