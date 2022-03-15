package openapi;

import dto.ExpireDate;
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

public class ExpireDateApi {
    private String key = "7ede7d2b6cc44edca91f";  // 인증키
    private String apiName = "I1250";  // 서비스명
    private String fileFormat = "json";  // 요청 파일형식
    private String pageStart;  // 요청 시작페이지
    private String pageEnd;  // 요청 끝페이지
    private String menuName;  // 품목명

    private Map<String, Map<String, String>> resultMap = new HashMap<>();

    public ExpireDateApi() {
        this.pageStart = "1";
        this.pageEnd = "10";
    }

    public ExpireDateApi(String pageStart, String pageEnd, String menuName) {
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
        this.menuName = menuName;
    }

    public List<ExpireDate> findAll(String itemName) {
        List<ExpireDate> list = new ArrayList<>();

        URL url = getUrl(itemName);
        JSONArray row = jsonUrlParser(url);

        // 검색 결과 저장
        for (Object o : row) {
            JSONObject o1 = (JSONObject) o;

            ExpireDate data = new ExpireDate();

            data.setData1(o1.get("PRDLST_NM").toString());  // 제품명
            data.setData2(o1.get("POG_DAYCNT").toString());  // 유통기한
            data.setData3(o1.get("BSSH_NM").toString());  // 업소명
            data.setData4(o1.get("PRDLST_DCNM").toString());  // 유형

            list.add(data);
        }

        return list;
    }

    private JSONArray jsonUrlParser(URL url) {
        String result ="";
        JSONArray row = null;
        try {
            // json 읽어오기
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            // json 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            // json 계층 1단계
            JSONObject rcp_way2 = (JSONObject) jsonObject.get(apiName);
//            System.out.println("총 검색결과: " + rcp_way2.get("total_count"));
//            System.out.println();

            // 검색 결과 가져오기(배열)
            row = (JSONArray) rcp_way2.get("row");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return row;
    }

    private URL getUrl(String itemName) {
        // api url 만들기
        URL url = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api");  // api url
            urlBuilder.append("/" + key); // 서비스키
            urlBuilder.append("/" + apiName);  // API 명
            urlBuilder.append("/" + fileFormat);  // 형식
            urlBuilder.append("/" + pageStart + "/" + pageEnd); // 요청시작, 종료 위치
            urlBuilder.append("/PRDLST_NM=" + URLEncoder.encode(itemName, "UTF-8")); // 품목명
            url = new URL(urlBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}
