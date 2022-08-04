import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PeptideIds {

    private Connection connect() {
        // SQLite connection string  

        //String url = "jdbc:sqlite:peptide_ids";
        Connection conn = null;
        String url = "jdbc:sqlite:/Users/claricepark/IdeaProjects/data-analysis-of-protein-turnover-using-N15-label/peptide_ids";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT * FROM peptide";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set  
            while (rs.next()) {
//                System.out.println(rs.getInt("Unique") +  "\t" +
//                        rs.getString("FileName") + "\t" +
//                        rs.getDouble("XCorr"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        PeptideIds app = new PeptideIds();
        app.selectAll();
    }

}  