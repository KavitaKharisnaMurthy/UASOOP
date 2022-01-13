import  java.sql.SQLException ;
import  java.sql.Statement ;

 public class karyawanalfa {

    private String Nama_karyawan  =  null ;
    private int ID_karyawan  ;
    private  String NO_telp =  null ;
    public karyawanalfa ( int  ID_karyawan, String  Nama_karyawan , String  NO_telp ) {
        this.Nama_karyawan = Nama_karyawan;
        this.ID_karyawan = ID_karyawan;
        this.NO_telp = NO_telp;
    }
    public  String  getNama_karyawan () {
        return Nama_karyawan;
    }
    public int  getID_karyawan () {
        return ID_karyawan;
    }
    public  String  getNO_telp () {
        return NO_telp;
    }
}