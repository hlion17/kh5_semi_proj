package dto;

public class Ref {
	private int refMemberNo;
	private int refCode;
	private int MemberNo;
	private String refName;
	
	@Override
	public String toString() {
		return "Ref [refMemberNo=" + refMemberNo + ", refCode=" + refCode + ", MemberNo=" + MemberNo + ", refName="
				+ refName + "]";
	}

	public int getRefMemberNo() {
		return refMemberNo;
	}

	public void setRefMemberNo(int refMemberNo) {
		this.refMemberNo = refMemberNo;
	}

	public int getRefCode() {
		return refCode;
	}

	public void setRefCode(int refCode) {
		this.refCode = refCode;
	}

	public int getMemberNo() {
		return MemberNo;
	}

	public void setMemberNo(int memberNo) {
		MemberNo = memberNo;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}
	
	
}
