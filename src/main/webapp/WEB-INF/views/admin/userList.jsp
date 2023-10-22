<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
      integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    ></script>
    <script>
      function allCheckbox() {
        let checkbox = $("input[type=checkbox]");
        if ($("#allCheckbox").is(":checked")) {
          checkbox.prop("checked", true);
        } else {
          checkbox.prop("checked", false);
        }
      }

      function updateGrade() {
        let grade = $("select[name=grade]").val();
        let checkboxes = $("input[type=checkbox]:checked");
        checkboxes.each(function () {
          let id = $(this).closest("tr").find("td:eq(1)").text();
          let data = {
            id: id,
            grade: grade,
          };
          $.ajax({
            url: "${pageContext.request.contextPath}/admin/modifyGrade",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (rdata) {
              console.log(rdata);
              /* 질문!!! 성공하면 변경된 정보로 바꾸는 방법은? ajax??? */
              if (rdata == 1) {
                let updateGrade = $("select[name=grade]").val();
                alert("등급은?" + updateGrade);
                $(this).closest("tr").find("td:eq(6)").text(updateGrade);
              }
            },
            error: function (error) {
              alert("등급 변경 실패");
            },
          });
        });
      }
    </script>
  </head>
  <body>
    <h2>회원목록</h2>
    <div>
      <span>선택한 회원을</span>
      <select name="grade">
        <option value="준회원">준회원</option>
        <option value="정회원">정회원</option>
        <option value="우수회원">우수회원</option>
        <option value="관리자">관리자</option>
        <option value="블랙리스트">블랙리스트</option>
      </select>
      <span> 등급으로 변경합니다.</span>
      <button type="button" onclick="updateGrade()">변경하기</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>
            <input type="checkbox" id="allCheckbox" onclick="allCheckbox()" />
          </th>
          <th>회원번호</th>
          <th>이름</th>
          <th>이메일</th>
          <th>전화번호</th>
          <th>프로필사진</th>
          <th>등급</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${userVos}">
          <tr>
            <td><input type="checkbox" /></td>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.phone}</td>
            <td>${item.photo}</td>
            <td>${item.grade}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
