package kr.co.manager.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by avalo on 2017-03-02.
 */
public class LocaleResource {

    static final String ResourceName = "exception.ErrorMessage";

    public static void init(String language)
    {
        if (language.toLowerCase().startsWith("ko"))
            init(Locale.KOREAN);
        else if (language.toLowerCase().startsWith("en"))
            init(Locale.ENGLISH);
        else
            init(Locale.ENGLISH);
    }

    public static void init(Locale locale)
    {
        resourceBundle = getResourceBundle(locale);
    }

    private static ResourceBundle resourceBundle = getResourceBundle();


    private static LocaleResource instance = null;

    public static synchronized LocaleResource getInstance()
    {
        if (instance == null)
            instance = new LocaleResource();

        return instance;
    }

    private LocaleResource()
    {
    }

    public String getMessage(String id)
    {
        try {
            return resourceBundle.getString(id);
        }catch(MissingResourceException e)
        {
            //아이디와 관련되는 메시지를 찾을 수 없다면 ID를 그냥 반환한다.
            return id;
        }
    }

    public String getMessage(String id, Object[] arguments)
    {
        String message = getMessage(id);
        try {
            return MessageFormat.format(message, arguments);
        }catch(Throwable t)
        {
            return message;
        }
    }

    static ResourceBundle getResourceBundle()
    {
        return getResourceBundle(Locale.getDefault());
    }

    static ResourceBundle getResourceBundle(Locale locale)
    {
        return ResourceBundle.getBundle(ResourceName , locale);
    }

    public String toString()
    {
        return resourceBundle.toString();
    }

}
