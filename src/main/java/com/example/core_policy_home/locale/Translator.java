package com.example.core_policy_home.locale;

import com.example.core_policy_home.constants.enums.Message;
import com.example.core_policy_home.constants.Constants;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Translator {

  private static final MultiResourceBundleControl control
      = new MultiResourceBundleControl(Constants.PATH_MESSAGE + Constants.PATH_ERROR, Constants.PATH_MESSAGE, Constants.PATH_ERROR);

  public static String toMessage(Message msg, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    return getMessage(msg.toString(), locale, getArgTranslate(args));
  }

  public static String toMessage(String code, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    return getMessage(code, locale, getArgTranslate(args));
  }

  private static Object[] getArgTranslate(Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    Object[] argsTranslate = new Object[args.length];
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        // translate message args
        argsTranslate[i] = getMessage(String.valueOf(args[i]), locale);
      }
    }

    return argsTranslate;
  }

  private static String getMessage(String code, Locale locale, Object... args) {
    ResourceBundle resourceBundle = ResourceBundle
        .getBundle(control.getBaseName(), locale, control);
    String message;
    try {
      message = resourceBundle.getString(code);
      if (args.length > 0) {
        return MessageFormat.format(message, args);
      }
    } catch (Exception ex) {
      log.error(">>> Can not get message with code {}", code);
      message = code;
    }

    return message;
  }

}
