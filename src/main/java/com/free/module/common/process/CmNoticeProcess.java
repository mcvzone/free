package com.free.module.common.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.free.module.common.model.vo.NoticeVo;

import net.sf.json.JSONObject;

public class CmNoticeProcess {
	private static final Logger logger = LoggerFactory.getLogger(CmNoticeProcess.class);
	
	public JSONObject selectNoticeList(){
		logger.debug("list in.......");
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", "ok");
		jsonResult.put("list", this.getTestList());
		return jsonResult;
	}

	public JSONObject selectNoticeDetail(Map<String, String> mParam){
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("list", this.getTestList());
		
		String sSeq = mParam.get("seq");
		for( NoticeVo notice : this.getTestList() ){
			logger.debug("seq : " + sSeq + ", notice seq : " + notice.getSeq());
			
			if( sSeq.equals(notice.getSeq()) ){
				jsonResult.put("desc", notice);
				break;
			}
		}
		
		return jsonResult;
	}
	
	private List<NoticeVo> getTestList(){
		List<String> attachs = new ArrayList<String>();
		attachs.add("axisj-1.0.19.zip");
		attachs.add("chatiNG-master.zip");
		attachs.add("한글.파(일명_사용가)능.zip");
		
		List<NoticeVo> notices = new ArrayList<NoticeVo>();
		NoticeVo notice = new NoticeVo();
		notice.setSeq("1");
		notice.setTitle("홈페이지 오픈 했습니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20151010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("홈페이지 오픈 했습니다.\n기념글 입니다.");
		notice.setRed_count("11");
		//notice.setAttachs(attachs);
		notices.add(notice);
		
		notice = new NoticeVo();
		notice.setSeq("2");
		notice.setTitle("회원가입 관련 사항입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20141010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉모밀");
		notice.setRed_count("22");
		//notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("3");
		notice.setTitle("15차 정기 모임 관련 사항 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20131010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("정기 모임에 꼭 참여 하시기 바랍니다.\n\n\n안그러면 후회 하실꺼에요.");
		notice.setRed_count("333");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("4");
		notice.setTitle("2015년 1차 집계 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20131210");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉면");
		notice.setRed_count("1234");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("5");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("6");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);


		notice = new NoticeVo();
		notice.setSeq("7");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("8");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("9");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("10");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("11");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("12");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("13");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("14");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("15");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("16");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("17");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("18");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("19");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("20");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("21");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("22");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("23");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("24");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("25");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("26");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("27");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("28");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("29");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notice.setStart_date("20150910");
		notice.setEnd_date("20150920");
		notice.setDescription("냉무");
		notice.setRed_count("5678");
//		notice.setAttachs(attachs);
		notices.add(notice);
		return notices;
	}
}
