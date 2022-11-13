package kr.co.manager.exception;

public class QgException extends Exception {

	private static final long serialVersionUID = 1L;
    static final LocaleResource localeResource = LocaleResource.getInstance();

    public QgException(String message) {
        super(localeResource.getMessage(message));
    }

    public QgException(String message, Throwable cause) {
        super(localeResource.getMessage(message), cause);
    }

    public QgException(String message,Object[] arguments) {
        super(localeResource.getMessage(message,arguments));
    }

    public QgException(String message,Object[] arguments, Throwable cause) {
        super(localeResource.getMessage(message,arguments), cause);
    }

    public int getErrorCode()
    {
        int idx = this.getMessage().indexOf(",");
        if (idx < 0)
            return 9999;

        String errorCode = this.getMessage().substring(0,idx).trim();
        try {
            return Integer.parseInt(errorCode);
        }catch(Exception ex)
        {
            return 9999;
        }
    }


    public String getErrorMessage(){

        int idx = this.getMessage().indexOf(",");

        return idx < 0 ? this.getMessage() :  this.getMessage().substring(idx+1, this.getMessage().length()).trim();

    }


    public static void main(String[] args) {

        (new QgException("cmp.invalid.refernumber", new Object[] {123})).printStackTrace();
    }

}
