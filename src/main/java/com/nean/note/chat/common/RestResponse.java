package com.nean.note.chat.common;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class RestResponse<T> {

  /**
   * 响应编码,0为正常,-1 错误
   */
  private int code;

  /**
   * 响应提示信息
   */
  private String msg;

  /**
   * 响应内容
   */
  private T result;


  public RestResponse() {
   this(200, "success");
  }

  public RestResponse(int code, String msg) {
   this.code = code;
   this.msg = msg;
  }


  public static <T> RestResponse<T> validFail(String msg) {
   RestResponse<T> response = new RestResponse<T>();
   response.setCode(-1);
   response.setMsg(msg);
   return response;
  }

  public static <T> RestResponse<T> validFail(T result,String msg) {
   RestResponse<T> response = new RestResponse<T>();
   response.setCode(-1);
   response.setResult(result);
   response.setMsg(msg);
   return response;
  }


  /**
   * 添加正常响应数据（包含响应内容）
   *
   * @return RestResponse Rest服务封装相应数据
   */
  public static <T> RestResponse<T> success(T result) {
   RestResponse<T> response = new RestResponse<T>();
   response.setResult(result);
   return response;
  }

  public static <T> RestResponse<T> success(T result,String msg) {
   RestResponse<T> response = new RestResponse<T>();
   response.setResult(result);
   response.setMsg(msg);
   return response;
  }

  /**
   * 添加正常响应数据（不包含响应内容）
   *
   * @return RestResponse Rest服务封装相应数据
   */
  public static <T> RestResponse<T> success() {
   return new RestResponse<T>();
  }


  public Boolean isSuccessful() {
   return this.code == 200;
  }

 }