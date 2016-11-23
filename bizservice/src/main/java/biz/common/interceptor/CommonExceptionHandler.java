package biz.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import biz.common.exception.BaseErrorCodes;
import biz.common.exception.BaseException;
import biz.common.resp.BaseResp;
import biz.common.utils.GsonUtil;

public class CommonExceptionHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			Integer status = BaseErrorCodes.SERVER_FAIL;
			String errorMsg = "额，好像哪里出错了!!";
			if(ex instanceof BaseException) {
				status = ((BaseException)ex).getStatus();
				errorMsg = ((BaseException)ex).getErrorMsg();
			}
			BaseResp result = new BaseResp(status, errorMsg);
			response.getWriter().write(GsonUtil.toJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
