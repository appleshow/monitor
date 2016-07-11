package com.aps.monitor.comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class TT2 {

	public static String toWrite;
	public static ObjectNode jsonPar;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fi = new FileInputStream("d:\\hb.txt");
		//  DataInputStream dr = new DataInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fi));
		FileOutputStream fo = new FileOutputStream("d:\\hb-data.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));

		String data;
		List<String[]> aa;
		Optional<String> lineData;
		lineData = Optional.ofNullable(br.readLine());
		int count = 0;

		System.out.println("******Begin******");
		while (lineData.isPresent()) {
			data = lineData.get();
			toWrite = "";
			if (data.length() > 2 && data.startsWith("##") && data.indexOf("&&") > 0 && data.indexOf("DataTime=") > 0) {
				aa = Arrays.asList(data.split("&&")).stream().map(item -> item.split(";")).collect(Collectors.toList());
				if (aa.size() >= 2) {
					//aa.stream().forEach(item -> Arrays.asList(item).stream().map(par -> par.split(",")).collect(Collectors.toList()).forEach(System.out::println));
					Arrays.asList(aa.get(0)).stream().filter(item -> item.startsWith("MN=")).findFirst().ifPresent(item -> {
						format2KeyValue(item).ifPresent(keyValue -> toWrite += " " + keyValue.getValue());
					});
					Arrays.asList(aa.get(0)).stream().filter(item -> item.startsWith("CN=")).findFirst().ifPresent(item -> {
						format2KeyValue(item).ifPresent(keyValue -> toWrite += " " + keyValue.getValue());
					});
					Arrays.asList(aa.get(1)).stream().filter(item -> item.startsWith("DataTime=")).findFirst().ifPresent(item -> {
						format2KeyValue(item).ifPresent(keyValue -> toWrite += " " + keyValue.getValue());
					});
					jsonPar = JsonUtil.getObjectNodeInstance();
					for (String item : aa.get(1)) {
						if (item.indexOf(",") > 0) {
							Arrays.asList(item.split(",")).stream().forEach(par -> {
								format2KeyValue(par).ifPresent(keyValue -> {
									jsonPar.put(keyValue.getKey(), keyValue.getValue());
								});
							});
						} else if (!item.startsWith("DataTime=")) {
							format2KeyValue(item).ifPresent(keyValue -> {
								jsonPar.put(keyValue.getKey(), keyValue.getValue());
							});

						}
					}
					if (jsonPar.size() > 0) {
						count++;
						toWrite += " " + jsonPar.toString();
						bw.write(toWrite.trim() + "\n");
					}
				}
			}

			lineData = Optional.ofNullable(br.readLine());
		}

		bw.close();
		fo.close();

		System.out.println("******Finished【" + count + "】******");
	}

	public static Optional<KeyValue> format2KeyValue(String data) {
		Optional<KeyValue> optional;

		if (data.indexOf("=") > 0) {
			String[] array = data.split("=");
			KeyValue keyValue;
			if (array.length >= 2) {
				keyValue = new KeyValue(array[0], array[1]);
			} else {
				keyValue = new KeyValue(array[0], "");
			}
			optional = Optional.of(keyValue);
		} else {
			optional = Optional.ofNullable(null);
		}

		return optional;
	}

}

class KeyValue {
	private String key;
	private String value;

	public KeyValue(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @Title: getKey
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getKey() {
		return key;
	}

	/**
	 * @Title: setKey
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @Title: getValue
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */

	public String getValue() {
		return value;
	}

	/**
	 * @Title: setValue
	 * @Description:
	 * @return: String
	 * @since 1.0.0
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
