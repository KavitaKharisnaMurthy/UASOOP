import javafx.application.Application;
import javafx.scene.Scene;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.ResultSet;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import java.io.FileNotFoundException;

public class App<Label, VBox, Scene, Button, Stage> extends Application {
    TableView<Database> tableView = new TableView<Database>();

    public static void main(String[] args) {
        launch(args);
    }

    private static void launch(String[] args) {
    }

    @Override
    public <tableView, tableView, tableView> void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("UASOOPR");
        TableColumn<Database, String> columnID_karyawan = new TableColumn<>("No ID");
        columnID_karyawan.setCellValueFactory(new PropertyValueFactory<>("ID_karayawan"));

        TableColumn<Database, String> columnNama_karyawan = new TableColumn<>("Nama karyawan");
        columnNama_karyawan.setCellValueFactory(new PropertyValueFactory<>("Nama_karyawan"));

        TableColumn<Database, String> columnNO_telp = new TableColumn<>("NO telp");
        columnNO_telp.setCellValueFactory(new PropertyValueFactory<>("NO_telp"));

        tableView.getColumns().add(columnID_karyawan);
        tableView.getColumns().add(columnNama_karyawan);
        tableView.getColumns().add(columnNO_telp);

        ToolBar toolBar = new ToolBar();

        Button button1 = new Button("Add Nama karyawan");
        toolBar.getItems().add(button1);
        button1.setOnAction(e -> add());

        Button button2 = new Button("Delete");
        toolBar.getItems().add(button2);
        button2.setOnAction(e -> delete());

        Button button3 = new Button("EDIT");
        toolBar.getItems().add(button3);
        button3.setOnAction(e -> edit());

        Button button4 = new Button("Refresh");
        toolBar.getItems().add(button4);
        button4.setOnAction(e -> re());

        VBox vbox = new VBox(tableView, toolBar);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();
        load();
        
        Statement stmt;
        try {
           Database db = new Database();
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Nama karyawan");
            tableView.getItems().clear();
            // tampilkan hasil query
            while (rs.next()) {
                tableView.getItems().add(new karyawanalfa(rs.getInt("ID_karyawan"),
            rs.getString("Nama_karyawan"), rs.getString("NO_telp")));
            }

            stmt.close();
            db.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Object labelNama_karyawan) {
        Stage addStage = new Stage();
        Button save = new Button("Save");

        addStage.setTitle("add data Nama karyawan");

        TextField Nama_karyawanField = new TextField();
        TextField NO_telpField = new TextField();
        Label labelNama_karyawan = new Label("Nama karyawan");
        Label labelNO_telp = new Label("NO telp");

        VBox hbox1 = new VBox(5, labelNama_karyawan, Nama_karyawanField);
        VBox hbox2 = new VBox(5, labelNO_telp, NO_telpField);
        VBox vbox = new VBox(20, hbox1, hbox2, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "insert into Nama karywan SET Nama_karyawan='%s', NO_telp='%s'";
                sql = String.format(sql, Nama_karyawanField.getText(), NO_telpField.getText());
                state.execute(sql);
                addStage.close();
                load();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }

    public void delete(Object labelID_karyawan, Object ID_karyawanField) {
        Stage addStage = new Stage();
        Button save = new Button("DELETE");

        addStage.setTitle("Delete Data");

        TextField noField = new TextField();
        Label labelNo = new Label("Nama karyawan");

        VBox hbox1 = new VBox(5, labelID_karyawan, ID_karyawanField);
        VBox vbox = new VBox(20, hbox1, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "delete from Nama karyawan WHERE Nama_karyawan='%s'";
                sql = String.format(sql, noField.getText());
                state.execute(sql);
                addStage.close();
                load();
            } catch (SQLException e1) {

                e1.printStackTrace();
                System.out.println();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }

    public void edit() {
        Stage addStage = new Stage();
        Button save = new Button("Save");

        addStage.setTitle("Edit Nama karyawan);

        TextField Nama_karyawanField = new TextField();
        TextField No_telpField = new TextField();
        Label labelNama_karyawan = new Label("Nama karyawan");
        Label labelNO_telp= new Label("NO telp");

        VBox hbox1 = new VBox(5, labelNama_karyawan, Nama_karyawanField);
        VBox hbox2 = new VBox(5, labelNO_telp, NO_telpField);
        VBox vbox = new VBox(20, hbox1, hbox2, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "UPDATE Nama karyawan SET NO_telp='%s' WHERE Nama_karyawan='%s'";
                sql = String.format(sql, NO_telpField.getText(), Nama_karyawanField.getText());
                state.execute(sql);
                addStage.close();
                load();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }

    public void load() {
        Statement stmt;
        tableView.getItems().clear();
        try {
            Database db = new Database ();
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Nama karyawan");
            while (rs.next()) {
                tableView.getItems().addAll(new karyawanalfa(rs.getInt("ID_karyawan"), 
                rs.getString("Nama_karyawan"), rs.getString("NO_telp")));
            }
            stmt.close();
            db.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void re() {
        Database db = new  Database();
        try {
            Statement state = db.conn.createStatement();
            String sql = "ALTER TABLE Nama karyawan DROP ID_karyawan";
            sql = String.format(sql);
            state.execute(sql);
            re2();

        } catch (SQLException e1) {
            e1.printStackTrace();
            System.out.println();
        }
    }

    public void re2() {
        Database db = new Database();
        try {

            Statement state = db.conn.createStatement();
            String sql = "ALTER TABLE menu ADD ID_karyawan INT NOT NULL AUTO_INCREMENTPRIMARY KEY FIRST";
            sql = String.format(sql);
            state.execute(sql);
            load();
        } catch (SQLException e1) {
            e1.printStackTrace();
            System.out.println();
        }
    }
}