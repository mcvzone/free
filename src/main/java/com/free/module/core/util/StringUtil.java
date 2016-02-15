package com.free.module.core.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class StringUtil {
    //private static Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * @param value
     * @param default_value
     * @return
     */
    public static long parseLong(String value, long default_value){
        long return_value = 0l;
        try{
            if(value!=null){
                return_value = Long.parseLong(value.replaceAll(",", ""));
            }
        }catch(Exception ex){
            return_value = default_value;
            //logger.error(ex.getMessage(), ex);
        }
        return return_value;
    }
    /**
     * @param value
     * @param default_value
     * @return
     */
    public static float parseFloat(String value, float default_value){
        float return_value = 0l;
        try{
            if(value!=null){
                return_value = Float.parseFloat(value.replaceAll(",", ""));
            }
        }catch(Exception ex){
            return_value = default_value;
            //logger.error(ex.getMessage(), ex);
        }
        return return_value;
    }
    /**
     * @param value
     * @param default_value
     * @return
     */
    public static double parseDouble(String value, double default_value){
        double return_value = 0l;
        try{
            if(value!=null && !value.trim().equals("")){
                return_value = Double.parseDouble(value.replaceAll(",", ""));
            }
        }catch(Exception ex){
            return_value = default_value;
            //logger.error(ex.getMessage(), ex);
        }
        return return_value;
    }
    /**
     * @param value
     * @param default_value
     * @return
     */
    public static int parseInt(String value, int default_value){
        int return_value = 0;
        try{
            if(value!=null){
                return_value = Integer.parseInt(value.replaceAll(",", ""));
            }
        }catch(Exception ex){
            return_value = default_value;
            //log.logError(ex.getMessage(), ex);
        }
        return return_value;
    }
    /**
     * @return
     */
    public static String [] sort(String [] strValue){
        String sTemp=null;
        for(int idx=0; idx<strValue.length; idx++){
            for(int jdx=0; jdx<strValue.length; jdx++){
                if(strValue[idx].compareTo(strValue[jdx])>0){
                    sTemp = strValue[jdx];
                    strValue[jdx] = strValue[idx];
                    strValue[idx] = sTemp;
                }
            }
        }
        return strValue; 
    }
    /**
     * ����
     * @param sData
     * @param size
     * @return
     */
    public static String [] splitData(String sData, String SeParator){
        String [] result = null;
        try{
            ArrayList<String> arrayList = new ArrayList<String>();
            int nCount = 1;
            int idx=0;
            int iPosStart = 0;
            int iPosEnd   = -1;
            // 
            for(idx=0; idx<sData.length(); idx++){
                if( sData.substring(idx, idx+1).equals(SeParator)){
                    iPosEnd = idx;
                    if(iPosStart<=iPosEnd){
                        arrayList.add(sData.substring(iPosStart, iPosEnd));
                    }
                    iPosStart = idx+1;
                }
            }
            if(iPosStart<idx){
                if(iPosStart<sData.length()){
                    arrayList.add(sData.substring(iPosStart, sData.length()));
                }
            }
            result = new String[arrayList.size()];
            for(idx=0; idx<arrayList.size(); idx++){
                result[idx] = arrayList.get(idx);
            }
        }catch(Exception ex){
            //logger.error(ex.getMessage(), ex);
        }
        
        return result;
    }
    // Null ���� check�Ѵ�.
    public static String checkNull(String str) {
        if(str == null) {
            str = "";
        } else {
            str = str.trim();
        }
        return str;
    }
    // ������ ��ȯ�Ѵ�
    public static String convertKorToUTF(String str) {
        try {
            return new String(str.getBytes("8859_1"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            //logger.error(ex.getMessage(), ex);
        }
        return "";
    }
    // ������ ��ȯ�Ѵ�
    public static String convertUTFToKor(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "8859_1");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

}
