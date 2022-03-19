package dto;

public class Ref {
	private int refMemberNo;
	private int refCode;
	private int MemberNo;
	private String refName;
	private int yourMemberNo;
	
	@Override
	public String toString() {
		return "Ref [refMemberNo=" + refMemberNo + ", refCode=" + refCode + ", MemberNo=" + MemberNo + ", refName="
				+ refName + ", yourMemberNo=" + yourMemberNo + "]";
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

	public int getYourMemberNo() {
		return yourMemberNo;
	}

	public void setYourMemberNo(int yourMemberNo) {
		this.yourMemberNo = yourMemberNo;
	}
	
	
}
