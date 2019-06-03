package StockTradingSystem.http_utils;

import com.google.gson.Gson;


public class CustomResp {
    private String resultJSON;
    private String objectJSON;
    private Gson json = new Gson();

    public CustomResp(String oneJSON) {
        resultJSON = new Gson().fromJson(oneJSON, CustomResp.class).resultJSON;
        objectJSON = new Gson().fromJson(oneJSON, CustomResp.class).objectJSON;
    }

    public CustomResp(String resultJSON, String objectJSON) {
        this.resultJSON = resultJSON;
        this.objectJSON = objectJSON;
    }

    public CustomResp(Result result, Object object) {
        this.resultJSON = json.toJson(result);
        this.objectJSON = json.toJson(object);
    }


    public CustomResp(Result result) {
        this.resultJSON = json.toJson(result);
        this.objectJSON = null;
    }


    @Override
    public String toString() {
        return this.resultJSON + "\n" + objectJSON;
    }

    public String getResultJSON() {
        return resultJSON;
    }

    public void setResultJSON(Result result) {
        this.resultJSON = json.toJson(result);
    }

    public String getObjectJSON() {
        return objectJSON;
    }

    public void setObjectJSON(Object object) {
        this.objectJSON = json.toJson(object);
    }

}