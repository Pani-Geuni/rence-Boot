<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ****************** -->
<!-- Host Apply SECTION -->
<!-- ****************** -->

<section class="bodyWrap">
	<div class="applyWrap">
		<div class="titleWrap">
			<h1>호스트 신청하기</h1>
		</div>

		<form action="backoffice_insertOK" id="insertForm" method="POST" enctype="multipart/form-data">
			<div class="inputWrap">
				<p>사업자 이름</p>
				<input type="text" id="owner_name" name="owner_name"
					placeholder="사업자 이름을 입력하세요" />
			</div>
			<div class="inputWrap">
				<p>사업자 등록 번호</p>
				<div class="check_wrap">
					<input type="text" id="backoffice_id" name="backoffice_id"
						placeholder="사업자 등록 번호를 입력하세요 (- 포함)" />
					<span class="warning-text blind">형식에 맞지 않습니다.</span>
				</div>
			</div>
			<div class="inputWrap">
				<p>상호명</p>
				<input type="text" id="backoffice_name" name="backoffice_name"
					placeholder="상호명을 입력해 주세요" />
			</div>
			<div class="inputWrap">
				<p>사업체 명</p>
				<input type="text" id="company_name" name="company_name"
					placeholder="사업체 명을 입력해 주세요" />
			</div>
			<div class="inputWrap">
				<p>사업자 전화번호</p>
				<div class="check_wrap">
					<input type="tel" id="backoffice_tel" name="backoffice_tel" placeholder="사업자 전화번호를 입력해 주세요. (- 포함)" />
					<span class="warning-text blind">형식에 맞지 않습니다.</span>
				</div>
			</div>
			<div class="inputWrap email">
				<p>사업자 이메일</p>
				<div>
					<input type="email" id="backoffice_email" name="backoffice_email" placeholder="사업자 이메일을 입력해 주세요" />
					<input type="button" id="btn-certification" value="인증번호 발송">
				</div>
			</div>
			<div class="inputWrap email">
				<p>사업자 이메일 확인</p>
				<div>
					<input type="text" id="auth_code" name="auth_code" placeholder="인증 번호를 입력하세요" />
					<input type="button" id="btn-check-certification" value="인증번호 확인">
				</div>
			</div>
			<div class="inputWrap location">
				<p>사업장 위치</p>
				<div class="input-location">
					<div>
						<input type="text" id="zipcode" name="zipcode" placeholder="우편번호" readonly/>
						<input id="find-zipcode" onclick="sample4_execDaumPostcode()" type="button" value="우편번호 찾기" />
					</div>

					<input type="text" id="roadname_address" name="roadname_address" placeholder="도로명주소" readonly /> <br /> 
					<input type="text" id="number_address" name="number_address" placeholder="지번주소" readonly /> <br /> 
					<input type="text" id="detail_address" name="detail_address" placeholder="상세주소" />
				</div>
				<!-- END input-location -->
			</div>
			<!-- END inputWrap location  -->

