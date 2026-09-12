package app.lamenna.commons.security;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserContext userContext;

    public CurrentUserArgumentResolver(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.hasParameterAnnotation(CurrentUser.class)
                && param.getParameterType().equals(AuthenticatedUser.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        var user = userContext.currentUser().orElse(null);
        if (user == null) {
            CurrentUser annotation = parameter.getParameterAnnotation(CurrentUser.class);
            boolean required = annotation == null || annotation.required();
            if (required) {
                throw new UnauthenticatedException("Authentication required");
            }
        }

        return user;
    }
}
