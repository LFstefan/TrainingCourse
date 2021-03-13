public class ExceptionTest {
    public static void main(String[] args) {
        System.out.println("开始测试");
        // Long tradeNo = 3L;
        Long tradeNo = null;
        try {
            AppResponse.TRADE_NO_NOT_EXIST.checkNull(tradeNo);
        } catch (BusinessException e) {
            AppException appException = e.getAppException();
            System.out.println(appException.getCode() + " --- " + appException.getMessage());
        }
        System.out.println("测试结束");
    }
}