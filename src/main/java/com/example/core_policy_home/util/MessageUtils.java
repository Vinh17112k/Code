package com.example.core_policy_home.util;

import com.sun.istack.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

@Slf4j
public abstract class MessageUtils {
    private static final String BASE_NAME = "i18n/messages";

    private MessageUtils(){}
    public static String getMessage(String code, Locale locale) {
        return getMessage(code, locale, (Object) null);
    }

    public static String getMessage(String code, Locale locale, Object... args) {
        UTF8ResourceBundle resourceBundle = new UTF8ResourceBundle(BASE_NAME, locale);
        String message;
        try {
            message = resourceBundle.getString(code);
            message = MessageFormat.format(message, args);
        } catch (Exception ex) {
            log.info(">>> Can not get message with code {}", code);
            log.info(ex.getMessage(), ex);
            message = code;
        }

        return message;
    }

    public static String getMessage(String code) {
        return getMessage(code, LocaleContextHolder.getLocale(), (Object) null);
    }

    public static String getMessage(String code, Object... args) {
        return getMessage(code, LocaleContextHolder.getLocale(), args);
    }
}

class UTF8ResourceBundle extends ResourceBundle {

    public UTF8ResourceBundle(String baseName, Locale locale) {
        this.setParent(ResourceBundle.getBundle(baseName, locale, new UTF8Control("properties")));
    }

    protected Object handleGetObject(@NotNull String key) {
        return this.parent.getObject(key);
    }

    public Enumeration<String> getKeys() {
        return this.parent.getKeys();
    }

    private static class UTF8Control extends Control {

        private final String resource;

        public UTF8Control(String resourceName) {
            this.resource = resourceName;
        }

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {
            String bundleName = this.toBundleName(baseName, locale);
            String resourceName = this.toResourceName(bundleName, this.resource);
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }

            if (stream != null) {
                try (InputStreamReader inputStream = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                    bundle = new PropertyResourceBundle(inputStream);
                } finally {
                    stream.close();
                }
            }

            return bundle;
        }
    }
}