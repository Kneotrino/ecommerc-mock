package org.kneotrino.ecommerce.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.kneotrino.ecommerce.constant.ResponseConstant;
import org.kneotrino.ecommerce.util.JsonUtil;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kneotrino
 * @since : 29/07/2021
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class GlobalResponse<T> {

    private String result;
    private Integer code = ResponseConstant.SUCCESS_GENERIC;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;
    private String message = ResponseConstant.MESSAGE_SUCCESS;

    private T data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public GlobalResponse(T content, int code) {
        setDateTime(new Date());
        setData(content);
        if (content == null) {
            result = "Data is null";
        }
        setCode(code);
    }

    public GlobalResponse(T content) {
        setDateTime(new Date());
        setData(content);
        if (content == null) {
            result = "Data is null";
        }
        setCode(ResponseConstant.SUCCESS_GENERIC);
    }

    public GlobalResponse(T content, String message) {
        setDateTime(new Date());
        setData(content);
        setMessage(message);
    }

    public GlobalResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalResponse(T content, String message, int code) {
        setDateTime(new Date());
        setData(content);
        setMessage(message);
        setCode(code);
    }

    public GlobalResponse() {
        setDateTime(new Date());
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @JsonIgnore
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String toString() {
        return JsonUtil.getString(this);
    }

    public ResponseEntity<GlobalResponse<T>> asResponseEntity() {
        return ResponseEntity.status(code).body(this);
    }
}
