package cn.hsp.login.mapper;

import cn.hsp.login.bean.Role;
import cn.hsp.login.bean.UserDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 花生皮编程(CSDN、简书、掘金、今日头条、微信公众号、抖音、快手、B站、西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface AuthMapper {
    /**
     * 根据用户名查找用户
     */
    @Select("select id, name, password from sys_user where name = #{name}")
    UserDetail findUserByName(@Param("name") String name);

    /**
     * 创建新用户
     */
    @Insert("insert into sys_user (name, password) VALUES (#{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserDetail user);

    /**
     * 创建用户角色
     */
    @Insert("insert into sys_user_role (user_id, role_id) VALUES (#{userId}, #{roleId})")
    void insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

    /**
     * 根据用户id查找该用户角色
     */
    @Select("select * from sys_role where id in (SELECT role_id from sys_user_role where user_id = #{userId})")
    Role findRoleByUserId(@Param("userId") long userId);
}
