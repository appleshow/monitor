package com.aps.monitor.comm;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
	public static String writeResponseAsString(ResponseData responseData) {
		String jsonString;

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
	public static RequestRefPar readRequestRefPar(String inPar) {
		RequestRefPar requestRefPar = new RequestRefPar();

		requestRefPar.setParCount(0);
		try {
			requestRefPar = jsonMapper.readValue(inPar, RequestRefPar.class);
		} catch (IOException e) {
		}

		System.out.println("--------------------------INPAR----------------------------------------");
		System.out.println(inPar);
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

		requestMdyPar.setParCount(0);
		try {
			requestMdyPar = jsonMapper.readValue(inpar, RequestMdyPar.class);
		} catch (IOException e) {
		}

		System.out.println("--------------------------MDY----------------------------------------");
		System.out.println(inpar);
		System.out.println("--------------------------MDY----------------------------------------");

		return requestMdyPar;
	}

	/**
	 * 
	 * @Title: valueToArrayNode
	 * @Description: TODO
	 * @param: @param object
	 * @param: @return
	 * @return: ArrayNode
	 * @throws
	 * @since 1.0.0
	 */
	public static ArrayNode valueToArrayNode(Object object) {
		ArrayNode arrayNode;

		try {
			arrayNode = jsonMapper.valueToTree(object);
		} catch (IllegalArgumentException e) {
			return null;
		}
		
		return arrayNode;
	}
}
