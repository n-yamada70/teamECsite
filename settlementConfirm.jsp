<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/settlementConfirm.css">
<link rel="stylesheet" type="text/css" href="./css/home.css">
<title>決済確認</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<script src="./js/arizona.js"></script>
<div id="contents">

<div class="SettlementConfirmh1">
<h1>決済確認画面</h1>
</div>

<!-- リストが存在し、かつリストにデータが入っていればデータを表示、そうでなければ「宛先情報がありません」を表示 -->

<s:if test="dtoList!=null && dtoList.size()>0">

<div class="info">
		宛先情報を選択してください。
</div>
	<s:form id="settlementConfirmForm">
	<table class="list-table">
		<thead>
		<tr>
 			<th>#</th>
    		<th>姓</th>
    		<th>名</th>
    		<th>ふりがな</th>
    		<th>住所</th>
    		<th>電話番号</th>
    		<th>メールアドレス</th>
  		</tr>
  		</thead>

		<tbody>
		<s:iterator value="dtoList" status="st">
  		<tr>
			<td>
				<!-- 1回目の処理ならば、チェックありのラジオボタンを表示  -->
				<s:if test="#st.index == 0">
				<input type="radio" name="id" checked="checked" value="<s:property value='id'/>"/>
				</s:if>
				<!-- 2回目以降の処理ならば、チェックなしのラジオボタンを表示  -->
				<s:else>
				<input type="radio" name="id" value="<s:property value='id'/>"/>
				</s:else>
  			</td>
    		<td><s:property value="familyName"/></td>
    		<td><s:property value="firstName"/></td>
    		<td><s:property value="familyNameKana"/><span>　</span><s:property value="firstNameKana"/></td>
    		<td><s:property value="userAddress"/></td>
    		<td><s:property value="telNumber"/></td>
    		<td><s:property value="email"/></td>
    	</tr>
    	</s:iterator>
    	</tbody>

    </table>

			<div class="submit_btn_box">
			<s:submit value="決済" class="submit_btn" onClick="goSettlementCompleteAction()"/>
			</div>
			<div class="submit_btn_box">
   			<s:submit value="削除" class="submit_btn" onClick="goDeleteDestinationAction()"/>
   			</div>

   	</s:form>

	</s:if>
	<s:else>
	<div class="info">
	    宛先情報がありません。
	</div>
	</s:else>

	<div class="submit_btn_box">
		<s:form action="CreateDestinationAction">
    		<s:submit value="新規宛先登録" class="submit_btn"/>
   		</s:form>
    </div>

</div>
</body>
</html>