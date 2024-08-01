package dto;

public class CategoryDto {
    private String ctgID;
    private String ctgName;
    
    public CategoryDto() {
    }
    public CategoryDto(String ctgID, String ctgName) {
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
        return "CategoryDto [ctgID=" + ctgID + ", ctgName=" + ctgName + "]";
    }
    

    
    
}
