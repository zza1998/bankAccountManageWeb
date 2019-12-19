package com.zza.jpaa.common;

import com.zza.jpaa.annotion.CurrentUser;
import com.zza.jpaa.entity.dto.UserInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfo.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserInfo userInfo = (UserInfo) webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if (userInfo != null) {
            return userInfo;
        }
        // return new UserInfo("402881e56d446baf016d446bb8fa0002","jack","aa");
        throw new MissingServletRequestPartException("currentUser");
    }
}
