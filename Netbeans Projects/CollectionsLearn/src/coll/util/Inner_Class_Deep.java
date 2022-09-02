package coll.util;

public class Inner_Class_Deep {

    public static void main(String[] args) {
        Book b = new Book("Power");

//        Book.Cover cover = b.getCover();
//        Can't access private member class instance.


//        System.out.println(b.getCover().toString());
//        toString() in coll.util.Book.Cover is defined in an inaccessible class or interface
        
        System.out.println(b.getCover());

    }

}

class Book {

    private Cover cover;

    Book(String cover) {
        this.cover = new Cover(cover);
    }

    private class Cover {

        String title;
        private int pages;

        public Cover(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Cover{" + "title=" + title + ", pages=" + pages + '}';
        }
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }
}
