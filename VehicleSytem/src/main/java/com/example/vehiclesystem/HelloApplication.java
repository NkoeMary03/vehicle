package com.example.vehiclesystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private TableView<Vehicle> vehicleTable;
    private TableView<Customer> customerTable;
    private TableView<Booking> bookingTable;
    private String currentUserRole = "";

    // In-memory data stores
    private List<User> users = new ArrayList<>();
    private ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private ObservableList<Booking> bookings = FXCollections.observableArrayList();
    private ObservableList<Payment> payments = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Add some sample data
        initializeSampleData();

        primaryStage.setTitle("Vehicle Rental System");
        showWelcomeDashboard(primaryStage);
    }

    private void initializeSampleData() {
        // Add some default users
        users.add(new User("admin", "admin123", "Admin"));
        users.add(new User("employee", "emp123", "Employee"));
        users.add(new User("customer1", "cust123", "Customer"));

        // Add sample vehicles data
        vehicles.add(new Vehicle(1, "Toyota", "Camry", "Sedan", 50.0, true));
        vehicles.add(new Vehicle(2, "Honda", "CR-V", "SUV", 70.0, true));
        vehicles.add(new Vehicle(3, "Ford", "F-150", "Truck", 90.0, false));
        vehicles.add(new Vehicle(4, "Tesla", "Model 3", "Sedan", 85.0, true));
        vehicles.add(new Vehicle(5, "Chevrolet", "Tahoe", "SUV", 95.0, true));
        vehicles.add(new Vehicle(6, "Nissan", "Altima", "Sedan", 55.0, true));
        vehicles.add(new Vehicle(7, "Jeep", "Wrangler", "SUV", 80.0, false));
        vehicles.add(new Vehicle(8, "BMW", "X5", "SUV", 110.0, true));
        vehicles.add(new Vehicle(9, "Mercedes", "C-Class", "Sedan", 100.0, true));
        vehicles.add(new Vehicle(10, "Audi", "Q7", "SUV", 120.0, false));

        // Add sample customers data
        customers.add(new Customer(1, "John Doe", "john@example.com", "DL12345", "Rented Camry in Jan"));
        customers.add(new Customer(2, "Jane Smith", "jane@example.com", "DL67890", "Rented CR-V in Feb"));
        customers.add(new Customer(3, "Robert Johnson", "robert@example.com", "DL24680", "Rented F-150 in Mar"));
        customers.add(new Customer(4, "Emily Davis", "emily@example.com", "DL13579", "Rented Model 3 in Apr"));
        customers.add(new Customer(5, "Michael Wilson", "michael@example.com", "DL11223", "Rented Tahoe in May"));
        customers.add(new Customer(6, "Sarah Brown", "sarah@example.com", "DL44556", "Rented Altima in Jun"));
        customers.add(new Customer(7, "David Taylor", "david@example.com", "DL77889", "Rented Wrangler in Jul"));
        customers.add(new Customer(8, "Jessica Anderson", "jessica@example.com", "DL99001", "Rented X5 in Aug"));
        customers.add(new Customer(9, "Thomas Martinez", "thomas@example.com", "DL33445", "Rented C-Class in Sep"));
        customers.add(new Customer(10, "Lisa Robinson", "lisa@example.com", "DL66778", "Rented Q7 in Oct"));

        // Add some default bookings
        bookings.add(new Booking(1, 1, 1, LocalDate.now().minusDays(10), LocalDate.now().minusDays(5)));
        bookings.add(new Booking(2, 2, 2, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3)));
        bookings.add(new Booking(3, 3, 3, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5)));
        bookings.add(new Booking(4, 4, 4, LocalDate.now().minusDays(3), LocalDate.now().plusDays(7)));
        bookings.add(new Booking(5, 5, 5, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2)));

        // Add some default payments
        payments.add(new Payment(1, 1, 1, 250.0, LocalDate.now().minusDays(5), "Credit Card"));
        payments.add(new Payment(2, 2, 2, 490.0, LocalDate.now().minusDays(3), "Cash"));
        payments.add(new Payment(3, 3, 3, 450.0, LocalDate.now().minusDays(2), "Online"));
        payments.add(new Payment(4, 4, 4, 595.0, LocalDate.now().minusDays(1), "Credit Card"));
        payments.add(new Payment(5, 5, 5, 190.0, LocalDate.now(), "Cash"));
    }

    private void showWelcomeDashboard(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f8ff;");

        // Header
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #F2A2BD; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        Label title = new Label("Welcome to Mary's Vehicle Rental System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        header.getChildren().addAll(title);

        // Center Content - Login and Register Tabs
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Login Tab
        Tab loginTab = new Tab("Login");
        loginTab.setContent(createLoginForm(primaryStage));

        // Register Tab
        Tab registerTab = new Tab("Register");
        registerTab.setContent(createRegisterForm());

        tabPane.getTabs().addAll(loginTab, registerTab);

        // Footer
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(15));
        footer.setStyle("-fx-background-color: #F2A2BD;");
        Label footerText = new Label("2025 Vehicle Rental System. All rights reserved. @ Mary's.");
        footerText.setTextFill(Color.WHITE);
        footer.getChildren().add(footerText);

        root.setTop(header);
        root.setCenter(tabPane);
        root.setBottom(footer);

        Scene scene = new Scene(root, 800, 600);

        // Apply CSS styles
        String css =
                ".root {" +
                        "    -fx-background-color: #f0f8ff;" +
                        "}" +

                        /* Header styles */
                        ".header {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);" +
                        "    -fx-padding: 20;" +
                        "    -fx-alignment: center;" +
                        "}" +

                        /* Footer styles */
                        ".footer {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "    -fx-padding: 15;" +
                        "    -fx-alignment: center;" +
                        "}" +

                        /* General button styles */
                        ".button {" +
                        "    -fx-background-radius: 5;" +
                        "    -fx-border-radius: 5;" +
                        "    -fx-padding: 8 15;" +
                        "    -fx-font-size: 14px;" +
                        "    -fx-cursor: hand;" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);" +
                        "    -fx-transition: all 0.3s;" +
                        "}" +
                        ".button:hover {" +
                        "    -fx-scale-x: 1.03;" +
                        "    -fx-scale-y: 1.03;" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 2);" +
                        "}" +

                        /* Specific button types */
                        ".manage-btn {" +
                        "    -fx-background-color: #3498db;" +
                        "    -fx-text-fill: white;" +
                        "}" +
                        ".manage-btn:hover {" +
                        "    -fx-background-color: #2980b9;" +
                        "}" +

                        ".report-btn {" +
                        "    -fx-background-color: #2ecc71;" +
                        "    -fx-text-fill: white;" +
                        "}" +
                        ".report-btn:hover {" +
                        "    -fx-background-color: #27ae60;" +
                        "}" +

                        ".logout-btn {" +
                        "    -fx-background-color: #e74c3c;" +
                        "    -fx-text-fill: white;" +
                        "}" +
                        ".logout-btn:hover {" +
                        "    -fx-background-color: #c0392b;" +
                        "}" +

                        ".action-btn {" +
                        "    -fx-background-color: #9b59b6;" +
                        "    -fx-text-fill: white;" +
                        "}" +
                        ".action-btn:hover {" +
                        "    -fx-background-color: #8e44ad;" +
                        "}" +

                        ".login-btn {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "    -fx-text-fill: white;" +
                        "}" +
                        ".login-btn:hover {" +
                        "    -fx-background-color: #e891b0;" +
                        "}" +

                        /* Table styles */
                        ".table-view {" +
                        "    -fx-background-color: white;" +
                        "    -fx-border-color: #ddd;" +
                        "    -fx-border-radius: 5;" +
                        "}" +

                        ".table-view .column-header {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "    -fx-text-fill: white;" +
                        "    -fx-font-weight: bold;" +
                        "}" +

                        ".table-view .column-header-background {" +
                        "    -fx-background-color: transparent;" +
                        "}" +

                        ".table-row-cell:odd {" +
                        "    -fx-background-color: #f9f9f9;" +
                        "}" +

                        ".table-row-cell:even {" +
                        "    -fx-background-color: white;" +
                        "}" +

                        ".table-row-cell:selected {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "    -fx-text-fill: white;" +
                        "}" +

                        ".table-row-cell:selected .text {" +
                        "    -fx-fill: white;" +
                        "}" +

                        /* Tab styles */
                        ".tab-pane {" +
                        "    -fx-background-color: #f9f9f9;" +
                        "}" +

                        ".tab {" +
                        "    -fx-background-color: #e0e0e0;" +
                        "    -fx-background-radius: 5 5 0 0;" +
                        "    -fx-padding: 5 15;" +
                        "}" +

                        ".tab:selected {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "    -fx-text-fill: white;" +
                        "}" +

                        /* Form control styles */
                        ".text-field, .password-field, .combo-box, .date-picker, .text-area {" +
                        "    -fx-background-color: white;" +
                        "    -fx-border-color: #ddd;" +
                        "    -fx-border-radius: 5;" +
                        "    -fx-padding: 5;" +
                        "}" +

                        ".text-field:focused, .password-field:focused, .combo-box:focused, .date-picker:focused {" +
                        "    -fx-border-color: #F2A2BD;" +
                        "    -fx-border-width: 2;" +
                        "}" +

                        ".text-area {" +
                        "    -fx-border-radius: 5;" +
                        "}" +

                        ".text-area .content {" +
                        "    -fx-background-color: white;" +
                        "}" +

                        /* Label styles */
                        ".title-label {" +
                        "    -fx-font-size: 20px;" +
                        "    -fx-font-weight: bold;" +
                        "    -fx-text-fill: #333;" +
                        "}" +

                        ".section-label {" +
                        "    -fx-font-size: 16px;" +
                        "    -fx-font-weight: bold;" +
                        "    -fx-text-fill: #444;" +
                        "}" +

                        /* Menu box styles */
                        ".menu-box {" +
                        "    -fx-background-color: #f9f9f9;" +
                        "    -fx-border-radius: 5;" +
                        "    -fx-background-radius: 5;" +
                        "    -fx-padding: 20;" +
                        "    -fx-spacing: 15;" +
                        "    -fx-alignment: center;" +
                        "}" +

                        /* Form container styles */
                        ".form-container {" +
                        "    -fx-background-color: white;" +
                        "    -fx-border-radius: 5;" +
                        "    -fx-background-radius: 5;" +
                        "    -fx-padding: 25;" +
                        "    -fx-spacing: 20;" +
                        "    -fx-alignment: center;" +
                        "}" +

                        /* Chart styles */
                        ".chart {" +
                        "    -fx-background-color: white;" +
                        "    -fx-border-radius: 5;" +
                        "}" +

                        ".chart-title {" +
                        "    -fx-text-fill: #333;" +
                        "    -fx-font-weight: bold;" +
                        "}" +

                        ".axis-label {" +
                        "    -fx-text-fill: #555;" +
                        "}" +

                        /* Alert/dialog styles */
                        ".dialog-pane {" +
                        "    -fx-background-color: #f9f9f9;" +
                        "}" +

                        ".dialog-pane .header-panel {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "}" +

                        ".dialog-pane .header-panel .label {" +
                        "    -fx-text-fill: white;" +
                        "    -fx-font-weight: bold;" +
                        "}" +

                        /* Checkbox styles */
                        ".check-box {" +
                        "    -fx-text-fill: #333;" +
                        "}" +

                        ".check-box .box {" +
                        "    -fx-background-color: white;" +
                        "    -fx-border-color: #ddd;" +
                        "}" +

                        ".check-box:selected .mark {" +
                        "    -fx-background-color: #F2A2BD;" +
                        "}" +

                        /* General layout styles */
                        ".vbox, .hbox {" +
                        "    -fx-spacing: 10;" +
                        "    -fx-padding: 10;" +
                        "}" +

                        ".vbox > .button, .hbox > .button {" +
                        "    -fx-alignment: center;" +
                        "}" +

                        ".table-cell {" +
                        "    -fx-alignment: center;" +
                        "}" +

                        ".vbox {" +
                        "    -fx-alignment: center;" +
                        "}";

        scene.getStylesheets().add("data:text/css;charset=utf-8," + css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLoginForm(Stage primaryStage) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.getStyleClass().add("form-container");

        Label loginLabel = new Label("Login to Your Account");
        loginLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);

        ComboBox<String> roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("Admin", "Employee");
        roleCombo.setPromptText("Select Role");
        roleCombo.setMaxWidth(300);

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-btn");
        loginButton.setMaxWidth(300);
        loginButton.setOnAction(_ -> authenticate(usernameField.getText(), passwordField.getText(),
                roleCombo.getValue(), primaryStage));

        vbox.getChildren().addAll(loginLabel, usernameField, passwordField, roleCombo, loginButton);
        return vbox;
    }

    private VBox createRegisterForm() {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.getStyleClass().add("form-container");

        Label registerLabel = new Label("Create New Account");
        registerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Full Name");
        fullNameField.setMaxWidth(300);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setMaxWidth(300);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setMaxWidth(300);

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("action-btn");
        registerButton.setMaxWidth(300);
        registerButton.setOnAction(_ -> registerUser(fullNameField.getText(), emailField.getText(),
                phoneField.getText(), usernameField.getText(), passwordField.getText(),
                confirmPasswordField.getText()));

        vbox.getChildren().addAll(registerLabel, fullNameField, emailField, phoneField,
                usernameField, passwordField, confirmPasswordField, registerButton);
        return vbox;
    }

    private void registerUser(String fullName, String email, String phone, String username,
                              String password, String confirmPassword) {
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                username.isEmpty() || password.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                showError("Username already exists. Please choose another.");
                return;
            }
        }

        // Add new user
        users.add(new User(username, password, "Customer"));
        showSuccess("Registration successful! You can now login.");
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void authenticate(String username, String password, String role, Stage primaryStage) {
        if (role == null) {
            showError("Please select a role!");
            return;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) &&
                    ((role.equals("Admin") && user.getRole().equals("Admin")) ||
                            (role.equals("Employee") && user.getRole().equals("Employee")))) {
                currentUserRole = role;
                if (role.equals("Admin")) {
                    showAdminDashboard(primaryStage);
                } else {
                    showEmployeeDashboard(primaryStage);
                }
                return;
            }
        }

        showError("Invalid Credentials!");
    }

    private void showAdminDashboard(Stage primaryStage) {
        BorderPane layout = new BorderPane();

        // Create menu buttons with consistent styling
        Button manageVehiclesButton = createStyledButton("Manage Vehicles", "manage-btn");
        Button manageCustomersButton = createStyledButton("Manage Customers", "manage-btn");
        Button managePaymentsButton = createStyledButton("Manage Payments", "manage-btn");
        Button reportsButton = createStyledButton("Reports & Analytics", "report-btn");
        Button logoutButton = createStyledButton("Logout", "logout-btn");

        manageVehiclesButton.setOnAction(_ -> manageVehicles(primaryStage));
        manageCustomersButton.setOnAction(_ -> manageCustomers(primaryStage));
        managePaymentsButton.setOnAction(_ -> managePayments(primaryStage));
        reportsButton.setOnAction(_ -> showReports(primaryStage));
        logoutButton.setOnAction(_ -> logout(primaryStage));

        VBox menuBox = new VBox(15, manageVehiclesButton, manageCustomersButton,
                managePaymentsButton, reportsButton, logoutButton);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(20));
        menuBox.getStyleClass().add("menu-box");

        layout.setCenter(menuBox);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void manageVehicles(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        Button backButton = createStyledButton("Back", "manage-btn");
        backButton.setOnAction(e -> showAdminDashboard(primaryStage));

        VBox controls = new VBox(10);
        vehicleTable = new TableView<>();
        setupVehicleTable();
        vehicleTable.setItems(vehicles);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by Brand or Model");

        Button searchButton = createStyledButton("Search", "action-btn");
        searchButton.setOnAction(e -> searchVehicles(searchField.getText()));

        TextField brandField = new TextField();
        brandField.setPromptText("Brand");
        TextField modelField = new TextField();
        modelField.setPromptText("Model");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        TextField priceField = new TextField();
        priceField.setPromptText("Price per Day");
        CheckBox availabilityCheck = new CheckBox("Available");

        Button addButton = createStyledButton("Add Vehicle", "action-btn");
        addButton.setOnAction(e -> addVehicle(brandField.getText(), modelField.getText(),
                categoryField.getText(), priceField.getText(), availabilityCheck.isSelected()));

        Button updateButton = createStyledButton("Update Vehicle", "action-btn");
        updateButton.setOnAction(e -> updateVehicle(brandField.getText(), modelField.getText(),
                categoryField.getText(), priceField.getText(), availabilityCheck.isSelected()));

        Button deleteButton = createStyledButton("Delete Vehicle", "action-btn");
        deleteButton.setOnAction(e -> deleteVehicle());

        HBox vehicleControls = new HBox(10, brandField, modelField, categoryField, priceField, availabilityCheck,
                addButton, updateButton, deleteButton);
        vehicleControls.setAlignment(Pos.CENTER);

        controls.getChildren().addAll(searchField, searchButton, vehicleTable, vehicleControls);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        layout.setCenter(controls);
        layout.setBottom(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(10));

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void setupVehicleTable() {
        TableColumn<Vehicle, Integer> idCol = new TableColumn<>("Vehicle ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Vehicle, String> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vehicle, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Vehicle, Double> priceCol = new TableColumn<>("Price per Day");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Vehicle, Boolean> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));

        vehicleTable.getColumns().addAll(idCol, brandCol, modelCol, categoryCol, priceCol, availableCol);
        vehicleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void addVehicle(String brand, String model, String category, String priceStr, boolean available) {
        if (brand.isEmpty() || model.isEmpty() || category.isEmpty() || priceStr.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            showError("Price must be a number.");
            return;
        }

        int newId = vehicles.isEmpty() ? 1 : vehicles.get(vehicles.size() - 1).getId() + 1;
        vehicles.add(new Vehicle(newId, brand, model, category, price, available));
    }

    private void updateVehicle(String brand, String model, String category, String priceStr, boolean available) {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a vehicle to update.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            showError("Price must be a number.");
            return;
        }

        int index = vehicles.indexOf(selected);
        if (index != -1) {
            vehicles.set(index, new Vehicle(selected.getId(), brand, model, category, price, available));
            vehicleTable.refresh();
        }
    }

    private void deleteVehicle() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a vehicle to delete.");
            return;
        }

        vehicles.remove(selected);
    }

    private void searchVehicles(String query) {
        if (query == null || query.isEmpty()) {
            vehicleTable.setItems(vehicles);
            return;
        }

        ObservableList<Vehicle> filteredList = FXCollections.observableArrayList();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getBrand().toLowerCase().contains(query.toLowerCase()) ||
                    vehicle.getModel().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(vehicle);
            }
        }
        vehicleTable.setItems(filteredList);
    }

    private void showEmployeeDashboard(Stage primaryStage) {
        BorderPane layout = new BorderPane();

        Button manageBookingsButton = createStyledButton("Manage Bookings", "manage-btn");
        Button managePaymentsButton = createStyledButton("Payment & Billing", "manage-btn");
        Button logoutButton = createStyledButton("Logout", "logout-btn");

        manageBookingsButton.setOnAction(_ -> manageBookings(primaryStage));
        managePaymentsButton.setOnAction(_ -> managePayments(primaryStage));
        logoutButton.setOnAction(_ -> logout(primaryStage));

        VBox menuBox = new VBox(15, manageBookingsButton, managePaymentsButton, logoutButton);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(20));
        menuBox.getStyleClass().add("menu-box");

        layout.setCenter(menuBox);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void manageBookings(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Booking System");
        title.getStyleClass().add("title-label");

        bookingTable = new TableView<>();
        setupBookingTable();
        bookingTable.setItems(bookings);

        TextField customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");
        TextField vehicleIdField = new TextField();
        vehicleIdField.setPromptText("Vehicle ID");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");

        Button bookButton = createStyledButton("Book Vehicle", "action-btn");
        bookButton.setOnAction(e -> bookVehicle(customerIdField.getText(), vehicleIdField.getText(),
                startDatePicker.getValue(), endDatePicker.getValue()));

        Button updateButton = createStyledButton("Update Booking", "action-btn");
        updateButton.setOnAction(e -> updateBooking(customerIdField.getText(), vehicleIdField.getText(),
                startDatePicker.getValue(), endDatePicker.getValue()));

        Button cancelButton = createStyledButton("Cancel Booking", "action-btn");
        cancelButton.setOnAction(e -> cancelBooking());

        Button backButton = createStyledButton("Back", "manage-btn");
        backButton.setOnAction(e -> showEmployeeDashboard(primaryStage));

        HBox controls = new HBox(10, customerIdField, vehicleIdField, startDatePicker, endDatePicker);
        HBox buttons = new HBox(10, bookButton, updateButton, cancelButton, backButton);
        controls.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, bookingTable, controls, buttons);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void setupBookingTable() {
        TableColumn<Booking, Integer> bookingIdCol = new TableColumn<>("Booking ID");
        bookingIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Booking, Integer> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<Booking, Integer> vehicleIdCol = new TableColumn<>("Vehicle ID");
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));

        TableColumn<Booking, String> startDateCol = new TableColumn<>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Booking, String> endDateCol = new TableColumn<>("End Date");
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        bookingTable.getColumns().addAll(bookingIdCol, customerIdCol, vehicleIdCol, startDateCol, endDateCol);
        bookingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void bookVehicle(String customerIdStr, String vehicleIdStr, LocalDate startDate, LocalDate endDate) {
        if (customerIdStr.isEmpty() || vehicleIdStr.isEmpty() || startDate == null || endDate == null) {
            showError("Please fill in all fields.");
            return;
        }

        int customerId;
        int vehicleId;
        try {
            customerId = Integer.parseInt(customerIdStr);
            vehicleId = Integer.parseInt(vehicleIdStr);
        } catch (NumberFormatException e) {
            showError("Invalid ID input; please use numeric values.");
            return;
        }

        int newId = bookings.isEmpty() ? 1 : bookings.get(bookings.size() - 1).getId() + 1;
        bookings.add(new Booking(newId, customerId, vehicleId, startDate, endDate));
    }

    private void updateBooking(String customerIdStr, String vehicleIdStr, LocalDate startDate, LocalDate endDate) {
        Booking selected = bookingTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a booking to update.");
            return;
        }

        int customerId = Integer.parseInt(customerIdStr);
        int vehicleId = Integer.parseInt(vehicleIdStr);

        int index = bookings.indexOf(selected);
        if (index != -1) {
            bookings.set(index, new Booking(selected.getId(), customerId, vehicleId, startDate, endDate));
            bookingTable.refresh();
        }
    }

    private void cancelBooking() {
        Booking selected = bookingTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a booking to cancel.");
            return;
        }

        bookings.remove(selected);
    }

    private void managePayments(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Payment & Billing");
        title.getStyleClass().add("title-label");

        TextField vehicleIdField = new TextField();
        vehicleIdField.setPromptText("Vehicle ID");
        TextField rentalDurationField = new TextField();
        rentalDurationField.setPromptText("Rental Duration (days)");
        TextField additionalServicesField = new TextField();
        additionalServicesField.setPromptText("Additional Services ($)");

        ComboBox<String> paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll("Cash", "Credit Card", "Online");
        paymentMethodCombo.setPromptText("Select Payment Method");

        TextArea invoiceArea = new TextArea();
        invoiceArea.setPromptText("Invoice will be generated here...");
        invoiceArea.setPrefHeight(200);

        Button generateInvoiceButton = createStyledButton("Generate Invoice", "action-btn");
        generateInvoiceButton.setOnAction(e -> generateInvoice(vehicleIdField.getText(),
                rentalDurationField.getText(), additionalServicesField.getText(),
                paymentMethodCombo.getValue(), invoiceArea));

        Button backButton = createStyledButton("Back", "manage-btn");
        backButton.setOnAction(e -> showEmployeeDashboard(primaryStage));

        HBox inputFields = new HBox(10, vehicleIdField, rentalDurationField, additionalServicesField, paymentMethodCombo);
        HBox buttons = new HBox(10, generateInvoiceButton, backButton);
        inputFields.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, inputFields, buttons, invoiceArea);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void generateInvoice(String vehicleIdStr, String rentalDurationStr, String additionalServicesStr, String paymentMethod, TextArea invoiceArea) {
        if (vehicleIdStr.isEmpty() || rentalDurationStr.isEmpty() || additionalServicesStr.isEmpty() || paymentMethod == null) {
            showError("Please fill in all fields.");
            return;
        }

        int vehicleId;
        int rentalDuration;
        double additionalServices;
        try {
            vehicleId = Integer.parseInt(vehicleIdStr);
            rentalDuration = Integer.parseInt(rentalDurationStr);
            additionalServices = Double.parseDouble(additionalServicesStr);
        } catch (NumberFormatException e) {
            showError("Invalid input; please enter numeric values where required.");
            return;
        }

        double rentalPricePerDay = getRentalPriceForVehicle(vehicleId);
        double totalRentalFee = rentalPricePerDay * rentalDuration;
        double totalAmountDue = totalRentalFee + additionalServices;

        String invoice = "Invoice:\n" +
                "Vehicle ID: " + vehicleId + "\n" +
                "Rental Duration: " + rentalDuration + " days\n" +
                "Rental Price per Day: $" + rentalPricePerDay + "\n" +
                "Additional Services: $" + additionalServices + "\n" +
                "Total Amount Due: $" + totalAmountDue + "\n" +
                "Payment Method: " + paymentMethod + "\n" +
                "Thank you for your business!";
        invoiceArea.setText(invoice);
    }

    private double getRentalPriceForVehicle(int vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == vehicleId) {
                return vehicle.getPrice();
            }
        }
        showError("Vehicle ID not found.");
        return 0.0;
    }

    private void showReports(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label title = new Label("Reports & Analytics");
        title.getStyleClass().add("title-label");

        TabPane tabPane = new TabPane();

        Tab availableVehiclesTab = new Tab("Available Vehicles");
        availableVehiclesTab.setContent(createAvailableVehiclesReport());
        availableVehiclesTab.setClosable(false);

        Tab rentalHistoryTab = new Tab("Customer Rental History");
        rentalHistoryTab.setContent(createRentalHistoryReport());
        rentalHistoryTab.setClosable(false);

        Tab revenueTab = new Tab("Revenue Report");
        revenueTab.setContent(createRevenueReport());
        revenueTab.setClosable(false);

        Tab chartsTab = new Tab("Vehicle Statistics");
        chartsTab.setContent(createChartsReport());
        chartsTab.setClosable(false);

        Button backButton = createStyledButton("Back", "manage-btn");
        backButton.setOnAction(e -> showAdminDashboard(primaryStage));

        tabPane.getTabs().addAll(availableVehiclesTab, rentalHistoryTab, revenueTab, chartsTab);

        layout.getChildren().addAll(title, tabPane, backButton);
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private VBox createAvailableVehiclesReport() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label title = new Label("Available Vehicles Report");
        title.getStyleClass().add("section-label");

        TableView<Vehicle> reportTable = new TableView<>();

        TableColumn<Vehicle, Integer> idCol = new TableColumn<>("Vehicle ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Vehicle, String> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vehicle, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        reportTable.getColumns().addAll(idCol, brandCol, modelCol, categoryCol);
        reportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Vehicle> availableVehicles = FXCollections.observableArrayList();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        reportTable.setItems(availableVehicles);

        vbox.getChildren().addAll(title, reportTable);
        return vbox;
    }

    private VBox createRentalHistoryReport() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label title = new Label("Customer Rental History Report");
        title.getStyleClass().add("section-label");

        TableView<RentalHistory> reportTable = new TableView<>();

        TableColumn<RentalHistory, Integer> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<RentalHistory, String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<RentalHistory, Integer> vehicleIdCol = new TableColumn<>("Vehicle ID");
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));

        TableColumn<RentalHistory, String> rentalPeriodCol = new TableColumn<>("Rental Period");
        rentalPeriodCol.setCellValueFactory(new PropertyValueFactory<>("rentalPeriod"));

        reportTable.getColumns().addAll(customerIdCol, customerNameCol, vehicleIdCol, rentalPeriodCol);
        reportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<RentalHistory> rentalHistoryList = FXCollections.observableArrayList();
        for (Booking booking : bookings) {
            String customerName = "Unknown";
            for (Customer customer : customers) {
                if (customer.getId() == booking.getCustomerId()) {
                    customerName = customer.getName();
                    break;
                }
            }
            rentalHistoryList.add(new RentalHistory(
                    booking.getCustomerId(),
                    customerName,
                    booking.getVehicleId(),
                    booking.getStartDate() + " to " + booking.getEndDate()
            ));
        }
        reportTable.setItems(rentalHistoryList);

        vbox.getChildren().addAll(title, reportTable);
        return vbox;
    }

    private VBox createRevenueReport() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label title = new Label("Revenue Report");
        title.getStyleClass().add("section-label");

        BarChart<String, Number> revenueChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        revenueChart.setTitle("Monthly Revenue");
        revenueChart.getStyleClass().add("chart");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Revenue");

        // Generate sample revenue data
        series.getData().add(new XYChart.Data<>("Jan", 2500));
        series.getData().add(new XYChart.Data<>("Feb", 3200));
        series.getData().add(new XYChart.Data<>("Mar", 2800));
        series.getData().add(new XYChart.Data<>("Apr", 4100));
        series.getData().add(new XYChart.Data<>("May", 3700));

        revenueChart.getData().add(series);

        vbox.getChildren().addAll(title, revenueChart);
        return vbox;
    }

    private VBox createChartsReport() {
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Label title = new Label("Vehicle Statistics Charts");
        title.getStyleClass().add("section-label");

        // Pie Chart - Vehicle Categories Distribution
        PieChart categoryPieChart = new PieChart();
        categoryPieChart.setTitle("Vehicle Categories Distribution");

        // Calculate category counts
        int sedanCount = 0, suvCount = 0, truckCount = 0;
        for (Vehicle vehicle : vehicles) {
            switch (vehicle.getCategory()) {
                case "Sedan": sedanCount++; break;
                case "SUV": suvCount++; break;
                case "Truck": truckCount++; break;
            }
        }

        categoryPieChart.getData().addAll(
                new PieChart.Data("Sedan", sedanCount),
                new PieChart.Data("SUV", suvCount),
                new PieChart.Data("Truck", truckCount)
        );

        // Bar Chart - Vehicle Prices Comparison
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> priceBarChart = new BarChart<>(xAxis, yAxis);
        priceBarChart.setTitle("Vehicle Prices Comparison");
        xAxis.setLabel("Vehicle Model");
        yAxis.setLabel("Price per Day");

        XYChart.Series<String, Number> priceSeries = new XYChart.Series<>();
        priceSeries.setName("Rental Price");
        for (Vehicle vehicle : vehicles) {
            priceSeries.getData().add(new XYChart.Data<>(vehicle.getBrand() + " " + vehicle.getModel(), vehicle.getPrice()));
        }
        priceBarChart.getData().add(priceSeries);

        // Line Chart - Availability Trend
        CategoryAxis xAxisLine = new CategoryAxis();
        NumberAxis yAxisLine = new NumberAxis();
        LineChart<String, Number> availabilityLineChart = new LineChart<>(xAxisLine, yAxisLine);
        availabilityLineChart.setTitle("Availability Trend (Sample Data)");
        xAxisLine.setLabel("Month");
        yAxisLine.setLabel("Available Vehicles");

        XYChart.Series<String, Number> availabilitySeries = new XYChart.Series<>();
        availabilitySeries.setName("Available Vehicles");
        availabilitySeries.getData().add(new XYChart.Data<>("Jan", 5));
        availabilitySeries.getData().add(new XYChart.Data<>("Feb", 7));
        availabilitySeries.getData().add(new XYChart.Data<>("Mar", 6));
        availabilitySeries.getData().add(new XYChart.Data<>("Apr", 8));
        availabilitySeries.getData().add(new XYChart.Data<>("May", 9));
        availabilityLineChart.getData().add(availabilitySeries);

        // Stack charts in the VBox
        vbox.getChildren().addAll(
                title,
                new Label("Vehicle Categories Distribution"),
                categoryPieChart,
                new Label("Vehicle Prices Comparison"),
                priceBarChart,
                new Label("Availability Trend"),
                availabilityLineChart
        );

        return vbox;
    }

    private void logout(Stage primaryStage) {
        currentUserRole = "";
        primaryStage.close();
        start(new Stage());
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void manageCustomers(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Customer Management");
        title.getStyleClass().add("title-label");

        customerTable = new TableView<>();
        setupCustomerTable();
        customerTable.setItems(customers);

        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Information");

        TextField licenseField = new TextField();
        licenseField.setPromptText("Driving License Number");

        TextArea rentalHistoryArea = new TextArea();
        rentalHistoryArea.setPromptText("Rental History");
        rentalHistoryArea.setPrefHeight(100);

        Button addButton = createStyledButton("Add Customer", "action-btn");
        addButton.setOnAction(e -> addCustomer(nameField.getText(), contactField.getText(),
                licenseField.getText(), rentalHistoryArea.getText()));

        Button updateButton = createStyledButton("Update Customer", "action-btn");
        updateButton.setOnAction(e -> updateSelectedCustomer(nameField.getText(), contactField.getText(),
                licenseField.getText(), rentalHistoryArea.getText()));

        Button deleteButton = createStyledButton("Delete Customer", "action-btn");
        deleteButton.setOnAction(e -> deleteSelectedCustomer());

        Button backButton = createStyledButton("Back", "manage-btn");
        backButton.setOnAction(e -> showAdminDashboard(primaryStage));

        HBox inputFields = new HBox(10, nameField, contactField, licenseField);
        HBox buttons = new HBox(10, addButton, updateButton, deleteButton, backButton);
        inputFields.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, customerTable, inputFields, rentalHistoryArea, buttons);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void setupCustomerTable() {
        TableColumn<Customer, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

        TableColumn<Customer, String> licenseCol = new TableColumn<>("License Number");
        licenseCol.setCellValueFactory(new PropertyValueFactory<>("licenseNumber"));

        TableColumn<Customer, String> rentalHistoryCol = new TableColumn<>("Rental History");
        rentalHistoryCol.setCellValueFactory(new PropertyValueFactory<>("rentalHistory"));

        customerTable.getColumns().addAll(idCol, nameCol, contactCol, licenseCol, rentalHistoryCol);
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        customerTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Customer selected = customerTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    showCustomerDetails(selected);
                }
            }
        });
    }

    private void addCustomer(String name, String contact, String license, String rentalHistory) {
        if (name.isEmpty() || contact.isEmpty() || license.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        int newId = customers.isEmpty() ? 1 : customers.get(customers.size() - 1).getId() + 1;
        customers.add(new Customer(newId, name, contact, license, rentalHistory));
    }

    private void updateSelectedCustomer(String name, String contact, String license, String rentalHistory) {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a customer to update.");
            return;
        }

        int index = customers.indexOf(selected);
        if (index != -1) {
            customers.set(index, new Customer(selected.getId(), name, contact, license, rentalHistory));
            customerTable.refresh();
        }
    }

    private void deleteSelectedCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a customer to delete.");
            return;
        }

        customers.remove(selected);
    }

    private void showCustomerDetails(Customer customer) {
        showError("Customer ID: " + customer.getId() + "\nName: " + customer.getName() +
                "\nContact: " + customer.getContactInfo() + "\nLicense: " + customer.getLicenseNumber() +
                "\nRental History: " + customer.getRentalHistory());
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setMaxWidth(200);
        button.setMinWidth(200);
        return button;
    }

    // Data model classes
    public static class User {
        private String username;
        private String password;
        private String role;

        public User(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }

    public static class Vehicle {
        private final int id;
        private final String brand;
        private final String model;
        private final String category;
        private final double price;
        private final boolean available;

        public Vehicle(int id, String brand, String model, String category, double price, boolean available) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.category = category;
            this.price = price;
            this.available = available;
        }

        public int getId() { return id; }
        public String getBrand() { return brand; }
        public String getModel() { return model; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public boolean isAvailable() { return available; }
    }

    public static class Customer {
        private final int id;
        private final String name;
        private final String contactInfo;
        private final String licenseNumber;
        private final String rentalHistory;

        public Customer(int id, String name, String contactInfo, String licenseNumber, String rentalHistory) {
            this.id = id;
            this.name = name;
            this.contactInfo = contactInfo;
            this.licenseNumber = licenseNumber;
            this.rentalHistory = rentalHistory;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getContactInfo() { return contactInfo; }
        public String getLicenseNumber() { return licenseNumber; }
        public String getRentalHistory() { return rentalHistory; }
    }

    public static class RentalHistory {
        private final int customerId;
        private final String customerName;
        private final int vehicleId;
        private final String rentalPeriod;

        public RentalHistory(int customerId, String customerName, int vehicleId, String rentalPeriod) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.vehicleId = vehicleId;
            this.rentalPeriod = rentalPeriod;
        }

        public int getCustomerId() { return customerId; }
        public String getCustomerName() { return customerName; }
        public int getVehicleId() { return vehicleId; }
        public String getRentalPeriod() { return rentalPeriod; }
    }

    public static class Booking {
        private final int id;
        private final int customerId;
        private final int vehicleId;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Booking(int id, int customerId, int vehicleId, LocalDate startDate, LocalDate endDate) {
            this.id = id;
            this.customerId = customerId;
            this.vehicleId = vehicleId;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public int getId() { return id; }
        public int getCustomerId() { return customerId; }
        public int getVehicleId() { return vehicleId; }
        public LocalDate getStartDate() { return startDate; }
        public LocalDate getEndDate() { return endDate; }
    }

    public static class Payment {
        private final int id;
        private final int bookingId;
        private final int customerId;
        private final double amount;
        private final LocalDate paymentDate;
        private final String paymentMethod;

        public Payment(int id, int bookingId, int customerId, double amount, LocalDate paymentDate, String paymentMethod) {
            this.id = id;
            this.bookingId = bookingId;
            this.customerId = customerId;
            this.amount = amount;
            this.paymentDate = paymentDate;
            this.paymentMethod = paymentMethod;
        }

        public int getId() { return id; }
        public int getBookingId() { return bookingId; }
        public int getCustomerId() { return customerId; }
        public double getAmount() { return amount; }
        public LocalDate getPaymentDate() { return paymentDate; }
        public String getPaymentMethod() { return paymentMethod; }
    }
}