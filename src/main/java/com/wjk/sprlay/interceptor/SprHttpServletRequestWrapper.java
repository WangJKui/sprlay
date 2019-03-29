package com.wjk.sprlay.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName  SprHttpServletRequestWrapper
 * @Description 对@RequestBody解析数据 拦截器打印post数据
 * @author WangJKui
 * @date   2019年3月29日 下午3:20:53
 */
public class SprHttpServletRequestWrapper extends HttpServletRequestWrapper {


	private final String body;

	public SprHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
				char[] charBuffer = new char[1024];
				int len = 0;
				while ((len = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, len);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		body = stringBuilder.toString();
	}


	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		ServletInputStream servletInputStream = new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}
			@Override
			public boolean isReady() {
				return false;
			}
			@Override
			public void setReadListener(ReadListener readListener) {
			}
			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
		return servletInputStream;

	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public String getBody() {
		return this.body;
	}
}
