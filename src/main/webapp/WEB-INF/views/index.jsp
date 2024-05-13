<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<!-- Board Listings -->
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">제목</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boards.content}">
				<tr>
					<th scope="row">${board.id}</th>
					<td style="text-align: left;"><a href="/board/${board.id}">${board.title}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Pagination -->
	<nav aria-label="Page navigation">
		<ul class="pagination justify-content-center">
			<!-- 이전 버튼 표시 -->
			<li class="page-item ${boards.number == 0 ? 'disabled' : ''}"><a class="page-link" href="?page=0" aria-label="Previous"> <span aria-hidden="true">이전</span>
			</a></li>

			<!-- 페이지 번호 -->
			<c:set var="startPage" value="${boards.number > 4 ? boards.number - 4 : 0}" />
			<c:set var="endPage" value="${startPage + 9 < boards.totalPages ? startPage + 9 : boards.totalPages - 1}" />
			<c:forEach begin="${startPage}" end="${endPage}" var="i">
				<li class="page-item ${i == boards.number ? 'active' : ''}"><a class="page-link" href="?page=${i}">${i+1}</a></li>
			</c:forEach>

			<!-- 다음 버튼 표시 -->
			<li class="page-item ${boards.number + 10 >= boards.totalPages ? 'disabled' : ''}"><a class="page-link" href="?page=${boards.number+10}" aria-label="Next"> <span aria-hidden="true">다음
				</span>
			</a></li>
		</ul>
	</nav>

</div>

<%@ include file="layout/footer.jsp"%>
