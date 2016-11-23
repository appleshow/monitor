package com.aps.monitor.comm;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub

		ObjectNode objectNode = JsonUtil.getObjectNodeInstance();

		objectNode.put("aa", "aaaaaa1111");
		objectNode.put("bb", "bbbb2222");

		objectNode.forEach(node -> System.out.println(node.asText()));
		objectNode.fieldNames().forEachRemaining(name -> System.out.println(name));
		objectNode.fieldNames().forEachRemaining(name -> System.out.println(objectNode.get(name).asText()));

		String ss = "{\"12\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":0,\"alarm\":0,\"main\":0},\"13\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":0,\"alarm\":0,\"main\":0},\"14\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":0,\"alarm\":0,\"main\":0},\"01\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"5\",\"itemVmax\":\"10\",\"select\":1,\"alarm\":0,\"main\":0},\"02\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"22\",\"itemVmax\":\"66\",\"select\":1,\"alarm\":0,\"main\":0},\"03\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"88\",\"itemVmax\":\"105\",\"select\":1,\"alarm\":0,\"main\":0},\"04\":{\"itemUnit\":\"mg/m³\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":1,\"alarm\":0,\"main\":0},\"S01\":{\"itemUnit\":\"％\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":1,\"alarm\":0,\"main\":0},\"S02\":{\"itemUnit\":\"m³/h\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":1,\"alarm\":0,\"main\":0},\"S03\":{\"itemUnit\":\"℃\",\"itemVmin\":\"\",\"itemVmax\":\"\",\"select\":1,\"alarm\":0,\"main\":0}}";
		ObjectNode objectNode2 = JsonUtil.getObjectMapper().readValue(ss, ObjectNode.class);
		System.out.println(objectNode2.toString());

		System.out.println(objectNode2.get("12").get("itemUnit").asText());
	}

}
