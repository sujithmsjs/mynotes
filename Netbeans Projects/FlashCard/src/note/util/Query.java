package note.util;
public class Query {
    
    public static final String INSERT_WORD = "insert into fcards(title,brief,notes,topic) values(?,?,?,?)";
    public static final String SELECT_WORD = "select title,brief,notes,topic from fcards where title like ?";
    public static final String SEL_ALL_TITLES = "select title from fcards";
    public static final String SEL_ALL_TITLES_ON_DATE = "select title from fcards where entry=?";

}
