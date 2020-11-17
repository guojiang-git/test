package jiang.guo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 完成shiro的认证功能
 */
public class ShiroTest {
    @Test
    public void testName() {
        //1.读取shiro.ini配置文件，创建securityManager安全管理器对象
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = iniSecurityManagerFactory.createInstance();
        //2.将安全管理器对象设置到当前环境中
        SecurityUtils.setSecurityManager(securityManager);
        //3.创建主体对象(从当前线程中创建)
        Subject subject = SecurityUtils.getSubject();

        //4.创建身份凭证令牌(Token)
        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "abc123");

        //5.认证(登录)
        //5.1 先判断是否认证通过，没有认证过，才认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("authenticated:" + authenticated);
        //5.2没认证过，开始认证
        if (!subject.isAuthenticated()) {
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                System.out.println("账号不存在");
            } catch (IncorrectCredentialsException e) {
                System.out.println("密码错误");

            }
            boolean authenticated1 = subject.isAuthenticated();
            System.out.println("authenticated1:" + authenticated1);
        }
    }
}