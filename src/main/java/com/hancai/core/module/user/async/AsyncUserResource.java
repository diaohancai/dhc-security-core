package com.hancai.core.module.user.async;

import com.hancai.core.module.user.dto.User;
import com.hancai.core.module.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 用户资源 rest api -- 异步服务<br/>
 * Tomcat的servlet线程数量有限，对请求并发有限；
 * 采用异步服务，将提高servlet线程使用效率，提高Tomcat的请求吞吐量
 *
 * @author diaohancai
 */
@RestController
@RequestMapping("/api/async/users")
public class AsyncUserResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;

    @Autowired
    public AsyncUserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询用户详细信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public DeferredResult<User> getUser(@PathVariable String id) {
        logger.info("主线程 start");

        DeferredResult<User> deferredResult = new DeferredResult<>();

        /*
         * DeferredResult 异步服务：
         * 1、另外开启一个子线程进行业务处理
         * 2、对客户端来说，实际的业务处理速度并没有得到改善（实际上是变慢了，由于开线程需要额外的开销）
         * 3、Tomcat的servlet线程数有限，使用异步服务，将提高servlet线程的使用效率，提高Tomcat对请求的吞吐量
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("子线程 start");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                User user = userService.getUser(id);
                deferredResult.setResult(user); // deferredResult.setResult，则将result当响应返回

                logger.info("子线程 end");
            }
        }).start();

        logger.info("主线程 end");
        return deferredResult;
    }

}
