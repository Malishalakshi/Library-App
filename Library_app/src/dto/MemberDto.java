package dto;

public class MemberDto {
    private String memberID;
    private String memberName;
    private String dob;
    private String adrress;
    private String contact;
    private String email;
    public MemberDto() {
    }
    public MemberDto(String memberID, String memberName, String dob, String adrress, String contact, String email) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.dob = dob;
        this.adrress = adrress;
        this.contact = contact;
        this.email = email;
    }
    public String getMemberID() {
        return memberID;
    }
    public String getMemberName() {
        return memberName;
    }
    public String getDob() {
        return dob;
    }
    public String getAdrress() {
        return adrress;
    }
    public String getContact() {
        return contact;
    }
    public String getEmail() {
        return email;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "MemberDto [memberID=" + memberID + ", memberName=" + memberName + ", dob=" + dob + ", adrress="
                + adrress + ", contact=" + contact + ", email=" + email + "]";
    } 

    
    
}