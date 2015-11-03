package com.free.module.common.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.free.module.common.model.vo.NoticeVo;

public class CmNoticeProcess {
	private static final Logger logger = LoggerFactory.getLogger(CmNoticeProcess.class);
	
	public List<NoticeVo> selectNoticeList(){
		return this.getTestList();
	}

	public NoticeVo selectNoticeDetail(Map<String, String> mParam){
		String sSeq = mParam.get("seq");
		
		for( NoticeVo notice : this.getTestList() ){
			logger.debug("seq : " + sSeq + ", notice seq : " + notice.getSeq());
			
			if( sSeq.equals(notice.getSeq()) ){
				return notice;
			}
		}
		
		return null;
	}
	
	private List<NoticeVo> getTestList(){
		List<NoticeVo> notices = new ArrayList<NoticeVo>();
		NoticeVo notice = new NoticeVo();
		notice.setSeq("1");
		notice.setTitle("홈페이지 오픈 했습니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20151010");
		notices.add(notice);
		
		notice = new NoticeVo();
		notice.setSeq("2");
		notice.setTitle("회원가입 관련 사항입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20141010");
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("3");
		notice.setTitle("15차 정기 모임 관련 사항 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20131010");
		notice.setDescription("");
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("4");
		notice.setTitle("2015년 1차 집계 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20131210");
		notices.add(notice);

		notice = new NoticeVo();
		notice.setSeq("5");
		notice.setTitle("사고관련 공지 입니다.");
		notice.setWriter("NEO");
		notice.setReg_date("20101010");
		notices.add(notice);
		return notices;
	}
}
