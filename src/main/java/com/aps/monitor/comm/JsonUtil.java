package com.aps.monitor.comm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
	private static ObjectMapper jsonMapper = new ObjectMapper();

	static {
		jsonMapper.setSerializationInclusion(Include.NON_NULL);
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonMapper.setDateFormat(DateUtil.getSimpleDateFormat());
	}

	/**
	 * 
	 * @Title: getObjectNodeInstance
	 * @Description: TODO
	 * @param: @return
	 * @return: ObjectNode
	 * @throws
	 * @since 1.0.0
	 */
	public static ObjectNode getObjectNodeInstance() {
		return jsonMapper.createObjectNode();
	}

	/**
	 * 
	 * @Title: writeValueAsString
	 * @Description: TODO
	 * @param: @param object
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public static String writeValueAsString(Object object) {
		String jsonString;
		try {
			jsonString = jsonMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			jsonString = null;
		}

		return jsonString;
	}

	/**
	 * 
	 * @param <T>
	 * @Title: writeResponsAsString
	 * @Description: TODO
	 * @param: @param object
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public static <T> String writeResponseAsString(ResponseData<T> responseData) {
		String jsonString;

		try {
			jsonString = jsonMapper.writeValueAsString(responseData);
		} catch (JsonProcessingException e) {
			jsonString = ErrorUtil.nomarlException(e);
		}

		return jsonString;
	}

	/**
	 * 
	 * @Title: writeResponseAsString
	 * @Description: TODO
	 * @param: @param <T>
	 * @param: @param listData
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since 1.0.0
	 */
	public static <T> String writeResponseAsString(List<T> listData) {
		String jsonString;
		ResponseData<T> responseData = new ResponseData<T>();

		if (null == listData || listData.isEmpty()) {
			responseData.setCode(-1);
			responseData.setMessage("无法找到对应的资料...!!");
		} else {
			responseData.setCode(0);
			responseData.setRowcount(listData.size());
			responseData.setData(listData);
		}

		try {
			jsonString = jsonMapper.writeValueAsString(responseData);
		} catch (JsonProcessingException e) {
			jsonString = ErrorUtil.nomarlException(e);
		}

		System.out.println("--------------------------REF----------------------------------------");
		System.out.println(jsonString);
		System.out.println("--------------------------REF----------------------------------------");

		return jsonString;
	}

	/**
	 * 
	 * @Title: readValueAsObject
	 * @Description: TODO
	 * @param: @param data
	 * @param: @param object
	 * @param: @return
	 * @return: Object
	 * @throws
	 * @since 1.0.0
	 */
	public static Object readValueAsObject(Map<String, String> data, Class<?> object) {
		String jsonData;
		Object instance;

		try {
			jsonData = jsonMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			jsonData = null;
		}

		if (null != jsonData) {
			try {
				instance = jsonMapper.readValue(jsonData, object);
			} catch (IOException e) {
				instance = null;
			}
		} else {
			instance = null;
		}

		return instance;
	}

	/**
	 * 
	 * @Title: readRequestPar
	 * @Description: TODO
	 * @param: @param inpar
	 * @param: @return
	 * @return: RequestPar
	 * @throws
	 * @since 1.0.0
	 */
	public static RequestRefPar readRequestRefPar(String inpar) {
		RequestRefPar requestRefPar = new RequestRefPar();

		requestRefPar.setParcnt(0);
		try {
			requestRefPar = jsonMapper.readValue(inpar, RequestRefPar.class);
		} catch (IOException e) {
		}

		System.out.println("--------------------------INPAR----------------------------------------");
		System.out.println(inpar);
		System.out.println("--------------------------INPAR----------------------------------------");

		return requestRefPar;
	}

	/**
	 * 
	 * @Title: readRequestMdyPar
	 * @Description: TODO
	 * @param: @param inpar
	 * @param: @return
	 * @return: RequestMdyPar
	 * @throws
	 * @since 1.0.0
	 */
	public static RequestMdyPar readRequestMdyPar(String inpar) {
		RequestMdyPar requestMdyPar = new RequestMdyPar();

		requestMdyPar.setParcnt(0);
		try {
			requestMdyPar = jsonMapper.readValue(inpar, RequestMdyPar.class);
		} catch (IOException e) {
		}

		System.out.println("--------------------------MDY----------------------------------------");
		System.out.println(inpar);
		System.out.println("--------------------------MDY----------------------------------------");

		return requestMdyPar;
	}
}
