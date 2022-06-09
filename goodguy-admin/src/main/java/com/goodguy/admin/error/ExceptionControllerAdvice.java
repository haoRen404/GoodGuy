package com.goodguy.admin.error;

import com.goodguy.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * 统一异常处理类
 * https://z.itpub.net/article/detail/07D65E61437F86122657F32D0B98713B
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.goodguy.admin.controller")
public class ExceptionControllerAdvice {

    /**
     * 处理JSR303校验异常
     * @param e
     * @return
    */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){

        Map<String,String> map = new HashMap<>();
        // 获取数据校验的错误结果
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            map.put(field,message);
        });

        log.error("统一异常处理：数据校验异常。【{}】【{}】,【{}】", map, e.getMessage(),e.getClass());

        return R.fail("数据校验不通过").put("data", map);
    }

    /**
     * 线程编排执行异常
     * @param e
     * @return
    */
    @ExceptionHandler(value = ExecutionException.class)
    public R handleValidException(ExecutionException e){
        log.error("统一异常处理：线程编排执行异常。【{}】【{}】", e.getMessage(),e.getClass());
        return R.fail("线程编排执行异常");
    }

    /**
     * 线程编排执行中断异常
     * @param e
     * @return
    */
    @ExceptionHandler(value = InterruptedException.class)
    public R handleValidException(InterruptedException e){
        log.error("统一异常处理：线程编排执行中断异常。【{}】【{}】", e.getMessage(),e.getClass());
        return R.fail("线程执行中断异常");
    }

    /**
     * SQL语法异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public R handleValidException(SQLSyntaxErrorException e){
        log.error("统一异常处理：SQL语法异常。【{}】【{}】", e.getMessage(),e.getClass());
        return R.fail("SQL语法异常");
    }

    /**
     * 方法参数类型不匹配异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public R handleValidException(MethodArgumentTypeMismatchException e){
        String errmsg = "方法："+ Objects.requireNonNull(e.getParameter().getMethod())
                +",期望参数类型：" + e.getParameter().getParameterType()
                +",参数："+e.getName()
                +",信息："+e.getMessage();
        log.error("统一异常处理：方法参数类型不匹配异常,参数转换失败。【{}】【{}】", errmsg, e.getClass());
        return R.fail("方法参数类型不匹配异常");
    }

    //Illegal Access Exception
    @ExceptionHandler(value = IllegalAccessException.class)
    public void handleValidException(IllegalAccessException e){
        log.error("统一异常处理：非法访问异常,反射访问私有变量报错。【{}】【{}】", e.getMessage(),e.getClass());
    }

    /**
     * 捕获其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public R exception(Exception e) {
        log.error("统一异常处理：出现未知异常：【{}】【{}】", e.getMessage(),e.getClass());
        return R.fail("出现未知异常");
    }
}
