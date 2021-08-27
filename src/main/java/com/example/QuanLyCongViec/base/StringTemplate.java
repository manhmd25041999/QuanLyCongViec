package com.example.QuanLyCongViec.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringTemplate {
    private CharSequence content;
    private final Map<String, String> params = new HashMap<>();

    private void setContent(CharSequence content) {
        this.content = content;
    }

    public static StringTemplate from(CharSequence str) {
        StringTemplate template = new StringTemplate();
        template.setContent(str);
        return template;
    }

    public StringTemplate setParam(String key, String value) {
        value = value == null ? "" : value;
        this.params.put(key, value);
        return this;
    }

    public StringTemplate setParam(String key, int value) {
        return setParam(key, String.valueOf(value));
    }

    public StringTemplate setParam(String key, long value) {
        return this.setParam(key, String.valueOf(value));
    }

    public String build() {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(this.params);
        return stringSubstitutor.replace(this.content);
    }
}
