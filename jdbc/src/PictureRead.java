import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PictureRead {
    Connection connection = null;

    public PictureRead() {
        try {
            String url = "jdbc:mysql://localhost:3306/example";
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        try {
            String sql = "insert into images(id,img)values(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            File file = new File("C:\\Users\\song\\Pictures\\高清壁纸Z\\1.jpg");

            FileInputStream input = new FileInputStream(file);
            ps.setString(1, "1");
            ps.setBinaryStream(2, input, (int) file.length());
            ps.executeUpdate();
            System.out.println("插入成功");
            ps.close();
            input.close();
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read() {
        try {
            String sql = "select img from images where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            byte[] b = new byte[10240 * 10];

            while (rs.next()) {
                InputStream in = rs.getBinaryStream("img");
                in.read(b);
                File f = new File("C:\\Users\\song\\Pictures\\高清壁纸Z\\2.jpg");
                FileOutputStream out = new FileOutputStream(f);
                out.write(b, 0, b.length);
                out.close();
                System.out.println("成功获取图片");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PictureRead pictureRead = new PictureRead();
        pictureRead.insert();
        pictureRead.Read();
    }
}
