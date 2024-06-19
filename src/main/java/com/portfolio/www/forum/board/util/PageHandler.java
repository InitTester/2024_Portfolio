package com.portfolio.www.forum.board.util;

/* 페이징 처리를 위한 전체 페이지 */
public class PageHandler {
	
	/* PageHandler에 필요한 필드 선언
	 * 
	 * size : 게시물 수
	 * page : 현재 페이지
	 * totalPage : 총 게시물 수
	 * naviSize : 게시물 하단 네비 수
	 *  
	 * totalPageSize 계산을 위한 수량 
	 * begin 시작 페이지 
	 * end   끝 페이지
	 * prev 이전 화살표 
	 * next 다음 화살표
	 */
	private final Integer size; // 게시물 view 수량(ex .. 10개씩,20개씩)
	private final Integer page;
	private final Integer totalPage;
	private final Integer naviSize = 10; // 게시물 네비 수량 고정수량
	
	private Integer totalPageSize;
	private Integer begin;
	private Integer end;
	private Integer prev;
	private Integer next;
	
	public PageHandler(Integer size, Integer page, Integer totalPage) {
		// TODO Auto-generated constructor stub
		this.size = size;
		this.page = page;		
		this.totalPage = totalPage;

		setTotalPageSize(size, totalPage);
		setBegin(size, page);
		setEnd(size);
		setPrev();
		setNext();
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(Integer size, Integer totalPage) {
		this.totalPageSize = totalPage / size;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer size, Integer page) {
		this.begin = page == 0 ? 1 : ((page - 1) / size) * size + 1;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer size) {
		this.end = Math.min(getBegin() + size - 1, getTotalPageSize());
	}

	public Integer getPrev() {
		return prev;
	}

	public void setPrev() {
		
		this.prev = getBegin() <= naviSize ? 1 : getBegin();		
	}

	public Integer getNext() {
		return next;
	}

	public void setNext() {
		this.next = getEnd() != totalPage ? getEnd() : totalPage;
	}

	public Integer getSize() { 
		return size; 
	}
	 
	public Integer getPage() {
		return page;
	}

	public Integer getNaviSize() {
		return naviSize;
	}
}
