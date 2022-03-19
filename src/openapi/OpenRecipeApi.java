package openapi;

import dto.OpenRecipe;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenRecipeApi {
    // API 기본 정보
    private String key = "7ede7d2b6cc44edca91f";  // 인증키
    private String apiName = "COOKRCP01";  // 서비스명
    private String fileFormat = "json";  // 요청 파일형식

    private String totalCount;
    private JSONArray result;

    public OpenRecipeApi(String menuName, int startIndex, int endIndex) {
        URL url = getUrl(menuName, startIndex, endIndex);
        this.result = jsonUrlParser(url);
    }

    public int getTotal() {
        return Integer.parseInt(this.totalCount);
    }

    // 페이지 정보에 해당하는 게시물 얻어오는 메서드
    public List<OpenRecipe> getPageList() {
        // 레시피 정보를 담을 컬렉션
        List<OpenRecipe> list = new ArrayList<>();

        if (result == null) {
        	return null;
        }
        
        // 분석결과 컬렉션에 저장
        for (Object o : result) {
            // 상세 조리결과, 이미지를 담을 컬렉션
            List<Map<String, String>> manualList = new ArrayList<>();

            // 레시피 정보 DTO
            OpenRecipe data = new OpenRecipe();

            JSONObject o1 = (JSONObject) o;

            data.setData1(o1.get("RCP_NM").toString());  // 중복 삭제할 예정
            data.setData2(o1.get("RCP_WAY2").toString());  // 중복 삭제할 예정

            data.setSeq(Integer.parseInt(o1.get("RCP_SEQ").toString()));  // 일련번호
            data.setName((String) o1.get("RCP_NM"));  // 메뉴명
            data.setWay((String) o1.get("RCP_WAY2"));  // 조리방법
            data.setPat((String) o1.get("RCP_PAT2"));  // 요리종류
            data.setEng((String) o1.get("INFO_ENG"));  // 열량
            data.setPro((String) o1.get("INFO_PRO"));  // 단백질
            data.setFat((String) o1.get("INFO_FAT"));  // 지방
            data.setNa((String) o1.get("INFO_NA"));  // 나트륨
            data.setMainImg((String) o1.get("ATT_FILE_NO_MAIN"));  // 메인이미지 경로
            data.setIngrs((String) o1.get("RCP_PARTS_DTLS"));  // 재료정보

            // 상세 조리방법, 이미지 경로 추출 및 결합
            extractDetails(manualList, o1);

            data.setData4(manualList);  // 상세 조리법

            // 레시피 조회 결과 저장
            list.add(data);
        }
        return list;
    }

    // 상세 조리방법 추출하는 메서드
    private void extractDetails(List<Map<String, String>> manualList, JSONObject o1) {
        for (int i = 1; i <= 20; i++) {
            Map<String, String> manualMap = new HashMap<>();

            String manual = "";
            String manualImg = "";

            if (i < 10) {
                manual = "MANUAL0" + i;
                manualImg = "MANUAL_IMG0" + i;
            } else {
                manual = "MANUAL" + i;
                manualImg = "MANUAL_IMG" + i;
            }

            if ("".equals((String) o1.get(manual))) continue;
            manualMap.put((String) o1.get(manual), (String) o1.get(manualImg));

            manualList.add(manualMap);
        }
    }

    // url 요청결과(json) 분석하는 메서드
    // 객체 생성시 초기화 할 때 사용
    private JSONArray jsonUrlParser(URL url)  {
        String result = "";

        // json 읽어오기
        BufferedReader bf;
        JSONObject jsonObject = null;

        try {
            // 요청결과 받아오는 스트림 -> 결과: json
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            // 요청결과 json 분석
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // json 계층 1단계
        JSONObject rcp_way2 = (JSONObject) jsonObject.get(apiName);
        //System.out.println("총 검색결과: " + rcp_way2.get("total_count"));
        this.totalCount = (String) rcp_way2.get("total_count");

        // 검색 결과 가져오기(배열)
        JSONArray row = (JSONArray) rcp_way2.get("row");
        return row;
    }

    // 요청 url 만드는 메서드
    // 객체 생성 초기화 할 때 사용
    private URL getUrl(String itemName, int startIndex, int endIndex) {
        URL url = null;

        // api url 만들기
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api");  // api url
            urlBuilder.append("/" + key); // 서비스키
            urlBuilder.append("/" + apiName);  // API 명
            urlBuilder.append("/" + fileFormat);  // 형식
            urlBuilder.append("/" + startIndex + "/" + endIndex); // 시작, 종료 인덱스
            urlBuilder.append("/RCP_NM=" + URLEncoder.encode(itemName, "UTF-8")); // 메뉴명
            url = new URL(urlBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}
