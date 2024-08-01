package entity;

public class CategoryEntity {
    private String ctgID;
    private String ctgName;
    public CategoryEntity() {
    }
    public CategoryEntity(String ctgID, String ctgName) {
        this.ctgID = ctgID;
        this.ctgName = ctgName;
    }
    public String getCtgID() {
        return ctgID;
    }
    public void setCtgID(String ctgID) {
        this.ctgID = ctgID;
    }
    public String getCtgName() {
        return ctgName;
    }
    public void setCtgName(String ctgName) {
        this.ctgName = ctgName;
    }
    @Override
    public String toString() {
        return "CategoryEntity [ctgID=" + ctgID + ", ctgName=" + ctgName + "]";
    }
    
    
    
    
    
    
}
