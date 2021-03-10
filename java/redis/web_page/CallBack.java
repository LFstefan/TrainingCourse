package dcits.liufein.web_page;

/**
 * @Author:liufein
 * @Date:2019/6/6
 * @Description:
 */
public interface CallBack {
    Page callback(String request);
    String data2Json(String args);
}
