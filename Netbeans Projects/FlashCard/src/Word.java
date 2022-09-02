
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import note.util.DBUtil;

public class Word {

   private String title;
   private String brief;
   private String notes;
   private String topic;
   private InputStream image;


    
    

    public Word(String title, String brief, String notes, String topic, InputStream image) {
        this.title = title;
        this.brief = brief;
        this.notes = notes;
        this.topic = topic;
        this.image = image;
        onLoadValidate();
    }
    
    
    
    
    private void onLoadValidate() {
       if(title==null) title = "";
       if(brief==null) brief = "";
       if(notes==null) notes = "";
       if(topic==null) topic = "";
    }
    
    public static Word getWordByTitle(String title) {
        Word word = null;
        try {
            String query = "select title,brief,notes,topic,image from fcards";
            PreparedStatement ps = DBUtil.getPreStmt(query);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                System.out.println(rs.getRow());
                String t = rs.getString(1);
                String brief = rs.getString(2);
                String notes = rs.getString(3);
                String topic = rs.getString(4);;
                Blob blob = rs.getBlob(5);
                InputStream binaryStream = null;
                if (blob != null) {
                    binaryStream = blob.getBinaryStream();
                }
                word = new Word(t, brief, notes, topic, binaryStream);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return word;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    public String getNotes() {
        return notes;
    }

    public String getTopic() {
        return topic;
    }

    public InputStream getImage() {
        return image;
    }
    
    
    public boolean deleteIt(){
        boolean isDeleted = false ;
        try {
            String query = "delete from fcards where title=?";
            PreparedStatement ps = DBUtil.getPreStmt(query);
            ps.setString(1, title);
            int executeUpdate = ps.executeUpdate();
            isDeleted = executeUpdate>0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isDeleted;
    }
    
    public boolean deleteWordByTitle(String title) {
        boolean isDeleted = false;
        try {
            String query = "delete from fcards where title=?";
            PreparedStatement ps = DBUtil.getPreStmt(query);
            ps.setString(1, title);
            int executeUpdate = ps.executeUpdate();
            isDeleted = executeUpdate > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isDeleted;
    }
    
    
    public boolean update(Word w) {
        boolean isUpdated = false;
       try {
           String title = w.getTitle();
           String brief= w.getBrief();
           String notes= w.getNotes();
           String topic = w.getTitle();
           InputStream image = w.getImage();
           String q = "update fcards set title=?, brief=?,notes=?,topic=?,image=? where title=?";
           PreparedStatement ps = DBUtil.getPreStmt(q);
           ps.setString(1, title);
           ps.setString(2, brief);
           ps.setString(3, notes);
           ps.setString(4, topic);
           ps.setBlob(5, image);
           ps.setString(6, title);
           
           isUpdated = ps.executeUpdate()>0;
       } catch (Exception ex) {
           System.out.println(ex);
       }
       return isUpdated;
    }
    public static void main(String[] args) {
        Word w = new Word("Thokka", "Updated", "Upated", "Updated", null);
        w.update(w);
    }

//    public boolean update(Word w) {
//        boolean delete = deleteWordByTitle(w.title);
//        return w.save();
//    }

    
    public boolean isExisted(){
        boolean isExisted = false;
        try {
            String query = "select title from fcards where title=?";
            PreparedStatement ps = DBUtil.getPreStmt(query);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                isExisted= true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return isExisted;
    }
 
    
    public boolean save() {
       boolean  isSaved = false;
        
        try {
            String query = "insert into fcards(title,brief,notes,topic,image) values(?,?,?,?,?)";
            PreparedStatement ps = DBUtil.getPreStmt(query);
            ps.setString(1, title);
            ps.setString(2, brief);
            ps.setString(3, notes);
            ps.setString(4, topic);
            //InputStream blobFile = BlobImageUtil.getBlobFile(image);
            //   BufferedImage bufferedImage = BlobImageUtil.getBufferedImage(image);
            //   InputStream inputStream = BlobImageUtil.getInputStream(bufferedImage);
//            boolean hasImage = true;
//
//            if (image != null) {
//                hasImage = false;
                ps.setBlob(5, image);
//            }
            isSaved = ps.executeUpdate()>0;            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isSaved;
    }

    public static Word getWord(String title){
        Word w = null;
        try {
            String query = "select title,brief,notes,topic,image from fcards where title=?";
            PreparedStatement ps = DBUtil.getPreStmt(query);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                String t = rs.getString(1);
                String brief = rs.getString(2);
                String notes = rs.getString(3);
                String topic = rs.getString(4);
                Blob blob = rs.getBlob(5);
                InputStream binaryStream = null;
                if(blob!=null)
                    binaryStream = blob.getBinaryStream();
                w = new Word(title, brief, notes, topic, binaryStream);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return w;
    }

    @Override
    public String toString() {
        return "Word{" + "title=" + title + ", brief=" + brief + ", notes=" + notes + ", topic=" + topic + ", image=" + image + '}';
    }
}
