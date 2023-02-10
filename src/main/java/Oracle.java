import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Oracle {

    public static void main(String[] args) throws ClassNotFoundException {


        String url="jdbc:oracle:thin:@//localhost:1521/XE";
        String user="system";
        String password="oracle";


        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Load success");
            Connection con= DriverManager.getConnection(url,user,password);
            System.out.println("Baglanti basarili"+con);

            Random rand = new Random();
            int numberOfEntries = 50000;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfEntries; i++) {
                String query="insert into DB2 (SAYİ) values ('"+rand.nextInt(2000)+"') ";
                Statement stmt=con.createStatement();
                stmt.execute(query);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Time to put " + numberOfEntries + " entries: " + (endTime - startTime) + "ms");

            List<String> list=new ArrayList<>();
            startTime = System.currentTimeMillis();
            Statement stmt=con.createStatement();
            ResultSet rs;
            rs=stmt.executeQuery("Select * from DB2");
            int sayac=0;
            while ( rs.next() ) {
                String sayi = rs.getString("SAYİ");

                list.add(sayi);
                sayac++;

            }
            endTime = System.currentTimeMillis();

            System.out.println("Time to get " + sayac + " entries: " + (endTime - startTime) + "ms");

        }catch (ClassNotFoundException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
