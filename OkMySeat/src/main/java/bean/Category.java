package bean;

public class Category {
    private String content_id;
    private String category_id;

    public Category() {

    }

    public Category( String category_id,String content_id) {
        this.content_id = content_id;
        this.category_id = category_id;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
