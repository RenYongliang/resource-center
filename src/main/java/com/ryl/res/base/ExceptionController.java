package com.ryl.res.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

//    @ResponseBody
//    @ExceptionHandler(value = {MissingServletRequestParameterException.class, TypeMismatchException.class, MissingParamException.class,
//            BaseException.class, NumberFormatException.class})
//    public ResultModel handleClientException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        log.debug(String.format("handleClientException(HttpServletRequest, HttpServletResponse, Exception) - %s", e.getMessage()), e);
//
//        if (e instanceof MissingParamException) {
//            resp.setStatus(HttpStatus.FORBIDDEN.value());
//            MissingParamException missingParamE = (MissingParamException) e;
//            return ResultModel.error(missingParamE.getCode(), missingParamE.getMessage());
//        } else if (e instanceof TypeMismatchException) {
//            resp.setStatus(HttpStatus.FORBIDDEN.value());
//            return MessageResponse.error(ECommonRespCode.TYPE_MISMATCH_EXCEPTION.getCode(), ECommonRespCode.TYPE_MISMATCH_EXCEPTION.getDesc());
//        } else if (e instanceof NumberFormatException) {
//            resp.setStatus(HttpStatus.FORBIDDEN.value());
//            return MessageResponse.error(ECommonRespCode.TYPE_MISMATCH_EXCEPTION.getCode(), ECommonRespCode.TYPE_MISMATCH_EXCEPTION.getDesc());
//        } else if (e instanceof BaseException) {
//            resp.setStatus(HttpStatus.OK.value());
//            BaseException baseException = (BaseException) e;
//            return MessageResponse.error(baseException.getCode(), baseException.getMessage());
//        } else if (e instanceof MissingServletRequestParameterException) {
//            resp.setStatus(HttpStatus.FORBIDDEN.value());
//            return MessageResponse.error(ECommonRespCode.MISSING_PARAMETER.getCode(), ECommonRespCode.MISSING_PARAMETER.getDesc());
//        } else {
//            resp.setStatus(HttpStatus.FORBIDDEN.value());
//            return ResultModel.fail(ECommonRespCode.MISSING_PARAMETER.getCode(), ECommonRespCode.MISSING_PARAMETER.getDesc());
//        }
//    }

//    @ResponseBody
//    @ExceptionHandler(value = InternalServerException.class)
//    public MessageResponse handleServerException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        log.error(String.format("handleServerException(HttpServletRequest, HttpServletResponse, Exception) - %s", e.getMessage()), e);
//        resp.setStatus(HttpStatus.OK.value());
//
//        if (e instanceof InternalServerException) {
//            InternalServerException missingParamE = (InternalServerException) e;
//            return MessageResponse.error(missingParamE.getCode(), missingParamE.getMessage());
//        } else {
//            return MessageResponse.error(ECommonRespCode.INTERNAL_ERROR.getCode(), ECommonRespCode.INTERNAL_ERROR.getDesc());
//        }
//    }

//    @ResponseBody
//    @ExceptionHandler(value = ResourceNotFoundException.class)
//    public MessageResponse handleResourceNotFoundException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
//        log.error(String.format("handleResourceNotFoundException(HttpServletRequest, HttpServletResponse, Exception) - %s", e.getMessage()), e);
//        resp.setStatus(HttpStatus.NOT_FOUND.value());
//
//        if (e instanceof ResourceNotFoundException) {
//            ResourceNotFoundException missingParamE = (ResourceNotFoundException) e;
//            return MessageResponse.error(missingParamE.getCode(), missingParamE.getMessage());
//        } else {
//            return MessageResponse.error(ECommonRespCode.RESOURCE_NOT_FOUND.getCode(), ECommonRespCode.RESOURCE_NOT_FOUND.getDesc());
//        }
//
//    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultModel handleException(HttpServletResponse resp, Exception e) {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        log.error(String.format("handleException(HttpServletRequest, HttpServletResponse, Exception) - %s", e.getMessage()), e);
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            return ResultModel.fail(se.getErrorCode(),se.getErrorMessage());
        } else {
            //未捕捉的内部服务异常
            String name = Thread.currentThread().getName();
            log.debug(String.format("当前线程: %s,异常具体类容为：", name, e.getMessage()));
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                String threadName = stackTraceElement.getClass().getName();
                log.debug(String.format("具体异常跟踪:%s", threadName));
            }
            log.warn(String.format("uncaught internal server exception, msg: %s", e.getMessage()), e);
            return ResultModel.fail(ResultStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    /**
//     * 对验证约束异常进行拦截，返回约定的响应体
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public MessageResponse bindExceptionHandler(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        List<ObjectError> errors = bindingResult.getAllErrors();
//        StringBuffer buffer = new StringBuffer();
//        for (ObjectError error : errors) {
//            buffer.append(error.getDefaultMessage()).append(" ");
//        }
//        return MessageResponse.error(ECommonRespCode.MISSING_PARAMETER.getCode(), buffer.toString());
//
//    }
//
//
//    /**
//     * 参数类型转换错误
//     */
//    @ExceptionHandler(HttpMessageConversionException.class)
//    @ResponseBody
//    public MessageResponse parameterTypeException(HttpMessageConversionException exception) {
//        return MessageResponse.error(ECommonRespCode.MISSING_PARAMETER.getCode(), exception.getCause().getLocalizedMessage());
//
//    }

}