<!-- 			<div class="inputWrap"> -->
<!-- 				<p>사업체 태그</p> -->
<!-- 				<input type="text" id="backoffice_tag" name="backoffice_tag" -->
<!-- 					placeholder="사업체의 태그를 입력해 주세요" /> -->
<!-- 			</div> -->

			<div class="inputWrap info">
				<p>사업체 소개</p>
				<div class="check_wrap">
					<textarea id="backoffice_info" name="backoffice_info" placeholder="공간 소개를 입력해 주세요"></textarea>
					<div class="b_info_txt_length_wrap">
						<span class="b_info_txt_length">0</span>
						<span>&nbsp;/ 500</span>
					</div>
				</div>
			</div>
			
			<div class="inputWrap backoffice_type">
	        	<p>공간 태그</p>
	            <div class="option-group-column">
	              <div class="option-group-row">
	                <div class="option-item">
	                  <input
	                    type="checkbox"
	                    id="type_checkbox_desk"
	                    class="checkbox"
	                    name="backoffice_type"
	                    value="desk"
	                    readonly
	                  />
	                  <label>데스크</label>
	                </div>
	                <!-- END option-item -->
	                <div class="option-item">
	                  <input
	                    type="checkbox"
	                    id="type_checkbox_meeting_room"
	                    class="checkbox"
	                    name="backoffice_type"
	                    value="meeting_room"
	                    readonly
	                  />
	                  <label>회의실</label>
	                </div>
	                <!-- END option-item -->
	                <div class="option-item">
	                  <input
	                    type="checkbox"
	                    id="type_checkbox_office"
	                    class="checkbox"
	                    name="backoffice_type"
	                    value="office"
	                    readonly
	                  />
	                  <label>오피스</label>
	                </div>
	                <!-- END option-item -->
	              </div>
	              <!-- END option-group-row -->
	            </div>
	            <!-- END option-group-column -->
	          </div>
	          <!-- END inputWrap backoffice_type -->

			<div class="inputWrap option">
				<p>공간 옵션</p>
				<div class="option-group-column">
					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="chair-desk" class="checkbox" name="backoffice_option"
								value="chair-desk"  readonly /> <label>의자/테이블</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="internet-wifi" class="checkbox" name="backoffice_option"
								value="internet-wifi" /> <label>인터넷/Wi-Fi</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="restroom" class="checkbox" name="backoffice_option"
								value="restroom" /> <label>내부 화장실</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="no-smoking" class="checkbox" name="backoffice_option"
								value="no-smoking" /> <label>금연</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="smoking-room" class="checkbox" name="backoffice_option"
								value="smoking-room" /> <label>흡연실</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="terrace-rooftop" class="checkbox" name="backoffice_option"
								value="terrace-rooftop" /> <label>테라스/루프탑</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="tv-projector" class="checkbox" name="backoffice_option"
								value="tv-projector" /> <label>TV/프로젝터</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="whiteboard" class="checkbox" name="backoffice_option"
								value="whiteboard" /> <label>화이트보드</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="sound-microphone" class="checkbox" name="backoffice_option"
								value="sound-microphone" /> <label>음향/마이크</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="parking" class="checkbox" name="backoffice_option"
								value="parking" /> <label>주차</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="pc-laptop" class="checkbox" name="backoffice_option"
								value="pc-laptop" /> <label>PC/노트북</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="printer" class="checkbox" name="backoffice_option"
								value="printer" /> <label>복사/인쇄기</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="lounge" class="checkbox" name="backoffice_option" value="lounge" />
							<label>공용 라운지</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="kitchen" class="checkbox" name="backoffice_option"
								value="kitchen" /> <label>공용 주방</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="water-purifier" class="checkbox" name="backoffice_option"
								value="water-purifier" /> <label>정수기</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="can-food" class="checkbox" name="backoffice_option"
								value="can-food" /> <label>음식물 반입 가능</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="can-drink" class="checkbox" name="backoffice_option"
								value="can-drink" /> <label>주류 반입 가능</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="air-conditioner" class="checkbox" name="backoffice_option"
								value="air-conditioner" /> <label>에어컨</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="fitting-room" class="checkbox" name="backoffice_option"
								value="fitting-room" /> <label>탈의실</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="shower" class="checkbox" name="backoffice_option" value="shower" />
							<label>샤워시설</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="body-mirror" class="checkbox" name="backoffice_option"
								value="body-mirror" /> <label>전신거울</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="door-lock" class="checkbox" name="backoffice_option"
								value="door-lock" /> <label>도어락</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="outlet-multitap" class="checkbox" name="backoffice_option"
								value="outlet-multitap" /> <label>콘센트/멀티탭</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="personal-locker" class="checkbox" name="backoffice_option"
								value="personal-locker" /> <label>개인락커</label>
						</div>
					</div>
					<!-- END option-group row -->
					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="fax" class="checkbox" name="backoffice_option" value="fax" />
							<label>팩스</label>
						</div>
					</div>
					<!-- END option-group row -->
				</div>
				<!-- END option-group column -->
			</div>
			<!-- END inputWrap option -->

			<div class="inputWrap facilities">
				<p>주변 시설</p>
				<div class="option-group-column">
					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="public-parking" class="checkbox" name="backoffice_around"
								value="public-parking" /> <label>공영주차장</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="paid-parking" class="checkbox" name="backoffice_around"
								value="paid-parking" /> <label>유료주차장</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="pharmacy" class="checkbox" name="backoffice_around"
								value="pharmacy" /> <label>약국</label>
						</div>
					</div>
					<!-- END option-group row -->

					<div class="option-group-row">
						<div class="option-item">
							<input type="checkbox" id="hospital" class="checkbox" name="backoffice_around"
								value="hospital" /> <label>병원</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="convenience-store" class="checkbox" name="backoffice_around"
								value="convenience-store" /> <label>편의점</label>
						</div>

						<div class="option-item">
							<input type="checkbox" id="cafe" class="checkbox" name="backoffice_around"
								value="cafe" /> <label>카페</label>
						</div>
					</div>
					<!-- END option-group row -->
				</div>
				<!-- END option-group column -->
			</div>
			<!-- END inputWrap facilities -->

			<div class="inputWrap time">
				<p>운영 시간</p>

				<div class="custom-table">
					<div class="custom-table-heading">
						<div class="table-head-cell">
							<p>요일</p>
						</div>
						<div class="table-head-cell">
							<p>오픈 시간</p>
						</div>
						<div class="table-head-cell">
							<p>마감 시간</p>
						</div>
						<div class="table-head-cell">
							<p>휴무일 체크</p>
						</div>
					</div>
					<!-- END custom table heading -->

					<div class="custom-table-body">
						<div class="table-body-row">
							<div class="table-body-cell">일</div>
							<div class="table-body-cell">
								<input type="text" id="sun_stime" class="time-picker"
									name="sun_stime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="sun_etime" class="time-picker"
									name="sun_etime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="sun_dayoff" name="sun_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->

						<div class="table-body-row">
							<div class="table-body-cell">월</div>
							<div class="table-body-cell">
								<input type="text" id="mon_stime" class="time-picker"
									name="mon_stime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="mon_etime" class="time-picker"
									name="mon_etime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="mon_dayoff" name="mon_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->

						<div class="table-body-row">
							<div class="table-body-cell">화</div>
							<div class="table-body-cell">
								<input type="text" id="tue_stime" class="time-picker"
									name="tue_stime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="tue_etime" class="time-picker"
									name="tue_etime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="tue_dayoff" name="tue_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->

						<div class="table-body-row">
							<div class="table-body-cell">수</div>
							<div class="table-body-cell">
								<input type="text" id="wed_stime" class="time-picker"
									name="wed_stime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="wed_etime" class="time-picker"
									name="wed_etime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="wed_dayoff" name="wed_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->

						<div class="table-body-row">
							<div class="table-body-cell">목</div>
							<div class="table-body-cell">
								<input type="text" id="thu_stime" class="time-picker"
									name="thu_stime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="thu_etime" class="time-picker"
									name="thu_etime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="thu_dayoff" name="thu_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->

						<div class="table-body-row">
							<div class="table-body-cell">금</div>
							<div class="table-body-cell">
								<input type="text" id="fri_stime" class="time-picker"
									name="fri_stime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="fri_etime" class="time-picker"
									name="fri_etime" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="fri_dayoff" name="fri_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->

						<div class="table-body-row">
							<div class="table-body-cell">토</div>
							<div class="table-body-cell">
								<input type="text" id="sat_stime" class="time-picker"
									name="sat_stime" value="" readonly />
							</div>
							<div class="table-body-cell">
								<input type="text" id="sat_etime" class="time-picker"
									name="sat_etime" value="" readonly />
							</div>
							<div class="table-body-cell">
								<input type="checkbox" id="sat_dayoff" name="sat_dayoff" value="T" />
							</div>
						</div>
						<!-- END table body row -->
					</div>
					<!-- END custom table body -->
				</div>
				<!-- END custom table -->
			</div>
			<!-- END inputWrap time -->

			<div class="inputWrap">
				<p>공간 사진</p>
	
				<div class="filebox">
	              <input class="upload-name" value="" placeholder="첨부파일(.jpg/.jpeg/.png)" />
	              <label for="multipartFile_room">파일찾기</label>
	              <input type="file" id="multipartFile_room" name="multipartFile_room" accept=".jpg, .jpeg, .png" multiple="multiple" />
	            </div>
	          	<input type="file" id="multipartFile_host" name="multipartFile_host" style="display: none;"/>
          </div>
          <!-- END inputWrap image -->

			<div class="submit">
				<input type="button" id="submit" class="submit-application" value="호스트 신청하기">
				<input type="submit" id="real-submit" class="submit-application" value="호스트 신청하기" style="display:none;">
			</div>
		</form>
	</div>
	<!-- END applyWrap -->
</section>
<!-- END bodyWrap -->