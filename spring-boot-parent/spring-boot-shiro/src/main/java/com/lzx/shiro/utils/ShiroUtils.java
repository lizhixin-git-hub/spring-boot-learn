package com.lzx.shiro.utils;

import com.lzx.shiro.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * shiro操作工具类
 */
public class ShiroUtils {

    /** 私有构造器 **/
    private ShiroUtils(){}

    private static RedisSessionDAO redisSessionDAO = SpringUtils.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户Session
     * @Return SysUserEntity 用户信息
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     * @Return SysUserEntity 用户信息
     */
    public static SysUserEntity getUserInfo() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     * @param  username  用户名称
     * @param  isRemoveSession 是否删除Session
     */
    public static void deleteCache(String username, boolean isRemoveSession){
        //从缓存中获取Session
        Session session = null;
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        SysUserEntity sysUserEntity;
        Object attribute = null;
        for(Session sessionInfo : sessions){
            //遍历Session,找到该用户名称对应的Session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            sysUserEntity = (SysUserEntity) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (sysUserEntity == null) {
                continue;
            }
            if (Objects.equals(sysUserEntity.getUsername(), username)) {
                session=sessionInfo;
                break;
            }
        }
        if (session == null) {
            return;
        }
        //删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        //删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
    }

}