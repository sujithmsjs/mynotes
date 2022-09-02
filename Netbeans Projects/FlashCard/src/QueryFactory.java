
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import note.util.DBUtil;


public class QueryFactory {
    public static boolean addNewTopic(String showInputDialog) {
        boolean isSaved = false;
        try {
           String q = "insert into topics(tname) values(?)";
            PreparedStatement ps = DBUtil.getPreStmt(q);
            ps.setString(1, showInputDialog);
            if(ps.executeUpdate()>0){
                isSaved = true;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            //ex.printStackTrace();
        }
        return isSaved;
    }

    private String selectColumns;
    private String orderBy;
    private String title;
    private String entry;
    private String topic;
    private String images;
    
    private boolean hasTitle;
    private boolean hasEntry;
    private boolean hasTopic;
    private boolean hasImages;
    private String isDesc;
    private int paraCode;

    public void setSelectColumns(String selectColumns) {
        this.selectColumns = selectColumns;
    }

    public void setOrderBy(String orderBy) {
        if(!orderBy.equals(""))
            this.orderBy = " order by "+orderBy;
        else
            this.orderBy="";
    }

    public void setConditions(boolean hasTitle, boolean hasEntry, boolean hasTopic, boolean hasImages) {
        this.hasTitle = hasTitle;
        this.hasEntry = hasEntry;
        this.hasTopic = hasTopic;
        this.hasImages = hasImages;
    }


    public void setValues(String title, String entry, String topic, String images) {
        this.title = "%"+title+"%";
        this.entry = entry;
        this.topic = topic;
        this.images = images;
    }
    
    public String getConditions(){
        paraCode = 0;
        String cond = "";
        if (hasImages) {
            if (images.equals("None")) {
                cond = cond + "image is null and ";
            } else {
                cond = cond + "image is not null and ";
            }
            paraCode+=1;
        }
        if (hasEntry) {
            if(entry.length()>0&&!entry.equals("All")){
                cond = cond +" date_format(entry,'%b %D')=? and "; //date_format(entry,'%b %D')='Apr 22nd'
                paraCode+=2;
            }
        }
        
        if (hasTitle) {
            if (title.length() > 0) {
                cond = cond + " title like(?) and "; //date_format(entry,'%b %D')='Apr 22nd'
                paraCode+=4;
            }
        }
                
        if (hasTopic) {
            if (topic.length() > 0) {
                cond = cond + " topic=? and "; //date_format(entry,'%b %D')='Apr 22nd'
                paraCode+=8;
            }
        }
        
        int lastIndexOf = cond.lastIndexOf("and");
        String conditions="";
        if(lastIndexOf>0)
            conditions = cond.substring(0, lastIndexOf);
        
        if(paraCode>0)
            conditions=" where "+conditions;
        
        
        System.out.println(paraCode);

            
        
      return  conditions+orderBy;
    }
    
    
    public String getQuery(){
        String condition = getConditions();
        condition = "select "+selectColumns+" from fcards "+condition;
        if(condition.indexOf("by")>0)
            condition = condition+" "+isDesc;
        return condition;
    }

    public int getParaCode() {
        return paraCode;
    }

    void setDescOrder(String desc) {
        this.isDesc = desc;
    }
    
    public Vector<String> getTitles() {
        Vector<String> al = null;
        try {
            String query = getQuery();
            PreparedStatement ps = DBUtil.getPreStmt(query);
            switch (paraCode) {
                case 2:
                case 3:
                    ps.setString(1, entry);
                    break;
                case 4:
                case 5:
                    ps.setString(1, title);
                    break;
                case 6:
                case 7:
                    ps.setString(1, entry);
                    ps.setString(2, title);
                    break;
                case 8:
                case 9:
                    ps.setString(1, topic);
                    break;
                case 10:
                case 11:
                    ps.setString(1, entry);
                    ps.setString(2, topic);
                    break;
                case 12:
                case 13:
                    ps.setString(1, title);
                    ps.setString(2, topic);
                    break;
                case 14:
                case 15:
                    ps.setString(1, entry);
                    ps.setString(2, title);
                    ps.setString(3, topic);
            }
            al = new Vector<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                al.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return al;
    }
    
    public ResultSet getTitleResultSet() {
        Vector<String> al = null;
        ResultSet rs = null;
        try {
            String query = getQuery();
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            switch (paraCode) {
                case 2:
                case 3:
                    ps.setString(1, entry);
                    break;
                case 4:
                case 5:
                    ps.setString(1, title);
                    break;
                case 6:
                case 7:
                    ps.setString(1, entry);
                    ps.setString(2, title);
                    break;
                case 8:
                case 9:
                    ps.setString(1, topic);
                    break;
                case 10:
                case 11:
                    ps.setString(1, entry);
                    ps.setString(2, topic);
                    break;
                case 12:
                case 13:
                    ps.setString(1, title);
                    ps.setString(2, topic);
                    break;
                case 14:
                case 15:
                    ps.setString(1, entry);
                    ps.setString(2, title);
                    ps.setString(3, topic);
            }
            al = new Vector<>();
            rs = ps.executeQuery();
            System.out.println("Prapared Statement : "+ps.toString());
            rs.last();
            int rows = rs.getRow();
            System.out.println("rows = " + rows);
           
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static void main(String[] args) {
        QueryFactory qf = new QueryFactory();
        qf.setValues("", "", "", "");
        qf.setConditions(false, true, false, false);
        qf.setSelectColumns("distinct date_format(entry,'%b %D')");
        qf.setOrderBy("entry");
        qf.setDescOrder("asc");
        ResultSet rs = qf.getTitleResultSet();
        WordReader r = new WordReader(rs,1);
        System.out.println(r.getWordsCount());
        
    }
//    
//    public static String[] getTopics(){
//        
//        QueryFactory qf = new QueryFactory();
//        qf.setValues("", "", "", "");
//        qf.setConditions(false, true, false, false);
//        qf.setSelectColumns("distinct topic");
//        qf.setOrderBy("entry");
//        qf.setDescOrder("asc");
//        Vector<String> topics = qf.getTitles();
//        System.out.println(qf.getQuery());
//        System.out.println(qf.getTitles());
//        return topics.toArray(new String[]{});
//    }
    
    public static String[] getTopics(){
        String q = "select tname from topics order by tname";
        Vector<String> vect = null;
        try {
            PreparedStatement ps = DBUtil.getPreStmt(q);
            ResultSet rs = ps.executeQuery();
            vect = new Vector<>();
            while(rs.next()){
                vect.add(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String[] toArray = null;
        if(vect!=null){
            toArray = vect.toArray(new String[]{});
        }
            //System.out.println(vect);
         return toArray;
    }
    
    public static String[] getDates(){
        QueryFactory qf = new QueryFactory();
        qf.setValues("", "", "", "");
        qf.setConditions(false, true, false, false);
        qf.setSelectColumns("distinct date_format(entry,'%b %D')");
        qf.setOrderBy("entry");
        qf.setDescOrder("asc");
        Vector<String> dates = qf.getTitles();
        System.out.println(qf.getQuery());
        System.out.println(qf.getTitles());
        return dates.toArray(new String[]{});
    }

}
