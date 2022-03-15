package util;

public class Paging {
	
	private int curPage; //���� ������ ��ȣ

	private int totalCount;	//�� �Խñ� ��
	private int listCount; //�� ������ �� ������ �Խñ� ��
	private int totalPage; //�� �������� ��

	private int pageCount; //�� ȭ�鿡 ��µ� ���������̼��� ����
	private int startPage; //ȭ�鿡 ���̴� ���������̼��� ���� ��ȣ
	private int endPage; //ȭ�鿡 ���̴� ���������̼��� �� ��ȣ

	private int startNo; //ȭ�鿡 ���̴� �Խñ��� ���� ��ȣ
	private int endNo; //ȭ�鿡 ���̴� �Խñ��� �� ��ȣ
	
	
	//����Ʈ ������ - ����¡������ �ϼ����� �ʴ´�
	public Paging() { }
	
	public Paging(int totalCount, int curPage) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		
		makePaging();
	}

	public Paging(int totalCount) {
		setTotalCount(totalCount);
		
		makePaging();
	}
	
	public Paging(int totalCount, int curPage, int listCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		
		makePaging();
	}
	
	public Paging(int totalCount, int curPage, int listCount, int pageCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		setPageCount(pageCount);
		
		makePaging();
	}
	
	
	//����¡ ������ �����ϴ� �޼ҵ�
	private void makePaging() {
		if( totalCount == 0 )	return; //�Խñ��� ���� ��� �ߴ�
		
		//�⺻�� ����
		if( curPage == 0 )		setCurPage(1); //ù �������� �⺻ �������� �����Ѵ�
		if( listCount == 0 )	setListCount(10); //ȭ�鿡 ������ �Խñ� ���� 10���� �����Ѵ�
		if( pageCount == 0 )	setPageCount(10); //ȭ�鿡 ������ ����¡ ���� 10���� �����Ѵ�
		
		//----------------------------------------
		
		//�� �������� �� ���
		totalPage = totalCount / listCount;
		if( totalCount % listCount > 0 )	totalPage++;

		//�� �������� �� ��� ���� �۾�
		if( curPage > totalPage )	curPage = totalPage;
		
		//----------------------------------------
		
		//ȭ�鿡 ���̴� ���������̼��� ���� ��ȣ, �� ��ȣ ���
		startPage = ( (curPage-1)/pageCount ) * pageCount + 1;
		endPage = startPage + pageCount - 1;

		//���������̼� ���� �۾�
		if( endPage > totalPage )	endPage = totalPage;
		
		//----------------------------------------

		// ȭ�鿡 ���̴� �Խñ��� ���� ��ȣ, �� ��ȣ ���
		startNo = ( curPage-1 ) * listCount + 1;
		endNo = curPage * listCount;
	}
	
	
	@Override
	public String toString() {
		return "Paging [curPage=" + curPage + ", totalCount=" + totalCount + ", listCount=" + listCount + ", totalPage="
				+ totalPage + ", pageCount=" + pageCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startNo=" + startNo + ", endNo=" + endNo + "]";
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

}
