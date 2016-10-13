package org.tat.api.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tat.api.core.filter.DynamicRequestJsonFilterSupport;
import org.tat.api.core.filter.Embed;

public class EmbedInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private DynamicRequestJsonFilterSupport filterSupport;
//	private ThreadLocal<Boolean> requiresReset = new ThreadLocal<Boolean>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			Embed filter = method.getMethodAnnotation(Embed.class);
			if(filter!=null){
				String param = filter.param();
				if (param != null && param.length() > 0) {
					String filterParamValue = request.getParameter(param);
					if (filterParamValue != null) {
						filterSupport.setFilterFields(filterParamValue.split(","));
//						requiresReset.set(true);
					}
				}
			}
		}
//		requiresReset.remove();
		return true;
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		Boolean reset = requiresReset.get();
		
//		if (reset != null && reset) {
			filterSupport.clear();
//		}
	}
}
