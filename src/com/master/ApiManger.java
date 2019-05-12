package com.master;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 這只是方便同學了解打api的流程操作,同學們可以依照自己喜歡的方式做
 */
public class ApiManger {
    /**
     * url跟token不用改
     */
    private String url = "https://campus.kits.tw/api/v1/data/";
    private String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjFhNjM3NDcwOTg3NTVhM2U4NjA2MzM2MzViNTkwYzQ2ZTA2ZTYwMTg3MjQzMTBkZjEwZGZmZGEwM2FkNjI1MmY1ZTNiMWJjNzNiNTE4MjI0In0.eyJhdWQiOiIyIiwianRpIjoiMWE2Mzc0NzA5ODc1NWEzZTg2MDYzMzYzNWI1OTBjNDZlMDZlNjAxODcyNDMxMGRmMTBkZmZkYTAzYWQ2MjUyZjVlM2IxYmM3M2I1MTgyMjQiLCJpYXQiOjE1NTY5NDk1NzMsIm5iZiI6MTU1Njk0OTU3MywiZXhwIjoxNTg4NTcxOTczLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.3niLgPCQ55Q1mar3Sp0PLa2A7x_2P574CJlKSb2wfKHKU2t-TVQ0NiKovDOWIItoRpnoyLa6YDNTC4csl4yAkAA3NO1HBML7_2Vlex_ir9av9fyB-Rw1bWbtqS50b0aerpLIdYpawO7EkYFc2S3Ph8dTZyYiuaeTC00HHl157uic92kPGcxs3SFKP45xxbkx4S1Xx-1k90bbOhothodift6tyBLp2FpGiaCE16B64dIXXy7_QKHOs-bC5a4PySk7qz2zOWk_cib8yoGZBDTxA70MHENaW27WWRMj-o81JVEF8Ad5_lz-gn8w7nW5b-OiHPVQ749PmXuRryvv-jEJWFDqOmsglMwWxnZU2MrM3t1iyYyPUXxNOb-vM1VJ1TJSy0lxp9y_1GXbKPl9e0XHiMt49aaSck1wKh1moKDx1NM-j_l88IUETQgLR3WYYZ_DFfYwxEhVCLZtlgNQIb2kMFGivrZCL6BJlr0CkmOr8w8R8ZkxbkMn0XUIWx8104ho0IDjOPm_0smE-gKrlHVoquKhZ_2G6L7rfrsM6XNjy3Idbt7ed83RMBLLL5afU3LYFk02q3_DkYxOBqdcjdiExrgy--2A_6erZExKmR-xq3MlX0go5C-x7-CYE9VmX281DLrNw0ujzYp4ntC36CZVaRrIfNppvR2a0dsFnOkbWjM";
    private HttpURLConnection connection;

    public ApiManger() throws IOException {
        httpConfig();
    }

    /**
     * 初始化連線資訊
     * @throws IOException
     */
    private void httpConfig() throws IOException {
        URL object = new URL(url);
        connection = (HttpURLConnection) object.openConnection();
        // optional method is GET
        connection.setRequestMethod("GET");
        //add request header
        connection.setRequestProperty ("Authorization", token);
        connection.setRequestProperty("Accept", "application/json");
    }

    /**
     *
     * @param macAddress 要找的sensor的MacAddress
     * @return 回傳一個List裡面包含sensor的資料
     */
    public List<SensorModel> getSensorDataByMacAddress(String macAddress) throws IOException {
        url += macAddress;
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        List<SensorModel> dataList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.toString());
        for(int i = 0;i < jsonArray.length(); i++){
            JSONObject sensorModel = jsonArray.getJSONObject(i);
            SensorModel sensorModel1 = new SensorModel(
                    sensorModel.getInt("id"),
                    sensorModel.optString("macaddr"),
                    sensorModel.optString("data"),
                    sensorModel.optString("lat"),
                    sensorModel.optString("lng"),
                    sensorModel.optString("created_at"),
                    sensorModel.optString("updated_at"));
            dataList.add(sensorModel1);
        }
        return dataList;
    }

}
