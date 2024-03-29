package br.com.paulodt.apicurrencyconverter.entity;

public class Conversion {
    private String date;
    private Info info;
    private String rate;
    private String result;
        
    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getRate() {
      return rate;
    }

    public void setRate(String rate) {
      this.rate = rate;
    }

    public String getResult() {
      return result;
    }

    public void setResult(String result) {
      this.result = result;
    }

    @Override
    public String toString() {
      return "Conversion [date=" + date + ", rate=" + rate + ", result=" + result + "]";
    }

    public Info getInfo() {
      return info;
    }

    public void setInfo(Info info) {
      this.info = info;
    }

}
